package logic.parser;

import data.Data;
import logic.item.Item;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xlo on 2015/12/8.
 * it's the testing code for item parser
 */
public class ItemParserTest {

    @Test
    public void check_load_right_item_data() {
        Item item = new ItemParser().getOne(Data.itemList[0]);
        assertEquals("ITEM000001", item.getKey());
        assertEquals(40, item.getValue(), 1e-3);
    }

    @Test
    public void check_load_right_item_list() {
        List<Item> items = new ItemParser().getList(Data.itemList);
        assertEquals(3, items.size());
        assertEquals("ITEM000001", items.get(0).getKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_key_is_not_start_at_item() {
         new ItemParser().getOne("WORD000001:40");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_value_not_double() {
        new ItemParser().getOne("ITEM000001:4a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_length_of_key_not_10() {
        new ItemParser().getOne("ITEM00001:40");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_not_have_value() {
        new ItemParser().getOne("ITEM000001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_key_not_number() {
        new ItemParser().getOne("ITEM000a01:40");
    }

}