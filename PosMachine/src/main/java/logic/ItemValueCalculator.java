package logic;

import logic.discount.DiscountPromotion;
import logic.discount.DiscountPromotionManager;
import logic.discount.SecondHalfPricePromotion;
import logic.discount.SecondHalfPricePromotionManager;
import logic.item.Item;

/**
 * Created by xlo on 2015/12/8.
 * it's the discount calculator
 */
public class ItemValueCalculator {

    public double getValue(Item item, int num) {
        DiscountPromotion discountPromotion = DiscountPromotionManager.getDiscountPromotionManager()
                .getDiscountPromotion(item.getKey());
        SecondHalfPricePromotion secondHalfPricePromotion = SecondHalfPricePromotionManager.getDiscountPromotionManager()
                .getDiscountPromotion(item.getKey());
        double oneValue = item.getValue() * discountPromotion.getValue();
        double result = 0;
        result += getHalfPricePromotion(num, secondHalfPricePromotion, oneValue);
        return result;
    }

    protected double getHalfPricePromotion(int num, SecondHalfPricePromotion secondHalfPricePromotion, double oneValue) {
        double result = 0;
        if (secondHalfPricePromotion != null) {
            result += oneValue * (num / 2);
            result += (oneValue * secondHalfPricePromotion.getValue()) * (num / 2);
            if (num % 2 != 0) {
                result += oneValue;
            }
        } else {
            result += oneValue * num;
        }
        return result;
    }

}
