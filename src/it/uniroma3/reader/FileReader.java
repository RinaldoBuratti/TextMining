package it.uniroma3.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class FileReader {

    public static ArrayList<String> getItalianNames() {

        File file = new File("ItalianNames.txt");
        ArrayList<String> arr = new ArrayList<String>();
        BufferedReader br = null;

        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            br = new BufferedReader(fr);

            String line;

            while( (line = br.readLine()) != null ) {
                arr.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unable to close file: " + file.toString());
            }
            catch(NullPointerException ex) {
            }
        }
		return arr;



    }

}