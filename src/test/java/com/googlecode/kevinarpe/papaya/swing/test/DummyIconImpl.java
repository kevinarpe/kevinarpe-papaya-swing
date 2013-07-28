package com.googlecode.kevinarpe.papaya.swing.test;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.IntArgs;

@NotFullyTested
public class DummyIconImpl
implements Icon {
    
    public static final int DEFAULT_WIDTH = 17;
    public static final int DEFAULT_HEIGHT = 31;
    
    public static final DummyIconImpl INSTANCE = new DummyIconImpl();
    public static final DummyIconImpl INSTANCE2 =
        new DummyIconImpl(DEFAULT_WIDTH * 2, DEFAULT_HEIGHT * 2);
    
    private final int _width;
    private final int _height;

    public DummyIconImpl() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public DummyIconImpl(int width, int height) {
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
