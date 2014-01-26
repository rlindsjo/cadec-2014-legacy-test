package org.callistasoftware.cadec2014.legacy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.callistasoftware.cadec2014.legacy.env.DataFetcher;
import org.callistasoftware.cadec2014.legacy.env.Env;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class BookControllerTest {

    private static final byte[] IMAGE_DATA = new byte[0];
    private static final byte[] JSON = "{}".getBytes();

    @Ignore
    @Test
    public void cancellingInputReturnsDefaultBook() {
    }

    @Ignore
    @Test
    public void whenFetchingFailsShowError() {
    }

    @Ignore
    @Test
    public void whenParsingFailsShowError() {
    }

    @Ignore
    @Test
    public void whenParsingSucceedsReturnBook() {
    }
}
