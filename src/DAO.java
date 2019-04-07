import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAO {
	
	public String name = "Test";
	public String presentationName;
	
	//List<Role> roles  = new ArrayList<>();
	//List<Asset> assets = new ArrayList<>();
	//List<Task> tasks = new ArrayList<>();
	
	Map<String, Role> roles = new HashMap<>();
	Map<String, Asset> assets = new HashMap<>();
	Map<String, Task> tasks = new HashMap<>();
	
	static String toProperCase(String s) {
	    return s.substring(0, 1).toUpperCase() +
	               s.substring(1).toLowerCase();
	}
	
	class Element{
		public String id;
		public String presentationName;
		public String briefDescription;
		public String name;
		
		public String camelCaseName;
		public String pascalCaseName;
		public void setName(String lower_case_name) {
			 String[] parts = lower_case_name.split("_");
			   String camelCaseString = "";
			   for (String part : parts){
			      camelCaseString = camelCaseString + toProperCase(part);
			   }
			   camelCaseName = camelCaseString;
			   name = lower_case_name;
			   pascalCaseName = camelCaseString.substring(0,1).toLowerCase()
					   +camelCaseString.substring(1);
		}
	}
	
	class Role extends Element{
		
	}
	
	class Asset extends Element{
		boolean hasStates = false;
		public List<String> states = new ArrayList<>();
		
		// Maps product id to state name
		public Map<String, String> stateIds = new HashMap<>();
	}
	
	class Task extends Element{
		public List<String> performersID = new ArrayList<>();
		public String mandatoryInput = ""; // id(s) of input assets
		public String output = "";// id of output asset
	}
	
	
	public static DAO createFromJSON(JSONObject daoJson) {
		DAO ret = new DAO();
		JSONObject methodPlugin = 
				daoJson.getJSONObject("uma:MethodLibrary")
				.getJSONObject("MethodPlugin");
		ret.name = methodPlugin.getString("name");
		
		JSONArray methodPackage = methodPlugin.getJSONArray("MethodPackage");
		
		// ret name method package
		ret.presentationName = methodPackage.getJSONObject(1).getString("presentationName");
		
		JSONArray contentElement = methodPackage.getJSONObject(1).getJSONArray("ContentElement");
		
		// for all elements
		for(int i = 0; i < contentElement.length(); i++) {
			JSONObject elem = contentElement.getJSONObject(i);
			
			String type = elem.getString("xsi:type");
			switch(type) {
			case "uma:Artifact":
				String fullName =elem.getString("name");
				String[] parts = fullName.split("_");
				if(Arrays.asList(parts).contains("state")) {
					String assetName = parts[0];
					String state = "";
					for(int k = 1; k < parts.length; k++) {
						if(parts[k].equals("state")) {
							state = parts[k+1];
							break;
						}
						assetName+="_"+parts[k];
					}
					boolean assetExists = false;
					String assetId = "";
					for(Asset a : ret.assets.values()) {
						if(a.name.equals(assetName)) {
							assetExists = true;
							assetId = a.id;
						}
					}
					// if first time, create
					if(!assetExists) {
						Asset asset = ret.new Asset();
						asset.id = elem.getString("id");
						asset.setName(assetName);
						asset.briefDescription = elem.getString("briefDescription");
						asset.presentationName = elem.getString("presentationName");
						asset.hasStates = true;
						asset.states.add(state);
						asset.stateIds.put(asset.id,state);
						ret.assets.put(asset.id,asset);
					} else {
						Asset asset = ret.assets.get(assetId);
						asset.states.add(state);
						asset.stateIds.put(elem.getString("id"),state);
						for(String id : asset.stateIds.keySet())
							ret.assets.put(id,asset);
					}
					
				} else {
					//if no state
					Asset asset = ret.new Asset();
					asset.id = elem.getString("id");
					asset.setName(elem.getString("name"));
					asset.briefDescription = elem.getString("briefDescription");
					asset.presentationName = elem.getString("presentationName");
					
					ret.assets.put(asset.id,asset);
					}
				
				break;
			case "uma:Role":
				Role role = ret.new Role();
				role.id = elem.getString("id");
				role.setName(elem.getString("name"));
				role.briefDescription = elem.getString("briefDescription");
				role.presentationName = elem.getString("presentationName");
				
				ret.roles.put(role.id, role);
				break;
			case "uma:Task":
				Task task= ret.new Task();
				task.id = elem.getString("id");
				task.setName(elem.getString("name"));
				task.briefDescription = elem.getString("briefDescription");
				task.presentationName = elem.getString("presentationName");
				if(elem.has("MandatoryInput"))
					task.mandatoryInput = elem.getString("MandatoryInput");
				if(elem.has("Output"))
						task.output = elem.getString("Output");
				
				// check if 1 or multiple performers
				try {
					JSONArray performers = elem.getJSONArray("PerformedBy");
					for(int j = 0; j < performers.length();j++) {
						task.performersID.add(performers.getString(j));
					}
				} catch(JSONException e) {
					String performers = elem.getString("PerformedBy");
					task.performersID.add(performers);
				}
				
				ret.tasks.put(task.id,task);
				break;
			}	
		}
		
		return ret;
	}
	
	public void printDAO() {
		System.out.println("Name: "+this.presentationName);
		
		System.out.println("Roles:");
		for(String key : roles.keySet()) {
			Role r = roles.get(key);
			System.out.println(r.name);
		}
		
		System.out.println("Assets:");
		for(String key : assets.keySet()) {
			Asset a = assets.get(key);
			System.out.println(a.name);
			if(a.hasStates) {
				System.out.println("   States:");
				for(String state : a.states) {
					System.out.println(state);
				}
			}
		}
		
		System.out.println("Tasks:");
		for(String key : tasks.keySet()) {
			Task t = tasks.get(key);
			System.out.println(t.name);
			System.out.println("Performers:");
			for(String id : t.performersID) {
				System.out.println(roles.get(id).presentationName);
			}
		}
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(name+".cto", "UTF-8");
			
			// namespace
			writer.println("namespace org.agrid.supplychain");
			
			printEnums(writer);
			
			printParticipants(writer);
			
			printTransactions(writer);
			
			PrintAssets(writer);
			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void printEnums(PrintWriter writer) {
		List<String> printedEnums = new ArrayList<>();
		for(Asset a : assets.values()) {
			if(a.hasStates && !printedEnums.contains(a.name)) {
				writer.print("/**\r\n" + 
						" * The status of a "+a.camelCaseName+"\r\n" + 
						" */\r\n" + 
						"enum "+a.camelCaseName+"State {\r\n"); 
				for(String state : a.states)
					writer.print("  o "+state.toUpperCase()+"\r\n");
				
				writer.print("}\r\n");
				printedEnums.add(a.name);
			}
		}
		
	}

	private void printTransactions(PrintWriter writer) {
		writer.println("\r\n //Participants");
		for(String key : tasks.keySet()) {
			
			Task t = tasks.get(key);
			writer.print("/**\r\n" + 
					"  * "+t.presentationName+": "+t.briefDescription+"\r\n" + 
					" */\r\n" + 
					"transaction "+t.camelCaseName+" {\r\n");
			if(!t.output.equals("")) {
				writer.print("  --> "+assets.get(t.output).camelCaseName+" "+assets.get(t.output).pascalCaseName+" \r\n");
			}
			if(!t.mandatoryInput.equals("")) {
				// check if same with output
				if(!assets.get(t.mandatoryInput).name.equals(assets.get(t.output).name)) 
				writer.print("  --> "+assets.get(t.mandatoryInput).camelCaseName+" "+assets.get(t.mandatoryInput).pascalCaseName+" \r\n");
			}
			for(String id : t.performersID) {
				writer.print("  --> "+roles.get(id).camelCaseName+" "+roles.get(id).pascalCaseName+" \r\n");
			}
			writer.print("}\r\n");
		}
	}

	private void printParticipants(PrintWriter writer) {
		writer.print("/**\r\n" + 
				"  * This participant is an abstract class that defines ontotites.\r\n" + 
				" */\r\n" + 
				"abstract participant Entity identified by entityKey {\r\n" + 
				"  o String entityKey\r\n" + 
				"}\r\n");
		
		for(Role r : roles.values()) {
			
			writer.print("/**\r\n" + 
					"  * A "+r.presentationName+" is a type of participant in the network.\r\n" + 
					" */\r\n" + 
					"participant "+r.camelCaseName+" extends Entity {\r\n"+
					"}\r\n");
			
		}
	}
	
	private void PrintAssets(PrintWriter writer) {
		writer.println("//Assets\r\n");
		writer.println("/** \r\n" + 
				" * Defines the product asset and its states which are: raw, cropped_raw, collected and packaged. These states are * represented as fields of the asset and the transactions (Crop, Collect, Package) change the state of the product.\r\n" + 
				"*/\r\n" + 
				"abstract asset Product identified by productId {\r\n" + 
				"  o String productId\r\n" + 
				"  o Integer quantity\r\n" + 
				"  o String measurementUnit\r\n" + 
				"}\r\n");

		
		// For all assets, find the tasks that output it, and all relevant performers
		List<String> printedEnums = new ArrayList<>();
		for(String key : assets.keySet()) {
			Asset a = assets.get(key);
			if(a.hasStates && printedEnums.contains(a.name)) {
				continue;
			} else if(a.hasStates) {
				printedEnums.add(a.name);
			}
			
			List<Role> assetAssociatedRoles = new ArrayList<>();
			for(Task t : tasks.values()) {
				if(t.output.equals(a.id)) {
					for(String id : t.performersID) {
						if(!assetAssociatedRoles.contains(roles.get(id)))
							assetAssociatedRoles.add(roles.get(id));
					}
				}
			}
			
			writer.print("/**\r\n" + 
					"  * "+a.presentationName+"\r\n" + 
					" */\r\n" + 
					"asset "+a.camelCaseName+" extends Product{\r\n");
			
			// attribute enums
			if(a.hasStates)
				writer.print("  o "+a.camelCaseName+"State "+a.pascalCaseName+"State\r\n"); 
			
			// associated roles
			for(Role r : assetAssociatedRoles) {
				writer.println("  --> "+r.camelCaseName+" "+r.pascalCaseName);
			}
			
			writer.print("}\r\n");
			}
	}
	
}