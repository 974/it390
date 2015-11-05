package sh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import jdodb.Item;
import jdodb.PMF;
import jdodb.ReportEntry;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;
import jdodb.User;

public class ReportEntryServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String processedState = "F", sn = "", ip = "", in = "";
		double num = 0;
		try{
			sn = req.getParameter("storeN").trim();
			in = req.getParameter("itemN").trim();
			ip = req.getParameter("itemP").trim();
			
			if ((ip == null) || ip.equals("") || in.equals("")){
				processedState = "F";
				System.out.println("got here");
				//req.setAttribute("processedState", processedState);
			    //req.getRequestDispatcher("ReportEntryItemNameAndPrice.jsp").forward(req, resp);
			    throw new NumberFormatException ("Invalid price!");
			}System.out.println("got ehre");
			for(int i = 0; i < ip.length();i++){
				if(ip.charAt(i) == '-'){
					processedState = "F";
					//req.setAttribute("processedState", processedState);
				    //req.getRequestDispatcher("ReportEntryItemNameAndPrice.jsp").forward(req, resp);
				    throw new NumberFormatException ("Invalid price!");
				}
				if(Character.isAlphabetic(ip.charAt(i))){
					processedState = "F";
					//req.setAttribute("processedState", processedState);
				    //req.getRequestDispatcher("ReportEntryItemNameAndPrice.jsp").forward(req, resp);
				    throw new NumberFormatException ("Invalid price!");
				}
			}
			num = Double.parseDouble(ip);
			processedState = "T";
		}catch(NumberFormatException e){
			processedState = "F";
			ip="";
			in="";
		}catch(NullPointerException e){
			processedState = "F";
			ip="";
			in="";
		}
		if(!processedState.equals("F")){
			processedState = createEntry(sn,in,num);
		}else{
			processedState="F";
		}
		
		try{
			req.setAttribute("processedState", processedState);
			req.setAttribute("storeNa", sn);
			req.setAttribute("itemPr", ip);
			req.setAttribute("itemNa", in);
		    req.getRequestDispatcher("ReportEntryItemNameAndPrice.jsp").forward(req, resp);
		}catch(NullPointerException e){
			e.printStackTrace();
		}

		
	}
	public String createEntry(String sn, String in, double num){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		//Find test user ID
		Query findUser = pm.newQuery("select from " + User.class.getName() + " where userName == findMe");
		findUser.declareParameters("String findMe");
		List <User> userID = (List<User>)findUser.execute("Jack");
		
		//Find store ID
		String storeID = null;
		javax.jdo.Extent<Store> extent1 = pm.getExtent(Store.class, false);
		for (Store me : extent1) {
			if (me.getStoreName().equalsIgnoreCase(sn)){
				storeID = me.getStoreID();
			}
		}
		if(storeID == null){
			extent1.closeAll();
			pm.close();
			return "F";
		}
		extent1.closeAll();
		
		//Find item ID
		String itemID = null;
		javax.jdo.Extent<Item> extent2 = pm.getExtent(Item.class, false);
		for (Item me : extent2) {
			if (me.getItemName().equalsIgnoreCase(in)){
				itemID = me.getItemID();
			}
		}
		if(itemID == null){
			extent2.closeAll();
			pm.close();
			return "F";
		}
		extent2.closeAll();

		//Find combined key
		javax.jdo.Extent<Stock> extent3 = pm.getExtent(Stock.class, false);
		ReportEntry re = null;
		for (Stock me : extent3) {
			if (me.getStoreID().equalsIgnoreCase(storeID) && me.getItemID().equalsIgnoreCase(itemID)){
				re = new ReportEntry(me.getCombineStoreAndItemID(),userID.get(0).getUserID(),num);
				pm.makePersistent(re);
				me.addItemPrice(num);
				extent3.closeAll();
				pm.close();
				return "T";
			}

		}
		extent3.closeAll();
		pm.close();
		return "F";
	}
}
