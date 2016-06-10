package it.uniroma3.pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {
	public static HashMap<String, ArrayList<String>> match(String doc) {
		HashMap<String,ArrayList<String>> result = new HashMap<String,ArrayList<String>>();
		/* Regole per il pattern matching */
		HashMap<String,String> patterns = new HashMap<String,String>();
		patterns.put("telephone", "([+]?[0-9]{2,4})?( )?[0-9]{2,4}(/|-| |.|)[0-9]{6,10}");
		patterns.put("name", "[A-Z][a-z]+ [A-Z][a-z]+");
		patterns.put("email", "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
		
		/* Applico ogni regola al documento */
		for(String s: patterns.keySet()) {
			ArrayList<String> tmp = new ArrayList<String>();
			String regex = patterns.get(s);
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(doc);
			
			while (m.find()) {
				tmp.add(m.group());
			}
			result.put(s, tmp);
		}
		
		return result;
	};
	
/*	public static void main(String[] args) {
		String s = "06 9077225 Luca Avolio 06 9077225 martina@gmail.com 06 9077225 luca@uniroma3.it";
		System.out.println(match(s).toString());
	}*/


}
