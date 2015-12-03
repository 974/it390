package sh;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jdodb.PMF;
import jdodb.ShoppingList;
import jdodb.Store;

public class RemoveListServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query shoppingListToDelete = pm.newQuery("select from " + ShoppingList.class.getName() + " where shopListID == findMe");
		shoppingListToDelete.declareParameters("String findMe");
		String id= req.getParameter("deleteMe");
		javax.jdo.Extent<jdodb.ShoppingList> extent = pm.getExtent(jdodb.ShoppingList.class, false);
		ShoppingList delete = null;
		for (jdodb.ShoppingList me : extent) {
			if(me.getShopListID().equalsIgnoreCase(id)){
				delete = me;
				break;
			}
		}
		pm.deletePersistent(delete);
		pm.close();
		req.getRequestDispatcher("ViewShoppingList.jsp").forward(req, resp);
	}
}
