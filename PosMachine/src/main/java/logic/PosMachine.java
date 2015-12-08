package logic;

import logic.cart.Cart;
import logic.item.Item;
import logic.item.ItemManager;

import java.util.Collection;

/**
 * Created by xlo on 2015/12/8.
 * it's the pos machine
 */
public class PosMachine {

    public final static String ITEM_ERROR = "item error";

    public double calculatePrice(Collection<Cart> carts, ItemValueCalculator itemValueCalculator) {
        double result = 0;
        for (Cart cart : carts) {
            Item item = ItemManager.getItemManager().getItem(cart.getItem());
            if (item == null) {
                throw new IllegalArgumentException(ITEM_ERROR);
            }
            result += itemValueCalculator.getValue(item, cart.getNum());
        }
        return result;
    }

}
