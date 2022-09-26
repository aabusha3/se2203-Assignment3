package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Catalog {
    // Declare properties needed
    private final StringProperty CatalogName;
    private final IntegerProperty ID;

    // Constructor
    public Catalog(int ID, String catalogName){
        this.ID = new SimpleIntegerProperty(ID);
        CatalogName = new SimpleStringProperty(catalogName);
    }

    // Gettor methods
    public String getCatalogName() {
        return CatalogName.get().trim();
    }
    public int getID() {
        return ID.get();
    }

    // Settor methods
    public void setCatalogName(String name){
        this.CatalogName.set(name);
    }
    public void setID(int ID){
        this.ID.set(ID);
    }

    // Property return methods
    public StringProperty catalogNameProperty() {
        return CatalogName;
    }

    public IntegerProperty IDProperty() {
        return ID;
    }


}
