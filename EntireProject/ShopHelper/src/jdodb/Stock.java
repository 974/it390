package jdodb;

import java.util.Arrays;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Stock {
	public final int MAX_PRICES = 1000000;
	public Stock(String item, String store, double prices [], int numOfPrices){
		itemID = item;
		storeID = store;
		itemPrices = new double[MAX_PRICES];
		this.itemPrices = prices;
		counter = numOfPrices;
		double total = 0;
		for(int i = 0; i < counter;i++){
			total += itemPrices[i];
		}
		itemAveragePrice = total/counter;
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
	private double itemAveragePrice;
	@Persistent 
	private double [] itemPrices;
	@Persistent
	private int counter = 0;
	public String getItemID(){return itemID;}
	public String getStoreID(){return storeID;}
	public String getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public double getItemAveragePrice(){return itemAveragePrice;}
	public double [] getItemPrices(){return itemPrices;}
	public double getItemPrice(int idx){
		return itemPrices[idx];
	}
	public void addItemPrice(double price){
		itemPrices[counter] = price;
		counter++;
		double total = 0;
		for (int i = 0; i < counter; i++){
			total += itemPrices[counter];
		}
		itemAveragePrice = total/counter;
	}
	public void setStoreID(String id){storeID = id;}
	public void setItemID(String id){itemID = id;}
}
