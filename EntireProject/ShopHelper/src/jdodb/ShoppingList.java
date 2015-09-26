package jdodb;

import java.util.ArrayList;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ShoppingList {
	public ShoppingList(double bud, double total, Key user, ArrayList <ListItem>list){
		setBudget(bud);
		setTotalCost(total);
		userID = user;
		shoppingList = list;
	}
	@ForeignKey
	@Persistent
	@Element(dependent = "true")
	private Key userID;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key shopListID;
	@Persistent
	private double budget;
	@Persistent
	private double totalCost;
	@Persistent
	private String type;
	@Persistent
	private ArrayList <ListItem> shoppingList= new ArrayList<ListItem>();
	
	public Key getUserID(){return userID;}
	public Key getShopListID(){return shopListID;}
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
