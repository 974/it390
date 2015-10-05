package jdodb;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Item {
	public Item(String name, String type){
		setItemName(name);
		typeID = type;
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String itemID;
	@Persistent
	private String itemName;
	@ForeignKey
	@Persistent
	private String typeID;
	
	
	public String getItemID(){return itemID;}
	public String getTypeID(){return typeID;}
	public String getItemName(){return itemName;}
	
	public void setItemName(String name){itemName=name;}
}
