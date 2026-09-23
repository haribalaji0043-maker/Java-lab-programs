import java.sql.*;
import java.util.Scanner;

public class AirlineManagement{

    static final String URL = "jdbc:mysql://localhost:3306/airline_db";
    static final String USER = "root";
    static final String PASSWORD = "test@123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish Connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            int choice;

            do {
                System.out.println("\n========== Airline Management System ==========");
                System.out.println("1. Add Flight");
                System.out.println("2. View All Flights");
                System.out.println("3. Update Flight Ticket Price");
                System.out.println("4. Cancel/Delete Flight");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Flight ID: ");
                        int flightId = sc.nextInt();
                        sc.nextLine(); // Consume newline leftover
                        
                        System.out.print("Enter Airline Name (e.g., Boeing 737 / Indigo): ");
                        String flightName = sc.nextLine();
                        
                        System.out.print("Enter Source City: ");
                        String source = sc.nextLine();
                        
                        System.out.print("Enter Destination City: ");
                        String destination = sc.nextLine();
                        
                        System.out.print("Enter Ticket Price: ");
                        double price = sc.nextDouble();

                        String insert = "INSERT INTO flights (flight_id, flight_name, source, destination, price) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(insert);
                        ps.setInt(1, flightId);
                        ps.setString(2, flightName);
                        ps.setString(3, source);
                        ps.setString(4, destination);
                        ps.setDouble(5, price);

                        int row = ps.executeUpdate();
                        if (row > 0) {
                            System.out.println("Flight Added Successfully!");
                        }
                        break;

                    case 2:
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM flights");
                        
                        System.out.println("\n----------------------------------------------------------------------");
                        System.out.println("ID\tAirline\t\tSource\t\tDestination\tPrice");
                        System.out.println("----------------------------------------------------------------------");
                        
                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("flight_id") + "\t" +
                                rs.getString("flight_name") + "\t\t" +
                                rs.getString("source") + "\t\t" +
                                rs.getString("destination") + "\t\t$" +
                                rs.getDouble("price")
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter Flight ID to Update: ");
                        int updateId = sc.nextInt();
                        
                        System.out.print("Enter New Ticket Price: ");
                        double newPrice = sc.nextDouble();

                        String update = "UPDATE flights SET price = ? WHERE flight_id = ?";
                        PreparedStatement ps2 = con.prepareStatement(update);
                        ps2.setDouble(1, newPrice);
                        ps2.setInt(2, updateId);

                        int updateRow = ps2.executeUpdate();
                        if (updateRow > 0) {
                            System.out.println("Flight Price Updated Successfully!");
                        } else {
                            System.out.println("Flight ID Not Found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Flight ID to Delete: ");
                        int deleteId = sc.nextInt();

                        String delete = "DELETE FROM flights WHERE flight_id = ?";
                        PreparedStatement ps3 = con.prepareStatement(delete);
                        ps3.setInt(1, deleteId);

                        int deleteRow = ps3.executeUpdate();
                        if (deleteRow > 0) {
                            System.out.println("Flight Cancelled/Deleted Successfully!");
                        } else {
                            System.out.println("Flight ID Not Found.");
                        }
                        break;

                    case 5:
                        System.out.println("Thank you for using Airline Management System!");
                        break;

                    default:
                        System.out.println("Invalid Choice. Please try again.");
                }
            } while (choice != 5);

            con.close();
            sc.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver Not Found.");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}