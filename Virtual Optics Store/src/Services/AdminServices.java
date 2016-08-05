package Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.json.JSONException;
import org.json.JSONObject;

import DBLayer.BrandDAO;
import DBLayer.GlassesDAO;
import DBLayer.Globals;
import DBLayer.StoreDAO;
import DBLayer.UserDAO;
import Model.Admin;
import Model.Brand;
import Model.Glasses;
import Model.Store;


@Path("/admin")
public class AdminServices {
	
	@POST
	@Path("/signup")
	public String signup(@FormParam("fname") String fName,@FormParam("lname") String lName,@FormParam("userName") String userName,@FormParam("password") String password
			,@FormParam("address") String address,@FormParam("phone") String phone,@FormParam("gender") String gender, 
			@FormParam("store") String storeAddress){
		Store store = StoreDAO.getStoreByAddress(storeAddress);
		if(store == null){
			return Globals.STORE_NOT_EXIST;
		}
		Admin admin = new Admin(fName, lName, password, phone, address, gender, userName, store);
		return UserDAO.createAdmin(admin) ? Globals.SUCCESS : Globals.ALREADY_EXIST;
		
	}
	
	@POST
	@Path("/addNewGlasses")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String addNewGlasses(@Context ServletContext context, @Context HttpServletRequest request){
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(500000);
		FileUpload upload = new FileUpload(factory);
		Map<String, String> fields = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(request);
			FileItem file = null;
			for(FileItem item : items){
				if(item.isFormField()){
					fields.put(item.getFieldName(), item.getString());
				}
				else{
					file = item;
				}
			}
			if(file == null){
				return Globals.FILE_PROBLEM;
			}
			else{
				String color = fields.get("color");
				String modelName = fields.get("modelName");
				int convertable = Integer.parseInt(fields.get("convertable"));
				String brandName = fields.get("brand");
				String shape = fields.get("shape");
				int type = Integer.parseInt(fields.get("type"));
				String material = fields.get("material");
				double price = Double.parseDouble(fields.get("price"));
				Brand brand = BrandDAO.getBrandByName(brandName);
				if(brand == null){
					return Globals.BRAND_NOT_EXIST;
				}
				if(GlassesDAO.getGlassesByModelName(modelName) == null){
					String firstPathPart = context.getRealPath("") + "/";
					String secondPathPart = "Glasses"
							+ "/" + brandName + "/" + file.getName();
					FileUtilities.saveFile(firstPathPart + secondPathPart, file.getInputStream());
					Glasses glasses = new Glasses(color, modelName, secondPathPart, convertable, shape, type, material, price, brand);
					GlassesDAO.addGlasses(glasses);
					return Globals.SUCCESS;
				}
				else{
					return Globals.ALREADY_EXIST;
				}
			}
		} catch (Exception e) {
			return Globals.FILE_PROBLEM;
		}
	}

	@Path("/addNewBrand")
	@POST
	public String addNewBrand(@FormParam("brandName") String brandName, 
			@FormParam("country") String country){
		Brand brand = new Brand(brandName, country);
		return BrandDAO.addBrand(brand) ? Globals.SUCCESS : Globals.ALREADY_EXIST;
	}
	
	@Path("/getBrands")
	@GET
	public String getBrands(){
		List<Brand> brands = BrandDAO.getBrands();
		ArrayList<Brand> newBrands = new ArrayList<Brand>(brands);
		JSONObject obj;
		try {
			obj = JsonParser.prepareBrandsJSON(newBrands);
			return obj.toString();
		} catch (JSONException e) {
			return Globals.PARSING_ERROR;
		}
	}
}
