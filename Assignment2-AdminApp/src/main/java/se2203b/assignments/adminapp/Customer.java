package se2203b.assignments.adminapp;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Customer extends Registration{

    // Declare fields needed
    private final LongProperty bankCardNum;
    private final IntegerProperty SIN;
    private final StringProperty billingAddress, deliveryAddress, secondLevelPassword;
    private final ListProperty<Comment> commentRecord = new SimpleListProperty<>();

    // Constructor
    public Customer(String userName, String password, String email, long phoneNumber, long bankCardNum, int SIN, String billingAddress, String deliveryAddress, String secondLevelPassword){
        // Refer from the parent class
        super(userName, password, email, phoneNumber);
        // Instantiate the properties
        this.bankCardNum = new SimpleLongProperty(bankCardNum);
        this.SIN = new SimpleIntegerProperty(SIN);
        this.billingAddress = new SimpleStringProperty(billingAddress);
        this.deliveryAddress = new SimpleStringProperty(deliveryAddress);
        this.secondLevelPassword = new SimpleStringProperty(secondLevelPassword);
    }

    // Methods that return relevant properties
    public LongProperty bankCardNumProperty() {
        return bankCardNum;
    }

    public IntegerProperty SINProperty() {
        return SIN;
    }

    public StringProperty billingAddressProperty() {
        return billingAddress;
    }

    public StringProperty deliveryAddressProperty() {
        return deliveryAddress;
    }

    public StringProperty secondLevelPasswordProperty() {
        return secondLevelPassword;
    }

    // Settor methods
    public void setBankCardNum(Long bankCardNum){
        this.bankCardNum.set(bankCardNum);
    }

    public void setSIN(int SIN){
        this.SIN.set(SIN);
    }

    public void setBillingAddress(String billingAddress){
        this.billingAddress.set(billingAddress);
    }

    public void setDeliveryAddress(String deliveryAddress){
        this.deliveryAddress.set(deliveryAddress);
    }

    public void setSecondLevelPassword(String secondLevelPassword){
        this.secondLevelPassword.set(secondLevelPassword);
    }

    // Gettor methods
    public long getBankCardNum() {
        return bankCardNum.get();
    }

    public int getSIN() {
        return SIN.get();
    }

    public String getBillingAddress() {
        return billingAddress.get().trim();
    }

    public String getDeliveryAddress() {
        return deliveryAddress.get().trim();
    }

    public String getSecondLevelPassword() {
        return secondLevelPassword.get().trim();
    }

    // Methods to add and get comment
    public void addComment(Comment comment){
        commentRecord.add(comment);
    }

    public ListProperty<Comment> getCommentList(){
        return commentRecord;
    }
}
