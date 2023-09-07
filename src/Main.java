import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a list of numbers, separated by commas:");
        System.out.println("Example: 32, 15, 17, 19, 20, 21, 22, 25");

        // numbers to work with
        String input = scanner.nextLine();
        String[] inputArray = input.split(",");
        List<Integer> list = new ArrayList<>();
        for(String s : inputArray) {
            list.add(Integer.parseInt(s.trim()));
        }

        // array that hold the values
        List<Integer> divisibleBy2 = new ArrayList<>();
        List<Integer> divisibleBy3 = new ArrayList<>();
        List<Integer> divisibleBy5 = new ArrayList<>();
        List<Integer> notDivisibleBy2or3or5 = new ArrayList<>();

        System.out.println("Original list: " + list);
        for (Integer n: list ) {
            if (n % 2 == 0) {
                divisibleBy2.add(n);
            }

            if (n % 3 == 0) {
                divisibleBy3.add(n);
            }

            if (n % 5 == 0) {
                divisibleBy5.add(n);
            }

            if (n % 2 != 0 && n % 3 != 0 && n % 5 != 0) {
                notDivisibleBy2or3or5.add(n);
            }

        }
        System.out.println("Divisible by 2: " + divisibleBy2);
        System.out.println("Divisible by 3: " + divisibleBy3);
        System.out.println("Divisible by 5: " + divisibleBy5);
        System.out.println("Not divisible by 2, 3, or 5: " + notDivisibleBy2or3or5);
    }
}
