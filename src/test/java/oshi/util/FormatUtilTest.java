/**
 * Oshi (https://github.com/dblock/oshi)
 * 
 * Copyright (c) 2010 - 2016 The Oshi Project Team
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * dblock[at]dblock[dot]org
 * alessandro[at]perucchi[dot]org
 * widdis[at]gmail[dot]com
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.util;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormatSymbols;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class FormatUtilTest.
 */
public class FormatUtilTest {

    /** The decimal separator. */
    private static char DECIMAL_SEPARATOR;

    /**
     * Sets the up class.
     */
    @BeforeClass
    public static void setUpClass() {
        // use decimal separator according to current locale
        DecimalFormatSymbols syms = new DecimalFormatSymbols();
        DECIMAL_SEPARATOR = syms.getDecimalSeparator();
    }

    /**
     * Test format bytes.
     */
    @Test
    public void testFormatBytes() {
        assertEquals("0 bytes", FormatUtil.formatBytes(0));
        assertEquals("1 byte", FormatUtil.formatBytes(1));
        assertEquals("532 bytes", FormatUtil.formatBytes(532));
        assertEquals("1 KiB", FormatUtil.formatBytes(1024));
        assertEquals("1 GiB", FormatUtil.formatBytes(1024 * 1024 * 1024));
        assertEquals("1 TiB", FormatUtil.formatBytes(1099511627776L));
    }

    /**
     * Test format bytes with decimal separator.
     */
    @Test
    public void testFormatBytesWithDecimalSeparator() {
        String expected1 = "1" + DECIMAL_SEPARATOR + "3 KiB";
        String expected2 = "2" + DECIMAL_SEPARATOR + "3 MiB";
        String expected3 = "2" + DECIMAL_SEPARATOR + "2 GiB";
        String expected4 = "1" + DECIMAL_SEPARATOR + "1 TiB";
        assertEquals(expected1, FormatUtil.formatBytes(1340));
        assertEquals(expected2, FormatUtil.formatBytes(2400016));
        assertEquals(expected3, FormatUtil.formatBytes(2400000000L));
        assertEquals(expected4, FormatUtil.formatBytes(1099511627776L + 109951162777L));
    }

    /**
     * Test format hertz.
     */
    @Test
    public void testFormatHertz() {
        assertEquals("0 Hz", FormatUtil.formatHertz(0));
        assertEquals("1 Hz", FormatUtil.formatHertz(1));
        assertEquals("999 Hz", FormatUtil.formatHertz(999));
        assertEquals("1 kHz", FormatUtil.formatHertz(1000));
        assertEquals("1 MHz", FormatUtil.formatHertz(1000 * 1000));
        assertEquals("1 GHz", FormatUtil.formatHertz(1000 * 1000 * 1000));
        assertEquals("1 THz", FormatUtil.formatHertz(1000L * 1000L * 1000L * 1000L));
    }

    /**
     * Test format elapsed secs
     */
    public void testFormatElapsedSecs() {
        assertEquals("0 days, 00:00:00", FormatUtil.formatElapsedSecs(0));
        assertEquals("0 days, 03:25:45", FormatUtil.formatElapsedSecs(12345));
        assertEquals("1 days, 10:17:36", FormatUtil.formatElapsedSecs(123456));
        assertEquals("14 days, 06:56:07", FormatUtil.formatElapsedSecs(1234567));
    }

    /**
     * Test round.
     */
    @Test
    public void testRound() {
        assertEquals(42.42, FormatUtil.round(42.423f, 2), 0.00001f);
        assertEquals(42.43, FormatUtil.round(42.425f, 2), 0.00001f);
        assertEquals(42.5, FormatUtil.round(42.499f, 2), 0.00001f);
        assertEquals(42, FormatUtil.round(42, 2), 0.00001f);
    }
}
