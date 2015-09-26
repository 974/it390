package jdodb;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class Store {
	public Store(String name, String street, int zip){
		setStoreName(name);
		setStoreStreet(street);
		setStoreZip(zip);
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key storeID;
	@Persistent
	private String storeName;
	@Persistent
	private String storeStreet;
	@Persistent
	private int storeZIP;
	
	public Key getStoreID(){return storeID;}
	public String getStoreName(){return storeName;}
	public String getStoreStreet(){return storeStreet;}
	public int getStoreZIP(){return storeZIP;}
	
	public void setStoreName(String name){storeName=name;}
	public void setStoreStreet(String street){storeStreet=street;}
	public void setStoreZip(int zip){storeZIP=zip;}
	
	public static Store searchForStore(String storen){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Store.class);
		query.setFilter("storeName == storen");
		query.declareParameters("String storen");
		List<Store> s1= (List<Store>)query.execute(storen);
		Store s = s1.get(0); 
		query.closeAll();
		return s;
	}
}
