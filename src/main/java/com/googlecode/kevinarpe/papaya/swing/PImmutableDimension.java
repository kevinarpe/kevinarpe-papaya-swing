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

import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import com.google.common.base.Objects;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Immutable version of {@link Dimension}.
 * <p>
 * All instances of this class are fully immutable, thus thread-safe, and safe to store as
 * {@code static final} constants.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see #copyOfDimension(Dimension)
 * @see Dimension
 * @see Dimension2D
 */
@NotFullyTested
public final class PImmutableDimension
extends Dimension2D {
    
    /**
     * Default value for field {@link #width} and method {@link #getWidth()}.
     */
    public static final int DEFAULT_WIDTH;
    
    /**
     * Default value for field {@link #height} and method {@link #getHeight()}.
     */
    public static final int DEFAULT_HEIGHT;
    
    static {
        Dimension dim = new Dimension();
        DEFAULT_WIDTH = dim.width;
        DEFAULT_HEIGHT = dim.height;
    }
    
    /**
     * @see #getWidth()
     * @see #DEFAULT_WIDTH
     */
    public final int width;
    
    /**
     * @see #getHeight()()
     * @see #DEFAULT_HEIGHT
     */
    public final int height;
    
    /**
     * {@link #width} {@code + "x" +} {@link #height}, e.g., "32x32"
     */
    public final String description;

    /**
     * Calls {@link #PImmutableDimension(int, int)} with {@link #DEFAULT_WIDTH} and
     * {@link #DEFAULT_HEIGHT}.
     */
    public PImmutableDimension() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Like {@link Dimension}, the inputs are unchecked.
     * 
     * @param width
     *        any integer value -- unchecked
     * @param height
     *        any integer value -- unchecked
     */
    public PImmutableDimension(int width, int height) {
        this.width = width;
        this.height = height;
        description = String.format("%dx%d", width, height);
    }
    
    /**
     * Creates a new {@link PImmutableDimension} from another {@link Dimension}.
     * 
     * @param dim
     *        another {@link Dimension} to copy.  Must not be {@code null}
     * 
     * @return new {@link PImmutableDimension}
     * 
     * @throws NullPointerException
     *         if {@code dim} is {@code null}
     */
    public static PImmutableDimension copyOfDimension(Dimension dim) {
        ObjectArgs.checkNotNull(dim, "dim");
        
        PImmutableDimension x = new PImmutableDimension(dim.width, dim.height);
        return x;
    }

    /**
     * Returns field {@link #width} as a {@code double}.
     * <hr>
     * Docs from {@link Dimension2D#getWidth()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return width;
    }

    /**
     * Returns field {@link #height} as a {@code double}.
     * <hr>
     * Docs from {@link Dimension2D#getHeight()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     * @throws UnsupportedOperationException
     *         <b>always</b>
     */
    @Override
    public void setSize(double width, double height) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int hashCode() {
        int x = Objects.hashCode(width, height);
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        // Ref: http://stackoverflow.com/a/5039178/257299
        boolean result = (this == obj);
        if (!result && obj instanceof Dimension2D) {
            final Dimension2D other = (Dimension2D) obj;
            double otherWidth = other.getWidth();
            double otherHeight = other.getHeight();
            result = (this.width == ((int) otherWidth)) && (this.height == ((int) otherHeight));
        }
        return result;
    }

    @Override
    public String toString() {
        String x = String.format(
            "class %s ["
            + "%n\twidth: %d"
            + "%n\theight: %d"
            + "%n\t]",
            PImmutableDimension.class.getCanonicalName(),
            width,
            height);
        return x;
    }
}
