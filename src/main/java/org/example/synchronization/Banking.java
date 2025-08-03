package org.example.synchronization;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Banking {
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        BankAccount acc1 = new BankAccount("ACC1",1000);
        BankAccount acc2 = new BankAccount("ACC2",1000);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++) {
                    BankAccount.transfer(acc1, acc2, 1);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++) {
                    BankAccount.transfer(acc2, acc1, 1);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Hello and welcome!"+acc1.getBalance());
        System.out.println("Hello and welcome!"+acc2.getBalance());

    }
}