
package java_2_project;

import java.util.Date;

public class Paypal implements Payment{
    
    private final String paymentMethod = "Paypal";
    private String name;
    private double balance;
    private String email;
    private String phone;
    private java.util.Date accountDate;

    public Paypal(String name, String email) {
        this(0, name, email, "no phone exist!");
    }
    
    public Paypal(double balance, String name, String email){
        this(balance, name, email, "no phone exist!");
    }
    
    public Paypal(double balance, String name, String email, String phone) {
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accountDate = new java.util.Date();
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
