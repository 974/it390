package sh;

import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
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
			throws IOException {
		Store s = Store.searchForStore("Wegmans");
		try{
			if(s == null){
				LoadStartingData.loadData();
			}else{
			}
		
		}catch(NullPointerException e){
			System.out.println("Error!");
		}
	    String json = "{";
	    json += "\"Budget\":\"" + req.getParameter("budget") + "\"";
	    json += "}";
	    JSONObject fs = null;
	    String output = null;
	    try {
			fs = new JSONObject(json);
			 output = fs.getString("Budget");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    resp.setContentType("application/json");
	    resp.getWriter().println(output);
	    resp.sendRedirect("/index.html");
	}
}
