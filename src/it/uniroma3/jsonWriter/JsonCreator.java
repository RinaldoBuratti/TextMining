package it.uniroma3.jsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.uniroma3.agiw.path.PathMaker;

public class JsonCreator {
	JSONObject obj;
	JSONObject ner;				//NER
	JSONArray per;				//Lista di nomi per il campo PER del NER
	JSONArray org;				//Lista di org per il campo ORG del NER
	JSONArray loc;				//Lista di luoghi per il campo LOC del NER
	
	JSONObject pattern;			//Pattern
	JSONArray mail;				//Lista di email per il campo email del PATTERN
	JSONArray tel;				//Lista di telefoni per il campo tel del PATTERN
	JSONArray name;				//Lista di nomi per il campo name del PATTERN	
	
	public JsonCreator(String url) {
		this.obj = new JSONObject();
		this.ner = new JSONObject();
		this.pattern = new JSONObject();
		this.per = new JSONArray();
		this.org = new JSONArray();
		this.loc = new JSONArray();
		this.mail = new JSONArray();
		this.tel = new JSONArray();
		this.name = new JSONArray();
		this.obj.put("NER", ner);
		this.obj.put("PATTERN", pattern);
		this.obj.put("url", url);
		
		//Definizione oggetto NER;
		this.ner.put("PER", this.per);
		this.ner.put("ORG", this.org);
		this.ner.put("LOC", this.loc);
		
		//Definizione oggetto PATTERN
		this.pattern.put("email", this.mail);
		this.pattern.put("tel", this.tel);
		this.pattern.put("name", this.name);
	};
	
	public String toString() {
		return this.obj.toJSONString();
	};
	
	/* Inserisco nel campo PER del NER una lista di PERSONE */
	public boolean putPERInNER(ArrayList<String> persons) {		
		if (this.per.addAll(persons))
			return true;
		else
			return false;
	};

	/* Inserisco nel campo ORG del NER una lista di ORGANIZZAZIONI */
	public boolean putORGInNER(ArrayList<String> orgs) {		
		if (this.org.addAll(orgs))
			return true;
		else
			return false;
	}

	/* Inserisco nel campo LOC del NER una lista di LUOGHI */
	public boolean putLOCInNER(ArrayList<String> locs) {		
		if (this.loc.addAll(locs))
			return true;
		else
			return false;
	}
	
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

	/* Inserisco i valori nel NER */
	public boolean pushIntoNER(ArrayList<String> per, ArrayList<String> org, ArrayList<String> loc) {
		return this.putPERInNER(per) && this.putORGInNER(org) && this.putLOCInNER(loc);
	}
	
	/* Inserisco i valori nel PATTERN */
	public boolean pushIntoPATTERN(ArrayList<String> email, ArrayList<String> tel, ArrayList<String> names) {
		return this.putEMAILInPattern(email) && this.putTELInPattern(tel) && this.putNAMEInPattern(names);
	}

	/* Scrive il JSON in un file */
	public boolean write(String path) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/* Prova
	public static void main(String[] args){
		JsonCreator json = new JsonCreator("www.google.it");
		ArrayList<String> per = new ArrayList<String>();
		ArrayList<String> org = new ArrayList<String>();
		ArrayList<String> loc = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> tel = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		per.add("Emilio");
		per.add("Gianni");
		org.add("FCA");
		org.add("FIAT");
		loc.add("Rome");
		loc.add("Lazio");
		email.add("luc@tud.it");
		email.add("1@2.it");
		tel.add("99968564");
		names.add("Luca");
		names.add("giovanni");
		
		json.pushIntoNER(per, org, loc);
		json.pushIntoPATTERN(email, tel, names);
		System.out.println(json.toString());
		String path = PathMaker.createPath("adorno_corradini1.json");
		json.write(path);
	} */
}
