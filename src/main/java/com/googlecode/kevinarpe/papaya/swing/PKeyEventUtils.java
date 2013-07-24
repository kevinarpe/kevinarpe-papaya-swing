package com.googlecode.kevinarpe.papaya.swing;

import javax.swing.KeyStroke;

public final class PKeyEventUtils {

    public static int getKeyCode(char keyChar) {
        //Character.toUpperCase(ch)
        String keyCharStr = String.valueOf(keyChar);
        KeyStroke ks = KeyStroke.getKeyStroke(keyCharStr);
        if (null == ks) {
            throw new IllegalArgumentException(String.format(
                "Failed to find %s for character '%c' (\\u%04x)",
                KeyStroke.class.getCanonicalName(),
                keyChar,
                (int) keyChar));
        }
        int x = ks.getKeyCode();
        return x;
    }
}
