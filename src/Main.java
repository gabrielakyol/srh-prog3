import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (true) {
            System.out.println("Please choose one of the following options:");
            System.out.println("1: Create account");
            System.out.println("2: Close account");
            System.out.println("3: Set overdraft limit");
            System.out.println("4: Retrieve account");
            System.out.println("5: List accounts by branch");
            System.out.println("6: Insert credit or debit entries");
            System.out.println("7: Transfer amount");
            System.out.println("8: Withdraw");
            System.out.println("9: List all debit and credit entries");
            System.out.println("10: Print account statement");
            System.out.println("0: Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create account
                    System.out.println("--- Create account ---");
                    Integer accountNumber = accounts.size();
                    System.out.print("Please enter the branch code: ");
                    String branchCode = scanner.next();
                    System.out.print("Please enter the overdraft limit in €: ");
                    double overdraftLimit = scanner.nextDouble();

                    Account account = new Account(accountNumber, branchCode, overdraftLimit);
                    accounts.add(account);
                    System.out.println("--- Account created successfully. Account number: " + account.getAccountNumber() + " ---");
                    break;
                case 2:
                    // Close account (account total must be 0)
                    System.out.println("--- Close account ---");
                    System.out.print("Please enter the account number: ");
                    Integer accountNumberToClose = scanner.nextInt();
                    int accountsFound = 0;
                    for (Account a : accounts) {
                        if (a.getAccountNumber().equals(accountNumberToClose)) {
                            accountsFound += 1;
                            if (a.getBalance() > 0) {
                                System.out.println("Account balance is not 0. Please withdraw the remaining amount first.");
                                break;
                            } else if (a.getBalance() < 0) {
                                System.out.println("Account balance is negative. Please deposit the remaining amount first.");
                                break;
                            } else {
                                accounts.remove(a);
                                System.out.println("Found account ID: " + a.getAccountNumber() + " to close.");
                                break;
                            }
                        }
                    }
                    if (accountsFound == 0) {
                        System.out.println("--- Account not found. ---");
                    }
                    break;
                case 3:
                    // Possibility to have a preset maximum overdraft (account in the negative)
                    System.out.println("--- Set overdraft limit ---");
                    System.out.print("Please enter the account number: ");
                    Integer accountNumberToSetOverdraftLimit = scanner.nextInt();
                    int accountsFound2 = 0;
                    for (Account a : accounts) {
                        if (a.getAccountNumber().equals(accountNumberToSetOverdraftLimit)) {
                            accountsFound2 += 1;
                            System.out.println("Found account ID: " + a.getAccountNumber() + " to set overdraft limit.");
                            System.out.print("Please enter the overdraft limit in €: ");
                            double overdraftLimitToSet = scanner.nextDouble();
                            a.setOverdraftLimit(overdraftLimitToSet);
                            System.out.println("Overdraft limit set successfully.");
                            break;
                        }
                    }
                    if (accountsFound2 == 0) {
                        System.out.println("--- Account not found. ---");
                    }
                    break;
                case 4:
                    // Accounts be retrieved by account number and branch code
                    System.out.println("--- Retrieve account ---");
                    System.out.print("Please enter the account number: ");
                    Integer accountNumberToRetrieve = scanner.nextInt();
                    System.out.println("Please enter the branch code: ");
                    String branchCodeToRetrieve = scanner.next();
                    Account accountToRetrieve = null;
                    for (Account a : accounts) {
                        if (a.getAccountNumber().equals(accountNumberToRetrieve) && a.getBranchCode().equals(branchCodeToRetrieve)) {
                            accountToRetrieve = a;
                            System.out.println("Found account ID: " + a.getAccountNumber() + " and branch code: " + a.getBranchCode());
                            break;
                        }
                    }
                    if (accountToRetrieve != null) {
                        System.out.println("--- Account details ---");
                        System.out.println("Account Number: " + accountToRetrieve.getAccountNumber());
                        System.out.println("Branch Code: " + accountToRetrieve.getBranchCode());
                        System.out.println("Overdraft Limit: €" + accountToRetrieve.getOverdraftLimit());
                        System.out.println("Balance: €" + accountToRetrieve.getBalance());
                        System.out.println("Is Active: " + (accountToRetrieve.getIsActive() ? "Yes" : "No"));
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    // List accounts by branch
                    System.out.println("--- List accounts by branch ---");

                    break;
                case 6:
                    // Insert credit or debit entries
                    break;
                case 7:
                    // Transfer amount
                    break;
                case 8:
                    // Withdraw
                    break;
                case 9:
                    // List all debit and credit entries
                    break;
                case 10:
                    // Print account statement
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }
}
