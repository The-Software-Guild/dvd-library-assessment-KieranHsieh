package com.kieran.dvd_library.util;

/**
 * A utility class for processing strings
 */
public class StringUtils {
    /**
     * Builds an output string in the format %-TARGET_LENs which,
     * when passed to String.format(), will left align the formatted
     * value with TARGET_LEN characters of whitespace
     * @param targetLen The target length of the formatted String
     * @return %-TARGET_LENs
     */
    public static String buildLeftAlignedFormatStr(int targetLen) {
        return "%-" + targetLen + "s";
    }
}
