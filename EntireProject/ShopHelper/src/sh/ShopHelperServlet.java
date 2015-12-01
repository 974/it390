package sh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import jdodb.Item;
import jdodb.ListItem;
import jdodb.PMF;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.util.List;

import javax.jdo.Query;

import org.datanucleus.store.Extent;

@SuppressWarnings("serial")
public class ShopHelperServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Store s = Store.searchForStore("Wegmans");
		
	    UserService userService = UserServiceFactory.getUserService();
	    User currentUser = userService.getCurrentUser();
		try{
			if(s == null){
				LoadStartingData.loadData();
				System.out.println("Loaded!");
			}	
		}catch(NullPointerException e){
			System.out.println("Error!");
		}
		ShoppingList newSL = null;
		try{
			String convertMe = req.getParameter("budget").trim();
			if ((convertMe == null) || convertMe.equals("")){convertMe = "0";}
			double num = Double.parseDouble(convertMe);
			if(currentUser != null){
				newSL = new GenerateShopList().generateList(Double.parseDouble(req.getParameter("budget")),req.getParameter("options"),currentUser,req.getParameter("shopListName"));
			}else{
				newSL = new GenerateShopList().generateList(Double.parseDouble(req.getParameter("budget")),req.getParameter("options"),currentUser,null);
			}
			
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		try{
			req.setAttribute("outputList", newSL);
		    req.getRequestDispatcher("TestingThis.jsp").forward(req, resp);
		}catch(NullPointerException e){
			e.printStackTrace();
		}

		
	}
	
}
