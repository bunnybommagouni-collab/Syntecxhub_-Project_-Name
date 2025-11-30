import java.util.Scanner;

class Account {
    private int pin;
    private double balance;

    public Account(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public boolean verifyPin(int inputPin) {
        return inputPin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        balance += amt;
    }

    public boolean withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }
}

class ATM {
    private Account account;
    private boolean loggedIn;

    public ATM(Account account) {
        this.account = account;
        this.loggedIn = false;
    }

    public boolean login(int pin) {
        if (account.verifyPin(pin)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public boolean deposit(double amt) {
        account.deposit(amt);
        return true;
    }

    public boolean withdraw(double amt) {
        return account.withdraw(amt);
    }
}

public class ATMSystemSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account account = new Account(1234, 5000);
        ATM atm = new ATM(account);

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (!atm.login(pin)) {
            System.out.println("Wrong PIN. Session blocked.");
            return;
        }

        while (atm.isLoggedIn()) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + atm.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double dep = sc.nextDouble();
                    atm.deposit(dep);
                    System.out.println("Deposited");
                    break;

                case 3:
                    System.out.print("Enter amount: ");
                    double wd = sc.nextDouble();
                    if (atm.withdraw(wd)) {
                        System.out.println("Withdrawn");
                    } else {
                        System.out.println("Insufficient funds");
                    }
                    break;

                case 4:
                    atm.logout();
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
