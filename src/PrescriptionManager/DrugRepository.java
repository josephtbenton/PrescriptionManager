package PrescriptionManager;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class DrugRepository {
    private ObservableList<String> drugList;


    //constructor creates a table if one does not exist else just initializes connection to sqlite
    public DrugRepository() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        this.drugList = FXCollections.observableArrayList();
        String create = "CREATE TABLE IF NOT EXISTS Inventory (Drug_ID INT, Name CHAR(255), Strength CHAR(255), Count INT)";
        stat.execute(create);
        con.close();
    }

    //updates the count of a given drug id
    public void updateCount(int ID, int update) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        int count = getCount(ID);
        count = count + update;
        String updateCount = "UPDATE Inventory SET Count=" + count + "WHERE Drug_ID=" + ID;
        stat.execute(updateCount);
        con.close();

    }

    //gets the count of a given drug id
    public int getCount(int ID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String counts = "SELECT Count FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(counts);
        ResultSet count = stat.getResultSet();
        int res = count.getInt(1);
        con.close();
        count.close();
        return res;
    }

    public String getStrength(int ID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String counts = "SELECT Strength FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(counts);
        ResultSet count = stat.getResultSet();
        String res = count.getString(1);
        con.close();
        count.close();
        return res;
    }

    //gets the string name of a certin drug id
    public String getDrug(int ID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String drug = "SELECT Drugs FROM Inventory WHERE Drug_ID=" + ID;
        stat.execute(drug);
        ResultSet drugs = stat.getResultSet();
        String res = drugs.getNString(1);
        con.close();
        drugs.close();
        return res;
    }

    //gets the drug id of a given drug name
    public int getDrugID(String name , String strength) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String drugID = "SELECT Drug_ID FROM Inventory WHERE Name = \'" + name + "\' AND Strength = \'" + strength + "\'" ;
        stat.execute(drugID);
        ResultSet result = stat.getResultSet();
        int res = result.getInt(1);
        con.close();
        result.close();
        return res;
    }

    //adds a drug to Inventory.. first part increments drug id
    public void addDrug(String name, String strength, int count) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        drugList.add(name + " - " + strength);
        String getLength = "SELECT count(*) AS length FROM Inventory";
        stat.execute(getLength);
        ResultSet Length = stat.getResultSet();
        int length = Length.getInt(1);
        length +=1;
        String insert = "INSERT INTO Inventory VALUES (" + length + ", \'" + name + "\', \'" + strength + "\', " + count + ")";
        stat.execute(insert);
        con.close();
        Length.close();
    }

    //removes a drug given a drug Id
    public void removeDrug(int ID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
        Statement stat = con.createStatement();
        String remove = "DELETE FROM Inventory WHERE Drug_ID=" +ID;
        stat.execute(remove);
        con.close();
    }


    //ONLY CALL THIS ONCE in the CONTROllER so far since it adds to the list... will fix
    public ObservableList<String> getDrugList() throws SQLException {
        if (drugList.isEmpty()) {
            Connection con = DriverManager.getConnection("jdbc:sqlite:prescriptions.db");
            Statement stat = con.createStatement();
            String dugs = "SELECT Name, Strength FROM Inventory";
            stat.execute(dugs);
            ResultSet drugs = stat.getResultSet();
            while (drugs.next()) {
                drugList.add(drugs.getString(1) + " - " + drugs.getString(2));
            }
            con.close();
            drugs.close();
        }
        return drugList;
    }



}