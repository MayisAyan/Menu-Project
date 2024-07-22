import java.util.HashMap;


public class OrderList {
    public final HashMap<Integer, Order> orders; // integer - order.id Order - order
    private static OrderList instance;
    private OrderList() {
        orders = new HashMap<>();
    }

    public static OrderList getInstance() {
        synchronized (Object.class) {
            if (instance == null) {
                instance = new OrderList();
            }
            return instance;
        }
    }
}
