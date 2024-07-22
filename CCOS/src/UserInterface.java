import java.util.Scanner;

public class UserInterface {
    Admin admin = new Admin();
    Waiter waiter = new Waiter();

    public void start() {
        while (true) {
            System.out.println("""
                    Enter 1 to login admin mode.
                    Enter 2 to login waiter mode.
                    Enter 0 to exit.
                    """);
            Scanner sc = new Scanner(System.in);
            String operation;
            operation = sc.nextLine();
            switch (operation) {
                case "1" -> adminMode();
                case "2" -> waiterMode();
                case "0" -> System.exit(0);
                default -> System.out.println("Not valid operation.Enter again.");
            }
        }
    }

    private void adminMode() {
        Scanner sc = new Scanner(System.in);
        String operation;
        boolean condition = true;
        while (condition) {
            System.out.println("""
                Enter 1 to add an Item in menu.
                Enter 2 to modify menu.
                Enter 3 to view orders history.
                Enter 4 to save orders.
                Enter 5 to save menu.
                Enter 0 to exit.
                """);
            operation = sc.nextLine();
            switch (operation) {
                case "1" -> admin.addToMenu();
                case "2" -> admin.modifyMenu();
                case "3" -> admin.viewOrdersHistory();
                case "4" -> admin.savingOrders();
                case "5" -> admin.savingMenu();
                case "0" -> condition = false;
                default -> System.out.println("Not valid operation.Enter again.");
            }
        }
    }

    private void waiterMode() {
        Scanner sc = new Scanner(System.in);
        String operation;
        boolean condition = true;
        while (condition) {
            System.out.println("""
                Enter 1 for orders.
                Enter 2 for getting menu.
                Enter 3 to view orders history.
                Enter 0 to exit.
                """);
            operation = sc.nextLine();
            switch (operation) {
                case "1" -> waiter.placeOrder();
                case "2" -> waiter.showMenu();
                case "3" -> waiter.viewOrderHistory();
                case "0" -> condition = false;
                default -> System.out.println("Not valid operation.Enter again.");
            }
        }
    }
}
