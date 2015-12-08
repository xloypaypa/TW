package logic.discount;

/**
 * Created by xlo on 2015/12/8.
 * it's the discount promotion
 */
public class DiscountPromotion {

    private String item;
    private double value;

    public DiscountPromotion(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }
}
