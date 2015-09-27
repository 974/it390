package jdodb;

import java.util.ArrayList;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ShoppingList {
	public ShoppingList(double bud, double total, String user, ArrayList <ListItem>list){
		setBudget(bud);
		setTotalCost(total);
		userID = user;
		shoppingList = list;
	}
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
}
