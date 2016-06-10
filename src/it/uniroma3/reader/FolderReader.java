package it.uniroma3.reader;

import java.io.File;

public class FolderReader {
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
}

/*final File folder = new File("/home/you/Desktop");
listFilesForFolder(folder);*/