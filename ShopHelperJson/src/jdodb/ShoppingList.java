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
	private ArrayList <ListItem> shoppingList= new ArrayList<ListItem>();
	
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
	
	public List <String> createStringOutputs(){
		List <String> shopList = new ArrayList <String>();
		final int MAX_LINES = 500;
		int counter = 0;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String displayStrings [] = new String[MAX_LINES];
		displayStrings[counter] =  "Budget:$" + budget + " Type: " + type + "\n";
		shopList.add(displayStrings[counter]);
		counter++;
		//String displayMe = "Budget:$" + budget + "     Type: " + type + "\n";
		String JSONVer;
		JSONObject obj = new JSONObject();
		int stringIncr = 0;
		try {
			obj.put("username","Jack");
			obj.put("budget", new Double(budget));
			obj.put("total",new Double(totalCost));
			obj.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		for (ListItem i : shoppingList){
			Query findItemIDInStock = pm.newQuery("select from " + Stock.class.getName() + " where combineStoreAndItemID == findMe");
			findItemIDInStock.declareParameters("String findMe");
			List <Stock> results = (List<Stock>)findItemIDInStock.execute(i.getCombineStoreAndItemID());
			for (Stock s1 : results){
				//Find Item name and append name, price, and quantity
				Query findItemName = pm.newQuery("select from " +Item.class.getName() + " where itemID == findMe");
				findItemName.declareParameters("String findMe");
				List <Item> results1 = (List<Item>)findItemName.execute(s1.getItemID());
				displayStrings[counter] = "Item name: " +results1.get(0).getItemName() + ";Price per item: $" + s1.getItemPrice() + ";Quantity: " + i.getQuantity();
				shopList.add(displayStrings[counter]);
				try {
					obj.put("itemname" + stringIncr, results1.get(0).getItemName());
					obj.put("itemprice" + stringIncr, s1.getItemPrice());
					obj.put("itemquantity" + stringIncr, i.getQuantity());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				//Find Store and append its name
				Query findItemStore = pm.newQuery("select from " + Store.class.getName() + " where storeID == findMe");
				findItemStore.declareParameters("String findMe");
				List <Store> results2 = (List<Store>)findItemStore.execute(s1.getStoreID());
				displayStrings[counter] += ";Store name: " + results2.get(0).getStoreName() + "\n";
				shopList.add(displayStrings[counter]);
				counter++;
				try {
					obj.put("storename" + stringIncr, results2.get(0).getStoreName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				stringIncr++;
			}
		}
		displayStrings[counter] = "Total: $" + totalCost + "\n";
		shopList.add(displayStrings[counter]);
		counter++;
		pm.close();
		//return obj.toString();
		//return displayMe;
		//return displayStrings;
		return shopList;
		
	}
}
