package se2203b.assignments.adminapp;

import java.sql.*;

public class ItemAdapter {

    // Declare connection
    Connection connection;

    // Creates the table
    public ItemAdapter(Connection connection, Boolean reset) throws SQLException{
        // Assign connection
        this.connection = connection;
        if(reset){
            Statement stmt = connection.createStatement();
            try {
                stmt.execute("DROP TABLE Products");
            } catch (SQLException ignored) {}
            finally {
                stmt.execute("CREATE TABLE Products " +
                        "(ID INT NOT NULL PRIMARY KEY, Name CHAR(30), " +
                        "Price CHAR(10), Size CHAR(5), NumberInStock CHAR(5), " +
                        "CatalogID INT NOT NULL REFERENCES Catalogs(ID) ON DELETE CASCADE )");
            }
        }
    }

    // Insert a new product item to the table
    public void insertItem(int ID, String name, double price, String sizeOfProduct, int numberInStock, int catalogID) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Products(ID, Name, Price, Size, NumberInstock, CatalogID)" +
                "VALUES ("+ ID +", '"+ name +"', '"+ price +"', '"+ sizeOfProduct +"','"+ numberInStock +"', "+ catalogID +")");
    }

    // Deletes a product item from the table
    public void deleteItem(int ID) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM Products WHERE ID = "+ ID);
    }

    // Update info of an item
    public void updateItem(int id, String name, double price, String sizeOfProduct, int numberInStock, int catalogID) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Products "
                + " SET Name = '"+ name +"', Price = '"+ price +"', Size = '"+ sizeOfProduct +"', NumberInStock = '"+ numberInStock +
                "', CatalogID =" + catalogID + " WHERE ID = "+ id);
    }

    // Look up relevant product using ID
    public Item searchItem(int ID) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
        // Get relevant fields then instantiate
        Item item = null;
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            while(ID == iD){
                item = new Item(ID,
                        resultSet.getString("Name"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Size"),
                        resultSet.getInt("NumberInStock"),
                        resultSet.getInt("CatalogID"));
                break;
            }
        }
        return item;
    }

    public boolean checkForItems(int catalogID) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");

        boolean found = false;
        while (resultSet.next()){
            int cID = resultSet.getInt("CatalogID");
            if(catalogID == cID){
                found = true;
                break;
            }
        }
        return found;
    }
}
