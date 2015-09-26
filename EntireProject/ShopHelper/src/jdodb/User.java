package jdodb;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {
	public User(String name){
		setUserName(name);
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key userID;
	@Persistent
	private String userName;
	
	public Key getUserID(){return userID;}
	public String getUserName(){return userName;}
	
	public void setUserName(String name){userName = name;}
}
