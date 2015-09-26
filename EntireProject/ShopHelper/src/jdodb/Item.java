package jdodb;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Item {
	public Item(String name, Key type){
		setItemName(name);
		typeID = type;
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key itemID;
	@Persistent
	private String itemName;
	@ForeignKey
	@Persistent
	private Key typeID;
	
	
	public Key getItemID(){return itemID;}
	public Key getTypeID(){return typeID;}
	public String getItemName(){return itemName;}
	
	public void setItemName(String name){itemName=name;}
}
