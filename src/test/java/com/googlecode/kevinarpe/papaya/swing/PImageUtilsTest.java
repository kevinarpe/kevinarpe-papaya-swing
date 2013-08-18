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
