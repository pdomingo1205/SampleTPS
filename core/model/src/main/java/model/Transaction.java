package model;

import java.time.LocalDate;

import javafx.beans.property.*;


public class Transaction {

    private LongProperty transactionId;
    private StringProperty type;
    private StringProperty status;
    private StringProperty salesRep;
    private DoubleProperty amount;
    private ObjectProperty<LocalDate> date;


    public Transaction() {
        this.transactionId = new SimpleLongProperty();
        this.type = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.salesRep = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public Transaction(Long transactionId, String type, String status, String salesRep, Double amount, LocalDate date) {
        this.transactionId = new SimpleLongProperty(transactionId);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.salesRep = new SimpleStringProperty(salesRep);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<LocalDate>(date);
    }
    public Transaction(Long transactionId) {
        this.transactionId = new SimpleLongProperty(transactionId);
        this.type = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.salesRep = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public Transaction(Long transactionId, String type, String status, String salesRep, Double amount) {
        this.transactionId = new SimpleLongProperty(transactionId);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.salesRep = new SimpleStringProperty(salesRep);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }


    public long getTransactionId() {
        return transactionId.get();
    }

    public LongProperty transactionIdProperty() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId.set(transactionId);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
            System.out.println("Transaction " + type);
           this.type.set(type);

    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getSalesRep() {
        return salesRep.get();
    }

    public StringProperty salesRepProperty() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep.set(salesRep);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
