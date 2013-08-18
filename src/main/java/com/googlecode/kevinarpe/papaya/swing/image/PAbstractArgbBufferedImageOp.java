package com.googlecode.kevinarpe.papaya.swing.image;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.swing.PBufferedImageType;

/**
 * Subclass of {@link PAbstractBufferedImageOp} that restricts image type to ARGB and strictly RGB.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PArgbPixelMask
 * @see PAbstractBufferedImageOp
 */
@NotFullyTested
public abstract class PAbstractArgbBufferedImageOp
extends PAbstractBufferedImageOp {

    /**
     * Restricts image type to ARGB and strictly RGB.
     */
    protected PAbstractArgbBufferedImageOp() {
        super(PBufferedImageType.TYPE_INT_ARGB, PBufferedImageType.TYPE_INT_RGB);
    }
}
