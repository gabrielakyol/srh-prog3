import java.util.Scanner;

public class Interface {

    private static AccountId fetchAccountId(Scanner scanner) {
        System.out.print("Please enter the account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Please enter the branch code: ");
        int branchCode = scanner.nextInt();
        return new AccountId(accountNumber, branchCode);
    }

    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();
        Scanner scanner = new Scanner(System.in);

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
                case 1 -> {
                    // Create account
                    System.out.println("--- Create account ---");
                    System.out.print("Please enter the branch code: ");
                    int branchCode = scanner.nextInt();
                    System.out.print("Please enter the overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    AccountId creatingRes = bankSystem.createAccount(overdraftLimit, branchCode);
                    System.out.println("Account created: " + creatingRes);
                }
                case 2 -> {
                    // Close account (account total must be 0)
                    System.out.println("--- Close account ---");
                    bankSystem.closeAccount(fetchAccountId(scanner));
                }
                case 3 -> {
                    // Possibility to have a preset maximum overdraft (account in the negative)
                    System.out.println("--- Set overdraft limit ---");
                    System.out.print("Please enter the new overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    bankSystem.setOverdraftLimit(fetchAccountId(scanner), overdraftLimit);
                }
                case 4 -> {
                    // Accounts be retrieved by account number and branch code
                    System.out.println("--- Retrieve account ---");
                    Account account = bankSystem.retrieveAccount(fetchAccountId(scanner));
                    if (account != null) {
                        System.out.println("Account retrieved: " + account);
                    }
                }
                case 5 -> {
                    // Accounts can be listed by branch
                    System.out.println("--- List accounts by branch ---");
                    System.out.print("Please enter the branch code: ");
                    int branchCode = scanner.nextInt();
                    bankSystem.listAccountsByBranch(branchCode);
                }
                case 6 ->{
                    // Insert credit or debit entries
                    System.out.println("--- Insert credit or debit entries ---");
                    // fetch account
                    AccountId accountId = fetchAccountId(scanner);
                    // amount
                    System.out.print("Please enter the amount: ");
                    double amount = scanner.nextDouble();
                    // positive or negative
                    System.out.print("Please enter 1 for credit (positive) or 2 for debit (negative): ");
                    int creditOrDebit = scanner.nextInt();
                    // description
                    System.out.print("Please enter a description: ");
                    String description = scanner.next();
                    bankSystem.insertCreditOrDebitEntry(accountId, amount, description, creditOrDebit);
                }
                case 7 -> {
                    // Transfer an amount between 2 accounts
                    System.out.println("--- Transfer amount ---");
                    // fetch account sender
                    System.out.println("Please enter the account sender:");
                    AccountId sourceId = fetchAccountId(scanner);
                    // fetch account receiver
                    System.out.println("Please enter the account receiver:");
                    AccountId targetId = fetchAccountId(scanner);
                    // fetch amount
                    System.out.print("Please enter the amount to transfer: ");
                    int amount = scanner.nextInt();
                    // fetch description
                    System.out.print("Please enter a description: ");
                    String description = scanner.next();
                    bankSystem.transferMoney(sourceId, targetId, amount, description);
                }
                case 8 ->
                    // Withdraw
                        System.out.println("--- Withdraw ---");
                case 9 ->
                    // List all debit and credit entries
                        System.out.println("--- List all debit and credit entries ---");
                case 10 ->
                    // Print account statement (last value per day for the last 10 days)
                        System.out.println("--- Print account statement ---");
                case 0 -> {
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
