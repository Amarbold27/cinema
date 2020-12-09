package Admin_pane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * enehvv class ni observable torliin ogogdliig bii bolgohod
 * enehvv classiin torliin object vvsgehed
 * */
public class Data {
    private IntegerProperty Int1;
    private IntegerProperty Int2;
    private IntegerProperty Int3;
    private StringProperty String1;
    private StringProperty String2;
    private StringProperty String3;
    private StringProperty String4;
    private StringProperty String5;
    private StringProperty String6;
    private StringProperty String7;
    private StringProperty String8;
    private StringProperty String9;
    private StringProperty String10;
    public Data(){
        this.Int1=new SimpleIntegerProperty();
        this.Int2=new SimpleIntegerProperty();
        this.Int3=new SimpleIntegerProperty();
        this.String1=new SimpleStringProperty();
        this.String2=new SimpleStringProperty();
        this.String3=new SimpleStringProperty();
        this.String4=new SimpleStringProperty();
        this.String5=new SimpleStringProperty();
        this.String6=new SimpleStringProperty();
        this.String7=new SimpleStringProperty();
        this.String8=new SimpleStringProperty();
        this.String9=new SimpleStringProperty();
        this.String10=new SimpleStringProperty();
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

    public int getInt2() {
        return Int2.get();
    }

    public IntegerProperty int2Property() {
        return Int2;
    }

    public void setInt2(int int2) {
        this.Int2.set(int2);
    }

    public int getInt3() {
        return Int3.get();
    }

    public IntegerProperty int3Property() {
        return Int3;
    }

    public void setInt3(int int3) {
        this.Int3.set(int3);
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

    public String getString4() {
        return String4.get();
    }

    public StringProperty string4Property() {
        return String4;
    }

    public void setString4(String string4) {
        this.String4.set(string4);
    }

    public String getString5() {
        return String5.get();
    }

    public StringProperty string5Property() {
        return String5;
    }

    public void setString5(String string5) {
        this.String5.set(string5);
    }

    public String getString6() {
        return String6.get();
    }

    public StringProperty string6Property() {
        return String6;
    }

    public void setString6(String string6) {
        this.String6.set(string6);
    }

    public String getString7() {
        return String7.get();
    }

    public StringProperty string7Property() {
        return String7;
    }

    public void setString7(String string7) {
        this.String7.set(string7);
    }

    public String getString8() {
        return String8.get();
    }

    public StringProperty string8Property() {
        return String8;
    }

    public void setString8(String string8) {
        this.String8.set(string8);
    }

    public String getString9() {
        return String9.get();
    }

    public StringProperty string9Property() {
        return String9;
    }

    public void setString9(String string9) {
        this.String9.set(string9);
    }

    public String getString10() {
        return String10.get();
    }

    public StringProperty string10Property() {
        return String10;
    }

    public void setString10(String string10) {
        this.String10.set(string10);
    }
}
