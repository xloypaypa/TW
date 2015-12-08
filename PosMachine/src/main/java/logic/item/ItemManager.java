package logic.item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 2015/12/8.
 * it's the item manager
 */
public final class ItemManager {

    private static ItemManager itemManager;

    public static ItemManager getItemManager() {
        if (itemManager == null) {
            synchronized (ItemManager.class) {
                if (itemManager == null) {
                    itemManager = new ItemManager();
                }
            }
        }
        return itemManager;
    }

    private Map<String, Item> itemMap;

    private ItemManager() {
        this.itemMap = new HashMap<>();
    }

    public void addItem(Collection<Item> items) {
        for (Item item : items) {
            itemMap.put(item.getKey(), item);
        }
    }

    public Item getItem(String key) {
        return this.itemMap.get(key);
    }
}
