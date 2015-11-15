package sh;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;

import jdodb.PMF;
import jdodb.Store;
import jdodb.UserAccount;

public class ProcessUser {
	public static boolean userExists(User currentUser){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Extent<jdodb.UserAccount> extent1 = pm.getExtent(jdodb.UserAccount.class, false);
		for (jdodb.UserAccount me : extent1) {
			if (currentUser.getEmail().equalsIgnoreCase(me.getUserName())){
				extent1.closeAll();
				return true;
			}
		}
		extent1.closeAll();
		return false;
	}
	public static void userCreateAccount(User currentUser){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserAccount newUser = new UserAccount(currentUser.getEmail());
		pm.makePersistent(newUser);
		pm.close();
	}
}
