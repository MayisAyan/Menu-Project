import java.util.Scanner;

public class Bill {
    private static final double percent1 = 0.10;
    private static final double percent2 = 0.20;

    public void generatingCheck(Item item, int count) {
        double price = item.getPrice();
        StringBuilder billCheck = new StringBuilder();
        billCheck.append("----- Bill Check -----\n");
        billCheck.append("Bill of your order: ").append(price * count).append("\n");
        billCheck.append("Service fee(10% overall): ").append(price * count * percent1).append("\n");
        billCheck.append("Tax(20% overall): ").append(price * count * percent2).append("\n");
        billCheck.append("Tip(10% overall): ").append(price * count * percent1).append("\n");
        double total = (price * count) + (price * count * percent1) + (price * count * percent2) + (price * count * percent1);
        billCheck.append("All together:").append(total).append("\n");
        billCheck.append("Thank you for using our services. Have a good day\n");
        billCheck.append("______________________\n");

        System.out.println(billCheck.toString());

    }
    public Bill(Item item, int count) {
        generatingCheck(item,count);
    }
}
