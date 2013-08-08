package com.googlecode.kevinarpe.papaya.swing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;

import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Collection of static methods for {@link Image} and subclasses.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see Image
 * @see BufferedImage
 */
public final class PImageUtils {

    /**
     * Converts an {@link Image} to a {@link BufferedImage}.  If the input can be type cast and the
     * image type matches, return the input.
     * 
     * @param image
     *        input reference to be converted
     * @param imageType
     * <ul>
     *   <li>controls how the image data is stored.  Must not be {@code null}</li>
     *   <li>in most cases, {@link PBufferedImageType#TYPE_INT_ARGB} will suffice</li>
     * </ul>
     * 
     * @return
     * <ul>
     *   <li>type cast input reference if {@code image} is an instance of {@code BufferedImage} <b>and</b> {@code ((BufferedImage) image).getType()} matches {@code imageType}</li>
     *   <li>new {@code BufferedImage} with correct image type</li>
     * </ul>
     * 
     * @throws NullPointerException
     *         if {@code image} or {@code imageType} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code image} width or height is not positive (<= 0)
     */
    @FullyTested
    public static BufferedImage imageToBufferedImage(Image image, PBufferedImageType imageType) {
        ObjectArgs.checkNotNull(image, "image");
        ObjectArgs.checkNotNull(imageType, "imageType");
        
        if (image instanceof BufferedImage) {
            BufferedImage x = (BufferedImage) image;
            int actualImageType = x.getType();
            if (actualImageType == imageType.value) {
                return x;
            }
        }
        
        int width = image.getWidth((ImageObserver) null);
        if (width <= 0) {
            throw new IllegalArgumentException(String.format(
                "Argument 'image': Width is invalid: %d", width));
        }
        
        int height = image.getHeight((ImageObserver) null);
        if (height <= 0) {
            throw new IllegalArgumentException(String.format(
                "Argument 'image': Height is invalid: %d", height));
        }
        
        BufferedImage newImage = new BufferedImage(width, height, imageType.value);
        Graphics2D g = newImage.createGraphics();
        final int x = 0;
        final int y = 0;
        try {
            g.drawImage(image, x, y, (ImageObserver) null);
        }
        finally {
            g.dispose();
        }
        return newImage;
    }
    
    /**
     * This is a convenience method to call
     * {@link #scaleBrightness(BufferedImage, BufferedImage, float)} where {@code optDestImage} is
     * {@code null}.
     */
    @NotFullyTested
    public static BufferedImage scaleBrightness(BufferedImage srcImage, float scaleFactor) {
        final BufferedImage optDestImage = null;
        BufferedImage x = scaleBrightness(srcImage, optDestImage, scaleFactor);
        return x;
    }
    
    /**
     * Copies source image and changes its brightness.  Calls
     * {@link RescaleOp#filter(BufferedImage, BufferedImage)} to scale all color bands (possibly
     * RGB) equally.
     * 
     * @param srcImage
     *        source image.  Must not be {@code null}
     * @param optDestImage
     * <ul>
     *   <li>optional destination image.  May be {@code null}</li>
     *   <li>if {@code null}, a new {@code BufferedImage} is created by this method</li>
     * </ul>
     * @param scaleFactor
     * <ul>
     *   <li>each color band (possibly RGB) is multiplied by this value</li>
     *   <li>value 1.0 will copy the image exactly</li>
     *   <li>for example, to <i>reduce</i> brighness by 15%, use +0.85f</li>
     *   <li>for example, to <i>increase</i> brighness by 15%, use +1.15f</li>
     * </ul>
     * 
     * @return
     * <ul>
     *   <li>if {@code optDestImage} is {@code null}, new image with changed brightness</li>
     *   <li>else, reference to {@code optDestImage}</li>
     * </ul>
     * 
     * @throws NullPointerException
     *         if {@code srcImage} is {@code null}
     * 
     * @see RescaleOp
     */
    @NotFullyTested
    public static BufferedImage scaleBrightness(
            BufferedImage srcImage, BufferedImage optDestImage, float scaleFactor) {
        ObjectArgs.checkNotNull(srcImage, "srcImage");

        // TODO: What if optDestImage == srcImage?
        // Ref: http://javaingrab.blogspot.hk/2012/10/change-brightness-of-image-using.html
        final float offset = 0.0f;
        RescaleOp op = new RescaleOp(scaleFactor, offset, (RenderingHints) null);
        BufferedImage x = op.filter(srcImage, optDestImage);
        return x;
    }
    
    /**
     * This is a convenience method to call
     * {@link #toGrayscale(BufferedImage, BufferedImage)} where {@code optDestImage} is
     * {@code null}.
     */
    @NotFullyTested
    public static BufferedImage toGrayscale(BufferedImage srcImage) {
        final BufferedImage optDestImage = null;
        BufferedImage x = toGrayscale(srcImage, optDestImage);
        return x;
    }
    
    /**
     * Copies source image and converts to grayscale.  Calls
     * {@link ColorConvertOp#filter(BufferedImage, BufferedImage)} to convert to
     * {@link ColorSpace#CS_GRAY}.
     * 
     * @param srcImage
     *        source image.  Must not be {@code null}
     * @param optDestImage
     * <ul>
     *   <li>optional destination image.  May be {@code null}</li>
     *   <li>if {@code null}, a new {@code BufferedImage} is created by this method</li>
     * </ul>
     * 
     * @return
     * <ul>
     *   <li>if {@code optDestImage} is {@code null}, new image in grayscale</li>
     *   <li>else, reference to {@code optDestImage}</li>
     * </ul>
     * 
     * @throws NullPointerException
     *         if {@code srcImage} is {@code null}
     * 
     * @see ColorSpace#CS_GRAY
     * @see ColorConvertOp
     */
    @NotFullyTested
    public static BufferedImage toGrayscale(BufferedImage srcImage, BufferedImage optDestImage) {
        ObjectArgs.checkNotNull(srcImage, "srcImage");
        
        // TODO: What if optDestImage == srcImage?
        // TODO: Create enum PColorSpace?
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, (RenderingHints) null);
        BufferedImage x = op.filter(srcImage, optDestImage);
        return x;
    }
}
