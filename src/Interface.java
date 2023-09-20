import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockSystem stockSystem = new StockSystem();

        while (true) {
            System.out.println("Please choose one of the following options:");
            System.out.println("1: Add Company");
            System.out.println("2: List all Companies");
            System.out.println("3: Add Daily Info");
            System.out.println("4: List Daily Infos of a Company");


            System.out.println("0: Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Create Company
                    System.out.print("Enter the ticker symbol of the company: ");
                    String tickerSymbol = scanner.next();
                    System.out.print("Enter the name of the company: ");
                    String name = scanner.next();
                    stockSystem.createCompany(name, tickerSymbol.toUpperCase());

                }
                case 2 -> {
                    // List all Companies
                    System.out.println("Listing all companies:");
                    stockSystem.listCompanies();
                }
                case 3 -> {
                    // Add Daily Info
                    System.out.println("Adding daily info:");
                    System.out.print("Enter the ticker symbol of the company: ");
                    String tickerSymbol = scanner.next();
                    System.out.print("Enter the open price: ");
                    double open = scanner.nextDouble();
                    System.out.print("Enter the high price: ");
                    double high = scanner.nextDouble();
                    System.out.print("Enter the low price: ");
                    double low = scanner.nextDouble();
                    System.out.print("Enter the close price: ");
                    double close = scanner.nextDouble();
                    stockSystem.addDailyInfo(tickerSymbol.toUpperCase(), open, high, low, close);
                }
                case 4 -> {
                    // List Daily Infos of a Company
                    System.out.println("Listing daily infos of a company:");
                    System.out.print("Enter the ticker symbol of the company: ");
                    String tickerSymbol = scanner.next();
                    stockSystem.listDailyInfos(tickerSymbol.toUpperCase());
                }
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
