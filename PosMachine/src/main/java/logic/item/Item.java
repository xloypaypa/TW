package logic.item;

/**
 * Created by xlo on 2015/12/8.
 * it's the item
 */
public class Item {

    private String key;
    private double value;

    public Item(String key, double value) {
        this.key = key;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
