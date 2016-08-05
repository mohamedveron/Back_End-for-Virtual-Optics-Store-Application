package Services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Model.Brand;
import Model.Customer;
import Model.Glasses;

public class JsonParser {
	
	public static JSONObject prepareGlassesJSON(ArrayList<Glasses> glasses) throws JSONException{
		ArrayList<String> brands = new ArrayList<String>();
		ArrayList<String> modelNames = new ArrayList<String>();
		ArrayList<String> paths = new ArrayList<String>();
		ArrayList<Double> prices = new ArrayList<Double>();
		for(Glasses g : glasses){
			brands.add(g.getBrand().getName());
			modelNames.add(g.getModelName());
			paths.add(g.getModel());
			prices.add(g.getPrice());
		}
		JSONObject obj = new JSONObject();
		obj.put("brands", brands);
		obj.put("modelNames", modelNames);
		obj.put("paths", paths);
		obj.put("prices", prices);
		
		return obj;
	}
	public static JSONObject prepareBrandsJSON(ArrayList<Brand> brands) throws JSONException{
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> countries = new ArrayList<String>();
		for(Brand b : brands){
			names.add(b.getName());
			countries.add(b.getCountry());
		}
		JSONObject obj = new JSONObject();
		obj.put("names", names);
		obj.put("countries", countries);
		
		return obj;
	}
	public static JSONObject prepareCustomerJSON(Customer customer) throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("fname", customer.getfName());
		obj.put("lname", customer.getlName());
		obj.put("ID", customer.getID());
		obj.put("email", customer.getEmail());
		obj.put("password", customer.getPassword());
		obj.put("phone", customer.getPhone());
		obj.put("address", customer.getAddress());
		obj.put("gender", customer.getGender());
		return obj;
	}
}
