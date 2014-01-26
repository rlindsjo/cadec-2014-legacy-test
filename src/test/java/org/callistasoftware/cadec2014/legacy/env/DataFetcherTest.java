package org.callistasoftware.cadec2014.legacy.env;

import java.io.IOException;

import org.callistasoftware.cadec2014.legacy.env.DataFetcher;
import org.junit.Test;

public class DataFetcherTest {

    @Test(expected=RuntimeException.class)
    public void test() throws IOException {
        new DataFetcher("http://localhost/test").get(null, null);
    }

}
