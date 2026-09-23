import java.util.Scanner;

public class EBBillCalculation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Consumer Number: ");
        int consumerNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Consumer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Previous Reading: ");
        int previous = sc.nextInt();

        System.out.print("Enter Current Reading: ");
        int current = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Connection Type (Domestic/Commercial): ");
        String type = sc.nextLine();

        int units = current - previous;
        double amount = 0;

        if (type.equalsIgnoreCase("Domestic")) {

            if (units <= 100)
                amount = 0;
            else if (units <= 200)
                amount = (units - 100) * 2;
            else if (units <= 500)
                amount = (100 * 2) + (units - 200) * 4;
            else
                amount = (100 * 2) + (300 * 4) + (units - 500) * 6;

        } else if (type.equalsIgnoreCase("Commercial")) {

            if (units <= 100)
                amount = units * 2;
            else if (units <= 200)
                amount = (100 * 2) + (units - 100) * 4;
            else if (units <= 500)
                amount = (100 * 2) + (100 * 4) + (units - 200) * 6;
            else
                amount = (100 * 2) + (100 * 4) + (300 * 6) + (units - 500) * 7;

        } else {
            System.out.println("Invalid Connection Type");
            return;
        }

        System.out.println("\n------ EB BILL ------");
        System.out.println("Consumer No : " + consumerNo);
        System.out.println("Consumer Name : " + name);
        System.out.println("Connection Type : " + type);
        System.out.println("Units Consumed : " + units);
        System.out.println("Total Bill : Rs. " + amount);

        sc.close();
    }
}