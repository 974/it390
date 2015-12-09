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

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jdodb.Item;
import jdodb.PMF;
import jdodb.ReportEntry;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;
import jdodb.UserAccount;

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
			    throw new NumberFormatException ("Invalid price!");
			}
			for(int i = 0; i < ip.length();i++){
				if(ip.charAt(i) == '-'){
					processedState = "F";
				    throw new NumberFormatException ("Invalid price!");
				}
				if(Character.isAlphabetic(ip.charAt(i))){
					processedState = "F";
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
		UserService userService = UserServiceFactory.getUserService(); 
		User user = userService.getCurrentUser();
		List <UserAccount> userID=null;
		if(user!=null){
			Query findUser = pm.newQuery("select from " + UserAccount.class.getName() + " where userName == findMe");
			findUser.declareParameters("String findMe");
			userID = (List<UserAccount>)findUser.execute(user.getEmail());
		}
		
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
				if(user==null){
					re = new ReportEntry(me.getCombineStoreAndItemID(),null,num);
				}else{
					re = new ReportEntry(me.getCombineStoreAndItemID(),userID.get(0).getUserID(),num);
				}
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
