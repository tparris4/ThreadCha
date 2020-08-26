package com.example.mypackage;

public class Main {

    public static void main(String[] args) {
        // write your code here

        final BankAccount ba = new BankAccount("12345-67", 1000.00);
       // (new Thread(new deposit(300.00))).start();

        Thread trThread1 = new Thread(new Runnable() {
            @Override
            public void run(){
                ba.deposit(300.00);
                ba.withdrawal(50.00);
                System.out.println("Thread 1");
            }
        });
        Thread trThread2 = new Thread(new Runnable() {
            @Override
            public void run(){
                ba.deposit(203.75);
                ba.withdrawal(100.00);
                System.out.println("Thread 2");
            }
        });

        trThread1.start();
        trThread2.start();
        ba.printAccountNumber();
        System.out.println(ba.toString());

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ba.deposit(300.00);
//                    t1.sleep(2000);
//                    ba.withdrawal(100.00);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    t1.join(2000);
//                    ba.deposit(350.00);
//                    t2.sleep(2000);
//                    ba.withdrawal(150.00);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();


    }

}