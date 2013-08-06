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

import javax.swing.JLabel;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.widget.PJLabel;

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
@NotFullyTested
public final class PJLabelDefaults {
    
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
    
    public PJLabelDefaults(JLabel x) {
        ObjectArgs.checkNotNull(x, "x");
        
        this.text = x.getText();
        this.displayedMnemonicKeyCode = x.getDisplayedMnemonic();
        this.displayedMnemonicIndex = x.getDisplayedMnemonicIndex();
        
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
