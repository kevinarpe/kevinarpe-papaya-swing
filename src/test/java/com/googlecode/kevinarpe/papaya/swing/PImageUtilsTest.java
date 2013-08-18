package com.googlecode.kevinarpe.papaya.swing;

import java.awt.Image;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.test.PSampleIcon;

/**
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 *
 */
public class PImageUtilsTest {
    
    ///////////////////////////////////////////////////////////////////////////
    // ImageUtils.imageToBufferedImage
    //
    
    private static final Image SAMPLE_IMAGE;
    
    static {
        try {
            PImageIconAsync icon = new PImageIconAsync(PSampleIcon.EDIT_REDO_16x16.filePath);
            icon.waitForLoad();
            SAMPLE_IMAGE = icon.getImage();
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Test
    public void convertImageToCompatible_Pass()
    throws PathException, InterruptedException {
        Image image2 = PImageUtils.convertImageToCompatible(SAMPLE_IMAGE);
        Image image2b = PImageUtils.convertImageToCompatible(image2);
        // WTF?  Why does this work?
        // Above: Since image2 is already 'compatible', same ref is returned.
        Assert.assertTrue(image2 == image2b);
    }
    
//    @DataProvider
//    private static final Object[][] _ctor_FailWithNull_Data()
//    throws PathException, InterruptedException {
//        return new Object[][] {
//                { (Image) null, PBufferedImageType.TYPE_3BYTE_BGR },
//                { SAMPLE_IMAGE, (PBufferedImageType) null },
//                { (Image) null, (PBufferedImageType) null },
//        };
//    }

    @Test(expectedExceptions = NullPointerException.class)
    public void convertImageToCompatible_FailWithNull()
    throws PathException, InterruptedException {
        PImageUtils.convertImageToCompatible(null);
    }
}
