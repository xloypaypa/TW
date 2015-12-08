package logic.parser;

import logic.discount.DiscountPromotion;

/**
 * Created by xlo on 2015/12/8.
 * it's the discount promotion parser
 */
public class DiscountPromotionParser extends Parser<DiscountPromotion> {
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
    protected DiscountPromotion solveOneData(String message) {
        String[] data = message.split(":");
        return new DiscountPromotion(data[0], Double.valueOf(data[1]));
    }
}
