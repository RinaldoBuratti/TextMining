package it.uniroma3.agiw.path;

import java.io.File;
import java.nio.file.*;

import it.uniroma3.jsonWriter.JsonCreator;

public class PathMaker {

	public static String createPath(String fileName) {
		String path;
		boolean success;
		
		/* Verifica se la directory del nome già esiste, se non esiste al crea */
		String dir = fileName.substring(0,fileName.indexOf(".")-1);
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
		
		return fileName.substring(fileName.indexOf(".")-1);
	}
}