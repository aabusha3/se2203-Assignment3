package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Comment {

    // Declare properties
    private final StringProperty userComment;
    private final IntegerProperty ItemID;
    private final BooleanProperty isAnonymous, isVerified;

    // Constructor
    public Comment(String userComment, int itemID, boolean isAnonymous, boolean isVerified){
        // Set up the property using passed arguments
        this.userComment = new SimpleStringProperty(userComment);
        this.ItemID = new SimpleIntegerProperty(itemID);
        this.isAnonymous = new SimpleBooleanProperty(isAnonymous);
        this.isVerified = new SimpleBooleanProperty(isVerified);
    }

    // Methods that return relevant properties
    public StringProperty userCommentProperty() {
        return userComment;
    }

    public IntegerProperty itemIDProperty() {
        return ItemID;
    }

    public boolean isIsAnonymous() {
        return isAnonymous.get();
    }

    public boolean isIsVerified() {
        return isVerified.get();
    }

    // Settor methods
    public void setUserComment(String userComment){
        this.userComment.set(userComment);
    }

    public void setItemID(int itemID){
        this.ItemID.set(itemID);
    }

    public void setIsAnonymous(boolean isAnonymous){
        this.isAnonymous.set(isAnonymous);
    }

    public void setIsVerified(boolean isVerified){
        this.isVerified.set(isVerified);
    }

    // Gettor methods
    public String getUserComment() {
        return userComment.get().trim();
    }

    public int getItemID() {
        return ItemID.get();
    }

    public BooleanProperty isAnonymousProperty() {
        return isAnonymous;
    }

    public BooleanProperty isVerifiedProperty() {
        return isVerified;
    }
}
