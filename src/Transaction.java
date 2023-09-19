import java.time.Instant;
import java.util.UUID;

public record Transaction(
        UUID transactionId,
        AccountId sourceAccountId,
        AccountId targetAccountId,
        double amount,
        Instant timestamp,
        String description
) {
}
