package it.uniroma3.agiw.reader;

import java.io.File;

public class FolderReader {
	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				DataReader.getDataFromJSON(fileEntry.getPath());
			}
		}
	}
}