
import java.sql.*;
import java.util.Scanner;

public class ExpenseService {

    // ADD EXPENSE
    public void addExpense() {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0) {
                throw new Exception("Amount must be positive!");
            }

            System.out.print("Enter category: ");
            String category = sc.next();

            Date date = new Date(System.currentTimeMillis());

            String query = "INSERT INTO expenses(amount, category, date) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setString(2, category);
            ps.setDate(3, date);

            ps.executeUpdate();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // VIEW EXPENSES
    public void viewExpenses() {
        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT * FROM expenses";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getString("category") + " | " +
                        rs.getDate("date")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // MONTHLY REPORT
    public void generateReport() {
        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT category, SUM(amount) AS total FROM expenses GROUP BY category";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Monthly Report ---");

            while (rs.next()) {
                System.out.println(
                        rs.getString("category") + " : " +
                        rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}