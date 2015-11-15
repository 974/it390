package jdodb;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@PersistenceCapable
public class ShoppingList {
	public ShoppingList(double bud, double total, String user, ArrayList <ListItem>list){
		setBudget(bud);
		setTotalCost(total);
		userID = user;
		shoppingList = list;
		type = null;
	}
	public ShoppingList(){}
	@ForeignKey
	@Persistent
	@Element(dependent = "true")
	private String userID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String shopListID;
	@Persistent
	private double budget;
	@Persistent
	private double totalCost;
	@Persistent
	private String type;
	@Persistent
	private ArrayList <ListItem> shoppingList = new ArrayList<ListItem>();
	
	public String getUserID(){return userID;}
	public String getShopListID(){return shopListID;}
	public ArrayList<ListItem> getShoppingList(){return shoppingList;}
	public ListItem getItemFromShoppingList(int idx){return shoppingList.get(idx);}
	public double getBudget(){return budget;}
	public double getTotalCost(){return totalCost;}
	public String getType(){return type;}
	
	public void setBudget(double num){budget=num;}
	public void setTotalCost(double num){totalCost = num;}
	public void addToTotalCost(double num){totalCost += num;}
	public void addItemToShoppingList(ListItem item){shoppingList.add(item);}
	public void setType(String t){type = t;}
	
	public String [] createStringOutputs(){
		final int MAX_LINES = 500;
		int counter = 0;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String displayStrings [] = new String[MAX_LINES];
		displayStrings[counter++] =  "Budget:$" + budget + "\n"; //pos 0
		displayStrings[counter++] =  "Type: " + type + "\n";// pos 1
		//String displayMe = "Budget:$" + budget + "     Type: " + type + "\n";

		for (ListItem i : shoppingList){
			Query findItemIDInStock = pm.newQuery("select from " + Stock.class.getName() + " where combineStoreAndItemID == findMe");
			findItemIDInStock.declareParameters("String findMe");
			@SuppressWarnings("unchecked")
			List <Stock> results = (List<Stock>)findItemIDInStock.execute(i.getCombineStoreAndItemID());
			for (Stock s1 : results){
				//Find Item name and append name, price, and quantity
				Query findItemName = pm.newQuery("select from " +Item.class.getName() + " where itemID == findMe");
				findItemName.declareParameters("String findMe");
				@SuppressWarnings("unchecked")
				List <Item> results1 = (List<Item>)findItemName.execute(s1.getItemID());
				//Changed from s1.getItemPrice() to s1.getItemAveragePrice()
				displayStrings[counter] = "Item name: " +results1.get(0).getItemName() + ";Average Reported Price: $" + String.format("%.2f", s1.getItemAveragePrice()) + ";Quantity: " + i.getQuantity();
				//Find Store and append its name
				Query findItemStore = pm.newQuery("select from " + Store.class.getName() + " where storeID == findMe");
				findItemStore.declareParameters("String findMe");
				@SuppressWarnings("unchecked")
				List <Store> results2 = (List<Store>)findItemStore.execute(s1.getStoreID());
				displayStrings[counter] += ";Store name: " + results2.get(0).getStoreName() + "\n";
				counter++;
			}
		}
		displayStrings[counter++] = "Total: $" + String.format("%.2f",totalCost) + "\n";
		pm.close();
		return displayStrings;
		
	}
}
