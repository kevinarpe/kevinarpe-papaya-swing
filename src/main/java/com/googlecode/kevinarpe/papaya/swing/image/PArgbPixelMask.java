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
import com.googlecode.kevinarpe.papaya.argument.ArrayArgs;
import com.googlecode.kevinarpe.papaya.argument.IntArgs;

/**
 * Collection of bit masks to extract channels from an integer-packed ARGB pixel.  These masks also
 * work for strictly RGB pixels, as the highest byte is alpha and always zero.
 * <p>
 * Example: <pre>{@code
 * int pixel = ...;
 * int alpha255 = (pixel & PixelMask.ALPHA.mask) >> PArgbPixelMask.ALPHA.bitShift;
 * int red255 = (pixel & PixelMask.RED.mask) >> PArgbPixelMask.RED.bitShift;
 * int green255 = (pixel & PixelMask.GREEN.mask) >> PArgbPixelMask.GREEN.bitShift;
 * int blue255 = (pixel & PixelMask.BLUE.mask) >> PArgbPixelMask.BLUE.bitShift;
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
     * <ul>
     *   <li>{@link #bitShift} is 24</li>
     *   <li>{@link #mask} is 0xff000000</li>
     *   <li>{@link #component4ArrayIndex} is 0</li>
     *   <li>{@link #component3ArrayIndex} is -1 (invalid)</li>
     * </ul>
     * <p>
     * Example:
     * {@code int alpha255 = (pixel & PixelMask.ALPHA.mask) >> PixelMask.ALPHA.bitShift;}
     */
    ALPHA(24, 0),
    
    /**
     * Bit mask to extract red color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * <ul>
     *   <li>{@link #bitShift} is 16</li>
     *   <li>{@link #mask} is 0x00ff0000</li>
     *   <li>{@link #component4ArrayIndex} is 1</li>
     *   <li>{@link #component3ArrayIndex} is 0</li>
     * </ul>
     * <p>
     * Example:
     * {@code int red255 = (pixel & PixelMask.RED.mask) >> PixelMask.RED.bitShift;}
     */
    RED(16, 1),
    
    /**
     * Bit mask to extract green color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * <ul>
     *   <li>{@link #bitShift} is 8</li>
     *   <li>{@link #mask} is 0x0000ff00</li>
     *   <li>{@link #component4ArrayIndex} is 2</li>
     *   <li>{@link #component3ArrayIndex} is 1</li>
     * </ul>
     * <p>
     * Example:
     * {@code int green255 = (pixel & PixelMask.GREEN.mask) >> PixelMask.GREEN.bitShift;}
     */
    GREEN(8, 2),
    
    /**
     * Bit mask to extract blue color channel from an integer-packed ARGB (or strictly RGB) pixel.
     * <p>
     * <ul>
     *   <li>{@link #bitShift} is 0</li>
     *   <li>{@link #mask} is 0x000000ff</li>
     *   <li>{@link #component4ArrayIndex} is 3</li>
     *   <li>{@link #component3ArrayIndex} is 2</li>
     * </ul>
     * <p>
     * Example:
     * {@code int blue255 = (pixel & PixelMask.BLUE.mask) >> PixelMask.BLUE.bitShift;}
     */
    BLUE(0, 3),
    ;

    /**
     * Mask to extract a channel from an integer-package ARGB (or strictly RGB) pixel.  If
     * necessary, shift the resulting bits with {@link #bitShift}.
     * <p>
     * Example: {@code pixel & PixelMask.BLUE.mask}
     */
    public final int mask;
    
    /**
     * Number of bits to shift to access a single channel.
     * <p>
     * Example: <pre>{@code 
     * int pixel = alpha255 << PArgbPixelMask.ALPHA.bitShift
     *           | red255 << PArgbPixelMask.RED.bitShift
     *           | green255 << PArgbPixelMask.GREEN.bitShift
     *           | blue255 << PArgbPixelMask.BLUE.bitShift;
     * }</pre>
     * <p>
     * Hint: Order does not matter in the example above.
     */
    public final int bitShift;
    
    /**
     * Index for ARGB (4-part) component arrays.  Used by (de)compose helper methods, e.g.,
     * {@link #decomposeArgbPixelUnchecked(int, int[])} and
     * {@link #composeArgbPixelChecked(float[])}.
     * <p>
     * Example: <pre>{@code
     * int[] argbComponent4Arr = ...;
     * int red255 = argbComponent4Arr[PArgbPixelMask.RED.component4ArrayIndex];
     * }</pre>
     * 
     * @see #component3ArrayIndex
     */
    public final int component4ArrayIndex;
    
    /**
     * Index for RGB (3-part) component arrays: {@link #component4ArrayIndex} minus one.  This
     * value is invalid for {@link #ALPHA}.
     * 
     * @see #component4ArrayIndex
     */
    public final int component3ArrayIndex;
    
    private PArgbPixelMask(int bitShift, int component4ArrayIndex) {
        this.mask = 0xff << bitShift;
        this.bitShift = bitShift;
        this.component4ArrayIndex = component4ArrayIndex;
        this.component3ArrayIndex = component4ArrayIndex - 1;
    }
    
    /**
     * This is a convenience method to call {@link #decomposeArgbPixelUnchecked(int, int[])},
     * except inputs are carefully checked.  If speed is critical, or the inputs are well-sourced,
     * consider directly calling {@link #decomposeArgbPixelUnchecked(int, int[])}.
     * 
     * @throws NullPointerException
     *         if {@code outputArgbComponent4Arr} is {@code null}
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if pixel is outside range 0 to 255 (inclusive)</li>
     *   <li>if number of elements in {@code outputArgbComponent4Arr} is not four (4)</li>
     * </ul>
     */
    public static int[] decomposeArgbPixelChecked(int pixel, int[] outputArgbComponent4Arr) {
        IntArgs.checkValueRange(pixel, 0, 255, "pixel");
        // TODO: Change to use IntArrays.checkExactLength(...)
        ArrayArgs.checkExactLength(outputArgbComponent4Arr, 4, "outputArgbComponent4Arr");
        
        int[] x = decomposeArgbPixelUnchecked(pixel, outputArgbComponent4Arr);
        return x;
    }
    
    /**
     * Decomposes a single, integer-packed ARGB (or strictly RGB) pixel into its components: alpha,
     * red, green, blue.  Each component has range 0 to 255.  If speed is not critical, or the
     * inputs are poorly-sourced, consider using {@link #decomposeArgbPixelChecked(int, int[])}.
     * If the resulting decomposition must be floats, use
     * {@link #decomposeArgbPixelUnchecked(int, float[])}.
     * 
     * @param pixel
     *        single, integer-packed ARGB (or strictly RGB) pixel
     * @param outputArgbComponent4Arr
     *        array with exact length four (4).  Must be pre-allocated
     * 
     * @return reference to decomposed array, {@code outputArgbComponent4Arr}
     * 
     * @see #decomposeArgbPixelChecked(int, int[])
     * @see #decomposeArgbPixelUnchecked(int, float[])
     * @see #component4ArrayIndex
     */
    public static int[] decomposeArgbPixelUnchecked(int pixel, int[] outputArgbComponent4Arr) {
        for (PArgbPixelMask pixelMask: PArgbPixelMask.values()) {
            outputArgbComponent4Arr[pixelMask.component4ArrayIndex] =
                (pixel & pixelMask.mask) >> pixelMask.bitShift;
        }
        return outputArgbComponent4Arr;
    }
    
    /**
     * This is a convenience method to call {@link #decomposeArgbPixelUnchecked(int, float[])},
     * except inputs are carefully checked.  If speed is critical, or the inputs are well-sourced,
     * consider directly calling {@link #decomposeArgbPixelUnchecked(int, float[])}.
     * 
     * @throws NullPointerException
     *         if {@code outputArgbComponent4Arr} is {@code null}
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if pixel is outside range 0 to 255 (inclusive)</li>
     *   <li>if number of elements in {@code outputArgbComponent4Arr} is not four (4)</li>
     * </ul>
     */
    public static float[] decomposeArgbPixelChecked(int pixel, float[] outputArgbComponent4Arr) {
        IntArgs.checkValueRange(pixel, 0, 255, "pixel");
        ArrayArgs.checkExactLength(outputArgbComponent4Arr, 4, "outputArgbComponent4Arr");
        
        float[] x = decomposeArgbPixelUnchecked(pixel, outputArgbComponent4Arr);
        return x;
    }
    
    /**
     * Decomposes a single, integer-packed ARGB (or strictly RGB) pixel into its components: alpha,
     * red, green, blue.  Each component has range 0.0f to 1.0f.  If speed is not critical, or the
     * inputs are poorly-sourced, consider using {@link #decomposeArgbPixelChecked(int, float[])}.
     * If the resulting decomposition must be ints, use
     * {@link #decomposeArgbPixelUnchecked(int, int[])}.
     * 
     * @param pixel
     *        single, integer-packed ARGB (or strictly RGB) pixel
     * @param outputArgbComponent4Arr
     *        array with exact length four (4).  Must be pre-allocated
     * 
     * @return reference to decomposed array, {@code outputArgbComponent4Arr}
     * 
     * @see #decomposeArgbPixelChecked(int, float[])
     * @see #decomposeArgbPixelUnchecked(int, int[])
     * @see #component4ArrayIndex
     */
    public static float[] decomposeArgbPixelUnchecked(int pixel, float[] outputArgbComponent4Arr) {
        for (PArgbPixelMask pixelMask: PArgbPixelMask.values()) {
            outputArgbComponent4Arr[pixelMask.component4ArrayIndex] =
                ((pixel & pixelMask.mask) >> pixelMask.bitShift) / 255.0f;
        }
        return outputArgbComponent4Arr;
    }
    
    /**
     * This is a convenience method to call {@link #composeArgbPixelUnchecked(int[])}, except
     * inputs are carefully checked.  If speed is critical, or the inputs are well-sourced,
     * consider directly calling {@link #composeArgbPixelUnchecked(int[])}.
     * 
     * @throws NullPointerException
     *         if {@code outputArgbComponent4Arr} is {@code null}
     * @throws IllegalArgumentException
     *         if number of elements in {@code outputArgbComponent4Arr} is not four (4)
     */
    public static int composeArgbPixelChecked(int[] argbComponent4Arr) {
        ArrayArgs.checkExactLength(argbComponent4Arr, 4, "argbComponent4Arr");
        
        int x = composeArgbPixelUnchecked(argbComponent4Arr);
        return x;
    }
    
    /**
     * Composes a single, integer-packed ARGB pixel from its components: alpha, red, green, blue.
     * Each component has range 0 to 255.  If speed is not critical, or the
     * inputs are poorly-sourced, consider using {@link #composeArgbPixelChecked(int[])}.  If
     * composing floats, use {@link #composeArgbPixelUnchecked(float[])}.
     * 
     * @param argbComponent4Arr
     *        array with exact length four (4) with ARGB components
     * 
     * @return composed ARGB pixel
     * 
     * @see #composeArgbPixelChecked(int[])
     * @see #composeArgbPixelUnchecked(float[])
     */
    public static int composeArgbPixelUnchecked(int[] argbComponent4Arr) {
        int pixel = 0;
        for (PArgbPixelMask pixelMask: PArgbPixelMask.values()) {
            pixel |= (argbComponent4Arr[pixelMask.component4ArrayIndex] << pixelMask.bitShift);
        }
        return pixel;
    }
    
    /**
     * This is a convenience method to call {@link #composeArgbPixelUnchecked(float[])}, except
     * inputs are carefully checked.  If speed is critical, or the inputs are well-sourced,
     * consider directly calling {@link #composeArgbPixelUnchecked(float[])}.
     * 
     * @throws NullPointerException
     *         if {@code outputArgbComponent4Arr} is {@code null}
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if pixel is outside range 0.0f to 1.0f (inclusive)</li>
     *   <li>if number of elements in {@code outputArgbComponent4Arr} is not four (4)</li>
     * </ul>
     */
    public static int composeArgbPixelChecked(float[] argbComponent4Arr) {
        ArrayArgs.checkExactLength(argbComponent4Arr, 4, "argbComponent4Arr");
        
        int x = composeArgbPixelUnchecked(argbComponent4Arr);
        return x;
    }
    
    /**
     * Composes a single, integer-packed ARGB pixel from its components: alpha, red, green, blue.
     * Each component has range 0.0f to 1.0f.  If speed is not critical, or the
     * inputs are poorly-sourced, consider using {@link #composeArgbPixelChecked(float[])}.  If
     * composing ints, use {@link #composeArgbPixelUnchecked(int[])}.
     * 
     * @param argbComponent4Arr
     *        array with exact length four (4) with ARGB components
     * 
     * @return composed ARGB pixel
     * 
     * @see #composeArgbPixelChecked(float[])
     * @see #composeArgbPixelUnchecked(int[])
     */
    public static int composeArgbPixelUnchecked(float[] argbComponent4Arr) {
        int pixel = 0;
        for (PArgbPixelMask pixelMask: PArgbPixelMask.values()) {
            int scaled = (int) (255.0f * argbComponent4Arr[pixelMask.component4ArrayIndex]);
            pixel |= (scaled << pixelMask.bitShift);
        }
        return pixel;
    }
}
