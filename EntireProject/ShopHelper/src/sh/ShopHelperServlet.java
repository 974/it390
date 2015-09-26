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

import java.util.List;
import javax.jdo.Query;

@SuppressWarnings("serial")
public class ShopHelperServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Store s = Store.searchForStore("Wegmans");
		try{
			if(s == null){
				LoadStartingData.loadData();
				System.out.println("Data Loaded");
			}else{
				System.out.println("Data Already Loaded");
			}
		
		}catch(NullPointerException e){
			System.out.println("Error!");
		}

	}
}
