public record Account(
        AccountId accountId,
        double overdraftLimit,
        double balance,
        boolean isActive)
{
    public Account updateActive(boolean isActive) {
        return new Account(
                accountId, overdraftLimit, balance, isActive
        );
    }

    public boolean isAccountActive() {
        return isActive;
    }

    public Account updateBalance(double balance){
        return new Account(
                accountId, overdraftLimit, balance, isActive
        );
    }

    public Account updateOverdraftLimit(double overdraftLimit){
        return new Account(
                accountId, overdraftLimit, balance, isActive
        );
    }
}
