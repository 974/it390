package sh;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import jdodb.Item;
import jdodb.ListItem;
import jdodb.PMF;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;
import jdodb.Template;
import jdodb.User;

public class LoadStartingData {
	public static void loadData(){

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Store s = new Store("Giant","5684 Corner Street",87987);
			Store s1 = new Store("Wegmans","9874 Stauten Avenue",98761);
			Store s2 = new Store("Bloom","9874 Bricks Street",98654);
			pm.makePersistent(s);
			pm.makePersistent(s1);
			pm.makePersistent(s2);
			Template t = new Template("Food");
			Template t1 = new Template("Clothing");
			Template t2 = new Template("Party");
			pm.makePersistent(t);
			pm.makePersistent(t1);			
			pm.makePersistent(t2);
			User u = new User("Jack");
			User u1 = new User("Ian");
			pm.makePersistent(u);
			pm.makePersistent(u1);	
			Item i = new Item("Meat",t.getTypeID());
			Item i1 = new Item("Cookies",t.getTypeID());
			Item i2 = new Item("Party Mix",t2.getTypeID());
			Item i3 = new Item("Milk",t.getTypeID());
			Item i4 = new Item("Hot Pockets",t.getTypeID());
			Item i5 = new Item("Soda",t.getTypeID());
			Item i6 = new Item("Party Punch",t2.getTypeID());
			Item i7 = new Item("Shirt",t1.getTypeID());
			Item i8 = new Item("Pants",t1.getTypeID());
			Item i9 = new Item("Eggs",t.getTypeID());
			pm.makePersistent(i);
			pm.makePersistent(i1);			
			pm.makePersistent(i2);			
			pm.makePersistent(i3);			
			pm.makePersistent(i4);			
			pm.makePersistent(i5);			
			pm.makePersistent(i6);			
			pm.makePersistent(i7);			
			pm.makePersistent(i8);			
			pm.makePersistent(i9);
			Stock as = new Stock(5,i.getItemID(),s.getStoreID());
			Stock as1 = new Stock(4,i.getItemID(),s1.getStoreID());
			Stock as2 = new Stock(3,i1.getItemID(),s1.getStoreID());
			Stock as3 = new Stock(2,i1.getItemID(),s.getStoreID());
			Stock as4 = new Stock(4,i4.getItemID(),s1.getStoreID());
			Stock as5 = new Stock(3,i9.getItemID(),s1.getStoreID());
			Stock as6 = new Stock(2.50,i9.getItemID(),s.getStoreID());
			pm.makePersistent(as);			
			pm.makePersistent(as1);			
			pm.makePersistent(as2);			
			pm.makePersistent(as3);			
			pm.makePersistent(as4);
			pm.makePersistent(as5);			
			pm.makePersistent(as6);
			ArrayList <ListItem> l1= new <ListItem>ArrayList();
			ListItem o1 = new ListItem(2,as.getCombineStoreAndItemID());
			ListItem o2 = new ListItem(2,as.getCombineStoreAndItemID());
			ListItem o3 = new ListItem(1,as6.getCombineStoreAndItemID());
			l1.add(o1);
			l1.add(o2);
			l1.add(o3);
			ShoppingList shpLst = new ShoppingList(15,15,u.getUserID(),l1);
			pm.makePersistent(shpLst);
		}finally{
			pm.close();
		}

	}

}
