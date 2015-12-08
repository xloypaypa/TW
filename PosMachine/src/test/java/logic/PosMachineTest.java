package logic;

import data.Data;
import logic.cart.Cart;
import logic.item.ItemManager;
import logic.parser.ItemParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 2015/12/8.
 * it's the post machine test
 */
public class PosMachineTest {

    @BeforeClass
    public static void loadItemData() {
        ItemManager.getItemManager().addItem(new ItemParser().getList(Data.itemList));
    }

    @Test
    public void should_be_140_when_one_item1_and_two_item2() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart("ITEM000001", 1));
        carts.add(new Cart("ITEM000003", 2));
        assertEquals(105, new PosMachine().calculatePrice(carts, new ItemValueCalculator()), 1e-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_use_not_exist_item() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart("ITEM000001", 1));
        carts.add(new Cart("ITEM000002", 2));
        new PosMachine().calculatePrice(carts, new ItemValueCalculator());
    }

}