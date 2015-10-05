package sh;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;

import jdodb.Item;
import jdodb.ListItem;
import jdodb.PMF;
import jdodb.ShoppingList;
import jdodb.Stock;
import jdodb.Store;
import jdodb.Template;
import jdodb.User;
import java.util.List;
import javax.jdo.Query;

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
			Item i10 = new Item("Potatoe Chips",t.getTypeID());
			Item i11 = new Item("Salmon",t.getTypeID());
			Item i12 = new Item("Bananas",t.getTypeID());
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
			pm.makePersistent(i10);
			pm.makePersistent(i11);
			pm.makePersistent(i12);
			Stock as = new Stock(6,i.getItemID(),s.getStoreID());
			Stock as1 = new Stock(5.5,i.getItemID(),s1.getStoreID());
			Stock as2 = new Stock(3,i1.getItemID(),s1.getStoreID());
			Stock as3 = new Stock(2,i1.getItemID(),s.getStoreID());
			Stock as4 = new Stock(4,i4.getItemID(),s1.getStoreID());
			Stock as5 = new Stock(3,i9.getItemID(),s1.getStoreID());
			Stock as6 = new Stock(2.5,i9.getItemID(),s.getStoreID());
			Stock as7 = new Stock(3.5,i10.getItemID(),s2.getStoreID());
			Stock as8 = new Stock(3,i10.getItemID(),s1.getStoreID());
			Stock as9 = new Stock(4,i10.getItemID(),s.getStoreID());
			Stock as10 = new Stock(5,i11.getItemID(),s1.getStoreID());
			Stock as11 = new Stock(6,i11.getItemID(),s2.getStoreID());
			Stock as12 = new Stock(6,i12.getItemID(),s1.getStoreID());
			Stock as13 = new Stock(6,i12.getItemID(),s.getStoreID());
			pm.makePersistent(as);			
			pm.makePersistent(as1);			
			pm.makePersistent(as2);			
			pm.makePersistent(as3);			
			pm.makePersistent(as4);
			pm.makePersistent(as5);			
			pm.makePersistent(as6);
			pm.makePersistent(as7);
			pm.makePersistent(as8);
			pm.makePersistent(as9);
			pm.makePersistent(as10);
			pm.makePersistent(as11);
			pm.makePersistent(as12);
			pm.makePersistent(as13);
			ArrayList <ListItem> l1= new <ListItem>ArrayList();
			ListItem o1 = new ListItem(2,as.getCombineStoreAndItemID());
			ListItem o2 = new ListItem(2,as.getCombineStoreAndItemID());
			ListItem o3 = new ListItem(1,as6.getCombineStoreAndItemID());
			pm.makePersistent(o1);
			pm.makePersistent(o2);
			pm.makePersistent(o3);
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
