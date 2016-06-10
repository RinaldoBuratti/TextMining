package it.uniroma3.pattern;

import java.util.regex.Pattern;

public class PatternMatching {
	public static void main(String[] args) {
		String regex = "^([+]?[0-9]{2,4})?( )?[0-9]{2,4}(/|-| |.|)[0-9]{6,10}$";
		String string = "06/56324998";
		
		if (Pattern.matches(regex,string))
			System.out.println("true");
		else
			System.out.println("false");
	}

}
