package it.uniroma3.agiw.pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniroma3.reader.FileReader;

public class PatternMatcher {
	public static HashMap<String, ArrayList<String>> match(String doc) {
		HashMap<String,ArrayList<String>> result = new HashMap<String,ArrayList<String>>();
		ArrayList<String> names = FileReader.getItalianNames();
		/* Regole per il pattern matching */
		HashMap<String,String> patterns = new HashMap<String,String>();
		patterns.put("telephone", "( )(([+][3][9])|([0][0][3][9]))?( )?[3|0][0-9]{1,3}(/|-| |.|)[0-9]{6,10}");
		patterns.put("name", "[A-Z][a-z]+ [A-Z][a-z]+");
		patterns.put("email", "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");

		/* Applico ogni regola al documento */
		for(String s: patterns.keySet()) {
			ArrayList<String> tmp = new ArrayList<String>();
			String regex = patterns.get(s);
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(doc);

			while (m.find()) {
				if(s.equals("name")) {						//controlla che il nome sia italiano
					String first = m.group().split(" ")[0];	//dalla oppia nome-cognome recupera solo il nome
					first = first.toLowerCase();
					if(names.contains(first)) {				// verifica se il nome � nella lista dei nomi italaiani
						tmp.add(m.group());
					}
				}					
				else 
					tmp.add(m.group());
			}
			result.put(s, tmp);
		}

		return result;
	};
}
