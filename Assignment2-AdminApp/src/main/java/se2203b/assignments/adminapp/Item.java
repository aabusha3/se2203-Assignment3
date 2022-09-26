package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Item {
    // Declare fields needed
    private final StringProperty name, size;
    private final DoubleProperty price;
    private final IntegerProperty numberInStock, ID, catalogID;
    private final ListProperty<Comment> verifiedComments = new SimpleListProperty<>();

    // Constructor
    public Item(int ID, String name, double price, String size, int numberInStock, int catalogID){
        // Instantiate the properties with passed arguments
        this.name = new SimpleStringProperty(name);
        this.ID = new SimpleIntegerProperty(ID);
        this.price = new SimpleDoubleProperty(price);
        this.size = new SimpleStringProperty(size);
        this.numberInStock = new SimpleIntegerProperty(numberInStock);
        this.catalogID = new SimpleIntegerProperty(catalogID);
    }

    // Methods that return relevant properties
    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public IntegerProperty numberInStockProperty() {
        return numberInStock;
    }

    public IntegerProperty catalogIDProperty() {
        return catalogID;
    }

    // Settor methods
    public void setName(String name){
        this.name.set(name);
    }

    public void setID(int ID){
        this.ID.set(ID);
    }

    public void setPrice(double price){
        this.price.set(price);
    }

    public void setSize(String size){
        this.size.set(size);
    }

    public void setNumberInStock(int numberInStock){
        this.numberInStock.set(numberInStock);
    }

    // Gettor methods
    public String getName() {
        return name.get().trim();
    }

    public int getID() {
        return ID.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getSize() {
        return size.get().trim();
    }

    public int getNumberInStock() {
        return numberInStock.get();
    }

    public int getCatalogID() {
        return catalogID.get();
    }

    // Method that adds verified comments from a group of customers
    public void addVerifiedComments(Customer[] customers){
        for(Customer customer : customers)// For each customer

            for (Comment Comment : customer.getCommentList())// For each comment from their comment list

                if((Comment.getItemID() == this.getID()) && (Comment.isIsVerified())) // If the id matches, and the comment is verified
                    verifiedComments.add(Comment);
    }

    // Method that returns the verified comment list
    public ListProperty<Comment> getVerifiedComments(){
        return verifiedComments;
    }
}
