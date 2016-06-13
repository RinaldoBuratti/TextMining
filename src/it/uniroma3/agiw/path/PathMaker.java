package it.uniroma3.agiw.path;

import java.io.File;
import java.nio.file.*;
import java.util.Arrays;

import it.uniroma3.jsonWriter.JsonCreator;

public class PathMaker {

	public static String createPath(String fileName) {
		String path;
		boolean success;

		/* Verifica se la directory del nome gi� esiste, se non esiste al crea */
		String dir = fileName.replace(getNumber(fileName)+".json", "");
		path = "docs/"+dir;
		File f = new File(path);
		if (!f.exists()) {
			success = new File(path).mkdir();
			if(success) 
				System.out.println("Creata cartella: "+path);
			else {
				System.out.println("Errore nella creazione della cartella!");
				return null;
			}
		};
		path += "/"+ createFileName(fileName);
		return path;
	}

	private static String createFileName(String fileName) {

		return getNumber(fileName)+".json";
	}

	private static String getNumber(String fileName) {   
		String str = fileName.replaceAll("[^-?0-9]+", ""); 
		return str;
	}
}