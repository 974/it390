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
	public final int MAX_PRICES = 100;
	public Stock(String item, String store, double prices [], int numOfPrices){
		itemID = item;
		storeID = store;
		itemPrices = new double[MAX_PRICES];
		counter = numOfPrices;
		double total = 0;
		for(int i = 0; i < counter;i++){
			itemPrices[i] = prices[i];
		}
		sortPrices();
		
		double midValue = itemPrices[counter/2];
		double lrVal = 0;
		double hrVal = 0;
		if(midValue >= 0 && midValue < 5){
			lrVal = midValue-2.5;
			hrVal = midValue+2.5;
		}else if(midValue >= 5 && midValue <15){
			lrVal = midValue-3.5;
			hrVal = midValue+3.5;
		}else if(midValue >= 15 && midValue < 30){
			lrVal = midValue-4;
			hrVal = midValue+4;
		}else if(midValue >= 30 && midValue < 65){
			lrVal = midValue-6.5;
			hrVal = midValue+6.5;
		}else if(midValue >=65 && midValue < 80){
			lrVal = midValue-7.5;
			hrVal = midValue+7.5;
		}else if(midValue >=80 && midValue < 110){
			lrVal = midValue-8.5;
			hrVal = midValue+8.5;
		}else if( midValue >= 110){
			lrVal = midValue-14.5;
			hrVal = midValue+14.5;
		}
		int numAddedToTotal = 0;
		for (int i = 0; i < counter; i++){
			if(itemPrices[i] >= lrVal && itemPrices[i] <= hrVal){
				total += itemPrices[i];
				numAddedToTotal++;
			}else{
				total += 0;
			}
		}
		itemAveragePrice = total/numAddedToTotal; 
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
	private double [] itemPrices = new double[MAX_PRICES];
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
	private void sortPrices(){
		for(int i = 0; i < counter;i++){
			int j = i;
			while(j > 0 && itemPrices[j] < itemPrices[j-1]){
				double temp = itemPrices[j];
				itemPrices[j] = itemPrices[j-1];
				itemPrices[j-1] = temp;
				j--;
			}
		}
	}
	public void addItemPrice(double price){
		itemPrices[counter] = price;
		counter++;
		sortPrices();
		double total = 0;
		double midValue = itemPrices[counter/2];
		double lrVal = 0;
		double hrVal = 0;
		if(midValue >= 0 && midValue < 5){
			lrVal = midValue-2.5;
			hrVal = midValue+2.5;
		}else if(midValue >= 5 && midValue <15){
			lrVal = midValue-3.5;
			hrVal = midValue+3.5;
		}else if(midValue >= 15 && midValue < 30){
			lrVal = midValue-4;
			hrVal = midValue+4;
		}else if(midValue >= 30 && midValue < 65){
			lrVal = midValue-6.5;
			hrVal = midValue+6.5;
		}else if(midValue >=65 && midValue < 80){
			lrVal = midValue-7.5;
			hrVal = midValue+7.5;
		}else if(midValue >=80 && midValue < 110){
			lrVal = midValue-8.5;
			hrVal = midValue+8.5;
		}else if( midValue >= 110){
			lrVal = midValue-15.5;
			hrVal = midValue+15.5;
		}
		int numAddedToTotal = 0;
		for (int i = 0; i < counter; i++){
			if(itemPrices[i] >= lrVal && itemPrices[i] <= hrVal){
				total += itemPrices[i];
				numAddedToTotal++;
			}else{
				total += 0;
			}
		}
		itemAveragePrice = total/numAddedToTotal; 
	}
	public void setStoreID(String id){storeID = id;}
	public void setItemID(String id){itemID = id;}
}
