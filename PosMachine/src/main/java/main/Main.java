package main;

import data.Data;
import logic.ItemValueCalculator;
import logic.PosMachine;
import logic.discount.DiscountPromotionManager;
import logic.discount.SecondHalfPricePromotionManager;
import logic.item.ItemManager;
import logic.parser.CartParser;
import logic.parser.DiscountPromotionParser;
import logic.parser.ItemParser;
import logic.parser.SecondHalfPricePromotionParser;

/**
 * Created by xlo on 2015/12/8.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) {
        ItemManager.getItemManager().addItem(new ItemParser().getList(Data.itemList));

        DiscountPromotionManager.getDiscountPromotionManager()
                .addDiscountPromotion(new DiscountPromotionParser().getList(Data.discountPromotion));

        SecondHalfPricePromotionManager.getDiscountPromotionManager()
                .addSecondHalfPricePromotion(new SecondHalfPricePromotionParser().getList(Data.secondHalfPricePromotion));

        System.out.println(new PosMachine().calculatePrice(new CartParser().getList(Data.cartList), new ItemValueCalculator()));
    }
}
