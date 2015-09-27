package jdodb;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ListItem {
	public ListItem(int quan, String code){
		setQuantity(quan);
		combineStoreAndItemID = code;
	}
	@ForeignKey
	@Persistent
	private String combineStoreAndItemID;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String listID;
	@Persistent
	private int quantity;
	
	public String getCombineStoreAndItemID(){return combineStoreAndItemID;}
	public String getListID(){return listID;}
	public int getQuantity(){return quantity;}
	
	public void setQuantity(int quan){quantity = quan;}
}
