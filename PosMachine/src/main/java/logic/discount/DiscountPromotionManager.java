package logic.discount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 2015/12/8.
 * it's the discount manager
 */
public final class DiscountPromotionManager {

    private static DiscountPromotionManager discountPromotionManager;

    public static DiscountPromotionManager getDiscountPromotionManager() {
        if (discountPromotionManager == null) {
            synchronized (DiscountPromotionManager.class) {
                if (discountPromotionManager == null) {
                    discountPromotionManager = new DiscountPromotionManager();
                }
            }
        }
        return discountPromotionManager;
    }

    private Map<String, DiscountPromotion> discountPromotionMap;

    private DiscountPromotionManager() {
        this.discountPromotionMap = new HashMap<>();
    }

    public void addDiscountPromotion(Collection<DiscountPromotion> discountPromotionCollection) {
        for (DiscountPromotion now : discountPromotionCollection) {
            this.discountPromotionMap.put(now.getItem(), now);
        }
    }

    public DiscountPromotion getDiscountPromotion(String item) {
        DiscountPromotion discountPromotion = this.discountPromotionMap.get(item);
        if (discountPromotion == null) {
            discountPromotion  = new DiscountPromotion(item, 100);
        }
        return discountPromotion;
    }
}
