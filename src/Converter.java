import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.XML;

public class Converter {
	
final public static int PRINT_INDENT_FACTOR = 4;	
String data;
String file_name;
String json_content;
	
	public Converter(){
		data =""; }
	
	 public void xml_reader(String str){
		 String[] output = str.split("\\.");
		 file_name = output[0]+".json";
	    try {
			data = new String(Files.readAllBytes(Paths.get(str)));
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 }
	 
	 public void convert(){		 
		 JSONObject xmlJSONObj = XML.toJSONObject(data);
		 json_content = xmlJSONObj.toString(PRINT_INDENT_FACTOR);
         System.out.println(json_content);
	 }
	 
	 public void createJson(){
		 
		 PrintWriter writer;
			try {
				writer = new PrintWriter(file_name, "UTF-8");
				 writer.println(json_content);
		            writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
}
