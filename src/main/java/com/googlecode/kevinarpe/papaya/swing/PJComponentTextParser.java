package com.googlecode.kevinarpe.papaya.swing;

import java.awt.event.KeyEvent;
import java.text.Normalizer;

import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicHTML;

import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;

/**
 * Parses widget text labels for optional mnemonics, e.g., {@code "Save &As..."}.  This class
 * should be used by widget constructors that accept text labels and related
 * {@code setText(String)} methods.  The ampersand character, {@code '&'}, is used before the
 * mnemonic character, e.g., {@code "Save &As..."}  The sequence {@code "&&"} will produce a
 * single, literal ampersand character: {@code '&'}.
 * <p>
 * Text is tested for HTML via {@link BasicHTML#isHTMLString(String)}.  As of writing, this test is
 * very simple: If the string exactly starts with {@code "<html>"} then it is HTML.  If the text
 * is not HTML, but contains newlines, after parsing the mnemonic, the text is converted to HTML
 * (prepend {@code "<html>"} and convert {@code "<"} to {@code "&lt;"}) and newlines are converted
 * to {@code "<br>"}.  Additionally, if a mnemonic character was found, the character is surrounded
 * by {@code "<u>"} and {@code "</u>"} to simulate the underline effect common on most windowing
 * systems for keyboard mnemonics.
 * <p>
 * A text label may contain exactly zero or one marked mnemonic character, but an unlimited number
 * of escaped markers ({@code "&&"}).
 * <p>
 * When necessary, Latin characters that use diacritical marks, such as the letter å, are handled
 * correctly through Unicode decomposition.  Example: Å -> A & ̊
 * <p>
 * Example: {@code "Save &As..."}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Save &As..."}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Save As..."}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code 'A'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code 5}</li>
 * </ul>
 * Example: {@code "S&ave As..."}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "S&ave As..."}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Save As..."}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code 'a'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "H&åkon"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "H&åkon"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Håkon"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code 'å'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "Sample"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Sample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Sample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && Example"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Sample && Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Sample & Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && &Example"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Sample && &Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "Sample & Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code 'E'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_E}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code 9}</li>
 * </ul>
 * Example: {@code "<html><b>S</b>ample"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "<html><b>S</b>ample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "<html><b>S</b>ample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample\nAnd\nExample"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Sample\nAnd\nExample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}:
 *   {@code "<html>Sample<br>And<br>Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample\nAnd\n&Example"}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "Sample\nAnd\n&Example"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}:
 *   {@code "<html>Sample<br>And<br><u>E</u>xample"}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code 'E'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_E}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code null}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code null}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code null}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code ""}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code ""}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code ""}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "   "}
 * <ul>
 *   <li>{@link PJComponentTextParser#textBeforeParse}: {@code "   "}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#textAfterParse}: {@code "   "}</li>
 *   <li>{@link PJComponentTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * When setting the mnemonic for a button:
 * <ul>
 *   <li>do <b>not</b> use {@link PJComponentTextParser#mnemonicKeyChar} with
 *   {@link AbstractButton#setMnemonic(char)};</li>
 *   <li>instead, use {@link PJComponentTextParser#mnemonicKeyCode} with
 *   {@link AbstractButton#setMnemonic(int)}</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see AbstractButton#setMnemonic(int)
 * @see AbstractButton#setDisplayedMnemonicIndex(int)
 * @see #textBeforeParse
 * @see #isHTMLStringBeforeParse
 * @see #textAfterParse
 * @see #isHTMLStringAfterParse
 * @see #hasMnemonic
 * @see #mnemonicKeyChar
 * @see #mnemonicKeyCode
 * @see #mnemonicIndex
 */
@FullyTested
public class PJComponentTextParser {
    
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
     * @see #textAfterParse
     */
    public final String textBeforeParse;
    
    /**
     * If {@link #textBeforeParse} is HTML string according to
     * {@link BasicHTML#isHTMLString(String)}.
     */
    public final boolean isHTMLStringBeforeParse;
    
    /**
     * Text label cleaned of mnemonic markers, e.g., {@code "&Sample" -> "Sample"}.  Escaped marker
     * chars will each be reduced to a single literal marker, e.g.,
     * {@code "Sample && Example" -> "Sample & Example"}.
     * <p>
     * This value is intended to be used with methods such as
     * {@link AbstractButton#setText(String)}.
     * 
     * @see #textBeforeParse
     */
    public final String textAfterParse;
    
    /**
     * If {@link #textAfterParse} is HTML string according to
     * {@link BasicHTML#isHTMLString(String)}.
     */
    public final boolean isHTMLStringAfterParse;
    
    /**
     * If {@code true}, the original {@link #textBeforeParse} has exactly one marked mnemonic
     * character, e.g., {@code "&Sample"}.
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
     *   <li>do <b>not</b> use {@link PJComponentTextParser#mnemonicKeyChar} with
     *   {@link AbstractButton#setMnemonic(char)};</li>
     *   <li>instead, use {@link PJComponentTextParser#mnemonicKeyCode} with
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
     *   <li>do <b>not</b> use {@link PJComponentTextParser#mnemonicKeyChar} with
     *   {@link AbstractButton#setMnemonic(char)};</li>
     *   <li>instead, use {@link PJComponentTextParser#mnemonicKeyCode} with
     *   {@link AbstractButton#setMnemonic(int)}</li>
     * </ul>
     * 
     * @see #mnemonicKeyChar
     * @see #mnemonicIndex
     */
    public final int mnemonicKeyCode;
    
    /**
     * The index in {@link #textAfterParse} for {@link #mnemonicKeyChar}, e.g.,
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
     * examples, {@linkplain PJComponentTextParser read the class docs}.
     * 
     * @param label
     *        text label to parse for mnemonic metadata.  May be empty or {@code null}.
     * 
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if last character is a dangling mnemonic marker, e.g., {@code "Sample&"}</li>
     *   <li>if a second character is marked as the mnemonic, e.g., {@code "&Sa&mple"}</li>
     *   <li>if marked character does not have a virtual key code, e.g., {@code "&大阪"}</li>
     * </ul>
     * 
     * @see PJComponentTextParser
     * @see #textBeforeParse
     * @see #isHTMLStringBeforeParse
     * @see #textAfterParse
     * @see #isHTMLStringAfterParse
     * @see #hasMnemonic
     * @see #mnemonicKeyChar
     * @see #mnemonicKeyCode
     * @see #mnemonicIndex
     */
    public PJComponentTextParser(String label) {
        this.textBeforeParse = label;
        
        isHTMLStringBeforeParse = BasicHTML.isHTMLString(label);
        
        String _labelAfterParse = label;
        boolean _isHTMLStringAfterParse = isHTMLStringBeforeParse;
        boolean _hasMnemonic = false;
        char _mnemonicKeyChar = DEFAULT_MNEMONIC_KEY_CHAR;
        int _mnemonicKeyCode = DEFAULT_MNEMONIC_KEY_CODE;
        int _mnemonicIndex = DEFAULT_MNEMONIC_INDEX;
        
        if (null != _labelAfterParse && !isHTMLStringBeforeParse) {
            final String originalLabel = _labelAfterParse;
            _labelAfterParse = _labelAfterParse.replace("\r\n", "\n");
            _labelAfterParse = _labelAfterParse.replace("\n", StringUtils.NEW_LINE);
            int labelAfterParseLen = _labelAfterParse.length();
            int escapeCount = 0;  // each pair of mnemonic markers ("&&") is one escape
            int index = -1;
            while (true) {
                index = _labelAfterParse.indexOf(MARKER, 1 + index);
                if (-1 == index) {
                    break;
                }
                if (index == labelAfterParseLen - 1) {
                    throw new IllegalArgumentException(String.format(
                        "Last char must not be a mnemonic marker ('%c'): '%s'",
                        MARKER, originalLabel));
                }
                if (index < labelAfterParseLen - 1) {
                    final char nextChar = _labelAfterParse.charAt(1 + index);
                    if (MARKER == nextChar) {
                        _labelAfterParse = StringUtils.removeCharAt(_labelAfterParse, index);
                        ++escapeCount;
                        --labelAfterParseLen;
                        continue;
                    }
                }
                if (_hasMnemonic) {
                    throw new IllegalArgumentException(String.format(
                        "Second mnemonic marker ('%c') found at index %d: '%s'",
                        MARKER, index + escapeCount + 1, originalLabel));
                }
                _mnemonicKeyChar = _labelAfterParse.charAt(1 + index);
                try {
                    _mnemonicKeyCode = _tryGetKeyCode(_mnemonicKeyChar);
                }
                catch (RuntimeException e) {
                    _mnemonicKeyCode = _tryGetKeyCodeWithUnicodeDecomposition(_mnemonicKeyChar);
                    if (DEFAULT_MNEMONIC_KEY_CODE == _mnemonicKeyCode) {
                        String msg = String.format(
                            "Invalid mnemonic char ('%c' -> '\\u%04x') at index %d: '%s'",
                            _mnemonicKeyChar,
                            (int) _mnemonicKeyChar,
                            index + escapeCount + 1,
                            originalLabel);
                        throw new IllegalArgumentException(msg, e);
                    }
                }
                _mnemonicIndex = index;
                _hasMnemonic = true;
                _labelAfterParse = StringUtils.removeCharAt(_labelAfterParse, index);
                --labelAfterParseLen;
            }  // end while(true)
            
            if (_labelAfterParse.contains(StringUtils.NEW_LINE)) {
                if (_hasMnemonic) {
                    String before = _labelAfterParse.substring(0, _mnemonicIndex);
                    String after = _labelAfterParse.substring(_mnemonicIndex + 1);
                    before = before.replace("<", "&lt;");
                    after = after.replace("<", "&lt;");
                    _labelAfterParse = before + "<u>" + _mnemonicKeyChar + "</u>" + after;
                    _mnemonicIndex = -1;
                }
                else {
                    _labelAfterParse = _labelAfterParse.replace("<", "&lt;");
                }
                _labelAfterParse =
                    "<html>" + _labelAfterParse.replace(StringUtils.NEW_LINE, "<br>");
                _isHTMLStringAfterParse = true;
            }
        }
        
        textAfterParse = _labelAfterParse;
        isHTMLStringAfterParse = _isHTMLStringAfterParse;
        hasMnemonic = _hasMnemonic;
        mnemonicKeyChar = _mnemonicKeyChar;
        mnemonicKeyCode = _mnemonicKeyCode;
        mnemonicIndex = _mnemonicIndex;
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
