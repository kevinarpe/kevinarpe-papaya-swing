package com.googlecode.kevinarpe.papaya.swing;

import java.awt.event.KeyEvent;
import java.text.Normalizer;

import javax.swing.AbstractButton;

import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Parses widget text labels for optional mnemonics, e.g., {@code "Save &As..."}.  This class
 * should be used by widget constructors that accept text labels and related
 * {@code setText(String)} methods.  The ampersand character, {@code '&'}, is used before the
 * mnemonic character, e.g., {@code "Save &As..."}  The sequence {@code "&&"} will produce a
 * single, literal ampersand character: {@code '&'}.
 * <p>
 * A text label may contain exactly zero or one marked mnemonic character, but an unlimited number
 * of escaped markers ({@code "&&"}).
 * <p>
 * When necessary, Latin characters that use diacritical marks, such as the letter å, are handled
 * correctly through Unicode decomposition.  Example: Å -> A & ̊
 * <p>
 * Example: {@code "Save &As..."}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "Save &As..."}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Save As..."}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code true}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code 'A'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code 5}</li>
 * </ul>
 * Example: {@code "S&ave As..."}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "S&ave As..."}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Save As..."}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code true}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code 'a'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "H&åkon"}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "H&åkon"}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Håkon"}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code true}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code 'å'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "Sample"}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "Sample"}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Sample"}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code false}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && Example"}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "Sample && Example"}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Sample & Example"}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code false}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && &Example"}
 * <ul>
 *   <li>{@link PMnemonicHelper#label}: {@code "Sample && &Example"}</li>
 *   <li>{@link PMnemonicHelper#labelWithoutMarkers}: {@code "Sample & Example"}</li>
 *   <li>{@link PMnemonicHelper#hasMnemonic}: {@code true}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyChar}: {@code 'E'}</li>
 *   <li>{@link PMnemonicHelper#mnemonicKeyCode}: {@link KeyEvent#VK_E}</li>
 *   <li>{@link PMnemonicHelper#mnemonicIndex}: {@code 9}</li>
 * </ul>
 * When setting the mnemonic for a button:
 * <ul>
 *   <li>do <b>not</b> use {@link PMnemonicHelper#mnemonicKeyChar} with
 *   {@link AbstractButton#setMnemonic(char)};</li>
 *   <li>instead, use {@link PMnemonicHelper#mnemonicKeyCode} with
 *   {@link AbstractButton#setMnemonic(int)}</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see AbstractButton#setMnemonic(int)
 * @see AbstractButton#setDisplayedMnemonicIndex(int)
 * @see #label
 * @see #labelWithoutMarkers
 * @see #hasMnemonic
 * @see #mnemonicKeyChar
 * @see #mnemonicKeyCode
 * @see #mnemonicIndex
 */
@FullyTested
public class PMnemonicHelper {
    
    /**
     * The character used to mark a mnemonic, e.g., {@code "&Sample"} to mark {@code S}.
     */
    public static final char MARKER = '&';
    
    static final char DEFAULT_MNEMONIC_KEY_CHAR = 0;
    static final int DEFAULT_MNEMONIC_KEY_CODE = 0;
    static final int DEFAULT_MNEMONIC_INDEX = -1;
    
    /**
     * Original text label to the constructor {@link #PMnemonicHelper(String)}.  This text may
     * contain mnemonic markers, e.g., {@code "&Sample"}.
     * 
     * @see #labelWithoutMarkers
     */
    public final String label;
    
    /**
     * Text label cleaned of mnemonic markers, e.g., {@code "&Sample" -> "Sample"}.  Escaped marker
     * chars will each be reduced to a single literal marker, e.g.,
     * {@code "Sample && Example" -> "Sample & Example"}.
     * <p>
     * This value is intended to be used with methods such as
     * {@link AbstractButton#setText(String)}.
     * 
     * @see #label
     */
    public final String labelWithoutMarkers;
    
    /**
     * If {@code true}, the original {@link #label} has exactly one marked mnemonic character,
     * e.g., {@code "&Sample"}.
     */
    public final boolean hasMnemonic;
    
    /**
     * The Unicode character marked as the mnemonic character, e.g., {@code "&Sample"} marks
     * {@code S}.
     * <p>
     * If {@link #hasMnemonic} is {@code false}, this field has value zero.
     * <p>
     * When setting the mnemonic for a button:
     * <ul>
     *   <li>do <b>not</b> use {@link PMnemonicHelper#mnemonicKeyChar} with
     *   {@link AbstractButton#setMnemonic(char)};</li>
     *   <li>instead, use {@link PMnemonicHelper#mnemonicKeyCode} with
     *   {@link AbstractButton#setMnemonic(int)}</li>
     * </ul>
     * 
     * @see #hasMnemonic
     * @see #mnemonicKeyCode
     */
    public final char mnemonicKeyChar;
    
    /**
     * The (case-insensitive) virtual key code for the marked mnemonic character, e.g.,
     * {@code "&Sample"} or {@code "&sample"} -> {@link KeyEvent#VK_S}.
     * <p>
     * If {@link #hasMnemonic} is {@code false}, this field has value zero.
     * <p>
     * When setting the mnemonic for a button:
     * <ul>
     *   <li>do <b>not</b> use {@link PMnemonicHelper#mnemonicKeyChar} with
     *   {@link AbstractButton#setMnemonic(char)};</li>
     *   <li>instead, use {@link PMnemonicHelper#mnemonicKeyCode} with
     *   {@link AbstractButton#setMnemonic(int)}</li>
     * </ul>
     * 
     * @see #mnemonicKeyChar
     * @see #mnemonicIndex
     */
    public final int mnemonicKeyCode;
    
    /**
     * The index in {@link #labelWithoutMarkers} for {@link #mnemonicKeyChar}, e.g.,
     * {@code "Sample && &Example"} -> {@code "Sample & Example"} -> index of {@code 'E'} -> 9.
     * <p>
     * If {@link #hasMnemonic} is {@code false}, this field has value {@code -1} (negative one).
     * <p>
     * This value may be used with {@link AbstractButton#setDisplayedMnemonicIndex(int)}.
     * 
     * @see #mnemonicKeyCode
     */
    public final int mnemonicIndex;

    /**
     * Parses a text label for a widget, such as a button, to extract mnemonic metadata.  For
     * examples, {@linkplain PMnemonicHelper read the class docs}.
     * 
     * @param label
     *        text label to parse for mnemonic metadata.  May be empty, but not {@code null}.
     * 
     * @throws NullPointerException
     *         if {@code label} is {@code null}
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if last character is a dangling mnemonic marker, e.g., {@code "Sample&"}</li>
     *   <li>if a second character is marked as the mnemonic, e.g., {@code "&Sa&mple"}</li>
     *   <li>if marked character does not have a virtual key code, e.g., {@code "&大阪"}</li>
     * </ul>
     * 
     * @see PMnemonicHelper
     * @see #label
     * @see #labelWithoutMarkers
     * @see #hasMnemonic
     * @see #mnemonicKeyChar
     * @see #mnemonicKeyCode
     * @see #mnemonicIndex
     */
    public PMnemonicHelper(String label) {
        this.label = ObjectArgs.checkNotNull(label, "label");
        
        final String originalLabel = label;
        int labelLen = label.length();
        boolean foundMnemonic = false;
        int escapeCount = 0;  // each pair of mnemonic markers ("&&") is one escape
        int index = -1;
        char keyChar = DEFAULT_MNEMONIC_KEY_CHAR;
        int keyCode = DEFAULT_MNEMONIC_KEY_CODE;
        int charIndex = DEFAULT_MNEMONIC_INDEX;
        while (true) {
            index = label.indexOf(MARKER, 1 + index);
            if (-1 == index) {
                break;
            }
            if (index == labelLen - 1) {
                throw new IllegalArgumentException(String.format(
                    "Last char must not be a mnemonic marker ('%c'): '%s'", MARKER, originalLabel));
            }
            if (index < labelLen - 1) {
                final char nextChar = label.charAt(1 + index);
                if (MARKER == nextChar) {
                    label = StringUtils.removeCharAt(label, index);
                    ++escapeCount;
                    --labelLen;
                    continue;
                }
            }
            if (foundMnemonic) {
                throw new IllegalArgumentException(String.format(
                    "Second mnemonic marker ('%c') found at index %d: '%s'",
                    MARKER, index + escapeCount + 1, originalLabel));
            }
            keyChar = label.charAt(1 + index);
            try {
                keyCode = _tryGetKeyCode(keyChar);
            }
            catch (RuntimeException e) {
                keyCode = _tryGetKeyCodeWithUnicodeDecomposition(keyChar);
                if (DEFAULT_MNEMONIC_KEY_CODE == keyCode) {
                    String msg = String.format(
                        "Invalid mnemonic char ('%c' -> '\\u%04x') at index %d: '%s'",
                        keyChar, (int) keyChar, index + escapeCount + 1, originalLabel);
                    throw new IllegalArgumentException(msg, e);
                }
            }
            charIndex = index;
            foundMnemonic = true;
            label = StringUtils.removeCharAt(label, index);
            --labelLen;
        }  // end while(true)
        
        labelWithoutMarkers = label;
        hasMnemonic = foundMnemonic;
        mnemonicKeyChar = keyChar;
        mnemonicKeyCode = keyCode;
        mnemonicIndex = charIndex;
    }
    
    private static int _tryGetKeyCode(char keyChar) {
        int keyCode = 0;
        try {
            keyCode = PKeyStrokeUtils.getKeyCode(keyChar);
        }
        catch (RuntimeException e) {
            char maybeLetterOrDigitToUpper = Character.toUpperCase(keyChar);
            try {
                keyCode =
                    PKeyStrokeUtils.getKeyCode(maybeLetterOrDigitToUpper);
            }
            catch (RuntimeException e2) {
                throw e;
            }
        }
        return keyCode;
    }
    
    private static int _tryGetKeyCodeWithUnicodeDecomposition(char keyChar) {
        int keyCode = DEFAULT_MNEMONIC_KEY_CODE;
        // You are now entering The Insane Unicode Zone.
        // On many platforms, if the keyChar uses a diacritic, such as Å or å, the virtual
        // key code cannot be found.  Instead, try to decompose the character to its Latin
        // letter and diacritic.  Example: Å -> A & ̊ 
        String decomp = Normalizer.normalize(String.valueOf(keyChar), Normalizer.Form.NFD);
        int decompLen = decomp.length();
        if (2 == decompLen) {
            char maybeLetterOrDigit = decomp.charAt(0);
            char maybeDiacritic = decomp.charAt(1);
            int maybeDiacriticType = Character.getType(maybeDiacritic);
            if (Character.NON_SPACING_MARK == maybeDiacriticType) {
                try {
                    keyCode = _tryGetKeyCode(maybeLetterOrDigit);
                }
                catch (RuntimeException e2) {
                    // ignore
                }
            }
        }
        return keyCode;
    }
}
