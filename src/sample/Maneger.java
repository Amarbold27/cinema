package sample;

import javafx.beans.property.SimpleStringProperty;

public class Maneger extends Person {
    SimpleStringProperty staffPosition;
    Maneger(String firstName,String lastName,int age,boolean gender,String phoneNumber,String email, String staffPosition) {
        super(firstName,lastName, age, gender, phoneNumber, email);
        this.staffPosition = new SimpleStringProperty(staffPosition);
    }

    public String getStaffPosition() {
        return staffPosition.get();
    }

    public void setStaffPosition(String staffPosition) {
       this.staffPosition.set(staffPosition);
    }
}
