package jdodb;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Item {
	public Item(String name, long type){
		setItemName(name);
		typeID = type;
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long itemID;
	@Persistent
	private String itemName;
	@ForeignKey
	@Persistent
	private long typeID;
	
	
	public long getItemID(){return itemID;}
	public long getTypeID(){return typeID;}
	public String getItemName(){return itemName;}
	
	public void setItemName(String name){itemName=name;}
}
