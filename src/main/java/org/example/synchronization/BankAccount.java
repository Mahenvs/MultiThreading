package org.example.synchronization;

public class BankAccount {
    private String AccountNumber;
    private  double Balance;

    public BankAccount(String accountNumber, double balance){
        this.AccountNumber = accountNumber;
        this.Balance = balance;
    }

    public synchronized double getBalance(){
        return this.Balance;
    }
    public synchronized void deposit(double amount){
        this.Balance += amount;
    }
    public synchronized void withDraw(double amount){
        if(this.Balance >= amount) {
            this.Balance -= amount;
        }else{
            System.out.println("InSufficient Funds");
        }
    }

//    This synchronizes only on the two involved accounts, so:
//    Thread A (acc1 → acc2) and Thread B (acc3 → acc4) can run at the same time
//    But two threads touching acc1 or acc2 will still block each other safely
//    Deadlocks are prevented using consistent lock ordering (sort by account number)
//      This is known as fine-grained locking, which balances safety and concurrency.
    public static void transfer(BankAccount fromAccount, BankAccount toAccount,double amount){
        BankAccount firstLock = fromAccount.AccountNumber.compareTo(toAccount.AccountNumber) < 0 ? fromAccount : toAccount;
        BankAccount secondLock = fromAccount != firstLock ? fromAccount : toAccount;
        synchronized (firstLock){
            synchronized (secondLock){
                fromAccount.withDraw(amount);
                toAccount.deposit(amount);
            }
        }
    }

}
