package it.uniroma3.agiw.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLGetRankedNamedEntities {

	static String Api_key="59306558c68b0b4813cb84735f5cf657e361daf6";
	static String Url="https://it.wikipedia.org/wiki/Lorenzo_Valentini";


	public URLGetRankedNamedEntities(String url) {
		this.Url=url;
	}

	public static void main(String[] argc) {
		getEntitiesToJsonString();
	}


	public static String getEntitiesToJsonString() {

		String API_KEY = Api_key;
		String url = Url;
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