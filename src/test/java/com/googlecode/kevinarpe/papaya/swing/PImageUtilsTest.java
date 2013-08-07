package com.googlecode.kevinarpe.papaya.swing;

import java.awt.Image;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.test.PSampleIcon;

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
    public void imageToBufferedImage_Pass()
    throws PathException, InterruptedException {
        Image image2 =
            PImageUtils.imageToBufferedImage(SAMPLE_IMAGE, PBufferedImageType.TYPE_INT_ARGB);
        Image image2b = PImageUtils.imageToBufferedImage(image2, PBufferedImageType.TYPE_INT_ARGB);
        Assert.assertTrue(image2 == image2b);
    }
    
    @DataProvider
    private static final Object[][] _ctor_FailWithNull_Data()
    throws PathException, InterruptedException {
        return new Object[][] {
                { (Image) null, PBufferedImageType.TYPE_3BYTE_BGR },
                { SAMPLE_IMAGE, (PBufferedImageType) null },
                { (Image) null, (PBufferedImageType) null },
        };
    }

    @Test(expectedExceptions = NullPointerException.class,
            dataProvider = "_ctor_FailWithNull_Data")
    public void imageToBufferedImage_FailWithNull(Image image, PBufferedImageType imageType)
    throws PathException, InterruptedException {
        PImageUtils.imageToBufferedImage(image, imageType);
    }
}
