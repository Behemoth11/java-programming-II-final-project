package lib.service.saving;

public class SavingAccount {

    public String id ;
    public double balance;
    public double interestRate;


    public SavingAccount setId(String id) {
        this.id = id;
        return this;
    }

    public SavingAccount setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    protected SavingAccount setBalance(double newBalance){
        this.balance = newBalance;
        return this;
    };

    public void deposit(double amount){
        assert amount >= 0;
        this.balance += amount;
    }

    public void withdraw(double amount){
        assert amount >= 0;
        this.balance-= amount;
    }

}
