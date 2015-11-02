import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Inventory {
    private Statement stat;
    private Connection con;
    private ObservableList<String> drugList;


    //constructor creates a table if one does not exist else just initializes connection to sqlite
    public Inventory() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:testdb");
        stat = con.createStatement();
        this.drugList = FXCollections.observableArrayList();
        String create = "Create table Inventory (Drug_ID INT, Drugs CHAR(255), Strength CHAR(255), Count INT)";
        DatabaseMetaData base = con.getMetaData();
        ResultSet alreadyExists = base.getTablePrivileges(null, null, "Inventory");
        if (!alreadyExists.next()) {
            stat.execute(create);
        }
    }

    //updates the count of a given drug id
    public void updateCount(int ID, int update) throws SQLException {
        int count = getCount(ID);
        count = count + update;
        String updateCount = "Update Inventory set Count=" + count + "where Drug_ID=" + ID;
        stat.execute(updateCount);

    }

    //gets the count of a given drug id
    public int getCount(int ID) throws SQLException {
        String counts = "select Count from Inventory where Drug_ID=" + ID;
        stat.execute(counts);
        ResultSet count = stat.getResultSet();
        return count.getInt(1);
    }

    //gets the string name of a certin drug id
    public String getDrug(int ID) throws SQLException {
        String drug = "select Drugs from Inventory where Drug_ID=" + ID;
        stat.execute(drug);
        ResultSet drugs = stat.getResultSet();
        return drugs.getNString(1);
    }

    //gets the drug id of a given drug name
    public int getDrugID(String name) throws SQLException {
        String drugID = "select Drug_ID from Inventory where Drugs=" + name;
        stat.execute(drugID);
        ResultSet drugid = stat.getResultSet();
        return drugid.getInt(1);
    }

    //adds a drug to Inventory.. first part increments drug id
    public void addDrug(String name, String strength, int count) throws SQLException{
        String getLength = "select count(*) as length from Inventory";
        stat.execute(getLength);
        ResultSet Length = stat.getResultSet();
        int length = Length.getInt(1);
        length +=1;
        String insert = "insert into Inventory (Drug_ID, Drugs, Strength, Count) values (" + length + ", " + name + ", " + strength + ", " + count + ")";
        stat.execute(insert);
    }

    //removes a drug given a drug Id
    public void removeDrug(int ID) throws SQLException {
        String remove = "delete from Inventory where Drug_ID=" +ID;
        stat.execute(remove);
    }

    //ONLY CALL THIS ONCE in the CONTROller so far since it adds to the list... will fix
    public ObservableList<String> getDrugList() throws SQLException {
        String dugs = "select Drugs from Inventory";
        stat.execute(dugs);
        ResultSet drugs = stat.getResultSet();
        while (drugs.next()) {
            drugList.add(drugs.getString(1));
        }

        return drugList;
    }



}