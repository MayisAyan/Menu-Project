import java.util.Scanner;

public class Item {
    private static int idCounter = -1;
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;

    private int idGenerator(){
        return ++idCounter;
    }
    public void changePrice() {
        System.out.println("Enter a new price.");
        Scanner sc = new Scanner(System.in);
        double price = 0.0;
        boolean validInput = false;
        while (!validInput) {
            String input = sc.nextLine();
            try {
                price = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid price. Enter again.");
            }
        }
        this.price = price;
    }

    public void changeDescription() {
        System.out.println("Enter a new (reasonable) description.");
        Scanner sc = new Scanner(System.in);
        this.description = sc.nextLine();
    }

    public void changeName() {
        System.out.println("Enter a new (reasonable) name.");
        Scanner sc = new Scanner(System.in);
        this.name = sc.nextLine();
    }

    public void changeCategory() {
        System.out.println("Enter a category(drink/snack/meal).");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String newCategory = sc.nextLine().toLowerCase().trim();
            if (newCategory.equals("drink") || newCategory.equals("snack") || newCategory.equals("meal")) {
                this.category = newCategory;
                break;
            } else {
                System.out.println("Not valid category.Try again");
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public int getId(){
        return id;
    }

    public Item(String name, String description, double price, String category){
        this.id = idGenerator();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    public Item copyOf(Item item){
        return new Item(item.id, item.name, item.description, item.price,item.category);
    }
    private Item(int id, String name, String description, double price, String category){}
    @Override
    public int hashCode(){
        return id + name.hashCode() + description.hashCode() + Double.hashCode(price) + category.hashCode();
    }
    public String print(){
        return " Name: " + getName() + " Description: " + getDescription() + " Category: " + getCategory() + " Price: " + getPrice();
    }
}
