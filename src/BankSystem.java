import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BankSystem {
    private final HashMap<AccountId, Account> accounts = new HashMap<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    AccountId clearingAccountId;

    public BankSystem() {
        // create bank internal clearing account
        clearingAccountId = new AccountId(0, 0);
        Account clearingAccountAccount = new Account(clearingAccountId, 1000000, 0, true);
        accounts.put(clearingAccountId, clearingAccountAccount);
    }

    // createAccount method
    public AccountId createAccount(double overdraftLimit, int branchCode) {
        AccountId accountId = new AccountId(accounts.size(), branchCode);
        Account account = new Account(accountId, overdraftLimit, 0, true);
        accounts.put(accountId, account);
        return accountId;
    }

    // closeAccount method
    public void closeAccount(AccountId accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }
        double balance = account.balance();
        if (balance == 0) {
            accounts.put(accountId, account.updateActive(false));
            System.out.println("Account closed with id: " + accountId);
        } else if (balance > 0) {
            System.out.println("Account has a positive balance");
        } else {
            System.out.println("Account has a negative balance");
        }
    }

    // getAccount method
    public void setOverdraftLimit(AccountId accountId, double overdraftLimit) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return;
        } else if (!account.isAccountActive()) {
            System.out.println("Account is not active");
            return;
        } else if (overdraftLimit < 0) {
            System.out.println("Overdraft limit cannot be negative");
            return;
        } else if (overdraftLimit < account.balance()) {
            // overdraftLimit lower than balance
            System.out.println("Overdraft limit cannot be lowered than balance");
            return;
        }
        Account newAccount = account.updateOverdraftLimit(overdraftLimit);
        accounts.put(accountId, newAccount);
        System.out.println("Overdraft limit updated to " + overdraftLimit + " for " + accountId);
    }

    // retrieve account method
    public Account retrieveAccount(AccountId accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return null;
        }
        return accounts.get(accountId);
    }

    // listAccountsByBranch method
    public void listAccountsByBranch(int branchCode) {
        List<Account> accountsByBranch = new ArrayList<>();

        for (Account a : accounts.values()) {
            if (a.accountId().branchCode() == branchCode) {
                accountsByBranch.add(a);
            }
        }
        if (accountsByBranch.isEmpty()) {
            System.out.println("No accounts found for branch: " + branchCode);
            return;
        }
        System.out.println("Accounts by branch: " + branchCode);
        for (Account a : accountsByBranch) {
            System.out.println(a);
        }
    }

    // Transfer
    public void transferMoney(AccountId source, AccountId target, double amount, String description) {
        if (source.equals(target)) { // source and target are the same
            System.out.println("Source and target account are the same");
            return;
        } else if (source.accountNumber() == -1 || target.accountNumber() == -1) { // bank internal clearing account
            System.out.println("Bank internal clearing account cannot be used as source or target");
            return;
        } else if (amount < 0) { // amount is negative
            System.out.println("Amount cannot be negative");
            return;
        }
        // check if source exists
        Account sourceAccount = accounts.get(source);
        if (sourceAccount == null) {
            System.out.println("Sender Account not found");
            return;
        } else if (!sourceAccount.isAccountActive()) {
            System.out.println("Sender Account is not active");
            return;
        }
        // check if target exits
        Account targetAccount = accounts.get(target);
        if (targetAccount == null) {
            System.out.println("Receiver Account not found");
            return;
        } else if (!targetAccount.isAccountActive()) {
            System.out.println("Receiver Account is not active");
            return;
        }
        if (sourceAccount.balance() + sourceAccount.overdraftLimit() >= amount) {
            int newSourceBalance = (int) (sourceAccount.balance() - amount);
            int newTargetBalance = (int) (targetAccount.balance() + amount);
            Account newSourceAccount = sourceAccount.updateBalance(newSourceBalance);
            Account newTargetAccount = targetAccount.updateBalance(newTargetBalance);
            accounts.put(source, newSourceAccount);
            accounts.put(target, newTargetAccount);
            transactions.add(new Transaction(UUID.randomUUID(), source, target, amount, Instant.now(), description));
            System.out.println("Transfer successful");
        } else {
            System.out.println("Sender Account has insufficient funds");
        }
    }

    // Credit or Debit
    public void insertCreditOrDebitEntry(AccountId accountId, double amount, String description, int creditOrDebit) {
        if (accountId.accountNumber() == -1) { // bank internal clearing account
            System.out.println("Bank internal clearing account cannot be used as normal account");
            return;
        }
        if (amount < 0) { // if amount under 0
            System.out.println("Negative entries are not allowed. Put a positive number, and choose betwwen +/-");
            return;
        }
        if(creditOrDebit == 1 || creditOrDebit == 2) {
            // check if target account exists
            Account account = accounts.get(accountId);
            Account clearingAccount = accounts.get(clearingAccountId);
            if (account == null) {
                System.out.println("Account not found");
                return;
            } else if (!account.isAccountActive()) {
                System.out.println("Account is not active");
                return;
            }
            // check if amount is negative
            if (creditOrDebit == 2) {
                // check if account has sufficient funds
                if (account.balance() + account.overdraftLimit() >= amount) {
                    int newTargetBalance = (int) (account.balance() - amount);
                    int newClearingAccountBalance = (int) (clearingAccount.balance() + amount);
                    Account newSourceAccount = account.updateBalance(newTargetBalance);
                    Account newClearingAccount = clearingAccount.updateBalance(newClearingAccountBalance);
                    accounts.put(accountId, newSourceAccount);
                    accounts.put(clearingAccountId, newClearingAccount);
                    transactions.add(new Transaction(UUID.randomUUID(), clearingAccountId, accountId, amount, Instant.now(), description));
                    System.out.println("Transfer successful, took: " + amount + "€ from " + accountId);
                } else {
                    System.out.println(account.balance() + account.overdraftLimit());
                    System.out.println("Target account not sufficient");
                }
            } else {
                if (clearingAccount.balance() + clearingAccount.overdraftLimit() >= amount) {
                    int newClearingAccountBalance = (int) (clearingAccount.balance() - amount);
                    int newTargetBalance = (int) (account.balance() + amount);
                    Account newClearingAccount = clearingAccount.updateBalance(newClearingAccountBalance);
                    Account newSourceAccount = account.updateBalance(newTargetBalance);
                    accounts.put(clearingAccountId, newClearingAccount);
                    accounts.put(accountId, newSourceAccount);
                    transactions.add(new Transaction(UUID.randomUUID(), clearingAccountId, accountId, amount, Instant.now(), description));
                    System.out.println("Transfer successful, added: " + amount + "€ from " + accountId);
                } else {
                    System.out.println("Bank account not sufficient, This is how banking crisis happens.");
                }
            }
        } else {
            System.out.println("unknown operation: " + creditOrDebit + " - we only accept 1(+) or 2(-)");
        }
    }
}




