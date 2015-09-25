package jdodb;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable
public class Store {
	public Store(String name, String street, int zip){
		setStoreName(name);
		setStoreStreet(street);
		setStoreZip(zip);
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long storeID;
	@Persistent
	private String storeName;
	@Persistent
	private String storeStreet;
	@Persistent
	private int storeZIP;
	
	public long getStoreID(){return storeID;}
	public String getStoreName(){return storeName;}
	public String getStoreStreet(){return storeStreet;}
	public int getStoreZIP(){return storeZIP;}
	
	public void setStoreName(String name){storeName=name;}
	public void setStoreStreet(String street){storeStreet=street;}
	public void setStoreZip(int zip){storeZIP=zip;}
}
