package jdodb;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Stock {
	public Stock(double price, String item, String store){
		setItemPrice(price);
		itemID = item;
		storeID = store;
	}
	@ForeignKey
	@Persistent
	private String itemID;
	@ForeignKey
	@Persistent
	private String storeID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String combineStoreAndItemID;
	@Persistent
	private double itemPrice;
	
	public String getItemID(){return itemID;}
	public String getStoreID(){return storeID;}
	public String getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public double getItemPrice(){return itemPrice;}
	
	public void setItemPrice(double price){itemPrice = price;}
	public void setStoreID(String id){storeID = id;}
	public void setItemID(String id){itemID = id;}
}
