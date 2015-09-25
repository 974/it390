package jdodb;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Stock {
	public Stock(double price, long item, long store){
		setItemPrice(price);
		itemID = item;
		storeID = store;
	}
	@ForeignKey
	@Persistent
	private long itemID;
	@ForeignKey
	@Persistent
	private long storeID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long combineStoreAndItemID = itemID + storeID;
	@Persistent
	private double itemPrice;
	
	public long getItemID(){return itemID;}
	public long getStoreID(){return storeID;}
	public long getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public double getItemPrice(){return itemPrice;}
	
	public void setItemPrice(double price){itemPrice = price;}
	public void setStoreID(long id){storeID = id;}
	public void setItemID(long id){itemID = id;}
}
