package it.uniroma3.jsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.uniroma3.agiw.path.PathMaker;

public class JsonCreator {
	JSONObject obj;
	JSONObject ner;				//NER

	JSONObject pattern;			//Pattern
	JSONArray mail;				//Lista di email per il campo email del PATTERN
	JSONArray tel;				//Lista di telefoni per il campo tel del PATTERN
	JSONArray name;				//Lista di nomi per il campo name del PATTERN	

	public JsonCreator(String url) {
		this.obj = new JSONObject();
		this.ner = new JSONObject();
		this.pattern = new JSONObject();
		this.mail = new JSONArray();
		this.tel = new JSONArray();
		this.name = new JSONArray();
		this.obj.put("NER", ner);
		this.obj.put("PATTERN", pattern);
		this.obj.put("url", url);

		//Definizione oggetto PATTERN
		this.pattern.put("email", this.mail);
		this.pattern.put("tel", this.tel);
		this.pattern.put("name", this.name);
	};

	public String toString() {
		return this.obj.toJSONString();
	};

	/* Inserisco nel campo MAIL del PATTERN una lista di email */
	public boolean putEMAILInPattern(ArrayList<String> emails) {		
		if (this.mail.addAll(emails))
			return true;
		else
			return false;
	}

	/* Inserisco nel campo tel del PATTERN una lista di telefoni */
	public boolean putTELInPattern(ArrayList<String> tels) {		
		if (this.tel.addAll(tels))
			return true;
		else
			return false;
	}

	/* Inserisco nel campo MAIL del PATTERN una lista di email */
	public boolean putNAMEInPattern(ArrayList<String> names) {		
		if (this.name.addAll(names))
			return true;
		else
			return false;
	}

	/* Inserisco i valori nel PATTERN */
	public boolean pushIntoPATTERN(ArrayList<String> email, ArrayList<String> tel, ArrayList<String> names) {
		return this.putEMAILInPattern(email) || this.putTELInPattern(tel) || this.putNAMEInPattern(names);
	}

	/* Scrive il JSON in un file */
	public boolean write(String path) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			System.out.println("Salvato il documento " + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean pushMapIntoNER(HashMap<String,ArrayList<String>> map) {
		try {
			for(String s: map.keySet()) {			
				JSONArray atmp = new JSONArray();
				atmp.addAll(map.get(s));
				this.ner.put(s, atmp);
			
			}
		} catch (Exception e) {
			return false;
		};
		return true;
		
	}
}