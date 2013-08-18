package com.googlecode.kevinarpe.papaya.swing.image;

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

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;

/**
 * Collection of bit masks to extract channels from an integer-packed ARGB pixel.  These masks also
 * work for strictly RGB pixels, as the highest byte is alpha and always zero.
 * <p>
 * Example: <pre>{@code
 * int pixel = ...;
 * int alpha = pixel & PixelMask.ALPHA.mask;
 * int red = pixel & PixelMask.RED.mask;
 * int green = pixel & PixelMask.GREEN.mask;
 * int blue = pixel & PixelMask.BLUE.mask;
 * }</pre>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 */
@NotFullyTested
public enum PArgbPixelMask {
    
    /**
     * Bit mask to extract alpha channel from an integer-packed ARGB pixel.  If a pixel is strictly
     * RGB, the alpha channel will be zero.
     * <p>
     * {@link #bitShift} is 24, {@link #mask} is 0xff000000.
     * <p>
     * Example: <pre>{@code
     * int pixel = ...;
     * int alpha = pixel & PixelMask.ALPHA.mask;
     * }</pre>
     */
    ALPHA(24),
    
    /**
     * Bit mask to extract red color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * {@link #bitShift} is 16, {@link #mask} is 0x00ff0000.
     * <p>
     * Example: <pre>{@code
     * int pixel = ...;
     * int red = pixel & PixelMask.RED.mask;
     * }</pre>
     */
    RED  (16),
    
    /**
     * Bit mask to extract green color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * {@link #bitShift} is 8, {@link #mask} is 0x0000ff00.
     * <p>
     * Example: <pre>{@code
     * int pixel = ...;
     * int green = pixel & PixelMask.GREEN.mask;
     * }</pre>
     */
    GREEN(8),
    
    /**
     * Bit mask to extract blue color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * {@link #bitShift} is 0, {@link #mask} is 0x000000ff
     * <p>
     * Example: <pre>{@code
     * int pixel = ...;
     * int blue = pixel & PixelMask.BLUE.mask;
     * }</pre>
     */
    BLUE (0),
    ;
    
    /**
     * Number of bits to shift to access a single channel.
     * <p>
     * Example: <pre>{@code 
     * int pixel = alpha << PArgbPixelMask.ALPHA.bitShift
     *           & red << PArgbPixelMask.RED.bitShift
     *           & green << PArgbPixelMask.GREEN.bitShift
     *           & blue << PArgbPixelMask.BLUE.bitShift;
     * }</pre>
     * <p>
     * Hint: Order does not matter in the example above.
     */
    public final int bitShift;

    /**
     * Mask to extract a channel from an integer-package ARGB (or strictly RGB) pixel.
     */
    public final int mask;
    
    private PArgbPixelMask(int bitShift) {
        this.bitShift = bitShift;
        this.mask = 0xff << bitShift;
    }
}
