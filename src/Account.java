public class Account {
    private final Integer accountNumber;
    private final String BranchCode;
    private double overdraftLimit;
    private double balance;
    private boolean isActive;

    public Account (Integer accountNumber, String BranchCode, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.BranchCode = BranchCode;
        this.overdraftLimit = overdraftLimit;
        this.balance = 0;
        this.isActive = true;
    }

    // get
    public double getBalance() {
        return balance;
    }

    // account number
    public Integer getAccountNumber() {
        return accountNumber;
    }

    // branch code
    public String getBranchCode() {
        return BranchCode;
    }

    // overdraft limit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // is active
    public boolean getIsActive() {
        return isActive;
    }


    // set
    // set overdraft limit
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

}
