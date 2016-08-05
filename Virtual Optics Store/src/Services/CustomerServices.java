package Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import DBLayer.Globals;
import DBLayer.UserDAO;
import Model.Customer;

@Path("/customer")
public class CustomerServices {
	
	@Path("/test")
	public Response test(){
		return Response.status(200).build();
	}
	@Path("/signup")
	@POST
	public String signup(@FormParam("fname") String fname,@FormParam("lname") String lname,@FormParam("email") String email,@FormParam("password") String password
			,@FormParam("address") String address,@FormParam("phone") String phone,@FormParam("gender") String gender){
		Customer customer = new Customer(fname, lname, password, phone, address, gender,3,3, email);
		return UserDAO.createCustomer(customer) ? Globals.SUCCESS : Globals.ALREADY_EXIST;
	}
	
	@Path("/login")
	@POST
	public String login(@FormParam("email") String email,@FormParam("password") String password){
		Customer customer = UserDAO.getCustomerByEmailAndPassword(email, password);
		if(customer != null){
			try {
				JSONObject obj = JsonParser.prepareCustomerJSON(customer);
				return obj.toString();
			} catch (JSONException e) {
				return Globals.PARSING_ERROR;
			}
			
		}
		else{
			return Globals.USER_NOT_EXIST;
		}
	}
	
	@Path("/getCustomerByEmail")
	@POST
	public String getCustomerByEmail(@FormParam("email") String email){
		Customer customer = UserDAO.getCustomerByEmail(email);
		if(customer != null){
			try {
				JSONObject obj = JsonParser.prepareCustomerJSON(customer);
				return obj.toString();
			} catch (JSONException e) {
				return Globals.PARSING_ERROR;
			}
		}
		else{
			return Globals.USER_NOT_EXIST;
		}
	}
}
