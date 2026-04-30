public class BankAccount {
    private double balance;

    public BankAccount(double initialbalance) {
        this.balance = initialbalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit must not be negative or 0.");
        } else {
            balance = balance + amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
        }
    }
    
    public double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount(100.0);

        System.out.println(acc.getBalance());  // Expected: 100.0

        acc.deposit(50.0);
        System.out.println(acc.getBalance());  // Expected: 150.0

        acc.deposit(-20.0);                    // Expected: error message
        System.out.println(acc.getBalance());  // Expected: 150.0 (unchanged)

        acc.withdraw(30.0);
        System.out.println(acc.getBalance());  // Expected: 120.0

        acc.withdraw(200.0);                   // Expected: error message
        System.out.println(acc.getBalance());  // Expected: 120.0 (unchanged)
    }

}