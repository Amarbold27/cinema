package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleIntegerProperty age;
    private SimpleBooleanProperty gender;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty email;
    User(String firstName,String lastName,int age,boolean gender ,String phoneNumber,String email){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
        this.gender = new SimpleBooleanProperty(gender);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
    }

    public int getAge() {
        return age.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
