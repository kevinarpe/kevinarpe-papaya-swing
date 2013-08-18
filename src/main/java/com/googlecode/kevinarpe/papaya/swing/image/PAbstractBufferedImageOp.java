package com.googlecode.kevinarpe.papaya.swing.image;

import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ArrayArgs;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.PImageArgs;
import com.googlecode.kevinarpe.papaya.swing.PBufferedImageType;

/**
 * Abstract class for {@link BufferedImage} that implements {@link BufferedImageOp} for
 * transformation operations.  Subclasses only need to override {@link #processPixels(int[])}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PArgbPixelMask
 */
@NotFullyTested
public abstract class PAbstractBufferedImageOp
implements BufferedImageOp {
    
    private final List<PBufferedImageType> _validImageTypeList;
    
    /**
     * Constructor.
     * 
     * @param validImageTypeArr
     *        list of valid image types.  Must not be empty or contain {@code null} values
     */
    protected PAbstractBufferedImageOp(PBufferedImageType... validImageTypeArr) {
        ArrayArgs.checkNotEmptyAndElementsNotNull(validImageTypeArr, "validImageTypeArr");
        
        _validImageTypeList = ImmutableList.copyOf(validImageTypeArr);
    }
    
    /**
     * @return list of valid image types
     */
    public List<PBufferedImageType> getValidImageTypeList() {
        return _validImageTypeList;
    }
    
    protected void checkBufferedImageType(BufferedImage image, String argName) {
        List<PBufferedImageType> x = getValidImageTypeList();
        PImageArgs.checkBufferedImageType(image, argName, x);
    }
    
    // Ref: http://www.informit.com/articles/article.aspx?p=1013851&seqNum=8
    
    @Override
    public BufferedImage filter(BufferedImage srcImage, BufferedImage optDestImage) {
        checkBufferedImageType(srcImage, "srcImage");
        if (null == optDestImage) {
            PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
            ColorModel optDestCM = null;
            optDestImage = createCompatibleDestImage(srcImage, optDestCM);
        }
        else {
            PImageArgs.checkImageDimensionsValidAndEqual(
                srcImage, optDestImage, "srcImage", "optDestImage");
            final int srcImageType = srcImage.getType();
            final int destImageType = optDestImage.getType();
            if (srcImageType != destImageType) {
                PBufferedImageType srcImageType2 = PBufferedImageType.valueOf(srcImageType);
                PBufferedImageType destImageType2 = PBufferedImageType.valueOf(destImageType);
                String msg = String.format(
                    "Source and destination image types are different:%nSource: %s%nDest  : %s",
                    srcImageType2, destImageType2);
                throw new IllegalArgumentException(msg);
            }
            //checkBufferedImageType(optDestImage, "optDestImage");
        }
        
        final int width = srcImage.getWidth();
        final int height = srcImage.getHeight();
        final int[] pixelArr = new int[width * height];
        final WritableRaster srcImageRaster = srcImage.getRaster();
        final int x = 0;
        final int y = 0;
        srcImageRaster.getDataElements(x, y, width, height, pixelArr);
        
        ColorModel srcColorModel = srcImage.getColorModel();
        processPixels(pixelArr, srcColorModel);
        
        WritableRaster destImageRaster = optDestImage.getRaster();
        destImageRaster.setDataElements(x, y, width, height, pixelArr);
        
        return optDestImage;
    }
    
    /**
     * Transforms array of RGBA pixels packed as integers, where each channel has a range from 0 to
     * 255 (0xff).  Use the mask enum, {@link PArgbPixelMask}, to separate channels.
     * 
     * @param pixelArr
     *        writable row-wise pixel data for processing
     * @param colorModel
     *        color model for pixels
     */
    protected abstract void processPixels(int[] pixelArr, ColorModel colorModel);

    @Override
    public Rectangle2D getBounds2D(BufferedImage srcImage) {
        PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        
        final int x = 0;
        final int y = 0;
        final int width = srcImage.getWidth();
        final int height = srcImage.getHeight();
        Rectangle z = new Rectangle(x, y, width, height);
        return z;
    }

    /**
     * @throws NullPointerException
     *         if {@code srcImage} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code srcImage} width or height is not positive
     */
    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage srcImage, ColorModel optDestCM) {
        PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        
        if (null == optDestCM) {
            optDestCM = srcImage.getColorModel();
        }
        final int width = srcImage.getWidth();
        final int height = srcImage.getHeight();
        WritableRaster raster = optDestCM.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = optDestCM.isAlphaPremultiplied();
        BufferedImage x =
            new BufferedImage(optDestCM, raster, isAlphaPremultiplied, (Hashtable<?, ?>) null);
        return x;
    }

    /**
     * @throws NullPointerException
     *         if {@code srcPt} is {@code null}
     */
    @Override
    public Point2D getPoint2D(Point2D srcPt, Point2D optDstPt) {
        ObjectArgs.checkNotNull(srcPt, "srcPt");
        
        if (null == optDstPt) {
            optDstPt = new Point2D.Double(srcPt.getX(), srcPt.getY());
        }
        else {
            optDstPt.setLocation(srcPt);
        }
        return optDstPt;
    }

    /**
     * @return always {@code null}
     */
    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
