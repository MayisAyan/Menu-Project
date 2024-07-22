import java.time.LocalDate;
import java.util.HashMap;

public class Order {
    private static int idCounter = -1;
    private LocalDate date = LocalDate.now();
    private int idGenerator(){
        return ++idCounter;
    }
    public int getId(){ return idCounter ;}


    private final HashMap<Item, Integer> items = new HashMap<>(); // Item - order Integer - count
    public void viewOrder() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items.keySet()) {
            int count = items.get(item);
            sb.append(item.getName()).append('\t').append(count + '\t').append(count * item.getPrice());

        }
        System.out.println(sb);
    }
    public void placeOrder(Item item, int count){
        items.put(item, count);
    }

    public void modifyOrder(Item item, int count) {
        items.replace(item, count);
    }
    public void deleteOrder(Item item) {
        items.remove(item);
    }
    public Order() {
        idGenerator();
    }
}