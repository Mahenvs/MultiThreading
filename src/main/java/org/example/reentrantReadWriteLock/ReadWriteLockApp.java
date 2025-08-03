package org.example.reentrantReadWriteLock;

public class ReadWriteLockApp {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        // Start multiple reader threads first (10 readers)
        for (int i = 0; i < 10; i++) {
            final String user = "reader-" + i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    bankAccount.viewBalance(user);
                }
            });
            t.start();
        }
        for (int i = 0; i < 4; i++) {
            String user ="writer-"+i;
            // Start one writer thread after short delay
            Thread writer = new Thread(new Runnable() {
                @Override
                public void run() {
                    bankAccount.withdraw(360, user);
                }
            });
            try {
                Thread.sleep(200); // Let some readers start first
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            writer.start();
        }
    }

}
