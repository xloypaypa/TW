package logic.parser;

import logic.item.Item;

/**
 * Created by xlo on 2015/12/8.
 * it's the item parser
 */
public class ItemParser extends Parser<Item> {

    @Override
    protected void checkArgument(String message) {
        boolean flag = true;
        String[] data = message.split(":");
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
    protected Item solveOneData(String message) {
        String[] data = message.split(":");
        return new Item(data[0], Double.valueOf(data[1]));
    }

}
