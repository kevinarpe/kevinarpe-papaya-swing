package com.googlecode.kevinarpe.papaya.swing.theme;

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

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;

/**
 * Extends class {@link PThemeIconLoaderAbstract} with a fixed dimension for icons to load.
 * <p>
 * For KDE and GNOME icon themes, there are generally three sizes used in applications:
 * <ul>
 *   <li>16x16: Used for regular menu items</li>
 *   <li>22x22: Used for larger menu items or regular toolbar items</li>
 *   <li>32x32: Used for larger toolbar items</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconLoaderFixedDimensionFromPngFile
 */
public abstract class PThemeIconLoaderAbstractFixedDimension
extends PThemeIconLoaderAbstract {

    /**
     * Fixed number of pixels for icon width and height.
     */
    private final PImmutableDimension _fixedDimension;
    
    /**
     * @param fixedDimension
     *        dimension for icons to load.  Must not be {@code null}
     * 
     * @throws NullPointerException
     *         if {@code fixedDimension} is {@code null}
     */
    protected PThemeIconLoaderAbstractFixedDimension(PImmutableDimension fixedDimension) {
        super();
        _fixedDimension = ObjectArgs.checkNotNull(fixedDimension, "fixedDimension");
    }
    
    /**
     * Fixed number of pixels for icon width and height.
     */
    public PImmutableDimension getFixedDimension() {
        return _fixedDimension;
    }
}
