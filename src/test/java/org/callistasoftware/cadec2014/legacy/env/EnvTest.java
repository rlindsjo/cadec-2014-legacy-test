package org.callistasoftware.cadec2014.legacy.env;

import java.io.IOException;

import org.callistasoftware.cadec2014.legacy.env.Env;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class EnvTest {

    @Ignore("Must be run stand alone (Env can not be reset)")
    @Test(expected=IllegalStateException.class)
    public void gettingUnitializedInstansTrowsException() {
        Env.getInstance();
        Assert.fail("Exception should have been thrown");
    }

    @Ignore("Must be run stand alone (Env can not be reset)")
    @Test(expected=IllegalStateException.class)
    public void initializingTwiceTrowsException() throws IOException {
        try {
            Env.init();
        } catch (Exception e) {
            Assert.fail("First initialization should not throw exception");
        }
        Env.init();
        Assert.fail("Exception should have been thrown");
    }

}
