package com.googlecode.kevinarpe.papaya.swing;

import java.awt.event.KeyEvent;
import java.text.Normalizer;

import javax.swing.KeyStroke;

import com.googlecode.kevinarpe.papaya.annotation.FullyTested;

/**
 * Collection of static methods for {@link KeyStroke}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see KeyStroke
 */
public final class PKeyStrokeUtils {
    
    // scratch
    public static void main(String[] argArr) {
        for (char keyChar: new char[] { 'A', 'Ä', 'À', 'Á', 'Â', 'Å' }) {
            System.out.println(String.format(
                "\tgetType('%c'): %d", keyChar, Character.getType(keyChar)));
            for (Normalizer.Form form: Normalizer.Form.values()) {
                String n = Normalizer.normalize(String.valueOf(keyChar), form);
                System.out.print(String.format("'%c' -> '", keyChar));
                final int len = n.length();
                for (int i = 0; i < len; ++i) {
                    char oneChar = n.charAt(i);
                    System.out.print(oneChar);
                    System.out.print(' ');
                }
                System.out.println(String.format("' (len: %d) (%s)", n.length(), form.name()));
                if (2 == len) {
                    // Character.NON_SPACING_MARK
                    System.out.println(String.format(
                        "\tgetType([0]): %d, getType([1]): %d",
                        Character.getType(n.charAt(0)),
                        Character.getType(n.charAt(1))));
                }
            }
            System.out.println();
        }
        System.out.println("Á\u0041\u0301");
        if (!String.valueOf('Á').equals(String.valueOf('\u00C1'))) {
            throw new RuntimeException();
        }
        if (!String.valueOf('A').equals(String.valueOf('\u0041'))) {
            throw new RuntimeException();
        }
        if (!String.valueOf('Á').equals("\u0041\u0301")) {
            throw new RuntimeException();
        }
    }

    /**
     * Finds the virtual key code for a single Unicode character.
     * Example: {@code 'A'} -> {@link KeyEvent#VK_A}
     * <p>
     * The character is converted to a (one char) string, then
     * {@link KeyStroke#getKeyStroke(String)} is called.  On most systems, lower case Latin chars,
     * e.g., {@code 'a'} or {@code 'z'}, will not work.  Instead, first convert to upper case with
     * {@link Character#toUpperCase(char)}.  Additionally, on many systems, Latin chars with
     * diacritical marks, e.g., {@code 'Å'} or {@code 'Ã'}, will not work.
     * <p>
     * To programatically find virtual key codes for widget keyboard mnemonics from their text
     * labels, e.g., {@code "&Sample"}, see {@link PMnemonicHelper}.  This class also includes
     * support for Unicode decomposition to support Latin chars with diacritical marks.
     * 
     * @param keyChar
     * <ul>
     *   <li>single Unicode character, e.g., {@code '0'} or {@code 'A'}</li>
     *   <li>avoid lowercase Latin chars, e.g., {@code 'a'}</li>
     *   <li>must not be character zero</li>
     *   <li>non-Latin chars are unlikely to work</li>
     * </ul>
     * 
     * @return virtual key code from {@link KeyEvent}, e.g., {@link KeyEvent#VK_0} or
     *         {@link KeyEvent#VK_A}
     * 
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if {@code keyChar} is zero</li>
     *   <li>if {@link KeyStroke#getKeyStroke(String)} returns null -- no keystroke found</li>
     *   <li>if key code returned by {@link KeyStroke#getKeyStroke(String)} is zero</li>
     * </ul>
     * 
     * @see KeyStroke#getKeyStroke(String)
     * @see KeyEvent
     * @see PMnemonicHelper
     */
    @FullyTested
    public static int getKeyCode(char keyChar) {
        if (0 == keyChar) {
            throw new IllegalArgumentException(String.format(
                "Argument 'keyChar' is invalid: '%c' ('\\u%04x')", keyChar, (int) keyChar));
        }
        
        String keyCharStr = String.valueOf(keyChar);
        KeyStroke ks = KeyStroke.getKeyStroke(keyCharStr);
        if (null == ks) {
            throw new IllegalArgumentException(String.format(
                "Failed to find %s for character '%c' ('\\u%04x')",
                KeyStroke.class.getCanonicalName(),
                keyChar,
                (int) keyChar));
        }
        int x = ks.getKeyCode();
        if (0 == x) {
            throw new IllegalArgumentException(String.format(
                "Key code for character '%c' ('\\u%04x') is zero", keyChar, (int) keyChar));
        }
        return x;
    }
}
