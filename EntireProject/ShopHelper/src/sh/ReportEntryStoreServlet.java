package sh;

import java.io.IOException;
import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jdodb.PMF;
import jdodb.Store;

public class ReportEntryStoreServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String storeID = req.getParameter("store").trim();
		String storeN = "error";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Extent<Store> extent2 = pm.getExtent(Store.class, false);
		for (Store me : extent2) {
			if (me.getStoreID().equalsIgnoreCase(storeID)){
				storeN = me.getStoreName();
			}
		}
		extent2.closeAll();

		try{
			req.setAttribute("chosenStore", storeN);
			req.setAttribute("storeID", storeID);
		    req.getRequestDispatcher("ReportEntryItemNameAndPrice.jsp").forward(req, resp);
		}catch(NullPointerException e){
			e.printStackTrace();
		}

	}
	
}
