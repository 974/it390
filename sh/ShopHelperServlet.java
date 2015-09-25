package sh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import jdodb.PMF;
import jdodb.Store;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;

import javax.jdo.Query.*;
@SuppressWarnings("serial")
public class ShopHelperServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Query query = pm.newQuery(Store.class,"storeName != storen");
		query.declareParameters("String storen");
		List  <Store>results = (List<Store>)query.execute("Wegmans");
		try{
			if(results.size() == 0 || results == null){
				System.out.println("Data Loaded");
				LoadStartingData.loadData();
			}
		
		}catch(NullPointerException e){
			System.out.println("Data Already Loaded");
		}

	}
}
