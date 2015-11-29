package logic.buff;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 15/11/29.
 * it's the testing code for buff package
 */
public class BuffPackageTest {

    @Test
    public void should_have_20_effect_when_add_two_10_effect_buff() {
        BuffPackage buffPackage = new BuffPackage();
        buffPackage.addBuff(() -> 10);
        buffPackage.addBuff(() -> 10);
        assertEquals(20, buffPackage.getEffect(), 1e-3);
    }

}