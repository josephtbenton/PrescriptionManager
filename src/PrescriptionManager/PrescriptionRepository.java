package PrescriptionManager;

import java.sql.*;

import javafx.collections.*;


public class PrescriptionRepository {

	
	public PrescriptionRepository() throws ClassNotFoundException, SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
		Class.forName("org.sqlite.JDBC");
		String create = "CREATE TABLE IF NOT EXISTS Prescription (pid INT, did INT, quantity INT)";
		stat.execute(create);
        con.close();
	}
	
	public void addPrescription(int pid, int did, int quantity) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        stat.execute("SELECT Name, Strength FROM Inventory WHERE Drug_ID = " + did);
        ResultSet nameAndStrength = stat.getResultSet();
        String drugName = nameAndStrength.getString(1);
        String strength = nameAndStrength.getString(2);
        nameAndStrength.close();
        stat.execute("UPDATE Inventory SET Count = Count - " + quantity + " WHERE Drug_ID = " + did);
        String insert = "INSERT INTO Prescription VALUES (" + pid + ", " +  did + ", "  + quantity + ")";
        stat.execute(insert);
        con.close();
	}
	
	public void removePresripction(int pid, int did) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String remove = "DELETE FROM Prescription WHERE (pid=" + pid + " AND did=" + did + ")";
		stat.execute(remove);
	}

    public ObservableList<String> getPrescriptionList(String pid) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        ObservableList<String> prescriptionList = FXCollections.observableArrayList();
        stat.execute("SELECT Name, Strength, quantity FROM Inventory JOIN Prescription ON Inventory.Drug_ID = Prescription.did WHERE Prescription.pid = " + pid);
        ResultSet results = stat.getResultSet();
        while (results.next()) {
            prescriptionList.add(results.getString(1) + " | " + results.getString(2) + " | " + results.getString(3));
        }
        return prescriptionList;
    }
}
