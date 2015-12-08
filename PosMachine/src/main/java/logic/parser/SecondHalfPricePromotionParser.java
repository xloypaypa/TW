package logic.parser;

import logic.discount.SecondHalfPricePromotion;

/**
 * Created by xlo on 2015/12/8.
 * it's the second half price promotion parser
 */
public class SecondHalfPricePromotionParser extends Parser<SecondHalfPricePromotion> {
    @Override
    protected void checkArgument(String message) {
        boolean flag = true;
        if (!isKeyOk(message)) {
            flag = false;
        }

        if (!flag) {
            throw new IllegalArgumentException(DATA_ERROR);
        }
    }

    @Override
    protected SecondHalfPricePromotion solveOneData(String message) {
        return new SecondHalfPricePromotion(message);
    }
}
