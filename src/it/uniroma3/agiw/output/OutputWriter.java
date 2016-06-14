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
		ner2data.put("PER", new ArrayList<String>());
		ner2data.put("LOC", new ArrayList<String>());
		ner2data.put("ORG", new ArrayList<String>());
		ner2data.put("MONEY", new ArrayList<String>());
		ner2data.put("MISC", new ArrayList<String>());
		ner2data.put("DATE", new ArrayList<String>());
		ner2data.put("TIME", new ArrayList<String>());
		for(int i = 0; i < entities.length(); i++) {
			JSONObject temp = entities.getJSONObject(i);
			String category = temp.getString("type");
			if(category.equals("City") || category.equals("Continent") || category.equals("Country") || category.equals("Region") || category.equals("StateOrCounty")){
				ner2data.get("LOC").add(temp.getString("text"));
			}else if(category.equals("Company") || category.equals("Organization")){
				ner2data.get("ORG").add(temp.getString("text"));
			}else if(category.equals("Person")) {
				ner2data.get("PER").add(temp.getString("text"));
			}else if(category.equals("Money")) {
				ner2data.get("MONEY").add(temp.getString("text"));
			}else if(category.equals("Anniversary")) {
				ner2data.get("DATE").add(temp.getString("text"));
			}else
				ner2data.get("MISC").add(temp.getString("text"));		
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
