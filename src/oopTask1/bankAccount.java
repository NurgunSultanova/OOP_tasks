package oopTask1;

import java.util.ArrayList;

public class bankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private ArrayList<String> transactionHistory;
    private double interestRate;
    private int wrongAttemps;

    public bankAccount(String accountHolder,String accountNumber,int pin){
        this.accountHolder=accountHolder;
        this.accountNumber=accountNumber;
        this.pin=pin;
        this.balance=0.0;
        this.isLocked=false;
        this.wrongAttemps=0;
        this.transactionHistory=new ArrayList<>();
        }

        public void lockAccount() {
            if (isLocked) {
                System.out.println(" Xeberdarliq: 3 Defe yanlis sifre daxil edildi!");
            }
        }

        public void deposit(double amount) {
            if (amount < 0) {
                System.out.println("Xeberdarliq: menfi mebleg gebul edilmir!");
            } else {
                balance += amount;
                transactionHistory.add("Depozit: +" + amount);
            }
        }

        public boolean withDraw(double amount,int enteredPin) {
            if (isLocked) {
                System.out.println("Hesab bloklanib!");
                return false;
            }
            if (enteredPin == this.pin) {
                wrongAttemps = 0;

                if (amount > balance) {
                    System.out.println("Balans kifayet etmir!");
                    return false;
                } else {
                    balance -= amount;
                    transactionHistory.add("Withdraw: -" + amount);
                    return true;
                }
            } else {
                wrongAttemps++;
                System.out.println("Yanlis Pin!");
                if (wrongAttemps >= 3) {
                    lockAccount();
                }
                return false;
            }
        }
        public void checkBalance(int enteredPin) {
            if (enteredPin == this.pin) {
                System.out.println("Balans: " + balance);
            } else {
                System.out.println("Yanlis pin!");
            }
        }
        public void transferTo(bankAccount receiver,double amount,int enteredPin) {
            if (isLocked) {
                System.out.println("Hesab bloklanib.");
            } else {
                boolean success = this.withDraw(amount, enteredPin);
                if (success) {
                    receiver.deposit(amount);
                    transactionHistory.add("Transfer to " + receiver.accountHolder + ": -" + amount);
                    System.out.println("Kocurme ugurlu oldu.");
                } else {
                    System.out.println("Kocurme ugursuz oldu.");
                }
            }
        }
        public void applyInterest() {
        if (isLocked){
            System.out.println("Hesab bloklanib!");
        } else {
            balance +=balance*interestRate/100;
            System.out.println("Faiz hesablandiqdan sonra balans: "+balance);
        }
    }
    public void printHistory() {
        System.out.println(" Emeliyyat tarixcesi");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
