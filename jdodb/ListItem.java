package jdodb;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ListItem {
	public ListItem(int quan, long code){
		setQuantity(quan);
		combineStoreAndItemID = code;
	}
	@ForeignKey
	@Persistent
	private long combineStoreAndItemID;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key listID;
	@Persistent
	private int quantity;
	
	public long getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public Key getListID(){return listID;}
	public int getQuantity(){return quantity;}
	
	public void setQuantity(int quan){quantity = quan;}
}
