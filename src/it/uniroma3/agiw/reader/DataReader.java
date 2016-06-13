package it.uniroma3.agiw.reader;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.uniroma3.agiw.output.OutputWriter;
import it.uniroma3.agiw.path.PathMaker;

public class DataReader {

	public static void getDataFromJSON(String path){

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			String url = (String) jsonObject.get("url");
			String html = (String) jsonObject.get("html");
			int subIndex = path.lastIndexOf("/");
			String fileName = path.substring(subIndex+1, path.length());
			String outputPath = PathMaker.createPath(fileName);
			OutputWriter.writeJSON(url, html, outputPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
