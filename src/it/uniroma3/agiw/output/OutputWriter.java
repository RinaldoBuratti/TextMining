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
	
	public static void writeJSON(String url, String html) throws JSONException {
		
		JsonCreator jc = new JsonCreator(url);
		String jsonString = URLGetRankedNamedEntities.getEntitiesToJsonString(url);
		JSONObject jsonOutput = new JSONObject(jsonString);
		JSONArray entities = jsonOutput.getJSONArray("entities");
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();
		ArrayList<String> organization = new ArrayList<String>();
		for(int i = 0; i < entities.length(); i++) {
			if(entities.get(i) == "Person") {
				JSONObject temp = entities.getJSONObject(i);
				people.add(temp.getString("text"));
			}else if(entities.get(i) == "City" || entities.get(i) == "Country") {
				JSONObject temp = entities.getJSONObject(i);
				location.add(temp.getString("text"));
			}else if(entities.get(i) == "Organization") {
				JSONObject temp = entities.getJSONObject(i);
				organization.add(temp.getString("text"));
			}
		}
			
		HashMap<String, ArrayList<String>> information2data = PatternMatcher.match(html);
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> tel = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		
		jc.pushIntoNER(people, organization, location);	
		jc.pushIntoPATTERN(email, tel, names);
		System.out.println(information2data.toString());
		System.out.println("--");
		
	}
}
