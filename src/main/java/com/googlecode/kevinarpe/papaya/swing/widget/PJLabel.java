package com.googlecode.kevinarpe.papaya.swing.widget;

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

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PJComponentTextParser;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.demo.PJLabelDemo;

/**
 * Extension of {@link JLabel}.
 * <p>
 * <b>Label Text with Mnemonic Markers</b>
 * <p>
 * Many other GUI toolkits support mnemonic markers embedded in text to set keyboard shortcuts,
 * e.g., {@code "&Sample"} sets letter S as the keyboard shortcut character.  The method
 * {@link #setText(String)} incorporates the class {@link PJComponentTextParser} to enrich the text
 * label feature of {@code JLabel}.  It is also called by any constructor that accepts a
 * {@link String} parameter.
 * <p>
 * <b>{@link SwingConstants} vs. Enums</b>
 * <p>
 * As an alternative to the integer constants from interface {@link SwingConstants}, e.g.,
 * {@link SwingConstants#RIGHT}, two enums are used: {@link PHorizontalAlignment} and
 * {@link PVerticalAlignment}.  Parallel versions of existing constructors and methods exist for
 * these enums, such as:
 * <ul>
 *   <li>{@link #PJLabel(String, PHorizontalAlignment)}</li>
 *   <li>{@link #setHorizontalAlignment(PHorizontalAlignment)}</li>
 *   <li>{@link #getHorizontalAlignmentAsEnum()}</li>
 * </ul>
 * For a simple demo using this widget, see {@link PJLabelDemo}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see JLabel
 * @see #setText(String)
 */
@FullyTested
@SuppressWarnings("serial")
public class PJLabel
extends JLabel {
    
    /**
     * Stores defaults for {@link PJLabel}.
     * <p>
     * All instances of this class are fully immutable, thus thread-safe, and safe to store as
     * {@code static final} constants.
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     *
     * @see PJLabel#DEFAULTS
     * @see PJLabel#DEFAULTS_FOR_ICON_ONLY
     */
    public static final class PJLabelDefaults {
        
        /**
         * Default value for {@link PJLabel#getText()}.
         */
        public final String text;
        
        /**
         * Default value for {@link PJLabel#getDisplayedMnemonic()}.
         */
        public final int displayedMnemonicKeyCode;
        
        /**
         * Default value for {@link PJLabel#getDisplayedMnemonicIndex()}.
         */
        public final int displayedMnemonicIndex;
        
        /**
         * Default value for {@link PJLabel#getIcon()}.
         */
        public final Icon icon;
        
        /**
         * Default value for {@link PJLabel#getHorizontalAlignmentAsEnum()}.
         */
        public final PHorizontalAlignment horizontalAlignment;
        
        /**
         * Default value for {@link PJLabel#getVerticalAlignmentAsEnum()}.
         */
        public final PVerticalAlignment verticalAlignment;
        
        /**
         * Default value for {@link PJLabel#getHorizontalTextPositionAsEnum()}.
         */
        public final PHorizontalAlignment horizontalTextPosition;
        
        /**
         * Default value for {@link PJLabel#getVerticalTextPositionAsEnum()}.
         */
        public final PVerticalAlignment verticalTextPosition;
        
        private PJLabelDefaults(JLabel x) {
            ObjectArgs.checkNotNull(x, "x");
            
            this.text = x.getText();
            this.displayedMnemonicKeyCode = x.getDisplayedMnemonic();
            this.displayedMnemonicIndex = x.getDisplayedMnemonicIndex();
            this.icon = x.getIcon();
            
            this.horizontalAlignment =
                PHorizontalAlignment.valueOf(x.getHorizontalAlignment());
            
            this.verticalAlignment =
                PVerticalAlignment.valueOf(x.getVerticalAlignment());
            
            this.horizontalTextPosition =
                PHorizontalAlignment.valueOf(x.getHorizontalTextPosition());
            
            this.verticalTextPosition =
                PVerticalAlignment.valueOf(x.getVerticalTextPosition());
        }
    }
    
    /**
     * Defaults for {@link PJLabel} when the constructor uses a {@link String} and optionally an
     * {@link Icon}.
     */
    public static final PJLabelDefaults DEFAULTS;
    
    /**
     * Defaults for {@link PJLabel} when the constructor uses an {@link Icon}, but does not use a
     * {@link String}.
     */
    public static final PJLabelDefaults DEFAULTS_FOR_ICON_ONLY;
    
    static {
        JLabel x = new JLabel();
        DEFAULTS = new PJLabelDefaults(x);
        
        JLabel y = new JLabel(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                // empty
            }

            @Override
            public int getIconWidth() {
                return 17;
            }

            @Override
            public int getIconHeight() {
                return 31;
            }
        });
        
        DEFAULTS_FOR_ICON_ONLY = new PJLabelDefaults(y);
        
        @SuppressWarnings("unused")
        int dummy = 1;  // debug breakpoint
    }
    
    private String _originalText;

    /**
     * @see JLabel#JLabel()
     */
    public PJLabel() {
        super();
        _PJLabel_init(DEFAULTS.text);
    }

    /**
     * @see JLabel#JLabel(Icon)
     */
    public PJLabel(Icon image) {
        super(image);
        _PJLabel_init(DEFAULTS_FOR_ICON_ONLY.text);
    }

    /**
     * It is better to use {@link #PJLabel(Icon, PHorizontalAlignment)}.
     */
    public PJLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        _PJLabel_init(DEFAULTS_FOR_ICON_ONLY.text);
    }

    /**
     * @see JLabel#JLabel(Icon, int)
     */
    public PJLabel(Icon image, PHorizontalAlignment horizontalAlignment) {
        super(
            image,
            ObjectArgs.checkNotNull(horizontalAlignment, "horizontalAlignment").value);
        _PJLabel_init(DEFAULTS_FOR_ICON_ONLY.text);
    }

    /**
     * @see JLabel#JLabel(String)
     * @see #setText(String)
     */
    public PJLabel(String text) {
        super(text);
        _PJLabel_init(text);
    }

    /**
     * It is better to use {@link #PJLabel(String, PHorizontalAlignment)}.
     */
    public PJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        _PJLabel_init(text);
    }

    /**
     * @see JLabel#JLabel(String, int)
     * @see #setText(String)
     */
    public PJLabel(String text, PHorizontalAlignment horizontalAlignment) {
        super(
            text,
            ObjectArgs.checkNotNull(horizontalAlignment, "horizontalAlignment").value);
        _PJLabel_init(text);
    }

    /**
     * It is better to use {@link #PJLabel(String, Icon, PHorizontalAlignment)}.
     */
    public PJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        _PJLabel_init(text);
    }

    /**
     * @see JLabel#JLabel(String, Icon, int)
     * @see #setText(String)
     */
    public PJLabel(String text, Icon icon, PHorizontalAlignment horizontalAlignment) {
        super(
            text,
            icon,
            ObjectArgs.checkNotNull(horizontalAlignment, "horizontalAlignment").value);
        _PJLabel_init(text);
    }
    
    private void _PJLabel_init(String optText) {
        _originalText = optText;
    }

    /**
     * Sets the text for this label that may include a mnemonic character marker (&) or HTML
     * markup.  Read more here: {@link PJComponentTextParser}.  To access the original text passed
     * to this method, see {@link #getOriginalText()}.
     * <p>
     * Methods {@link #setDisplayedMnemonic(int)} and {@link #setDisplayedMnemonicIndex(int)} are
     * also called by this method.
     * <hr>
     * Docs from {@link JLabel#setText(String)}:
     * <p>
     * {@inheritDoc}
     * 
     * @see PJComponentTextParser
     * @see #setDisplayedMnemonic(int)
     * @see #setDisplayedMnemonicIndex(int)
     * @see #getOriginalText()
     */
    @Override
    public void setText(String text) {
        PJComponentTextParser x = new PJComponentTextParser(text);
        _originalText = text;
        super.setText(x.textAfterParse);
        setDisplayedMnemonic(x.mnemonicKeyCode);
        setDisplayedMnemonicIndex(x.mnemonicIndex);
    }
    
    /**
     * Retrieves the original value passed to {@link #setText(String)}.  The result may be
     * different than {@link #getText()}, as the text may have been modified to remove mnemonic
     * markers or add HTML tags.
     * <p>
     * The default value is {@code null}.
     * 
     * @see #getText()
     * @see #setText(String)
     */
    public String getOriginalText() {
        return _originalText;
    }
    
    /**
     * Retrieves the text for this label widget.  This method override exists <i>only</i> to add
     * this documentation.  It delegates directly to the superclass version:
     * {@link JLabel#getText()}.  The text originally passed to {@link #setText(String)} may be
     * different from the text returned by this method.  To access the original (unmodified) text,
     * see {@link #getOriginalText()}.
     * <hr>
     * Docs from {@link JLabel#getText()}:
     * <p>
     * {@inheritDoc}
     * 
     * @see #getOriginalText()
     */
    @Override
    public String getText() {
        String x = super.getText();
        return x;
    }
    
    /**
     * This is a convenience method to call {@link #setHorizontalAlignment(int)} with
     * {@code halign.value}.
     * <p>
     * The original Swing library was written before the {@code enum} type existed in Java.  As a
     * result, many legacy methods use integers and further check for valid values.  This method
     * simply replaces the integer parameter with a enumerated data type that is restricted to
     * values allowed by the method {@link #setHorizontalAlignment(int)}.  For example,
     * {@link SwingConstants#TOP} is not allowed, so it does not appear in the enum
     * {@link PHorizontalAlignment}.
     * 
     * @throws NullPointerException
     *         if {@code halign} is {@code null}
     * 
     * @see #getHorizontalAlignmentAsEnum()
     * @see #setHorizontalAlignment(int)
     * @see #getHorizontalAlignment()
     */
    public void setHorizontalAlignment(PHorizontalAlignment halign) {
        ObjectArgs.checkNotNull(halign, "halign");
        
        setHorizontalAlignment(halign.value);
    }
    
    /**
     * This is a convenience method to call {@link #getHorizontalAlignment()} and convert the
     * result to enum type {@link PHorizontalAlignment}.
     * 
     * @see #getHorizontalAlignment()
     * @see #setHorizontalAlignment(int)
     * @see #setHorizontalAlignment(PHorizontalAlignment)
     */
    public PHorizontalAlignment getHorizontalAlignmentAsEnum() {
        int x = getHorizontalAlignment();
        PHorizontalAlignment y = PHorizontalAlignment.valueOf(x);
        return y;
    }
    
    /**
     * This is a convenience method to call {@link #setVerticalAlignment(int)} with
     * {@code halign.value}.
     * <p>
     * The original Swing library was written before the {@code enum} type existed in Java.  As a
     * result, many legacy methods use integers and further check for valid values.  This method
     * simply replaces the integer parameter with a enumerated data type that is restricted to
     * values allowed by the method {@link #setVerticalAlignment(int)}.  For example,
     * {@link SwingConstants#LEADING} is not allowed, so it does not appear in the enum
     * {@link PVerticalAlignment}.
     * 
     * @throws NullPointerException
     *         if {@code halign} is {@code null}
     * 
     * @see #getVerticalAlignmentAsEnum()
     * @see #setVerticalAlignment(int)
     * @see #getVerticalAlignment()
     */
    public void setVerticalAlignment(PVerticalAlignment valign) {
        ObjectArgs.checkNotNull(valign, "valign");
        
        setVerticalAlignment(valign.value);
    }
    
    /**
     * This is a convenience method to call {@link #getVerticalAlignment()} and convert the result
     * to enum type {@link PVerticalAlignment}.
     * 
     * @see #getVerticalAlignment()
     * @see #setVerticalAlignment(int)
     * @see #setVerticalAlignment(PVerticalAlignment)
     */
    public PVerticalAlignment getVerticalAlignmentAsEnum() {
        int x = getVerticalAlignment();
        PVerticalAlignment y = PVerticalAlignment.valueOf(x);
        return y;
    }

    /**
     * This is a convenience method to call {@link #setHorizontalTextPosition(int)} with
     * {@code halign.value}.
     * <p>
     * The original Swing library was written before the {@code enum} type existed in Java.  As a
     * result, many legacy methods use integers and further check for valid values.  This method
     * simply replaces the integer parameter with a enumerated data type that is restricted to
     * values allowed by the method {@link #setHorizontalTextPosition(int)}.  For example,
     * {@link SwingConstants#TOP} is not allowed, so it does not appear in the enum
     * {@link PHorizontalAlignment}.
     * 
     * @throws NullPointerException
     *         if {@code halign} is {@code null}
     * 
     * @see #getHorizontalTextPositionAsEnum()
     * @see #setHorizontalTextPosition(int)
     * @see #getHorizontalTextPosition()
     */
    public void setHorizontalTextPosition(PHorizontalAlignment halign) {
        ObjectArgs.checkNotNull(halign, "halign");
        
        setHorizontalTextPosition(halign.value);
    }
    
    /**
     * This is a convenience method to call {@link #getHorizontalTextPosition()} and convert the
     * result to enum type {@link PHorizontalAlignment}.
     * 
     * @see #getHorizontalTextPosition()
     * @see #setHorizontalTextPosition(int)
     * @see #setHorizontalTextPosition(PHorizontalAlignment)
     */
    public PHorizontalAlignment getHorizontalTextPositionAsEnum() {
        int x = getHorizontalTextPosition();
        PHorizontalAlignment y = PHorizontalAlignment.valueOf(x);
        return y;
    }
    
    /**
     * This is a convenience method to call {@link #setVerticalTextPosition(int)} with
     * {@code halign.value}.
     * <p>
     * The original Swing library was written before the {@code enum} type existed in Java.  As a
     * result, many legacy methods use integers and further check for valid values.  This method
     * simply replaces the integer parameter with a enumerated data type that is restricted to
     * values allowed by the method {@link #setVerticalTextPosition(int)}.  For example,
     * {@link SwingConstants#LEADING} is not allowed, so it does not appear in the enum
     * {@link PVerticalAlignment}.
     * 
     * @throws NullPointerException
     *         if {@code halign} is {@code null}
     * 
     * @see #getVerticalTextPositionAsEnum()
     * @see #setVerticalTextPosition(int)
     * @see #getVerticalTextPosition()
     */
    public void setVerticalTextPosition(PVerticalAlignment valign) {
        ObjectArgs.checkNotNull(valign, "valign");
        
        setVerticalTextPosition(valign.value);
    }
    
    /**
     * This is a convenience method to call {@link #getVerticalTextPosition()} and convert the
     * result to enum type {@link PVerticalAlignment}.
     * 
     * @see #getVerticalTextPosition()
     * @see #setVerticalTextPosition(int)
     * @see #setVerticalTextPosition(PVerticalAlignment)
     */
    public PVerticalAlignment getVerticalTextPositionAsEnum() {
        int x = getVerticalTextPosition();
        PVerticalAlignment y = PVerticalAlignment.valueOf(x);
        return y;
    }
}
