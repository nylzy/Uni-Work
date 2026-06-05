class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public int withdraw(int amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Balance too low!");
        } else {
            balance -= amount;
            return balance;
        }
    }

    public void deposit(int amount) {
        if (amount <= 0){
            return balance;
        } else {
            balance += amount;
            return balance;
        }
}

public class InsufficientFundsException extends RuntimeException {

    private String message;

    public  InsufficientFundsException(String message) {
        super(mesasage);
    }
}