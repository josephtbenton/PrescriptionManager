/**
 * Created by kvgarimella on 11/6/15.
 */package PrescriptionManager;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DrugDataFetcher {
	private static int descriptionLine = 7;
	private static int startDescription = 36;
	private static int endDescription = 2;

    public static String getUrlContents(String drug) {
        ArrayList<String> urlContents = new ArrayList<String>();
        try {
            URL url = new URL("http://www.drugs.com/" + drug + ".html");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                urlContents.add(line);
            }
            bufferedReader.close();
            String description = urlContents.get(descriptionLine);
            description = description.substring(startDescription, description.length() - endDescription);
            return description;
        }
        catch(Exception e) {
            return "No info found for " + drug;
        }
    }
}
