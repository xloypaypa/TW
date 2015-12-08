package logic.parser;

import logic.cart.Cart;

/**
 * Created by xlo on 2015/12/8.
 * it's the cart parser
 */
public class CartParser extends Parser<Cart> {
    @Override
    protected void checkArgument(String message) {
        boolean flag = true;
        String[] data = message.split("-");
        if (data.length != 2) {
            flag = false;
        } else if (!isKeyOk(data[0])) {
            flag = false;
        }

        if (!flag) {
            throw new IllegalArgumentException(DATA_ERROR);
        }
    }

    @Override
    protected Cart solveOneData(String message) {
        String[] data = message.split("-");
        return new Cart(data[0], Integer.valueOf(data[1]));
    }
}
