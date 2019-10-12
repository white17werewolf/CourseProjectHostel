package model;


import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"FIO", "pas", "day","num", "price"})

public class Client {
    StringProperty FIO = new SimpleStringProperty("");
   IntegerProperty  pas = new SimpleIntegerProperty(0);
    IntegerProperty  day = new SimpleIntegerProperty(0);
    StringProperty  num = new SimpleStringProperty("");
    FloatProperty  price = new SimpleFloatProperty(0);


    public Client(){}


    public Client(String FIO, Integer pas, Integer day, String num, Float price) {
        this.FIO = new SimpleStringProperty(FIO);
        this.pas = new SimpleIntegerProperty(pas);
        this.day = new SimpleIntegerProperty(day);
        this.num = new SimpleStringProperty(num);
        this.price = new SimpleFloatProperty(price);
    }

    @XmlElement(name = "ФИО")
    public String getFIO() { return FIO.get(); }
    public StringProperty FIOProperty() {
        return FIO;
    }
    public void setFIO(String FIO) {
        this.FIO.set(FIO);
    }

    @XmlElement (name = "Паспорт")
    public int getPas() {
        return pas.get();
    }
    public IntegerProperty pasProperty() {
        return pas;
    }
    public void setPas(int pas) {
        this.pas.set(pas);
    }


    @XmlElement (name = "Дни")
    public int getDay() {
        return day.get();
    }
    public IntegerProperty DayProperty() {
        return day;
    }
    public void setDay(int day) {
        this.day.set(day);
    }

    @XmlElement (name = "Номер")
    public String getNum() {
        return num.get();
    }
    public StringProperty NumProperty() {
        return num;
    }
    public void setNum(String num) {
        this.num.set(num);
    }

    @XmlElement (name = "Цена")
    public float getPrice() {
        return price.get();
    }
    public FloatProperty priceProperty() {
        return price;
    }
    public void setPrice(float price) {
        this.price.set(price);
    }


}
