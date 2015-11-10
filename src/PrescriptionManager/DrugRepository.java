package PrescriptionManager;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class DrugRepository {
    private Statement stat;
    private Connection con;
    private ObservableList<String> drugList;


    //constructor creates a table if one does not exist else just initializes connection to sqlite
    public DrugRepository() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        stat = con.createStatement();
        this.drugList = FXCollections.observableArrayList();
        String create = "CREATE TABLE IF NOT EXISTS Inventory (Drug_ID INT, Name CHAR(255), Strength CHAR(255), Count INT)";
        stat.execute(create);
    }

    //updates the count of a given drug id
    public void updateCount(int ID, int update) throws SQLException {
        int count = getCount(ID);
        count = count + update;
        String updateCount = "UPDATE Inventory SET Count=" + count + "WHERE Drug_ID=" + ID;
        stat.execute(updateCount);

    }

    //gets the count of a given drug id
    public int getCount(int ID) throws SQLException {
        String counts = "SELECT Count FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(counts);
        ResultSet count = stat.getResultSet();
        return count.getInt(1);
    }

    public String getStrength(int ID) throws SQLException {
        String counts = "SELECT Strength FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(counts);
        ResultSet count = stat.getResultSet();
        return count.getString(1);
    }

    //gets the string name of a certin drug id
    public String getDrug(int ID) throws SQLException {
        String drug = "SELECT Drugs FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(drug);
        ResultSet drugs = stat.getResultSet();
        return drugs.getNString(1);
    }

    //gets the drug id of a given drug name
    public int getDrugID(String name , String strength) throws SQLException {
        String drugID = "SELECT Drug_ID FROM Inventory WHERE Name = \'" + name + "\' AND Strength = \'" + strength + "\'" ;
        stat.execute(drugID);
        ResultSet result = stat.getResultSet();
        return result.getInt(1);
    }

    //adds a drug to Inventory.. first part increments drug id
    public void addDrug(String name, String strength, int count) throws SQLException{
        drugList.add(name + " - " + strength);
        String getLength = "SELECT count(*) AS length FROM Inventory";
        stat.execute(getLength);
        ResultSet Length = stat.getResultSet();
        int length = Length.getInt(1);
        length +=1;
        String insert = "INSERT INTO Inventory VALUES (" + length + ", \'" + name + "\', \'" + strength + "\', " + count + ")";
        stat.execute(insert);
    }

    //removes a drug given a drug Id
    public void removeDrug(int ID) throws SQLException {
        String remove = "DELETE FROM Inventory WHERE Drug_ID=" +ID;
        stat.execute(remove);
    }


    //ONLY CALL THIS ONCE in the CONTROllER so far since it adds to the list... will fix
    public ObservableList<String> getDrugList() throws SQLException {
        String dugs = "SELECT Name, Strength FROM Inventory";
        stat.execute(dugs);
        ResultSet drugs = stat.getResultSet();
        while (drugs.next()) {
            drugList.add(drugs.getString(1) + " - " + drugs.getString(2));
        }

        return drugList;
    }



}