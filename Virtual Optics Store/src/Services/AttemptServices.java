package Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONException;
import org.json.JSONObject;

import DBLayer.AttemptDAO;
import DBLayer.GlassesDAO;
import DBLayer.UserDAO;
import Model.Attempt;
import Model.Customer;
import Model.Glasses;

@Path("/attempts")
public class AttemptServices {
	@Path("/getRecommendations")
	@POST
	public String getRecommendations(@FormParam ("email") String email){
		System.out.println("hereeeeeeeeee" + email);
		List<Glasses>list = AttemptDAO.getRecommendations(email);
		ArrayList<Glasses>glasses = new ArrayList<Glasses>(list);
		try {
			JSONObject json = JsonParser.prepareGlassesJSON(glasses);
			return json.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  return "error";
	}
	@Path("/addTry")
	@POST
	public String addTry(@FormParam ("email")String email,@FormParam ("modelName") String modelName){
		Customer customer = UserDAO.getCustomerByEmail(email);
		Glasses glasses = GlassesDAO.getGlassesByModelName(modelName);
		Attempt atm = new Attempt(glasses.getID(),customer.getID(),new Date(),customer,glasses);
		System.out.println(email+" dfdfdf "+modelName);
		AttemptDAO.addAttempt(atm);
		return "done";
	}
}
