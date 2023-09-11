public class Account {
    private final String accountNumber;
    private String BranchCode;
    private double overdraftLimit;
    private double balance;
    private boolean isActive;

    public Account (String accountNumber, String BranchCode, double overdraftLimit, double balance, boolean isActive) {
        this.accountNumber = accountNumber;
        this.BranchCode = BranchCode;
        this.overdraftLimit = overdraftLimit;
        this.balance = 0;
        this.isActive = true;
    }



}
