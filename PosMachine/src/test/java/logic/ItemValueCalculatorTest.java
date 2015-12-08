package logic;

import data.Data;
import logic.discount.DiscountPromotionManager;
import logic.discount.SecondHalfPricePromotionManager;
import logic.item.ItemManager;
import logic.parser.DiscountPromotionParser;
import logic.parser.ItemParser;
import logic.parser.SecondHalfPricePromotionParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 2015/12/8.
 * it's the discount calculator test
 */
public class ItemValueCalculatorTest {

    @BeforeClass
    public static void loadData() {
        ItemManager.getItemManager().addItem(new ItemParser().getList(Data.itemList));

        DiscountPromotionManager.getDiscountPromotionManager()
                .addDiscountPromotion(new DiscountPromotionParser().getList(Data.discountPromotion));

        SecondHalfPricePromotionManager.getDiscountPromotionManager()
                .addSecondHalfPricePromotion(new SecondHalfPricePromotionParser().getList(Data.secondHalfPricePromotion));
    }

    @Test
    public void should_be_15_when_one_item1() {
        assertEquals(30, new ItemValueCalculator().getValue(ItemManager.getItemManager().getItem("ITEM000001"), 1), 1e-3);
    }

    @Test
    public void should_be_25_when_one_item3() {
        assertEquals(50, new ItemValueCalculator().getValue(ItemManager.getItemManager().getItem("ITEM000003"), 1), 1e-3);
    }

    @Test
    public void should_be_54_when_one_item5() {
        assertEquals(54, new ItemValueCalculator().getValue(ItemManager.getItemManager().getItem("ITEM000005"), 1), 1e-3);
    }

    @Test
    public void should_be_45_when_one_item1() {
        assertEquals(45, new ItemValueCalculator().getValue(ItemManager.getItemManager().getItem("ITEM000001"), 2), 1e-3);
    }

    @Test
    public void should_be_75_when_one_item3() {
        assertEquals(75, new ItemValueCalculator().getValue(ItemManager.getItemManager().getItem("ITEM000003"), 2), 1e-3);
    }

}