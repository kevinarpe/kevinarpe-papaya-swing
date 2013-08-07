package com.googlecode.kevinarpe.papaya.swing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
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
}
