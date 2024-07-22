import java.util.HashMap;

public class Menu {
    public HashMap<Integer, Item> items; // integer - item.id Item - item

    private static Menu instance;
    public static Menu getInstance() {
        synchronized (Object.class) {
            if (instance == null) {
                instance = new Menu();
            }
            return instance;
        }
    }
    private Menu() {
        items = new HashMap<>();
    }
}
