package it.uniroma3.agiw.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import it.uniroma3.agiw.reader.FolderReader;

public class URLGetRankedNamedEntities {

	static String Api_key="ENTER API KEY HERE";
	static File Folder = new File("ENTER PATH HERE");

	public static void main(String[] argc) {
		FolderReader.listFilesForFolder(Folder);
	}


	public static String getEntitiesToJsonString(String url) {

		String API_KEY = Api_key;
		String StringJson="";

		try{
			URL urlObj = new URL("http://access.alchemyapi.com/calls/url/URLGetRankedNamedEntities?apikey=" + API_KEY + "&url="+ url +"&outputMode=json");
			//System.out.println(urlObj.toString() + "\n");

			URLConnection connection = urlObj.openConnection();
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			StringBuilder StringBuilderJson = new StringBuilder();
			while ((line = reader.readLine()) !=  null) {
				StringBuilderJson.append(line + "\n");
			}

			StringJson= StringBuilderJson.toString();
		}catch(Exception e){
		}
		System.out.println(StringJson.toString());
		return StringJson;

	}

}