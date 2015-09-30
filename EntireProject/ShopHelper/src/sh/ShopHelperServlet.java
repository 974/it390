package sh;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import jdodb.PMF;
import jdodb.Store;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.util.List;

import javax.jdo.Query;

@SuppressWarnings("serial")
public class ShopHelperServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Store s = Store.searchForStore("Wegmans");
		try{
			if(s == null){
				LoadStartingData.loadData();
				System.out.println("Loaded!");
			}else{
			}
		
		}catch(NullPointerException e){
			System.out.println("Error!");
		}
	    String jsonB = "{\"Budget\":\"" + req.getParameter("budget") + "\"}";
	    JSONObject fs = null;
	    String output = null;
	    try {
	    	fs = new JSONObject(jsonB);
			output = "Budget: " + fs.getString("Budget");
		    jsonB = "{\"Type\":\"" + req.getParameter("options") + "\"}";
		    fs = new JSONObject(jsonB);
		    output += "\n" + "Type: " +  fs.getString("Type");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    resp.setContentType("application/json");
	    resp.getWriter().println(output);
	}
}
