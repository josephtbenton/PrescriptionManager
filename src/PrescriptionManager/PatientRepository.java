package PrescriptionManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PatientRepository {
    private ObservableList<String> patientList;


    //constructor creates a table if one does not exist else just initializes connection to sqlite
    public PatientRepository() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        this.patientList = FXCollections.observableArrayList();
        String create = "CREATE TABLE IF NOT EXISTS patients (pid INT, firstname CHAR(255), lastname CHAR(255), dob CHAR(255))";
        stat.execute(create);
        con.close();
    }

    //gets the drug id of a given drug name
    public int getPID(String firstName, String lastName, String dob) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String drugID = "SELECT pid FROM patients WHERE Name = \'" + firstName + "\' AND lastname = \'" + lastName + "\' AND dob = \'" + dob + "\'";
        stat.execute(drugID);
        ResultSet drugid = stat.getResultSet();
        con.close();
        return drugid.getInt(1);
    }
    
    public String getDOB(int Pid) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String DOB = "SELECT dob FROM patients WHERE pid = " + Pid;
        stat.execute(DOB);
        ResultSet drugid = stat.getResultSet();
        con.close();
        return drugid.getString(1);
    }

    //adds a drug to Inventory.. first part increments drug id
    public void addPatient(String firstName, String lastName, String dob) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String lengthOf = "SELECT count(*) AS length FROM patients";
        stat.execute(lengthOf);
        ResultSet Length = stat.getResultSet();
        int length = Length.getInt(1);
        length +=1;
        String insert = "INSERT INTO patients VALUES (" + length + ", \'" + firstName + "\', \'" + lastName + "\', \'" + dob + "\')";
        stat.execute(insert);
        patientList.add(lastName + ", " + firstName + " (" + length + ")");
        con.close();
    }

    //removes a drug given a drug Id
    public void removePatient(int ID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String remove = "DELETE FROM patients WHERE pid=" + ID;
        stat.execute(remove);
        con.close();
    }


    //ONLY CALL THIS ONCE in the CONTROllER so far since it adds to the list... will fix
    public ObservableList<String> getPatientList() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
    	if (patientList.isEmpty()) {
	        String pats = "SELECT firstname, lastname, pid FROM patients";
	        stat.execute(pats);
	        ResultSet patients = stat.getResultSet();
	        while (patients.next()) {
	            patientList.add(patients.getString(2) +  ", " + patients.getString(1) + " (" + patients.getInt(3) + ")");
	        }
    	}
        con.close();
        return patientList;
    }



}
