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

import java.awt.image.ColorModel;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;

/**
 * Subclass of {@link PAbstractArgbBufferedImageOp} to implement a color to grayscale
 * transformation.  The formula used is part of the HDTV / ATSC standard.
 * <p>
 * <ul>
 *   <li>Ref(i): <a href="https://en.wikipedia.org/wiki/Grayscale#Converting_color_to_grayscale">
 *   https://en.wikipedia.org/wiki/Grayscale#Converting_color_to_grayscale</a></li>
 *   <li>Ref(ii): <a href="http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601">
 *   http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601</a></li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PAbstractArgbBufferedImageOp
 * @see PArgbPixelMask
 */
@NotFullyTested
public class PArgbBufferedImageToGrayscaleOp
extends PAbstractArgbBufferedImageOp {
    
    /**
     * Luma coefficient from HDTV standard for red component to grayscale conversion.
     * <p>
     * <a href="http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601">
     * http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601</a>
     */
    public final float BT_709_LUMA_COEFFICIENT_RED = 0.2126f;

    /**
     * Luma coefficient from HDTV standard for green component to grayscale conversion.
     * <p>
     * <a href="http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601">
     * http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601</a>
     */
    public final float BT_709_LUMA_COEFFICIENT_GREEN = 0.7152f;
    
    /**
     * Luma coefficient from HDTV standard for blue component to grayscale conversion.
     * <p>
     * <a href="http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601">
     * http://en.wikipedia.org/wiki/YUV#BT.709_and_BT.601</a>
     */
    public final float BT_709_LUMA_COEFFICIENT_BLUE = 0.0722f;
    
    public PArgbBufferedImageToGrayscaleOp() {
        super();
    }

    @Override
    protected void processPixels(int[] pixelArr, ColorModel colorModel) {
        //ColorSpace colorSpace = colorModel.getColorSpace();
        //colorSpace.toCIEXYZ(colorvalue)
        final int size = pixelArr.length;
        for (int i = 0; i < size; ++i) {
            final int pixel = pixelArr[i];
            
            final int alpha255 =
                (PArgbPixelMask.ALPHA.mask & pixel) >> PArgbPixelMask.ALPHA.bitShift;
            final int red255 =
                (PArgbPixelMask.RED.mask & pixel) >> PArgbPixelMask.RED.bitShift;
            final int green255 =
                (PArgbPixelMask.GREEN.mask & pixel) >> PArgbPixelMask.GREEN.bitShift;
            final int blue255 =
                (PArgbPixelMask.BLUE.mask & pixel) >> PArgbPixelMask.BLUE.bitShift;
                
            final int grayscale255 = Math.round(
                (BT_709_LUMA_COEFFICIENT_RED * red255)
                + (BT_709_LUMA_COEFFICIENT_GREEN * green255)
                + (BT_709_LUMA_COEFFICIENT_BLUE * blue255));
            
            final int pixel2 =
                (alpha255 << PArgbPixelMask.ALPHA.bitShift)
                | (grayscale255 << PArgbPixelMask.RED.bitShift)
                | (grayscale255 << PArgbPixelMask.GREEN.bitShift)
                | (grayscale255 << PArgbPixelMask.BLUE.bitShift);
            
            // For the debugger!
            final int alpha255b = (PArgbPixelMask.ALPHA.mask & pixel2) >> PArgbPixelMask.ALPHA.bitShift;
            final int red255b = (PArgbPixelMask.RED.mask & pixel2) >> PArgbPixelMask.RED.bitShift;
            final int green255b = (PArgbPixelMask.GREEN.mask & pixel2) >> PArgbPixelMask.GREEN.bitShift;
            final int blue255b = (PArgbPixelMask.BLUE.mask & pixel2) >> PArgbPixelMask.BLUE.bitShift;
                
            pixelArr[i] = pixel2;
        }
    }
}
