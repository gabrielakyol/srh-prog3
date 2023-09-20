import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockSystem {
    private final HashMap<String, Company> company = new HashMap<>();
    private final HashMap<String, List<DailyInfo>> dailyInfo = new HashMap<>();

    public StockSystem() {
        createCompany("Apple", "AAPL");
        createCompany("Amazon", "AMZN");
        createCompany("Google", "GOOG");
    }

    // create company
    public void createCompany(String name, String tickerSymbol) {
        if (company.containsKey(tickerSymbol)) {
            System.out.println("Company Ticker already exists: " + company.get(tickerSymbol));
        } else if (tickerSymbol.length() != 4) {
            System.out.println("Ticker symbol must be 4 characters long");
        } else if (name.length() < 2) {
            System.out.println("Company name must be at least 2 characters long");
        } else {
            company.put(tickerSymbol, new Company(name, tickerSymbol));
            dailyInfo.put(tickerSymbol, new ArrayList<>());
            System.out.println("Company created: " + tickerSymbol + " - " + name);
        }
    }

    // list companies key and value
    public void listCompanies() {
        for (String key : company.keySet()) {
            System.out.println(key + " -  " + company.get(key).companyName());
        }
    }

    // add daily info
    public void addDailyInfo(String tickerSymbol, double open, double high, double low, double close) {
        if (!company.containsKey(tickerSymbol)) {
            System.out.println("Ticker symbol not found");
        } else if (open < 0 || high < 0 || low < 0 || close < 0) {
            System.out.println("Price cannot be negative");
        } else if (!(open < high || high > low || low < close)) {
            System.out.println("Price order is not correct");
        } else {
            System.out.println("Daily info added");
            // check if ticker symbol exists
            DailyInfo dailyInfoObject  = new DailyInfo(Instant.now() ,open, high, low, close);
            dailyInfo.get(tickerSymbol).add(dailyInfoObject);
        }
    }

    // list daily infos of a company
    public void listDailyInfos(String companyName) {
        String tickerSymbol = company.get(companyName).tickerSymbol();
        if (!dailyInfo.containsKey(tickerSymbol)) {
            System.out.println("No daily info found for " + tickerSymbol);
        } else {
            for (DailyInfo dailyInfoObject : dailyInfo.get(tickerSymbol)) {
                System.out.println(dailyInfoObject);
            }
            System.out.println(dailyInfo.get(tickerSymbol).size());
        }
    }
}




