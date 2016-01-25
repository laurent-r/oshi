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
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * The Class ParseUtilTest.
 */
public class ParseUtilTest {

    /**
     * Test parse hertz.
     */
    @Test
    public void testParseHertz() {
        assertEquals(1L, ParseUtil.parseHertz("1Hz"));
        assertEquals(500L, ParseUtil.parseHertz("500 Hz"));
        assertEquals(1000L, ParseUtil.parseHertz("1kHz"));
        assertEquals(1000000L, ParseUtil.parseHertz("1MHz"));
        assertEquals(1000000000L, ParseUtil.parseHertz("1GHz"));
        assertEquals(1500000000L, ParseUtil.parseHertz("1.5GHz"));
        assertEquals(1000000000000L, ParseUtil.parseHertz("1THz"));
    }

    /**
     * Test parse string.
     */
    @Test
    public void testParseString() {
        assertEquals(1, ParseUtil.parseString("foo : 1", 0));
        assertEquals(2, ParseUtil.parseString("foo", 2));
    }

    /**
     * Test parse string.
     */
    @Test
    public void testHexStringToByteArray() {
        byte[] temp = { (byte) 0x12, (byte) 0xaf };
        assertTrue(Arrays.equals(temp, ParseUtil.hexStringToByteArray("12af")));
    }
}
