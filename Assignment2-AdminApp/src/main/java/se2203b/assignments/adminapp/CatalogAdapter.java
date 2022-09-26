package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CatalogAdapter {

    // Declare Connection
    Connection connection;

    public CatalogAdapter(Connection conn, boolean reset) throws SQLException{
        // Assign connection
        this.connection = conn;
        if(reset){
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE Products");
                stmt.execute("DROP TABLE Catalogs");
            } catch (SQLException ignored) {}
            finally {
                stmt.execute("CREATE TABLE Catalogs " +
                        "(ID INT NOT NULL PRIMARY KEY, Name CHAR(30))");
            }
        }
    }

    // Insert a new Catalog item to the table
    public void insertCatalog(int iD, String catalogName) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Catalogs(ID, Name)" +
                "VALUES (" + iD + ", '" + catalogName + "')");
    }

    // Deletes a Catalog item from the table using ID
    public void deleteCatalog(int id) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Catalogs WHERE ID = "+ id);
    }

    // Update info of a Catalog item using ID
    public void updateCatalog(int id, String catalogName) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Catalogs "
                + " SET Name = '"+ catalogName +"' WHERE ID =" + id);
    }

    // Look up relevant Catalog using ID
    public Catalog searchCatalog(int ID) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Catalogs");
        // Get relevant fields then instantiate
        Catalog catalog = null;
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            while(ID == iD){
                catalog = new Catalog(iD, resultSet.getString("Name"));
                break;
            }
        }
        return catalog;
    }

    // Returns the list to populate the combo boxes
    public ObservableList<String> getCatalogsNameList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Catalogs");

        while(rs.next())
            list.add(rs.getInt("ID") + "-" + rs.getString("Name"));

        return list;
    }
}
