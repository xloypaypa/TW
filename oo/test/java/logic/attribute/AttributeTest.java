package logic.attribute;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/30.
 * it's the testing code for attribute
 */
public class AttributeTest {

    @Test
    public void should_get_0_when_not_have_attribute() {
        Attribute attribute = new Attribute();
        assertEquals(0, attribute.getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void old_value_should_be_0_when_not_have_attribute_before() {
        Attribute attribute = new Attribute();
        assertEquals(0, attribute.setAttribute(AttributeType.HP, 10), 1e-3);
    }

    @Test
    public void should_get_2_after_add_value_1_twice() {
        Attribute attribute = new Attribute();
        attribute.addAttributeValue(AttributeType.HP, 1);
        attribute.addAttributeValue(AttributeType.HP, 1);
        assertEquals(2, attribute.getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_get_1_after_merge_value_1_attribute() {
        Attribute attribute = new Attribute();
        Attribute merge = new Attribute();
        merge.setAttribute(AttributeType.HP, 1);
        attribute.mergeAttribute(merge);
        assertEquals(1, attribute.getAttribute(AttributeType.HP), 1e-3);
    }

    @Test
    public void should_get_1_after_set_value_1() {
        Attribute attribute = new Attribute();
        attribute.setAttribute(AttributeType.HP, 1);
        assertEquals(1, attribute.getAttribute(AttributeType.HP), 1e-3);
    }

}