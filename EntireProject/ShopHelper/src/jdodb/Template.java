package jdodb;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Template {
	public Template(String type){
		setItemType(type);
	}
	//add typeID to uml
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key typeID;
	@Persistent
	private String itemType;
	
	public Key getTypeID(){return typeID;}
	public String getItemType(){return itemType;}
	
	public void setItemType(String type){itemType = type;}
}
