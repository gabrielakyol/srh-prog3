import java.time.Instant;

public record DailyInfo(
        Instant date,
        double open,
        double high,
        double low,
        double close
) {

}
