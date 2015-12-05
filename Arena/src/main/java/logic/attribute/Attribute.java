package logic.attribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 15/11/30.
 * it's the attribute
 */
public class Attribute {

    private final Map<AttributeType, Float> attribute;

    public Attribute() {
        this.attribute = new HashMap<>();
    }

    public float getAttribute(AttributeType attributeType) {
        Float value = this.attribute.get(attributeType);
        if (value == null) {
            return 0;
        } else {
            return value;
        }
    }

    public float setAttribute(AttributeType attributeType, float value) {
        Float old = this.attribute.put(attributeType, value);
        if (old == null) {
            return 0;
        } else {
            return old;
        }
    }

    public void mergeAttribute(Attribute attribute) {
        for (Map.Entry<AttributeType, Float> entry : attribute.attribute.entrySet()) {
            addAttributeValue(entry.getKey(), entry.getValue());
        }
    }

    public void addAttributeValue(AttributeType attributeType, float value) {
        setAttribute(attributeType, getAttribute(attributeType) + value);
    }
}
