package com.laptrinhjavaweb.utils;

import java.io.UnsupportedEncodingException;

public class EncodeUtils {
    public static String encoding(String text) {
        StringBuilder unicodeBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            String unicode = "\\u" + Integer.toHexString(character | 0x10000).substring(1);
            unicodeBuilder.append(unicode);
        }

        return unicodeBuilder.toString();
    }
}
