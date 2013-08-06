package com.googlecode.kevinarpe.papaya.swing;

/*
 * #%L
 * This file is part of Papaya Swing.
 * %%
 * Copyright (C) 2013 Kevin Connor ARPE (kevinarpe@gmail.com)
 * %%
 * Papaya Swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GPL Classpath Exception:
 * This project is subject to the "Classpath" exception as provided in
 * the LICENSE file that accompanied this code.
 * 
 * Papaya Swing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Papaya Swing.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicHTML;

import com.google.common.base.Objects;
import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;

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
 * correctly through Unicode decomposition.  Example: å -> a & ̊
 * <p>
 * Example: {@code "Save &As..."}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Save &As..."}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Save As..."}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code 'A'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code 5}</li>
 * </ul>
 * Example: {@code "S&ave As..."}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "S&ave As..."}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Save As..."}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code 'a'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "H&åkon"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "H&åkon"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Håkon"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code 'å'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_A}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code 1}</li>
 * </ul>
 * Example: {@code "Sample"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Sample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Sample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && Example"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Sample && Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Sample & Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample && &Example"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Sample && &Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "Sample & Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code 'E'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_E}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code 9}</li>
 * </ul>
 * Example: {@code "<html><b>S</b>ample"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "<html><b>S</b>ample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "<html><b>S</b>ample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample\nAnd\nExample"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Sample\nAnd\nExample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}:
 *   {@code "<html>Sample<br>And<br>Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "Sample\nAnd\n&Example"}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "Sample\nAnd\n&Example"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}:
 *   {@code "<html>Sample<br>And<br><u>E</u>xample"}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code true}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code 'E'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@link KeyEvent#VK_E}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code null}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code null}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code null}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code ""}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code ""}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code ""}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * Example: {@code "   "}
 * <ul>
 *   <li>{@link PJComponentToolTipTextParser#textBeforeParse}: {@code "   "}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringBeforeParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#textAfterParse}: {@code "   "}</li>
 *   <li>{@link PJComponentToolTipTextParser#isHTMLStringAfterParse}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#hasMnemonic}: {@code false}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyChar}: {@code '\u0000'}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicKeyCode}: {@code 0}</li>
 *   <li>{@link PJComponentToolTipTextParser#mnemonicIndex}: {@code -1}</li>
 * </ul>
 * When setting the mnemonic for a button:
 * <ul>
 *   <li>do <b>not</b> use {@link PJComponentToolTipTextParser#mnemonicKeyChar} with
 *   {@link AbstractButton#setMnemonic(char)};</li>
 *   <li>instead, use {@link PJComponentToolTipTextParser#mnemonicKeyCode} with
 *   {@link AbstractButton#setMnemonic(int)}</li>
 * </ul>
 * All instances of this class are fully immutable, thus thread-safe, and safe to store as
 * {@code static final} constants.
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
@NotFullyTested
public final class PJComponentToolTipTextParser {
    
    /**
     * Original text label to the constructor {@link #PJComponentTextParser(String)}.  This text
     * may contain mnemonic markers, e.g., {@code "&Sample"}.
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
     * Parses a text label for a widget, such as a button, to extract mnemonic metadata.  For
     * examples, {@linkplain PJComponentToolTipTextParser read the class docs}.
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
     * @see PJComponentToolTipTextParser
     * @see #textBeforeParse
     * @see #isHTMLStringBeforeParse
     * @see #textAfterParse
     * @see #isHTMLStringAfterParse
     * @see #hasMnemonic
     * @see #mnemonicKeyChar
     * @see #mnemonicKeyCode
     * @see #mnemonicIndex
     */
    public PJComponentToolTipTextParser(String optText) {
        this((JComponent) null, optText);
    }
    
    public PJComponentToolTipTextParser(JComponent optComp, String optText) {
        this.textBeforeParse = optText;
        
        if (null != optComp && PJComponentUtils.isHtmlDisabled(optComp)) {
            this.isHTMLStringBeforeParse = false;
            this.textAfterParse = optText;
            this.isHTMLStringAfterParse = false;
        }
        else {
            this.isHTMLStringBeforeParse = BasicHTML.isHTMLString(optText);
            
            String _textAfterParse = optText;
            boolean _isHTMLStringAfterParse = isHTMLStringBeforeParse;
            
            if (null != optText && !this.isHTMLStringBeforeParse) {
                _textAfterParse = _textAfterParse.replace("\r\n", "\n");
                _textAfterParse = _textAfterParse.replace("\n", StringUtils.NEW_LINE);
                if (_textAfterParse.contains(StringUtils.NEW_LINE)) {
                    _textAfterParse =
                        "<html>" + _textAfterParse.replace(StringUtils.NEW_LINE, "<br>");
                    _isHTMLStringAfterParse = true;
                }
            }
            this.textAfterParse = _textAfterParse;
            this.isHTMLStringAfterParse = _isHTMLStringAfterParse;
        }
    }
    
    @Override
    public int hashCode() {
        int x =
            Objects.hashCode(
                textBeforeParse,
                isHTMLStringBeforeParse,
                textAfterParse,
                isHTMLStringAfterParse);
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        // Ref: http://stackoverflow.com/a/5039178/257299
        boolean result = (this == obj);
        if (!result && obj instanceof PJComponentToolTipTextParser) {
            final PJComponentToolTipTextParser other = (PJComponentToolTipTextParser) obj;
            result =
                this.isHTMLStringBeforeParse == other.isHTMLStringBeforeParse
                && this.isHTMLStringAfterParse == other.isHTMLStringAfterParse
                && Objects.equal(this.textBeforeParse, other.textBeforeParse)
                && Objects.equal(this.textAfterParse, other.textAfterParse)
                ;
        }
        return result;
    }

    @Override
    public String toString() {
        String x = String.format(
            "%s: ["
            + "%n\ttextBeforeParse: '%s'"
            + "%n\tisHTMLStringBeforeParse: %s"
            + "%n\ttextAfterParse: '%s'"
            + "%n\tisHTMLStringAfterParse: %s"
            + "%n\t]",
            PJComponentToolTipTextParser.class.getCanonicalName(),
            textBeforeParse,
            isHTMLStringBeforeParse,
            textAfterParse,
            isHTMLStringAfterParse);
        return x;
    }
}
