package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class CheckOutInfo {

    // Declare fields needed
    private final StringProperty deliveryStatus;
    private final BooleanProperty isPaid;

    // Constructor
    public CheckOutInfo(boolean isPaid, String deliveryStatus){
        // Assign to class field and instantiate properties
        this.isPaid = new SimpleBooleanProperty(isPaid);
        this.deliveryStatus = new SimpleStringProperty(deliveryStatus);
    }

    // Methods that return relevant properties
    public StringProperty deliveryStatusProperty() {
        return deliveryStatus;
    }

    public BooleanProperty isPaidProperty() {
        return isPaid;
    }

    // Settor method
    public void setIsPaid(boolean isPaid){
        this.isPaid.set(isPaid);
    }

    public void setDeliveryStatus(String deliveryStatus){
        this.deliveryStatus.set(deliveryStatus);
    }

    // Gettor methods
    public boolean isIsPaid() {
        return isPaid.get();
    }

    public String getDeliveryStatus() {
        return deliveryStatus.get().trim();
    }
}
