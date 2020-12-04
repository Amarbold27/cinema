package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie_data {
    private IntegerProperty Int1;
    private StringProperty String1;
    private StringProperty String2;
    private StringProperty String3;
    private StringProperty String4;
    public Movie_data(){
        this.Int1=new SimpleIntegerProperty();
        this.String1=new SimpleStringProperty();
        this.String2=new SimpleStringProperty();
        this.String3=new SimpleStringProperty();
        this.String4=new SimpleStringProperty();
    }

    public int getInt1() {
        return Int1.get();
    }

    public IntegerProperty int1Property() {
        return Int1;
    }

    public void setInt1(int int1) {
        this.Int1.set(int1);
    }

    public String getString4() {
        return String4.get();
    }

    public StringProperty string4Property() {
        return String4;
    }

    public void setString4(String string4) {
        this.String4.set(string4);
    }

    public String getString1() {
        return String1.get();
    }

    public StringProperty string1Property() {
        return String1;
    }

    public void setString1(String string1) {
        this.String1.set(string1);
    }

    public String getString2() {
        return String2.get();
    }

    public StringProperty string2Property() {
        return String2;
    }

    public void setString2(String string2) {
        this.String2.set(string2);
    }

    public String getString3() {
        return String3.get();
    }

    public StringProperty string3Property() {
        return String3;
    }

    public void setString3(String string3) {
        this.String3.set(string3);
    }
}
