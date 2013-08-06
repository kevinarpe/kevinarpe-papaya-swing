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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PJComponentTextParser;
import com.googlecode.kevinarpe.papaya.swing.PJComponentToolTipTextParser;
import com.googlecode.kevinarpe.papaya.swing.PJComponentUtils;
import com.googlecode.kevinarpe.papaya.swing.PSwingUtils;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.test.PDummyIconImpl;
import com.googlecode.kevinarpe.papaya.swing.widget.defaults.PAbstractButtonDefaults;

/**
 * Extension of {@link JCheckBox}.
 * <p>
 * <b>Label Text with Mnemonic Markers</b>
 * <p>
 * Many other GUI toolkits support mnemonic markers embedded in text to set keyboard shortcuts,
 * e.g., {@code "&Sample"} sets letter S as the keyboard shortcut character.  The method
 * {@link #setText(String)} incorporates the class {@link PJComponentTextParser} to enrich the text
 * label feature of {@code JCheckBox}.  It is also called by any constructor that accepts a
 * {@link String} or {@link Action} parameter.
 * <p>
 * <b>{@link SwingConstants} vs. Enums</b>
 * <p>
 * As an alternative to the integer constants from interface {@link SwingConstants}, e.g.,
 * {@link SwingConstants#RIGHT}, two enums are used: {@link PHorizontalAlignment} and
 * {@link PVerticalAlignment}.  Parallel versions of existing methods exist for these enums,
 * such as:
 * <ul>
 *   <li>{@link #setHorizontalAlignment(PHorizontalAlignment)}</li>
 *   <li>{@link #getHorizontalAlignmentAsEnum()}</li>
 * </ul>
 * <p>
 * For a simple demo using this widget, see {@link PJCheckBoxDemo}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see JCheckBox
 * @see #setText(String)
 */
@FullyTested
@SuppressWarnings("serial")
public class PJCheckBox
extends JCheckBox
implements PTextLabel {
    
    /**
     * Defaults for {@link PJCheckBox} when the constructor uses a {@link String} and optionally an
     * {@link Icon}.
     */
    public static final PAbstractButtonDefaults DEFAULTS;
    
    /**
     * Defaults for {@link PJCheckBox} when the constructor uses an {@link Icon}, but does not use
     * a {@link String}.
     */
    public static final PAbstractButtonDefaults DEFAULTS_FOR_ICON_ONLY;
    
    /**
     * Defaults for {@link PJCheckBox} when the constructor uses an {@link Action}.
     */
    public static final PAbstractButtonDefaults DEFAULTS_FOR_ACTION;
    
    static {
        final JCheckBox x = new JCheckBox();
        DEFAULTS = new PAbstractButtonDefaults(x);
        
        final JCheckBox y = new JCheckBox(PDummyIconImpl.INSTANCE);
        DEFAULTS_FOR_ICON_ONLY = new PAbstractButtonDefaults(y);
        
        final JCheckBox z = new JCheckBox((Action) null);
        DEFAULTS_FOR_ACTION = new PAbstractButtonDefaults(z);
        
        @SuppressWarnings("unused")
        int dummy = 1;  // debug breakpoint
    }
    
    private String _originalText;

    /**
     * @see JCheckBox#JCheckBox()
     */
    public PJCheckBox() {
        super();
        PJCheckBoxInit(DEFAULTS.text);
    }

    /**
     * @see JCheckBox#JCheckBox(Icon)
     */
    public PJCheckBox(Icon icon) {
        super(icon);
        PJCheckBoxInit(DEFAULTS_FOR_ICON_ONLY.text);
    }

    /**
     * @see JCheckBox#JCheckBox(String)
     * @see #setText(String)
     */
    public PJCheckBox(String text) {
        super(text);
        PJCheckBoxInit(_originalText);
    }

    /**
     * @see JCheckBox#JCheckBox(Action)
     * @see #setText(String)
     */
    public PJCheckBox(Action a) {
        super(a);
        PJCheckBoxInit(_originalText);
    }

    /**
     * @see JCheckBox#JCheckBox(Icon, boolean)
     */
    public PJCheckBox(Icon icon, boolean selected) {
        super(icon, selected);
        PJCheckBoxInit(DEFAULTS_FOR_ICON_ONLY.text);
    }

    /**
     * @see JCheckBox#JCheckBox(String, boolean)
     * @see #setText(String)
     */
    public PJCheckBox(String text, boolean selected) {
        super(text, selected);
        PJCheckBoxInit(_originalText);
    }

    /**
     * @see JCheckBox#JCheckBox(String, Icon)
     * @see #setText(String)
     */
    public PJCheckBox(String text, Icon icon) {
        super(text, icon);
        PJCheckBoxInit(_originalText);
    }

    /**
     * @see JCheckBox#JCheckBox(String, Icon, boolean)
     * @see #setText(String)
     */
    public PJCheckBox(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
        PJCheckBoxInit(_originalText);
    }

    /**
     * Called by all constructors.
     */
    protected void PJCheckBoxInit(String optText) {
        setOriginalText(optText);
    }
    
    /**
     * Sets the text for this checkbox that may include a mnemonic character marker (&) or HTML
     * markup.  Read more here: {@link PJComponentTextParser}.  To access the original text passed
     * to this method, see {@link #getOriginalText()}.
     * <p>
     * Methods {@link #setMnemonic(int)} and {@link #setDisplayedMnemonicIndex(int)} are also
     * called by this method.
     * <hr>
     * Docs from {@link JCheckBox#setText(String)}:
     * <p>
     * {@inheritDoc}
     * 
     * @see PJComponentTextParser
     * @see #setMnemonic(int)
     * @see #setDisplayedMnemonicIndex(int)
     * @see #getOriginalText()
     */
    @Override
    public void setText(String text) {
        PJComponentTextParser x = new PJComponentTextParser(text);
        setOriginalText(text);
        super.setText(x.textAfterParse);
        setMnemonic(x.mnemonicKeyCode);
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
     * For subclasses to access members.
     * 
     * @param optText
     *        optional original text from {@link #setText(String)}.  May be {@code null}
     */
    protected void setOriginalText(String optText) {
        _originalText = optText;
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

    // TODO: Expand these methods to other classes.
    // TODO: Maybe getOriginalText() is useless.
    // getText() should ALWAYS return what was given to setText()
    // ... however, super.getText() may return processed text.
    // Same for ToolTipText
    
    private String _originalToolTipText;
    
    @Override
    public void setToolTipText(String optText) {
        PJComponentToolTipTextParser x = new PJComponentToolTipTextParser(this, optText);
        super.setToolTipText(x.textAfterParse);
        _originalToolTipText = optText;
    }
    
    // TODO: Upgrade PJComponentTextParser to use HTML_DISABLE ClientProperty correctly.
    
    @Override
    public String getToolTipText() {
        // TODO: Get the default right.
        return _originalToolTipText;
    }
    
    public void setHtmlDisabled(boolean flag) {
        PJComponentUtils.setHtmlDisabled(this, flag);
    }
    
    public boolean isHtmlDisabled() {
        boolean x = PJComponentUtils.isHtmlDisabled(this);
        return x;
    }
    
    @Override
    public boolean requestFocusInWindow() {
        if (PSwingUtils.isComponentShowing(this)) {
            boolean x = super.requestFocusInWindow();
            return x;
        }
        else {
            final PJCheckBox self = this;
            PSwingUtils.runAfterNextShow(this, new Runnable() {
                public void run() {
                    @SuppressWarnings("unused")
                    boolean x = self.requestFocusInWindow();
                }
            });
            return true;
        }
    }
    
    public void doClickAsync() {
        final PJCheckBox self = this;
        PSwingUtils.runAfterNextShow(this, new Runnable() {
            public void run() {
                self.doClick();
            }
        });
    }
    
    public void doClickAsync(final int pressTimeMillis) {
        final PJCheckBox self = this;
        PSwingUtils.runAfterNextShow(this, new Runnable() {
            public void run() {
                self.doClick(pressTimeMillis);
            }
        });
    }
}
