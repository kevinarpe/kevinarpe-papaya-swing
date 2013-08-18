package com.googlecode.kevinarpe.papaya.argument;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Joiner;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.swing.PBufferedImageType;
import com.googlecode.kevinarpe.papaya.swing.PImageUtils;
import com.googlecode.kevinarpe.papaya.swing.PTransparency;

/**
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 *
 */
public final class PImageArgs {

    /**
     * Tests if an image has a color model compatible with the current graphics configuration.
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param image
     *        an image reference
     * @param argName
     *        argument name for {@code image}, e.g., "bgImg" or "cancelImg"
     * 
     * @return the validated image reference
     * 
     * @throws NullPointerException
     *         if {@code image} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code image} is not compatible with current graphics configuration
     * 
     * @see PImageUtils#isCompatibleImage(BufferedImage)
     * @see PImageUtils#getDefaultGraphicsConfiguration()
     * @see BufferedImage#getColorModel()
     * @see GraphicsConfiguration#createCompatibleImage(int, int)
     */
    @NotFullyTested
    public static <TImage extends BufferedImage>
    TImage checkCompatibleImage(TImage image, String argName) {
        ObjectArgs.checkNotNull(image, "image");
        
        final ColorModel imageColorModel = image.getColorModel();
        final int imageColorModelTransparency = imageColorModel.getTransparency();
        
        final GraphicsConfiguration gConfig = PImageUtils.getDefaultGraphicsConfiguration();
        final ColorModel optCurrColorModel = gConfig.getColorModel(imageColorModelTransparency);
        if (!imageColorModel.equals(optCurrColorModel)) {
            PTransparency imageColorModelTransparency2 =
                PTransparency.valueOf(imageColorModelTransparency);
            String currColorModelStr =
                (null == optCurrColorModel ? "(unsupported)" : optCurrColorModel.toString());
            String w = StringArgs._getArgNameWarning(argName, "argName");
            String msg = String.format(
                "Argument '%s' is not a compatible image"
                + "%nCurrent ColorModel (Transparency.%s): %s"
                + "%nImage   ColorModel (Transparency.%s): %s%s",
                argName,
                imageColorModelTransparency2.name(),
                currColorModelStr,
                imageColorModel,
                w);
            throw new IllegalArgumentException(msg);
        }
        return image;
    }
    
    /**
     * Tests if an image has positive height and width.
     * 
     * @param image
     *        an image reference
     * @param argName
     *        argument name for {@code image}, e.g., "bgImg" or "cancelImg"
     * 
     * @return the validated image reference
     * 
     * @throws NullPointerException
     *         if {@code image} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code image} width or height is not positive
     */
    @NotFullyTested
    public static <TImage extends Image>
    TImage checkImageDimensionsValid(TImage image, String argName) {
        ObjectArgs.checkNotNull(image, "image");
        
        final int width = image.getWidth((ImageObserver) null);
        if (width <= 0) {
            String w = StringArgs._getArgNameWarning(argName, "argName");
            throw new IllegalArgumentException(String.format(
                "Argument '%s': Width is invalid: %d%s", argName, width, w));
        }
        
        final int height = image.getHeight((ImageObserver) null);
        if (height <= 0) {
            String w = StringArgs._getArgNameWarning(argName, "argName");
            throw new IllegalArgumentException(String.format(
                "Argument '%s': Height is invalid: %d", argName, height, w));
        }
        return image;
    }
    
    /**
     * Tests if two images have valid and equal dimensions (height and width).  This method is
     * useful for image transformations that need to check source and destination images.
     * 
     * @param image1
     *        an image reference
     * @param image2
     *        another image reference
     * @param image1ArgName
     *        argument name for {@code image1}, e.g., "bgImg" or "cancelImg"
     * @param image2ArgName
     *        argument name for {@code image2}, e.g., "bgImg" or "cancelImg"
     * 
     * @throws NullPointerException
     *         if {@code image1} and {@code image2} is {@code null}
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if {@code image1} or {@code image2} width or height is not positive</li>
     *   <li>if {@code image1} and {@code image2} width or height does not match</li>
     * </ul>
     * 
     * @see #checkImageDimensionsValid(Image, String)
     */
    @NotFullyTested
    public static void checkImageDimensionsValidAndEqual(
            Image image1, Image image2, String image1ArgName, String image2ArgName) {
        checkImageDimensionsValid(image1, "image1");
        if (image1 == image2) {
            return;
        }
        checkImageDimensionsValid(image2, "image2");
        
        final int srcWidth = image1.getWidth((ImageObserver) null);
        final int destWidth = image2.getWidth((ImageObserver) null);
        final int srcHeight = image1.getHeight((ImageObserver) null);
        final int destHeight = image2.getHeight((ImageObserver) null);
        
        if (srcWidth != destWidth || srcHeight != destHeight) {
            String w = StringArgs._getArgNameWarning(image1ArgName, "image1ArgName");
            String w2 = StringArgs._getArgNameWarning(image2ArgName, "image2ArgName");
            String msg = String.format(
                "Image dimensions are not equal:"
                + "%nArgument '%s' : %dx%d"
                + "%nArgument '%s': %dx%d%s%s",
                image1ArgName, srcWidth, srcHeight,
                image2ArgName, destWidth, destHeight,
                w, w2);
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * This is a convenience method to call
     * {@link #checkBufferedImageType(BufferedImage, String, List)}.
     */
    @NotFullyTested
    public static BufferedImage checkBufferedImageType(
            BufferedImage image, String argName, PBufferedImageType... validImageTypeArr) {
        List<PBufferedImageType> validImageTypeList = Arrays.asList(validImageTypeArr);
        checkBufferedImageType(image, argName, validImageTypeList);
        return image;
    }
    
    /**
     * Tests if {@link BufferedImage#getType()} is valid.
     * 
     * @param image
     *        an image reference
     * @param argName
     *        argument name for {@code image}, e.g., "bgImg" or "cancelImg"
     * @param validImageTypeCollection
     *        which types are valid.
     *        Must not be empty or contain any {@code null} values
     * 
     * @return the validated image reference
     * 
     * @throws NullPointerException
     *         if {@code image} or any element of {@code validImageTypeCollection} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code validImageTypeCollection} is empty
     * 
     * @see PBufferedImageType
     * @see #checkBufferedImageType(BufferedImage, String, PBufferedImageType...)
     */
    @NotFullyTested
    public static BufferedImage checkBufferedImageType(
            BufferedImage image,
            String argName,
            Collection<PBufferedImageType> validImageTypeCollection) {
        ObjectArgs.checkNotNull(image, "image");
        CollectionArgs.checkNotEmptyAndElementsNotNull(
            validImageTypeCollection, "validImageTypeList");
        
        PBufferedImageType srcImageType = PBufferedImageType.valueOf(image.getType());
        if (!validImageTypeCollection.contains(srcImageType)) {
            String w = StringArgs._getArgNameWarning(argName, "argName");
            int size = validImageTypeCollection.size();
            List<String> validImageTypeStrList = new ArrayList<String>(size);
            for (PBufferedImageType validImageType: validImageTypeCollection) {
                String x = String.format(
                    "%s.%s", BufferedImage.class.getSimpleName(), validImageType.name());
                validImageTypeStrList.add(x);
            }
            String msg = String.format(
                "Argument '%s': Unsupported image type: %s.%s"
                + "%nValid types: %s%s",
                argName, BufferedImage.class.getSimpleName(), srcImageType.name(),
                Joiner.on(", ").join(validImageTypeStrList), w);
            throw new IllegalArgumentException(msg);
        }
        return image;
    }
}
