package sh;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class ShopHelperServletJSON extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		String element = request.getParameter("element");
		try{

			if(element != null && element.equals("listInfo")){
				System.out.println(element);
				//result.put("budget", null);
				//result.put("type", null);
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().println(result.toString());
		response.flushBuffer();
	}
}
