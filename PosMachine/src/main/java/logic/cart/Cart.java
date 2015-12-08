package logic.cart;

/**
 * Created by xlo on 2015/12/8.
 * it's the cart
 */
public class Cart {

    private String item;
    private int num;

    public Cart(String item, int num) {
        this.item = item;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getItem() {
        return item;
    }
}
