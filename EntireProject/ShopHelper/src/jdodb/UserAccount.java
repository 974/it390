package jdodb;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserAccount {
	public UserAccount(String name){
		setUserName(name);
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String userID;
	@Persistent
	private String userName;
	
	public String getUserID(){return userID;}
	public String getUserName(){return userName;}
	
	public void setUserName(String name){userName = name;}
}
