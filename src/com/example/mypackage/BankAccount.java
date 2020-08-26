package com.example.mypackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private String accountNumber;
    ReentrantLock bufferLock = new ReentrantLock();


    public BankAccount(String accountNumber, double balance) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bufferLock = bufferLock;
    }


    //    public synchronized void deposit(double amount){
//        balance += amount;
//    }
//
//    public synchronized void withdrawal(double amount){
//        balance -= amount;
//    }

    public void deposit(double amount){
        boolean status = false;

        //trylcok
        try{
            if(bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    balance += amount;
                } finally {
                    bufferLock.unlock();
                }
            }
            else{
                System.out.println("Could not get the lock");
            }
        }catch(InterruptedException e){
            //do something
        }

        System.out.println("Transaction status = " + status);
        //reentrant lock
//        bufferLock.lock();
//        try{
//
//
//       // synchronized (this) {
//            balance += amount;
//
//        }
//        finally{
//        bufferLock.unlock();
//
//        }
    }



    public void withdrawal(double amount){

        boolean status = false;
        //local variables are threadsafe because they are stored on the thread stack, so each will have its own copy
        //try lock
        try{
            if(bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    balance -= amount;
                    status = true;
                } finally {
                    bufferLock.unlock();
                }
            }
            else{
                System.out.println("Could not get the lock");
            }
        }catch(InterruptedException e){
            //do something
        }
        System.out.println("Transaction status =" + status);

//renetrant lock
//        bufferLock.lock();
//        try{
//
//
//            // synchronized (this) {
//            balance -= amount;
//
//        }
//        finally{
//            bufferLock.unlock();
//
//        }

        //synchronized block
//        synchronized (this) {
//            balance -= amount;
//        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber(){

            System.out.println("Account number = " + this.accountNumber);
        //dont have to synchronize code
        }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + this.balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
