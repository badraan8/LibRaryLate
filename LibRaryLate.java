import java.util.ArrayList;
import java.util.Scanner;
public class LibRaryLate {
    abstract static class Library {

        String title;
        int daysLate;
        Library(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }
        abstract int calculateFine();
    }
    static class Book extends Library {

        Book(String title, int daysLate) {
            super(title, daysLate);
        }
        @Override
        int calculateFine() {
            return daysLate * 2;
        }
    }
    static class DVD extends Library {

        DVD(String title, int daysLate) {
            super(title, daysLate);
        }
        @Override
        int calculateFine() {
            if (daysLate == 0) {
                return 0;
            }

            return 10 + daysLate * 5;
        }
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Library> library = new ArrayList<>();

        System.out.print("How many returned items? ");
        int n = input.nextInt();
        while (n < 1 || n > 100) {
            System.out.print("Enter a number from 1 to 100: ");
            n = input.nextInt();
        }
        for (int i = 1; i <= n; i++) {

            System.out.println("Item " + i);

            System.out.print("Type (B/D): ");
            String type = input.next().toUpperCase();
            while (!type.equals("B") && !type.equals("D")) {
                System.out.print("Invalid type! Enter B or D: ");
                type = input.next().toUpperCase();
            }

            System.out.print("Title: ");
            String title = input.next();

            System.out.print("Days late: ");
            int days = input.nextInt();
            while (days < 0 || days > 365) {
                System.out.print("Enter days from 0 to 365: ");
                days = input.nextInt();
            }
            if (type.equals("B")) {
                library.add(new Book(title, days));
            } else {
                library.add(new DVD(title, days));
            }
        }
        int total = 0;

        System.out.println("\n--- LIBRARY FINE SUMMARY ---");

        for (Library item : library) {

            int fine = item.calculateFine();

            System.out.println(
                    item.title + ": " + fine + " units"
            );

            total += fine;
        }
        System.out.println("Total fines: " + total + " units");

        input.close();
    }
}