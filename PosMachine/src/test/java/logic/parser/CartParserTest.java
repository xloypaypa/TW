package logic.parser;

import data.Data;
import logic.cart.Cart;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 2015/12/8.
 * it's the cart parser test
 */
public class CartParserTest {

    @Test
    public void check_load_right_cart_data() {
        Cart cart = new CartParser().getOne(Data.cartList[0]);
        assertEquals("ITEM000001", cart.getItem());
        assertEquals(3, cart.getNum());
    }

    @Test
    public void check_load_right_cart_list() {
        List<Cart> carts = new CartParser().getList(Data.cartList);
        assertEquals(3, carts.size());
        assertEquals("ITEM000001", carts.get(0).getItem());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_key_is_not_start_at_cart() {
        new CartParser().getOne("WORD000001-3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_value_not_double() {
        new CartParser().getOne("ITEM000001-A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_length_of_key_not_10() {
        new CartParser().getOne("ITEM00001-40");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_not_have_value() {
        new CartParser().getOne("ITEM000001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_key_not_number() {
        new CartParser().getOne("ITEM000a01-40");
    }

}