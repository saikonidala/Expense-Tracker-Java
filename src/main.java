
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        ExpenseService service = new ExpenseService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    // Thread for adding expense
                    service.addExpense();
                    break;

                case 2:
                    service.viewExpenses();
                    break;

                case 3:
                    // Thread for report
                    service.generateReport();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
