package it.uniroma3.reader;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.uniroma3.agiw.output.OutputWriter;

public class DataReader {
	
	public static void getDataFromJSON(String path){
	
	JSONParser parser = new JSONParser();
	 
    try {

        Object obj = parser.parse(new FileReader(path));

        JSONObject jsonObject = (JSONObject) obj;

        String url = (String) jsonObject.get("url");
        String html = (String) jsonObject.get("html");
        OutputWriter.writeJSON(url, html);
    
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
