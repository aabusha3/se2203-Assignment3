package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Registration {

    // Declare property fields needed using the property class
    private final StringProperty userName, password, email;
    private final LongProperty phoneNumber;

    // Constructor which accepts relevant data types then set to the properties
    public Registration(String userName, String password, String email, long phoneNumber){
        // Set the properties
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleLongProperty(phoneNumber);
    }

    // Methods that return relevant properties
    public StringProperty userNameProperty() {
        return userName;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public LongProperty phoneNumberProperty() {
        return phoneNumber;
    }

    // Settor methods
    public void setUserName(String userName){
        this.userName.set(userName);
    }

    public void setPassword(String password){
        this.password.set(password);
    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public void setPhoneNumber(Long phoneNumber){
        this.phoneNumber.set(phoneNumber);
    }

    //Gettor methods
    public String getUserName() {
        return userName.get().trim();
    }

    public String getPassword() {
        return password.get().trim();
    }

    public String getEmail() {
        return email.get().trim();
    }

    public long getPhoneNumber() {
        return phoneNumber.get();
    }
}
