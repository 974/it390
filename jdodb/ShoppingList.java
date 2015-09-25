package jdodb;

import java.util.ArrayList;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ShoppingList {
	public ShoppingList(double bud, double total, long user, ArrayList <ListItem>list){
		setBudget(bud);
		setTotalCost(total);
		userID = user;
		shoppingList = list;
	}
	@ForeignKey
	@Persistent
	private long userID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long shopListID;
	@Persistent
	private double budget;
	@Persistent
	private double totalCost;
	@Persistent
	private String type;
	@Persistent
	private ArrayList <ListItem> shoppingList= new ArrayList<ListItem>();
	
	public long getUserID(){return userID;}
	public long getShopListID(){return shopListID;}
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
