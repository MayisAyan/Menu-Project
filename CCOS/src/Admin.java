import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    OrderList orderList = OrderList.getInstance();
    Menu menu = Menu.getInstance();

    public void addToMenu() {
        Item item = creatingNewItem();
        menu.items.put(item.getId(), item);
        System.out.println("Item is added.");
    }

    public void modifyMenu() {
        System.out.println("Enter a id of item you want to modify.");
        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        int count = 0;
        while(condition) {
            int id;
            while (true) {
                try {
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Not valid id");
                }
            }
            if (menu.items.containsKey(id)) {
                Item item = menu.items.get(id);
                System.out.println("Enter what part(name, price, category, description) of item do you want to change.If you want to change all parts enter all.");
                String operation;
                while (true) {
                    operation = sc.nextLine();
                    operation = operation.toLowerCase().trim();
                    System.out.println(operation);
                    if (operation.equals("name") || operation.equals("price") || operation.equals("category") || operation.equals("description") || operation.equals("all")) {
                        break;
                    } else {
                        System.out.println("Not valid category.Try again");
                    }
                }
                switch (operation) {
                    case "description" -> {
                        item.changeDescription();
                        System.out.println("Changes has done");
                    }

                    case "category" -> {
                        item.changeCategory();
                        System.out.println("Changes has done");
                    }

                    case "name" -> {
                        item.changeName();
                        System.out.println("Changes has done");
                    }

                    case "price" -> {
                        item.changePrice();
                        System.out.println("Changes has done");
                    }

                    case "all" -> {
                        item.changePrice();
                        item.changeName();
                        item.changeCategory();
                        item.changeDescription();
                        System.out.println("Changes has done");
                    }
                }
                condition = false;
            }else{
                System.out.println("Id does not exist.Enter again");
                condition = false;
                count = 1;
            }
            if(count == 1){
                condition = true;
            }
        }
    }

    public void viewOrdersHistory() {
        for (Integer key : orderList.orders.keySet()) {
            Order order = orderList.orders.get(key);
            System.out.println("ID: " + key + ", Order: " + order);
        }
    }

    private Item creatingNewItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a (reasonable) name of Item");
        String name = sc.nextLine();
        System.out.println("Enter a (reasonable) description");
        String description = sc.nextLine();
        double price = 0.0;
        boolean validInput = false;
        System.out.println("Enter a price.");
        while (!validInput) {
            String input = sc.nextLine();
            try {
                price = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid price. Enter again.");
            }
        }
        System.out.println("Enter a category(drink/snack/meal).");
        String category;
        while (true) {
            String newCategory = sc.nextLine();
            newCategory = newCategory.toLowerCase().trim();
            if (newCategory.equals("drink") || newCategory.equals("snack") || newCategory.equals("meal")) {
                category = newCategory;
                break;
            } else {
                System.out.println("Not valid category.Try again");
            }
        }
        Item item = new Item(name, description, price, category);
        return item;
    }

    public void savingOrders() {
        String fileName = "OrderList.txt";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(orderList.orders);
            objectOutputStream.flush();
        } catch (IOException e) {
        }
    }

    public void savingMenu() {
        String fileName = "Menu.txt";
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(menu.items);
            objectOutputStream.flush();
        } catch (IOException e) {
        }
    }
}

