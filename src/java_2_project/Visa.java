
package java_2_project;

import java.util.Date;

public class Visa implements Payment{
    
    private final String paymentMethod = "Visa";
    private long cardNumber;
    private double balance;
    private String name;
    private java.util.Date cardDate;
    
    public Visa(long cardNumber, String name) {
        this(0, cardNumber, name);
    }

    public Visa(double balance, long cardNumber, String name) {
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.name = name;
        this.cardDate = new java.util.Date();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public long getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public Date getCardDate() {
        return cardDate;
    }
    
    @Override
    public void deposite(double amount){
        this.balance += amount;
    }
    
    @Override
    public boolean withdraw(double amount){
        if (this.balance < amount)
            return false;
        
        this.balance -= amount;
        return true;
    }
}
