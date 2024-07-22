import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Waiter {
    Menu menu = Menu.getInstance();
    public void placeOrder(){
        System.out.println("""
                Enter 1 for new order.
                Enter 2 for changing order.
                Enter 3 for deleting order.
                Enter 0 to exit.
                """);
        Scanner sc = new Scanner(System.in);
        int operation;
        while (true) {
            try {
                operation = sc.nextInt();
                if(operation >= 0 && operation <= 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid operation");
            }
        }
        switch(operation) {
            case 1 -> newOrder();
            case 2 -> {
                int id = enteringId();
                changeOrder(id);
            }
            case 3 -> {
                int id = enteringId();
                deleteOrder(id);
            }
            case 0 -> {}
        }

    }

    public void showMenu(){
        if(menu.items.isEmpty()){
            System.out.println("Menu is empty. Enter items.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Item> entry : menu.items.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue().print()).append("\n");
        }
        System.out.println(sb);
    }
    private int enteringId() {
        System.out.println("Enter the id.");
        Scanner sc = new Scanner(System.in);
        int idOfOrder;
        while (true) {
            try {
                idOfOrder = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Not valid id");
            }
        }
        return idOfOrder;
    }
    private int enteringCountOfItem() {
        System.out.println("Enter count of item you want");
        Scanner sc = new Scanner(System.in);
        int count;
        while(true){
            try{
                count = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Not valid count");
            }
        }
        return count;
    }

    private void newOrder() {
        showMenu();
        Order order = new Order();
        int id = enteringId();
        Item item = menu.items.get(id);
        int count = enteringCountOfItem();
        order.placeOrder(item, count);
        OrderList orders = OrderList.getInstance();
        orders.orders.put(order.getId(),order);
        new Bill(item,count);
    }

    private void changeOrder(int id) {
        OrderList orders = OrderList.getInstance();
        Order order = orders.orders.get(id);
        int idOfItem = enteringId();
        Item item = menu.items.get(idOfItem);
        int count = enteringCountOfItem();
        order.modifyOrder(item, count);
        new Bill(item,count);
    }
    private void deleteOrder(int id){
        OrderList orders = OrderList.getInstance();
        Order order = orders.orders.get(id);
        int idOfItem = enteringId();
        Item item = menu.items.get(idOfItem);
        orders.orders.remove(id);
        order.deleteOrder(item);
    }

    public void viewOrderHistory() {
        Date date = settingDate();
        String filePath = "OrderList.txt";
        try {
            String content = readFile(filePath);
            if(content.contains(date.toString())) {
                System.out.println("File content:\n" + content);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

    }

    private Date settingDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date of order (format: dd/MM/yyyy): ");
        while (true) {
            String inputDateStr = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                return dateFormat.parse(inputDateStr);
            } catch (ParseException e) {
                System.out.println("Wrong Date format.Enter again");
            }
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
