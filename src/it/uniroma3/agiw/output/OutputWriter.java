package it.uniroma3.agiw.output;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma3.agiw.main.URLGetRankedNamedEntities;
import it.uniroma3.agiw.pattern.PatternMatcher;
import it.uniroma3.jsonWriter.JsonCreator;

public class OutputWriter {

	public static void writeJSON(String url, String html, String outputPath) throws JSONException {

		JsonCreator jc = new JsonCreator(url);
		String jsonString = URLGetRankedNamedEntities.getEntitiesToJsonString(url);
		JSONObject jsonOutput = new JSONObject(jsonString);
		JSONArray entities = jsonOutput.getJSONArray("entities");
		
		/*salva i dati contenuti nel json in una mappa*/
		HashMap<String, ArrayList<String>> ner2data = new HashMap<String, ArrayList<String>>();
		for(int i = 0; i < entities.length(); i++) {
			JSONObject temp = entities.getJSONObject(i);
			if(!ner2data.containsKey(temp.get("type"))){
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(temp.getString("text"));
				ner2data.put(temp.getString("type"), tempList);
			}
			else {
				ner2data.get(temp.getString("type")).add(temp.getString("text"));
			}
		}
		
		/*salva i dati ritornati dal pattern matching in 3 liste*/
		HashMap<String, ArrayList<String>> information2data = PatternMatcher.match(html);
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> tel = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		for(String s : information2data.get("email")) {
			if(email.contains(s))
				email.add(s);
		}
		for(String s : information2data.get("telephone")) {
			if(!tel.contains(s))
				tel.add(s);
		}for(String s : information2data.get("name")) {
			if(!names.contains(s))
				names.add(s);
		}
		
		
		jc.pushMapIntoNER(ner2data);	
		jc.pushIntoPATTERN(email, tel, names);
		jc.write(outputPath);
	}
}
