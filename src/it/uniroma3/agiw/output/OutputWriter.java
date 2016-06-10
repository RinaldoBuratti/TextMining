package it.uniroma3.agiw.output;

import it.uniroma3.agiw.main.URLGetRankedNamedEntities;

public class OutputWriter {
	
	public static void writeJSON(String url, String html){
		URLGetRankedNamedEntities.getEntitiesToJsonString(url);
	}
}
