import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        } else {
            System.out.println("Invalid amount for deposit.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Invalid amount for withdrawal or insufficient funds.");
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Quit");
    }

    public void performTransaction(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = scanner.nextDouble();
                if (userAccount.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. Remaining balance: " + userAccount.checkBalance());
                } else {
                    System.out.println("Withdrawal failed.");
                }
                break;

            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                if (userAccount.deposit(depositAmount)) {
                    System.out.println("Deposit successful. New balance: " + userAccount.checkBalance());
                } else {
                    System.out.println("Deposit failed.");
                }
                break;

            case 3:
                System.out.println("Current balance: " + userAccount.checkBalance());
                break;

            case 4:
                System.out.println("Exiting ATM. Thank you!");
                break;

            default:
                System.out.println("Invalid option. Please select a valid option.");
        }
    }

    public void runATM() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            System.out.print("Enter your choice (1-4): ");
            int option = scanner.nextInt();

            if (option == 4) {
                break;
            }

            performTransaction(option);
        }
    }
}


public class AtmInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000);  // Initial balance is 1000
        ATM atm = new ATM(userAccount);
        atm.runATM();
    }
}
