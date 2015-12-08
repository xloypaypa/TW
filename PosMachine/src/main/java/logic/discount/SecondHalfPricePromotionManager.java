package logic.discount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 2015/12/8.
 * it's the second half price promotion manager
 */
public final class SecondHalfPricePromotionManager {

    private static SecondHalfPricePromotionManager secondHalfPricePromotionManager;

    public static SecondHalfPricePromotionManager getDiscountPromotionManager() {
        if (secondHalfPricePromotionManager == null) {
            synchronized (DiscountPromotionManager.class) {
                if (secondHalfPricePromotionManager == null) {
                    secondHalfPricePromotionManager = new SecondHalfPricePromotionManager();
                }
            }
        }
        return secondHalfPricePromotionManager;
    }

    private Map<String, SecondHalfPricePromotion> secondHalfPricePromotionMap;

    private SecondHalfPricePromotionManager() {
        this.secondHalfPricePromotionMap = new HashMap<>();
    }

    public void addSecondHalfPricePromotion(Collection<SecondHalfPricePromotion> secondHalfPricePromotions) {
        for (SecondHalfPricePromotion now : secondHalfPricePromotions) {
            this.secondHalfPricePromotionMap.put(now.getItem(), now);
        }
    }

    public SecondHalfPricePromotion getDiscountPromotion(String item) {
        return  this.secondHalfPricePromotionMap.get(item);
    }

}
