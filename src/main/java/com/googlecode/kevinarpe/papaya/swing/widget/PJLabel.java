package com.googlecode.kevinarpe.papaya.swing.widget;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PJComponentTextParser;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.demo.PJLabelDemo;

/**
 * Extension of {@link JLabel}.  In particular, the method {@link #setText(String)}, which is
 * called by any constructor that accepts a {@link String} parameter, incorporates the class
 * {@link PJComponentTextParser} to enrich the text feature of {@code JLabel}.
 * <p>
 * For a simple demo using this widget, see {@link PJLabelDemo}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see JLabel
 * @see #setText(String)
 */
@SuppressWarnings("serial")
public class PJLabel
extends JLabel {
    
    public static final String DEFAULT_TEXT = "";
    
    public static final int DEFAULT_MNEMONIC_KEY_CODE =
        PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE;
    
    public static final int DEFAULT_MNEMONIC_INDEX = PJComponentTextParser.DEFAULT_MNEMONIC_INDEX;
    
    private String _originalText;

    /**
     * @see JLabel#JLabel()
     */
    public PJLabel() {
        super();
        _PJLabel_init(DEFAULT_TEXT);
    }

    /**
     * @see JLabel#JLabel(Icon, int)
     */
    public PJLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        _PJLabel_init(DEFAULT_TEXT);
    }

    /**
     * @see JLabel#JLabel(Icon)
     */
    public PJLabel(Icon image) {
        super(image);
        _PJLabel_init(DEFAULT_TEXT);
    }

    /**
     * @see JLabel#JLabel(String, Icon, int)
     * @see #setText(String)
     */
    public PJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        _PJLabel_init(text);
    }

    /**
     * @see JLabel#JLabel(String, int)
     * @see #setText(String)
     */
    public PJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        _PJLabel_init(text);
    }

    /**
     * @see JLabel#JLabel(String)
     * @see #setText(String)
     */
    public PJLabel(String text) {
        super(text);
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
     * <p>
     * A {@code null String} is treated as an empty string, {@code ""}.
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
    public void setVertialAlignment(PVerticalAlignment valign) {
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
    public void setVertialTextPosition(PVerticalAlignment valign) {
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
