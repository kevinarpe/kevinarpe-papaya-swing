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

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.util.Arrays;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.IntArgs;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.PImageArgs;

/**
 * Collection of static methods for {@link Image} and subclasses.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see Image
 * @see BufferedImage
 */
public final class PImageUtils {
    
    // TODO: When to call Image.flush()?
    
    private static GraphicsConfiguration _defaultGraphicsConfig;
    
    /**
     * Retrieves the default graphics configuration by calling:
     * {@code GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()}.
     * This method is cached and thread-safe.  The value will not change between calls.
     * 
     * @see GraphicsEnvironment#getLocalGraphicsEnvironment()
     * @see GraphicsEnvironment#getDefaultScreenDevice()
     * @see GraphicsDevice#getDefaultConfiguration()
     */
    @NotFullyTested
    public static GraphicsConfiguration getDefaultGraphicsConfiguration() {
        if (null == _defaultGraphicsConfig) {
            final GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            final GraphicsDevice gDevice = gEnv.getDefaultScreenDevice();
            _defaultGraphicsConfig = gDevice.getDefaultConfiguration();
        }
        return _defaultGraphicsConfig;
    }
    
    /**
     * Tests if an image has a color model compatible with the current graphics configuration.
     * This method does <b>not</b> check if the image dimensions are valid (positive).
     * <p>
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param image
     *        a {@link BufferedImage} reference
     * 
     * @return {@code true} if compatible
     * 
     * @throws NullPointerException
     *         if {@code image} is {@code null}
     * 
     * @see PImageArgs#checkCompatibleImage(BufferedImage, String)
     * @see #getDefaultGraphicsConfiguration()
     * @see BufferedImage#getColorModel()
     * @see #createCompatibleImage(BufferedImage)
     */
    @NotFullyTested
    public static boolean isCompatibleImage(BufferedImage image) {
        ObjectArgs.checkNotNull(image, "image");
        
        final ColorModel srcColorModel = image.getColorModel();
        final int srcColorModelTransparency = srcColorModel.getTransparency();
        
        final GraphicsConfiguration gConfig = getDefaultGraphicsConfiguration();
        final ColorModel currColorModel = gConfig.getColorModel(srcColorModelTransparency);
        if (null == currColorModel) {
            return false;
        }
        
        boolean x = srcColorModel.equals(currColorModel);
        return x;
    }
    
    /**
     * If necessary, copies the input to a new compatible image; else returns the input unchanged.
     * This method is closely related to {@link #convertImageToCompatible(Image)} but operates on
     * {@link BufferedImage}.
     * <p>
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param image
     *        input reference to be converted
     * 
     * @return input unchanged or copy of input in a compatible format
     * 
     * @throws NullPointerException
     *         if {@code srcImage} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code srcImage} width or height is not positive
     * 
     * @see #isCompatibleImage(BufferedImage)
     * @see #createCompatibleImage(BufferedImage)
     * @see #convertImageToCompatible(Image)
     */
    @NotFullyTested
    public static BufferedImage convertBufferedImageToCompatible(BufferedImage image) {
        ObjectArgs.checkNotNull(image, "image");
        
        if (isCompatibleImage(image)) {
            return image;
        }
        final BufferedImage newImage = createCompatibleImage(image);
        _drawImage(image, newImage);
        return newImage;
    }
    
    /**
     * If necessary, copies the input to a new compatible image; else returns the input unchanged.
     * This method is closely related to {@link #convertBufferedImageToCompatible(BufferedImage)}
     * but operates on {@link Image}.
     * <p>
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param image
     *        input reference to be converted
     * 
     * @return input unchanged or copy of input in a compatible format
     * 
     * @throws NullPointerException
     *         if {@code image} or {@code imageType} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code image} width or height is not positive (<= 0)
     * 
     * @see #isCompatibleImage(BufferedImage)
     * @see #createCompatibleImage(int, int, PTransparency)
     * @see #convertBufferedImageToCompatible(BufferedImage)
     */
    @NotFullyTested
    public static BufferedImage convertImageToCompatible(Image image) {
        ObjectArgs.checkNotNull(image, "image");
        
        if (image instanceof BufferedImage) {
            BufferedImage x = (BufferedImage) image;
            if (isCompatibleImage(x)) {
                return x;
            }
        }
        
        PImageArgs.checkImageDimensionsValid(image, "image");
        final int width = image.getWidth((ImageObserver) null);
        final int height = image.getHeight((ImageObserver) null);
        final BufferedImage newImage =
            createCompatibleImage(width, height, PTransparency.TRANSLUCENT);
        _drawImage(image, newImage);
        return newImage;
    }
    
    private static void _drawImage(Image srcImage, Image destImage) {
        ObjectArgs.checkNotNull(srcImage, "srcImage");
        ObjectArgs.checkNotNull(destImage, "destImage");
        
        final Graphics g = destImage.getGraphics();
        final int x = 0;
        final int y = 0;
        try {
            g.drawImage(srcImage, x, y, (ImageObserver) null);
        }
        finally {
            g.dispose();
        }
    }
    
    /**
     * Creates a new compatible image from another image's width, height, and transparency.
     * <p>
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param srcImage
     *        a {@link BufferedImage} reference
     * 
     * @return new compatible image with same features as input image
     * 
     * @throws NullPointerException
     *         if {@code srcImage} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code srcImage} width or height is not positive
     * 
     * @see #createCompatibleImage(int, int, PTransparency)
     */
    @NotFullyTested
    public static BufferedImage createCompatibleImage(BufferedImage srcImage) {
        ObjectArgs.checkNotNull(srcImage, "srcImage");
        PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int transparency = srcImage.getTransparency();
        GraphicsConfiguration gConfig = getDefaultGraphicsConfiguration();
        BufferedImage x = gConfig.createCompatibleImage(width, height, transparency);
        return x;
    }
    
    /**
     * Creates a new compatible image for a width, height, and transparency.
     * <p>
     * Compatible images are considered the optimal format to display images in AWT or Swing.
     * 
     * @param width
     *        number of pixels wide for the new image
     * @param height
     *        number of pixels high for the new image
     * @param transparency
     *        how transparency will be stored in the new image
     * 
     * @return new compatible image
     * 
     * @throws NullPointerException
     *         if {@code transparency} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code width} or {@code height} is not positive
     * 
     * @see #createCompatibleImage(BufferedImage)
     */
    @NotFullyTested
    public static BufferedImage createCompatibleImage(
            int width, int height, PTransparency transparency) {
        IntArgs.checkPositive(width, "width");
        IntArgs.checkPositive(height, "height");
        ObjectArgs.checkNotNull(transparency, "transparency");
        
        GraphicsConfiguration gConfig = getDefaultGraphicsConfiguration();
        BufferedImage x = gConfig.createCompatibleImage(width, height, transparency.value);
        return x;
    }

//    /**
//     * Converts an {@link Image} to a {@link BufferedImage}.  If the input is a
//     * {@code BufferedImage} and its image type matches, the input is returned.
//     * 
//     * @param image
//     *        input reference to be converted
//     * @param imageType
//     * <ul>
//     *   <li>controls how the image data is stored.  Must not be {@code null}</li>
//     *   <li>in most cases, {@link PBufferedImageType#TYPE_INT_ARGB} will suffice</li>
//     * </ul>
//     * 
//     * @return
//     * <ul>
//     *   <li>type cast input reference if {@code image} is an instance of {@code BufferedImage} <b>and</b> {@code ((BufferedImage) image).getType()} matches {@code imageType}</li>
//     *   <li>new {@code BufferedImage} with correct image type</li>
//     * </ul>
//     * 
//     * @throws NullPointerException
//     *         if {@code image} or {@code imageType} is {@code null}
//     * @throws IllegalArgumentException
//     *         if {@code image} width or height is not positive (<= 0)
//     */
//    @FullyTested
//    public static BufferedImage convertToBufferedImage(Image image, PBufferedImageType imageType) {
//        ObjectArgs.checkNotNull(image, "image");
//        ObjectArgs.checkNotNull(imageType, "imageType");
//        
//        if (image instanceof BufferedImage) {
//            BufferedImage x = (BufferedImage) image;
//            int actualImageType = x.getType();
//            if (actualImageType == imageType.value) {
//                return x;
//            }
//        }
//        
//        final int width = image.getWidth((ImageObserver) null);
//        if (width <= 0) {
//            throw new IllegalArgumentException(String.format(
//                "Argument 'image': Width is invalid: %d", width));
//        }
//        
//        final int height = image.getHeight((ImageObserver) null);
//        if (height <= 0) {
//            throw new IllegalArgumentException(String.format(
//                "Argument 'image': Height is invalid: %d", height));
//        }
//        
//        final BufferedImage newImage = new BufferedImage(width, height, imageType.value);
//        _drawImage(image, newImage);
//        return newImage;
//    }
    
    /**
     * This is a convenience method to call
     * {@link #createCompatibleImageWithScaledBrightness(BufferedImage, BufferedImage, float)}
     * where {@code optDestImage} is {@code null}.
     */
    @NotFullyTested
    public static BufferedImage createCompatibleImageWithScaledBrightness(
            BufferedImage srcImage, float scaleFactor) {
        final BufferedImage optDestImage = null;
        BufferedImage x =
            createCompatibleImageWithScaledBrightness(srcImage, optDestImage, scaleFactor);
        return x;
    }
    
    /**
     * Copies source image and changes its brightness.  All color components (probably R-G-B) are
     * scaled equally.  The alpha channel, if present, is never scaled.
     * 
     * @param srcImage
     *        source image.  Must not be {@code null}
     * @param optDestImage
     * <ul>
     *   <li>optional destination image.  May be {@code null}</li>
     *   <li>if {@code null}, a new {@code BufferedImage} is created by this method</li>
     *   <li>may be the same as {@code srcImage} for in-place operation</li>
     *   <li>if not {@code null}, must pass {@link #isCompatibleImage(BufferedImage)} test</li>
     * </ul>
     * @param scaleFactor
     * <ul>
     *   <li>each color band (probably R-G-B) is multiplied by this value</li>
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
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if {@code srcImage} width or height is not positive</li>
     *   <li>if {@code optDestImage} is not {@code null} and fails
     *   {@link #isCompatibleImage(BufferedImage)} test</li>
     *   <li>if {@code optDestImage} is not {@code null} and width or height is not positive</li>
     *   <li>if rescaling brightness fails</li>
     * </ul>
     * 
     * @see RescaleOp
     * @see #createCompatibleImageWithScaledBrightness(BufferedImage, float)
     */
    @NotFullyTested
    public static BufferedImage createCompatibleImageWithScaledBrightness(
            BufferedImage srcImage, BufferedImage optDestImage, float scaleFactor) {
        PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        if (null != optDestImage) {
            PImageArgs.checkCompatibleImage(optDestImage, "optDestImage");
            PImageArgs.checkImageDimensionsValid(optDestImage, "optDestImage");
        }
        
        if (1.0f == scaleFactor) {
            if (srcImage != optDestImage) {
                _drawImage(srcImage, optDestImage);
            }
            return optDestImage;
        }
        
        // Ref: http://javaingrab.blogspot.hk/2012/10/change-brightness-of-image-using.html
        ColorModel srcImageColorModel = srcImage.getColorModel();
        // For PBufferedImageType.TYPE_INT_ARGB, this is true.
        // For PBufferedImageType.TYPE_INT_RGB, this is false.
        boolean colorModelHasAlpha = srcImageColorModel.hasAlpha();
        // Includes optional alpha channel
        // For PBufferedImageType.TYPE_INT_ARGB (R-G-B-A model), there are four components (or
        //     channels).  The last is alpha channel.
        // For PBufferedImageType.TYPE_INT_RGB (R-G-B model), there are three components (or
        //     channels).  There is no alpha channel.
        int colorModelNumComponents = srcImageColorModel.getNumComponents();
        
        float[] scaleFactorArr = new float[colorModelNumComponents];
        Arrays.fill(scaleFactorArr, scaleFactor);
        if (colorModelHasAlpha) {
            // We assume alpha is always the last channel.  Controversial.
            scaleFactorArr[scaleFactorArr.length - 1] = 1.0f;  // Do not scale alpha
        }
        
        float[] offsetArr = new float[colorModelNumComponents];
        // Unnecessary: The default value for primitive float is 0.0f
        //Arrays.fill(offsetArr, 0.0f);
        
        if (null == optDestImage) {
            optDestImage = createCompatibleImage(srcImage);
        }
        RescaleOp op = new RescaleOp(scaleFactorArr, offsetArr, (RenderingHints) null);
        op.filter(srcImage, optDestImage);
        return optDestImage;
    }
    
    /**
     * This is a convenience method to call
     * {@link #createCompatibleGrayscaleImage(BufferedImage, BufferedImage)} where
     * {@code optDestImage} is {@code null}.
     */
    @NotFullyTested
    public static BufferedImage createCompatibleGrayscaleImage(BufferedImage srcImage) {
        final BufferedImage optDestImage = null;
        BufferedImage x = createCompatibleGrayscaleImage(srcImage, optDestImage);
        return x;
    }
    
    /**
     * Copies source image and converts all colors to grayscale.  The alpha channel, if present, is
     * preserved.
     * 
     * @param srcImage
     *        source image.  Must not be {@code null}
     * @param optDestImage
     * <ul>
     *   <li>optional destination image.  May be {@code null}</li>
     *   <li>if {@code null}, a new {@code BufferedImage} is created by this method</li>
     *   <li>may be the same as {@code srcImage} for in-place operation</li>
     *   <li>if not {@code null}, must pass {@link #isCompatibleImage(BufferedImage)} test</li>
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
     * @throws IllegalArgumentException
     * <ul>
     *   <li>if {@code srcImage} width or height is not positive</li>
     *   <li>if {@code optDestImage} is not {@code null} and fails
     *   {@link #isCompatibleImage(BufferedImage)} test</li>
     *   <li>if {@code optDestImage} is not {@code null} and width or height is not positive</li>
     *   <li>if grayscale transformation fails</li>
     * </ul>
     * 
     * @see #createCompatibleGrayscaleImage(BufferedImage)
     * @see ColorSpace#CS_GRAY
     * @see ColorConvertOp
     */
    @NotFullyTested
    public static BufferedImage createCompatibleGrayscaleImage(
            BufferedImage srcImage, BufferedImage optDestImage) {
        PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        if (null == optDestImage) {
            PImageArgs.checkCompatibleImage(optDestImage, "optDestImage");
            PImageArgs.checkImageDimensionsValid(srcImage, "srcImage");
        }
        else {
            PImageArgs.checkImageDimensionsValidAndEqual(
                srcImage, optDestImage, "srcImage", "optDestImage");
        }
        
        final ColorSpace destColorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        final ColorConvertOp op = new ColorConvertOp(destColorSpace, (RenderingHints) null);
        if (null == optDestImage) {
            optDestImage = createCompatibleImage(srcImage);
        }
        op.filter(srcImage, optDestImage);
        return optDestImage;
    }
}
