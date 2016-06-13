package it.uniroma3.agiw.output;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma3.agiw.main.URLGetRankedNamedEntities;
import it.uniroma3.agiw.path.PathMaker;
import it.uniroma3.agiw.pattern.PatternMatcher;
import it.uniroma3.jsonWriter.JsonCreator;

public class OutputWriter {

	public static void writeJSON(String url, String html, String outputPath) throws JSONException {

		JsonCreator jc = new JsonCreator(url);
		String jsonString = URLGetRankedNamedEntities.getEntitiesToJsonString(url);
		JSONObject jsonOutput = new JSONObject(jsonString);
		JSONArray entities = jsonOutput.getJSONArray("entities");
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> location = new ArrayList<String>();
		ArrayList<String> organization = new ArrayList<String>();
		for(int i = 0; i < entities.length(); i++) {
			JSONObject temp = entities.getJSONObject(i);
			if(temp.getString("type").equals("Person")) {
				JSONObject temp2 = entities.getJSONObject(i);
				people.add(temp2.getString("text"));
			}else if(temp.getString("type").equals("City") || temp.getString("type").equals("Country")) {
				JSONObject temp2 = entities.getJSONObject(i);
				location.add(temp2.getString("text"));
			}else if(temp.getString("type").equals("Organization")) {
				JSONObject temp2 = entities.getJSONObject(i);
				organization.add(temp2.getString("text"));
			}
		}

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
		
		jc.pushIntoNER(people, organization, location);	
		jc.pushIntoPATTERN(email, tel, names);
		jc.write(outputPath);
	}
}
