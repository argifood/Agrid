import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.json.JSONException;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args) {
		try {
			Converter cnr = new Converter();
			cnr.xml_reader("Farm.xml");
			cnr.convert();
			cnr.createJson();
			
			DAO farmTest = DAO.createFromJSON(new JSONObject(new String(Files.readAllBytes(Paths.get("Farm.json")))));
			System.out.println(farmTest.name);
			
			farmTest.printDAO();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

}
