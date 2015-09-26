package jdodb;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Stock {
	public Stock(double price, Key item, Key store){
		setItemPrice(price);
		itemID = item;
		storeID = store;
	}
	@ForeignKey
	@Persistent
	private Key itemID;
	@ForeignKey
	@Persistent
	private Key storeID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key combineStoreAndItemID;
	@Persistent
	private double itemPrice;
	
	public Key getItemID(){return itemID;}
	public Key getStoreID(){return storeID;}
	public Key getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public double getItemPrice(){return itemPrice;}
	
	public void setItemPrice(double price){itemPrice = price;}
	public void setStoreID(Key id){storeID = id;}
	public void setItemID(Key id){itemID = id;}
}
