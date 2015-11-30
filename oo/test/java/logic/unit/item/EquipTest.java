package logic.unit.item;

import logic.attribute.AttributeType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for equip
 */
public class EquipTest {

    @Test
    public void equip_with_10_defence_should_get_10_defence() {
        assertEquals(10, new Equip("", 10).getDefence().getEffect().getAttribute(AttributeType.SAVE_HP), 1e-3);
    }

}