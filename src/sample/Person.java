package sample;

public class Person {
    private int age;
    private String lastName;
    private String firstName;
    private boolean gender;
    private String phoneNumber;
    private String email;
    Person(String firstName,String lastName,int age,boolean gender ,String phoneNumber,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
