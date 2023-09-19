import java.util.ArrayList;
import java.util.HashMap;

public class StockSystem {
    private final HashMap<AccountId, Account> accounts = new HashMap<>();

    AccountId clearingAccountId;

    public StockSystem() {
        // create bank internal clearing account
        clearingAccountId = new AccountId(0, 0);
        Account clearingAccountAccount = new Account(clearingAccountId, 1000000, 0, true);
        accounts.put(clearingAccountId, clearingAccountAccount);
    }

}




