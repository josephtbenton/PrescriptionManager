package PrescriptionManager;

import java.sql.*;

import javafx.collections.*;


public class PrescriptionRepository {
	private Statement stat;
	private Connection con;
	private ObservableList<String> prescriptionrepo;
	
	
	public PrescriptionRepository() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:prescriptionDB");
		stat = con.createStatement();
		this.prescriptionrepo = FXCollections.observableArrayList();
		String create = "create table if not exists Prescriptions (pid INT, did INT, quantity INT)";
		stat.execute(create);
	}
	
	public void addPrescription(int pid, int did, int quantity) {
		String insert = "insert into Prescription values (" + pid + ", " +  did + ", "  + quantity + ")";
	}
	
	public void removePresripction(int pid, int did) throws SQLException {
		String remove = "delete from Prescriptions where (pid=" + pid + "and did=" + did + ")";
		stat.execute(remove);
	}
	

}
