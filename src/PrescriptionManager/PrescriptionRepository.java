package PrescriptionManager;

import java.sql.*;

import javafx.collections.*;


public class PrescriptionRepository {
	private Statement stat;
	private Connection con;
	private ObservableList<String> prescriptionList;
	
	
	public PrescriptionRepository() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
		stat = con.createStatement();
		this.prescriptionList = FXCollections.observableArrayList();
		String create = "CREATE TABLE IF NOT EXISTS Prescriptions (pid INT, did INT, quantity INT)";
		stat.execute(create);
	}
	
	public void addPrescription(int pid, int did, int quantity) {
		String insert = "INSERT INTO Prescription VALUES (" + pid + ", " +  did + ", "  + quantity + ")";
	}
	
	public void removePresripction(int pid, int did) throws SQLException {
		String remove = "DELETE FROM Prescriptions WHERE (pid=" + pid + "AND did=" + did + ")";
		stat.execute(remove);
	}

}
