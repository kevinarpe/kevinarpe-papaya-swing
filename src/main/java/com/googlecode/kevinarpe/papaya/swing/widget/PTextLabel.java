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

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PJComponentTextParser;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;

/**
 * New methods for subclasses of {@link JLabel} and {@link AbstractButton}.  It is strange that
 * {@code JLabel} and {@code AbstractButton} do not have the same parent class that controls all
 * features of a text (and icon) label. 
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PJCheckBox
 */
public interface PTextLabel {

    /**
     * Sets the text for this checkbox that may include a mnemonic character marker (&) or HTML
     * markup.  Read more here: {@link PJComponentTextParser}.  To access the original text passed
     * to this method, see {@link #getOriginalText()}.
     * <p>
     * Methods {@link #setMnemonic(int)} and {@link #setDisplayedMnemonicIndex(int)} are also
     * called by this method.
     * 
     * @see PJComponentTextParser
     * @see #setMnemonic(int)
     * @see #setDisplayedMnemonicIndex(int)
     * @see #getOriginalText()
     */
    public void setText(String text);
    
    public int getMnemonic();
    public void setMnemonic(int virtualKeyCode);
    
    public int getDisplayedMnemonicIndex();
    public void setDisplayedMnemonicIndex(int index);
    
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
    public String getOriginalText();
    
    /**
     * Retrieves the text for this label widget.  This method override exists <i>only</i> to add
     * this documentation.  It delegates directly to the superclass version:
     * {@link JLabel#getText()}.  The text originally passed to {@link #setText(String)} may be
     * different from the text returned by this method.  To access the original (unmodified) text,
     * see {@link #getOriginalText()}.
     * 
     * @see #getOriginalText()
     */
    public String getText();
    
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
    public void setHorizontalAlignment(PHorizontalAlignment halign);
    
    public void setHorizontalAlignment(int halign);
    
    /**
     * This is a convenience method to call {@link #getHorizontalAlignment()} and convert the
     * result to enum type {@link PHorizontalAlignment}.
     * 
     * @see #getHorizontalAlignment()
     * @see #setHorizontalAlignment(int)
     * @see #setHorizontalAlignment(PHorizontalAlignment)
     */
    public PHorizontalAlignment getHorizontalAlignmentAsEnum();
    
    public int getHorizontalAlignment();
    
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
    public void setVerticalAlignment(PVerticalAlignment valign);
    
    public void setVerticalAlignment(int valign);
    
    /**
     * This is a convenience method to call {@link #getVerticalAlignment()} and convert the result
     * to enum type {@link PVerticalAlignment}.
     * 
     * @see #getVerticalAlignment()
     * @see #setVerticalAlignment(int)
     * @see #setVerticalAlignment(PVerticalAlignment)
     */
    public PVerticalAlignment getVerticalAlignmentAsEnum();
    
    public int getVerticalAlignment();

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
    public void setHorizontalTextPosition(PHorizontalAlignment halign);
    
    public void setHorizontalTextPosition(int halign);
    
    /**
     * This is a convenience method to call {@link #getHorizontalTextPosition()} and convert the
     * result to enum type {@link PHorizontalAlignment}.
     * 
     * @see #getHorizontalTextPosition()
     * @see #setHorizontalTextPosition(int)
     * @see #setHorizontalTextPosition(PHorizontalAlignment)
     */
    public PHorizontalAlignment getHorizontalTextPositionAsEnum();
    
    public int getHorizontalTextPosition();
    
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
    public void setVerticalTextPosition(PVerticalAlignment valign);
    
    public void setVerticalTextPosition(int valign);
    
    /**
     * This is a convenience method to call {@link #getVerticalTextPosition()} and convert the
     * result to enum type {@link PVerticalAlignment}.
     * 
     * @see #getVerticalTextPosition()
     * @see #setVerticalTextPosition(int)
     * @see #setVerticalTextPosition(PVerticalAlignment)
     */
    public PVerticalAlignment getVerticalTextPositionAsEnum();
    
    public int getVerticalTextPosition();
}
