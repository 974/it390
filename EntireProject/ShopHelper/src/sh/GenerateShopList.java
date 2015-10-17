package sh;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdodb.Item;
import jdodb.ListItem;
import jdodb.PMF;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;
import jdodb.User;

public class GenerateShopList {
	public ShoppingList generateList(double budget, String type){
		ShoppingList s = new ShoppingList();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String typeIDWeNeed = null;
		//new
		String name = "";
		if (type.equalsIgnoreCase("thanksgiving dinner5")){
			if (budget < 80){
				name = "Bloom";
			}else if(budget >= 80 && budget< 115){
				name = "Giant";
			}else if (budget >= 115){
				name = "Wegmans";
			}
		}else if (type.equalsIgnoreCase("continental breakfast2")){
			if (budget < 30){
				name = "Bloom";
			}else if(budget >= 30 && budget< 75){
				name = "Giant";
			}else if (budget >= 75){
				name = "Wegmans";
			}	
		}else if (type.equalsIgnoreCase("halloween party5")){
			if (budget < 35){
				name = "Bloom";
			}else if(budget >= 35 && budget< 80){
				name = "Giant";
			}else if (budget >= 80){
				name = "Wegmans";
			}	
		}else if (type.equalsIgnoreCase("back to school clothes2")){
			name = "Macy's";
		}
		//new
		Query findStoreID = pm.newQuery("select from " + Store.class.getName() + " where storeName == findMe");
		findStoreID .declareParameters("String findMe");
		List <Store> r10 = (List<Store>)findStoreID .execute(name);
		String idForStore = r10.get(0).getStoreID();
		//Find the typeID primary key to find all items with this type.
		javax.jdo.Extent<jdodb.Template> extent1 = pm.getExtent(jdodb.Template.class, false);
		for (jdodb.Template me : extent1) {
			if (me.getItemType().equalsIgnoreCase(type)){
				typeIDWeNeed = me.getTypeID();
				break;
			}
		}
		extent1.closeAll();
		
		//Find the selection of items to generate the shopping list from.
		javax.jdo.Extent<Item> extent2 = pm.getExtent(Item.class, false);
		ArrayList <Item> itemsToGenerateFrom = new <Item>ArrayList();
		for (Item me : extent2) {
			if (me.getTypeID().equalsIgnoreCase(typeIDWeNeed)){
				itemsToGenerateFrom.add(me);
			}
		}
		extent2.closeAll();
		
		//Limit the pool of available items by trimming it down to the items that are not in the stock
		javax.jdo.Extent<Stock> extent3 = pm.getExtent(Stock.class, false);
		ArrayList <Stock> itemsToGenerateFromReduced = new <Stock>ArrayList();
		for (Stock me : extent3) {
			if (me.getStoreID().equalsIgnoreCase(idForStore)){
				
			
			for (Item examineMe:  itemsToGenerateFrom){
				//changed to take store into account
				
				if (me.getItemID().equalsIgnoreCase(examineMe.getItemID())){
					itemsToGenerateFromReduced.add(me);
				}
			}
		}
		}
		extent3.closeAll();
	
		//Display possible items to choose from
		//for (int i = 0; i < itemsToGenerateFromReduced.size();i++){System.out.println(itemsToGenerateFromReduced.get(i).getItemID());}
		
		//Choose items for list
		double total = 0; boolean max = false;
		ArrayList <ListItem> items = new <ListItem>ArrayList();
		while((total < budget) && !itemsToGenerateFromReduced.isEmpty()){
			int randNumItem = (int)(Math.random()*itemsToGenerateFromReduced.size());
			//Max Quantity of 3
			int randNumQuan = 0;
			if (type.equalsIgnoreCase("thanksgiving dinner5")){
				randNumQuan = (int)(Math.random()*2+1);
			}else if (type.equalsIgnoreCase("continental breakfast2")){
				randNumQuan = (int)(Math.random()*3+1);
			}else if (type.equalsIgnoreCase("halloween party5")){
				randNumQuan = (int)(Math.random()*3+1);
			}else if (type.equalsIgnoreCase("back to school clothes2")){
				randNumQuan = (int)(Math.random()*2+1);
			}
			//Changed from getItemPrice() to getItemAveragePrice()
			double costForItem = randNumQuan * (itemsToGenerateFromReduced.get(randNumItem).getItemAveragePrice());
			String itemIDToRemove = null;
			//Check to see if the quantity makes the total go over the budget
			if ((costForItem + total) <= budget){
				total += costForItem;
				items.add(new ListItem(randNumQuan, itemsToGenerateFromReduced.get(randNumItem).getCombineStoreAndItemID()));
				itemIDToRemove = itemsToGenerateFromReduced.get(randNumItem).getItemID();
				itemsToGenerateFromReduced.remove(randNumItem);
				removeItemFromReducedList(itemIDToRemove, itemsToGenerateFromReduced);
			}else{
				boolean goodQuan = false;
				//Reduce quantity to see if we can find a suitable quantity
				for (int i = randNumQuan; i > 0 && !goodQuan;i--){
					//Changed from getItemPrice() to getItemAveragePrice()
					costForItem = i * (itemsToGenerateFromReduced.get(randNumItem).getItemAveragePrice());
					if ((costForItem + total) <= budget){
						total += costForItem;
						items.add(new ListItem(randNumQuan, itemsToGenerateFromReduced.get(randNumItem).getCombineStoreAndItemID()));
						itemIDToRemove =  itemsToGenerateFromReduced.get(randNumItem).getItemID();
						itemsToGenerateFromReduced.remove(randNumItem);
						goodQuan = true;
						removeItemFromReducedList(itemIDToRemove, itemsToGenerateFromReduced);
					}
				}
				//If a quantity could not be established, we will still remove the item. However, we will not remove other items of its type
				if (goodQuan == false){
					itemsToGenerateFromReduced.remove(randNumItem);
				}
			}
		}

		//for (ListItem i : items){
		//	pm.makePersistent(i);
		//}
		Query findUser = pm.newQuery("select from " + User.class.getName() + " where userName == findMe");
		findUser.declareParameters("String findMe");
		List <User> results3 = (List<User>)findUser.execute("Jack");
		ShoppingList newShopList = new ShoppingList(budget,total, results3.get(0).getUserID(),items);
		newShopList.setType(type);
		pm.makePersistent(newShopList);
		pm.close();
				
		//Display possible items to choose from
		//for (int i = 0; i < items.size();i++){System.out.println(items.get(i).getQuantity() + " Total: " + total);}
		
		
		return newShopList;
	}
	public void removeItemFromReducedList(String id, ArrayList <Stock> srchMe){
		for (int i = 0; i < srchMe.size();i++){
			if(srchMe.get(i).getItemID().equalsIgnoreCase(id)){
				srchMe.remove(i);
			}
		}	
	}
}
