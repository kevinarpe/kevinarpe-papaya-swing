package com.googlecode.kevinarpe.papaya.swing.test;

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

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.IntArgs;

@NotFullyTested
public class PDummyIconImpl
implements Icon {
    
    public static final int DEFAULT_WIDTH = 17;
    public static final int DEFAULT_HEIGHT = 31;
    
    public static final PDummyIconImpl INSTANCE = new PDummyIconImpl();
    public static final PDummyIconImpl INSTANCE2 =
        new PDummyIconImpl(DEFAULT_WIDTH * 2, DEFAULT_HEIGHT * 2);
    
    private final int _width;
    private final int _height;

    public PDummyIconImpl() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public PDummyIconImpl(int width, int height) {
        _width = IntArgs.checkPositive(width, "width");
        _height = IntArgs.checkPositive(height, "height");
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // empty
    }

    @Override
    public int getIconWidth() {
        return _width;
    }

    @Override
    public int getIconHeight() {
        return _height;
    }
}
