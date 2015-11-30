package logic.buff;

import logic.attribute.AttributeType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for buff package
 */
public class BuffPackageTest {

    @Test
    public void should_have_10_attack_when_have_20_attack_and_10_defence_before() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addImmediatelyBuff(new NormalAttackBuff(10));
        buffPackage.addImmediatelyBuff(new NormalAttackBuff(10));
        buffPackage.addImmediatelyBuff(new DefenceBuff(10));
        assertEquals(-10, buffPackage.getEffect().getAttribute(AttributeType.HP), 1e-3);
    }

}