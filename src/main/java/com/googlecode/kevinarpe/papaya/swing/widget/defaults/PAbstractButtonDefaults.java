package com.googlecode.kevinarpe.papaya.swing.widget.defaults;

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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.widget.PJCheckBox;

/**
 * Stores defaults for subclasses of {@link AbstractButton}, e.g., {@link JButton},
 * {@link JRadioButton}, {@link JCheckBox}, etc.
 * <p>
 * All instances of this class are fully immutable, thus thread-safe, and safe to store as
 * {@code static final} constants.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 *
 * @see PJCheckBox#DEFAULTS
 * @see PJCheckBox#DEFAULTS_FOR_ICON_ONLY
 */
@NotFullyTested
public final class PAbstractButtonDefaults {
    
    // TODO: Merge this class with PJLabelDefaults to create PTextLabelDefaults
    
    /**
     * Default value for {@link AbstractButton#getText()}.
     */
    public final String text;
    
    /**
     * Default value for {@link AbstractButton#getMnemonic()}.
     */
    public final int mnemonicKeyCode;
    
    /**
     * Default value for {@link AbstractButton#getDisplayedMnemonicIndex()}.
     */
    public final int displayedMnemonicIndex;
    
    /**
     * Default value for {@link AbstractButton#getHorizontalAlignment()} converted to type
     * {@link PHorizontalAlignment}.
     */
    public final PHorizontalAlignment horizontalAlignment;
    
    /**
     * Default value for {@link AbstractButton#getVerticalAlignment()} converted to type
     * {@link PVerticalAlignment}.
     */
    public final PVerticalAlignment verticalAlignment;
    
    /**
     * Default value for {@link AbstractButton#getHorizontalTextPosition()} converted to type
     * {@link PHorizontalAlignment}.
     */
    public final PHorizontalAlignment horizontalTextPosition;
    
    /**
     * Default value for {@link AbstractButton#getVerticalTextPosition()} converted to type
     * {@link PVerticalAlignment}.
     */
    public final PVerticalAlignment verticalTextPosition;
    
    /**
     * Default value for {@link AbstractButton#isSelected()}.
     */
    public final boolean isSelected;
    
    /**
     * Create defaults object from a subclass of {@link AbstractButton}.
     * 
     * @param x
     *        instance of a subclass of {@link AbstractButton} to extract defaults
     * 
     * @throws NullPointerException
     *         if {@code x} is {@code null}
     */
    public PAbstractButtonDefaults(AbstractButton x) {
        ObjectArgs.checkNotNull(x, "x");
        
        this.text = x.getText();
        this.mnemonicKeyCode = x.getMnemonic();
        this.displayedMnemonicIndex = x.getDisplayedMnemonicIndex();
        
        this.horizontalAlignment =
            PHorizontalAlignment.valueOf(x.getHorizontalAlignment());
        
        this.verticalAlignment =
            PVerticalAlignment.valueOf(x.getVerticalAlignment());
        
        this.horizontalTextPosition =
            PHorizontalAlignment.valueOf(x.getHorizontalTextPosition());
        
        this.verticalTextPosition =
            PVerticalAlignment.valueOf(x.getVerticalTextPosition());
        
        this.isSelected = x.isSelected();
    }
}
