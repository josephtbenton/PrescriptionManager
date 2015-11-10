/**
 * Created by kvgarimella on 11/6/15.
 */package PrescriptionManager;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DrugDataFetcher {
    public static void main(String[] args)
    {
		// Try names such as atorvastatin, diazepam, lisinopril, naproxen, aspirin...
        Scanner scan = new Scanner(System.in);
        String input = null;
        while (input != "close") {
            input = scan.nextLine();
            System.out.println(getUrlContents(input));
        }
    }

    public static String getUrlContents(String drug) {
        ArrayList<String> what = new ArrayList<String>();
        try {
            URL url = new URL("http://www.drugs.com/" + drug + ".html");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                what.add(line);
            }
            bufferedReader.close();
            String description = what.get(7);
            description = description.substring(36, description.length() - 2);
            return description;
        }
        catch(Exception e) {
            return "No info found for " + drug;
        }
    }
}
