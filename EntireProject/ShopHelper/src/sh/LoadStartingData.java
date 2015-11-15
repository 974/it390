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
import jdodb.UserAccount;

import java.util.List;

import javax.jdo.Query;
import javax.servlet.http.HttpServlet;

public class LoadStartingData extends HttpServlet{
	public static void loadData() {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Store s = new Store("Giant","5684 Corner Street",87987);
			Store s1 = new Store("Wegmans","9874 Stauten Avenue",98761);
			Store s2 = new Store("Bloom","9874 Bricks Street",98654);
			Store s4 = new Store("Macy's","3456 Stark Street",22569);
			pm.makePersistent(s);
			pm.makePersistent(s1);
			pm.makePersistent(s2);
			pm.makePersistent(s4);
			Template t = new Template("continental breakfast2");
			Template t1 = new Template("thanksgiving dinner10");
			Template t2 = new Template("back to school clothes2");
			Template t3 = new Template("halloween party5");
			pm.makePersistent(t);
			pm.makePersistent(t1);			
			pm.makePersistent(t2);
			pm.makePersistent(t3);
			Item i = new Item("10 pound turkey",t1.getTypeID());
			Item i13 = new Item("Jellied Cranberry sauce 14 oz",t1.getTypeID());
			Item i1 = new Item("Stuffing 2 lb",t1.getTypeID());
			Item i2 = new Item("Party Mix 29 oz",t3.getTypeID());
			Item i3 = new Item("Milk 64 fl. oz",t.getTypeID());
			Item i4 = new Item("Potatoes 5 lb",t1.getTypeID());
			Item i5 = new Item("Soda 144 fl. oz",t3.getTypeID());
			Item i6 = new Item("Party Punch 128 fl. oz",t3.getTypeID());
			Item i7 = new Item("Shirt",t2.getTypeID());
			Item i8 = new Item("Pants",t2.getTypeID());
			Item i9 = new Item("Eggs 24 oz",t.getTypeID());
			Item i10 = new Item("Potatoe Chips 11 oz",t3.getTypeID());
			Item i11 = new Item("Ham 7 lb",t1.getTypeID());
			Item i12 = new Item("Pumpkin pie 30 oz",t1.getTypeID());
			Item pty1 = new Item("Confetti 1 lb",t3.getTypeID());
			Item i14 = new Item("Corn (canned) 15 oz",t1.getTypeID());
			Item cloth1 = new Item("Gloves",t2.getTypeID());
			Item cloth2 = new Item("Scarf",t2.getTypeID());
			Item i15 = new Item("English Muffin 12 oz",t.getTypeID());
			Item i16 = new Item("Sausage 12 oz",t.getTypeID());
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
			pm.makePersistent(i13);
			pm.makePersistent(pty1);
			pm.makePersistent(i14);
			pm.makePersistent(i15);
			pm.makePersistent(i16);
			pm.makePersistent(cloth1);
			pm.makePersistent(cloth2);
			
			double data [] = {35,23,26,27,20,25,27,28,29,25};
			Stock as = new Stock(i.getItemID(),s2.getStoreID(),data,data.length);
			double data1 [] = {35,35,30,30,26,27,28,29,26,28};
			Stock as1 = new Stock(i.getItemID(),s.getStoreID(),data1,data1.length);
			double data2 [] = {56,45,50,48,47,60,50,45,39,45};
			Stock as2 = new Stock(i.getItemID(),s1.getStoreID(),data2,data2.length);
			double data3 [] = {5,4,2,3,4,2,2,3,3,4};
			Stock as3 = new Stock(i13.getItemID(),s2.getStoreID(),data3,data3.length);
			double data4 [] = {5,2,2,3,4,6.3,5,5,4,3};
			Stock as4 = new Stock(i13.getItemID(),s.getStoreID(),data4,data4.length);
			double data5 [] = {5,8,2,3,4,7,5,5,6,5};
			Stock as5 = new Stock(i13.getItemID(),s1.getStoreID(),data5,data5.length);
			double data6 [] = {5,8,2,3,4,4,3,5,5,4};
			Stock as6 = new Stock(i1.getItemID(),s2.getStoreID(),data6,data6.length);
			double data7 [] = {5,6,2,3,4,10,5,8,9,7};
			Stock as7 = new Stock(i1.getItemID(),s1.getStoreID(),data7,data7.length);
			double data8 [] = {5,8,2,3,4,5,7,7,4,6};
			Stock as8 = new Stock(i1.getItemID(),s.getStoreID(),data8,data8.length);
			double data9 [] = {3,6,7,2,2,2,3,4,3,4};
			Stock as9 = new Stock(i2.getItemID(),s.getStoreID(),data9,data9.length);
			double data10 [] = {5,8,2,3,4,5,7,7,9,7};
			Stock as10 = new Stock(i2.getItemID(),s1.getStoreID(),data10,data10.length);
			
			double data11 [] = {2,4,4,2,3,4,3,4,4,5};
			Stock as11 = new Stock(i3.getItemID(),s1.getStoreID(),data11,data11.length);
			double data12 [] = {2,2,3,3,3,3,3,2,2,4,2};
			Stock as12 = new Stock(i3.getItemID(),s2.getStoreID(),data12,data12.length);
			double data13 [] = {2,2,2,2,4,3,3,2,3,3};
			Stock as13 = new Stock(i3.getItemID(),s.getStoreID(),data13,data13.length);
			double data14 [] = {8,9,6,13,6,8,5,4,7};
			Stock as14 = new Stock(i4.getItemID(),s1.getStoreID(),data14,data14.length);
			double data15 [] = {8,9,6,9,6,8,5,4,5,5,5};
			Stock as15 = new Stock(i4.getItemID(),s.getStoreID(),data15,data15.length);
			double data16 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as16 = new Stock(i4.getItemID(),s2.getStoreID(),data16,data16.length);
			double data17 [] = {4,4,4,5,4,5,6,7,4,3,5,5,4};
			Stock as17 = new Stock(i5.getItemID(),s.getStoreID(),data17,data17.length);
			double data18 [] = {4,4,4,5,4,5,6,7,4,3,5,8,6,5};
			Stock as18 = new Stock(i5.getItemID(),s1.getStoreID(),data18,data18.length);
			double data19 [] = {4,4,4,5,4,5,6,7,4,3,5,8,4,2,2};
			Stock as19 = new Stock(i5.getItemID(),s2.getStoreID(),data19,data19.length);
			double data20 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as20= new Stock(i6.getItemID(),s.getStoreID(),data20,data20.length);
			
			double data21 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as21 = new Stock(i6.getItemID(),s2.getStoreID(),data21,data21.length);
			double data22 [] = {15,15,15,25,14,13,14};
			Stock as22 = new Stock(i7.getItemID(),s4.getStoreID(),data22,data22.length);
			double data23 [] = {25,24,29,24,23,24.5};
			Stock as23 = new Stock(i8.getItemID(),s4.getStoreID(),data23,data23.length);
			double data24 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as24 = new Stock(i9.getItemID(),s.getStoreID(),data24,data24.length);
			double data25 [] = {4,4,4,5,4,5,6,7,4,3,5,9,6,6,6};
			Stock as25 = new Stock(i9.getItemID(),s1.getStoreID(),data25,data25.length);
			double data26 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as26 = new Stock(i9.getItemID(),s2.getStoreID(),data26,data26.length);
			double data27 [] = {4,4,4,5,4,5,6,7,4,3,5};
			Stock as27 = new Stock(i10.getItemID(),s.getStoreID(),data27,data27.length);
			double data28 [] = {4,4,4,5,4,5,6,7,4,3,5,6,6,6,6,6};
			Stock as28 = new Stock(i10.getItemID(),s1.getStoreID(),data28,data28.length);
			double data29 [] = {4,4,4,5,4,5,6,7,4,3,5,4,4,4,4,3,3,3,3.5};
			Stock as29 = new Stock(i10.getItemID(),s2.getStoreID(),data29,data29.length);
			double data30 [] = {20,20,20,20,15,15,25,25,19};
			Stock as30 = new Stock(i11.getItemID(),s2.getStoreID(),data30,data30.length);
			
			
			double data31 [] = {25,25,25,25,20,20,29,27,26};
			Stock as31 = new Stock(i11.getItemID(),s.getStoreID(),data31,data31.length);
			double data32 [] = {25,25,25,27,27,26,28,29,30};
			Stock as32 = new Stock(i11.getItemID(),s1.getStoreID(),data32,data32.length);
			double data33 [] = {10,8,9,9,8,12,12,9,10,9.5};
			Stock as33 = new Stock(i12.getItemID(),s.getStoreID(),data33,data33.length);
			double data34 [] = {12,12,13,10,8,8.5,9,9,11};
			Stock as34 = new Stock(i12.getItemID(),s1.getStoreID(),data34,data34.length);
			double data35 [] = {8,8,8,7,6,9,11,10,9,8};
			Stock as35 = new Stock(i12.getItemID(),s2.getStoreID(),data35,data35.length);
			double data36 [] = {5,5,5,5,5,4,4,4,4,9,9,6,6,5.5};
			Stock as36 = new Stock(pty1.getItemID(),s.getStoreID(),data36,data36.length);
			double data37 [] = {4,4,4,3,3,3,2,2,5,5,6,7,9};
			Stock as37 = new Stock(pty1.getItemID(),s2.getStoreID(),data37,data37.length);
			double data38 [] = {3,3,3,4,5,2,4,3,4,2};
			Stock as38 = new Stock(i14.getItemID(),s.getStoreID(),data38,data38.length);
			double data39 [] = {5,4,3,4,5,6,6,6,3,5};
			Stock as39 = new Stock(i14.getItemID(),s1.getStoreID(),data39,data39.length);
			double data40 [] = {5,4,3,4,5,6,4,5,4,3,3,4};
			Stock as40 = new Stock(i14.getItemID(),s.getStoreID(),data40,data40.length);
			
			double data41 [] = {13,13,13,12,9,19,14,15,15};
			Stock as41 = new Stock(cloth1.getItemID(),s4.getStoreID(),data41,data41.length);
			double data42 [] = {13,13,13,12,9,19,14,17,17,17};
			Stock as42 = new Stock(cloth2.getItemID(),s4.getStoreID(),data42,data42.length);
			double data43 [] = {4,4,4,3,3,2,9,5,5,4};
			Stock as43 = new Stock(i15.getItemID(),s2.getStoreID(),data43,data43.length);
			double data44 [] = {5,5,5,5,4,4,3,2,2,5};
			Stock as44 = new Stock(i15.getItemID(),s.getStoreID(),data44,data44.length);
			double data45 [] = {6,6,6,4,5,2,4,5,8,5,5};
			Stock as45 = new Stock(i15.getItemID(),s1.getStoreID(),data45,data45.length);
			double data46[] = {7,7,5,6,6,6.5,8,5,7,8};
			Stock as46 = new Stock(i16.getItemID(),s1.getStoreID(),data46,data46.length);
			double data47[] = {7,5,6,5,5,7,5,5,4,4,6};
			Stock as47 = new Stock(i16.getItemID(),s.getStoreID(),data47,data47.length);
			double data48 [] = {6,5,5,6,6.5,8,8,9,4,4,4};
			Stock as48 = new Stock(i16.getItemID(),s2.getStoreID(),data48,data48.length);
			
			
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
			pm.makePersistent(as14);
			pm.makePersistent(as15);
			pm.makePersistent(as16);
			pm.makePersistent(as17);
			pm.makePersistent(as18);
			pm.makePersistent(as19);
			pm.makePersistent(as20);
			pm.makePersistent(as21);
			pm.makePersistent(as22);
			pm.makePersistent(as22);
			pm.makePersistent(as23);
			pm.makePersistent(as24);
			pm.makePersistent(as25);
			pm.makePersistent(as26);
			pm.makePersistent(as27);
			pm.makePersistent(as28);
			pm.makePersistent(as29);
			pm.makePersistent(as30);
			pm.makePersistent(as31);
			pm.makePersistent(as32);
			pm.makePersistent(as33);
			pm.makePersistent(as34);
			pm.makePersistent(as35);
			pm.makePersistent(as36);
			pm.makePersistent(as37);
			pm.makePersistent(as38);
			pm.makePersistent(as39);
			pm.makePersistent(as40);
			pm.makePersistent(as41);
			pm.makePersistent(as42);
			pm.makePersistent(as43);
			pm.makePersistent(as44);
			pm.makePersistent(as45);
			pm.makePersistent(as46);
			pm.makePersistent(as47);
			pm.makePersistent(as48);
			
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
			//changed from u.getUserID() to null
			ShoppingList shpLst = new ShoppingList(15,15,null,l1);
			pm.makePersistent(shpLst);
		}finally{
			pm.close();
		}

	}

}
