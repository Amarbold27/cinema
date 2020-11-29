package Admin_pane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Branch {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private StringProperty addressProperty;
    private StringProperty phoneNumberProperty;
    public Branch(){
        this.idProperty=new SimpleIntegerProperty();
        this.nameProperty=new SimpleStringProperty();
        this.addressProperty=new SimpleStringProperty();
        this.phoneNumberProperty=new SimpleStringProperty();
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public String getAddressProperty() {
        return addressProperty.get();
    }

    public StringProperty addressPropertyProperty() {
        return addressProperty;
    }

    public void setAddressProperty(String addressProperty) {
        this.addressProperty.set(addressProperty);
    }

    public String getPhoneNumberProperty() {
        return phoneNumberProperty.get();
    }

    public StringProperty phoneNumberPropertyProperty() {
        return phoneNumberProperty;
    }

    public void setPhoneNumberProperty(String phoneNumberProperty) {
        this.phoneNumberProperty.set(phoneNumberProperty);
    }
}
