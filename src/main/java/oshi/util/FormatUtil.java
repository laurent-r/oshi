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

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Formatting utility for appending units or converting between number types.
 * 
 * @author dblock[at]dblock[dot]org
 */
public abstract class FormatUtil {

    /**
     * Added these variable for easier reading Using the IEC Standard for naming
     * the units.
     * (http://en.wikipedia.org/wiki/International_Electrotechnical_Commission)
     */
    final private static long kibiByte = 1024L;

    final private static long mebiByte = kibiByte * kibiByte;

    final private static long gibiByte = mebiByte * kibiByte;

    final private static long tebiByte = gibiByte * kibiByte;

    final private static long pebiByte = tebiByte * kibiByte;

    final private static long exbiByte = pebiByte * kibiByte;

    /**
     * Hertz related variables
     */
    final private static long kiloHertz = 1000L;

    final private static long megaHertz = kiloHertz * kiloHertz;

    final private static long gigaHertz = megaHertz * kiloHertz;

    final private static long teraHertz = gigaHertz * kiloHertz;

    final private static long petaHertz = teraHertz * kiloHertz;

    final private static long exaHertz = petaHertz * kiloHertz;

    /**
     * Format bytes into a rounded string representation using IEC standard
     * (matches Mac/Linux). To match Windows displays for KB, MB and GB, users
     * can edit the returned string to display the (incorrect) JEDEC units.
     * 
     * @param bytes
     *            Bytes.
     * @return Rounded string representation of the byte size.
     */
    public static String formatBytes(long bytes) {
        if (bytes == 1) { // bytes
            return String.format("%d byte", bytes);
        } else if (bytes < kibiByte) { // bytes
            return String.format("%d bytes", bytes);
        } else if (bytes < mebiByte && bytes % kibiByte == 0) { // KiB
            return String.format("%.0f KiB", (double) bytes / kibiByte);
        } else if (bytes < mebiByte) { // KiB
            return String.format("%.1f KiB", (double) bytes / kibiByte);
        } else if (bytes < gibiByte && bytes % mebiByte == 0) { // MiB
            return String.format("%.0f MiB", (double) bytes / mebiByte);
        } else if (bytes < gibiByte) { // MiB
            return String.format("%.1f MiB", (double) bytes / mebiByte);
        } else if (bytes % gibiByte == 0 && bytes < tebiByte) { // GiB
            return String.format("%.0f GiB", (double) bytes / gibiByte);
        } else if (bytes < tebiByte) { // GiB
            return String.format("%.1f GiB", (double) bytes / gibiByte);
        } else if (bytes % tebiByte == 0 && bytes < pebiByte) { // TiB
            return String.format("%.0f TiB", (double) bytes / tebiByte);
        } else if (bytes < pebiByte) { // TiB
            return String.format("%.1f TiB", (double) bytes / tebiByte);
        } else if (bytes % pebiByte == 0 && bytes < exbiByte) { // PiB
            return String.format("%.0f PiB", (double) bytes / pebiByte);
        } else if (bytes < exbiByte) { // PiB
            return String.format("%.1f PiB", (double) bytes / pebiByte);
        } else {
            return String.format("%d bytes", bytes);
        }
    }

    /**
     * Format hertz into a string to a rounded string representation.
     * 
     * @param hertz
     *            Hertz.
     * @return Rounded string representation of the hertz size.
     */
    public static String formatHertz(long hertz) {
        if (hertz < kiloHertz) { // Hz
            return String.format("%d Hz", hertz);
        } else if (hertz < megaHertz && hertz % kiloHertz == 0) { // KHz
            return String.format("%.0f kHz", (double) hertz / kiloHertz);
        } else if (hertz < megaHertz) {
            return String.format("%.1f kHz", (double) hertz / kiloHertz);
        } else if (hertz < gigaHertz && hertz % megaHertz == 0) { // MHz
            return String.format("%.0f MHz", (double) hertz / megaHertz);
        } else if (hertz < gigaHertz) {
            return String.format("%.1f MHz", (double) hertz / megaHertz);
        } else if (hertz < teraHertz && hertz % gigaHertz == 0) { // GHz
            return String.format("%.0f GHz", (double) hertz / gigaHertz);
        } else if (hertz < teraHertz) {
            return String.format("%.1f GHz", (double) hertz / gigaHertz);
        } else if (hertz < petaHertz && hertz % teraHertz == 0) { // THz
            return String.format("%.0f THz", (double) hertz / teraHertz);
        } else if (hertz < petaHertz) {
            return String.format("%.1f THz", (double) hertz / teraHertz);
        } else if (hertz < exaHertz && hertz % petaHertz == 0) { // EHz
            return String.format("%.0f EHz", (double) hertz / petaHertz);
        } else if (hertz < exaHertz) {
            return String.format("%.1f EHz", (double) hertz / petaHertz);
        } else {
            return String.format("%d Hz", hertz);
        }
    }

    /**
     * Formats an elapsed time in seconds as days, hh:mm:ss.
     * 
     * @param secs
     *            Elapsed seconds
     * @return A string representation of elapsed time
     */
    public static String formatElapsedSecs(long secs) {
        long eTime = secs;
        final long days = TimeUnit.SECONDS.toDays(eTime);
        eTime -= TimeUnit.DAYS.toSeconds(days);
        final long hr = TimeUnit.SECONDS.toHours(eTime);
        eTime -= TimeUnit.HOURS.toSeconds(hr);
        final long min = TimeUnit.SECONDS.toMinutes(eTime);
        eTime -= TimeUnit.MINUTES.toSeconds(min);
        final long sec = eTime;
        return String.format("%d days, %02d:%02d:%02d", days, hr, min, sec);
    }

    /**
     * Round to certain number of decimals.
     *
     * @param d
     *            Number to be rounded
     * @param decimalPlace
     *            Number of decimal places to round to
     * @return rounded result
     */
    public static float round(float d, int decimalPlace) {
        final BigDecimal bd = new BigDecimal(Float.toString(d)).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Convert unsigned int to signed long.
     * 
     * @param x
     *            Signed int representing an unsigned integer
     * @return long value of x unsigned
     */
    public static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    }

}
