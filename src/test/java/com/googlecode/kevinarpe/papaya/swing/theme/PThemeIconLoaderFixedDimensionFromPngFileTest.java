package com.googlecode.kevinarpe.papaya.swing.theme;

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

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.argument.PathArgsTest;
import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;
import com.googlecode.kevinarpe.papaya.swing.test.PSampleIcon;

public class PThemeIconLoaderFixedDimensionFromPngFileTest {

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconLoaderFixedDimensionFromPngFile.ctor
    //
    
    private static final File BASE_DIR_PATH =
        new File("src/test/resources/" + PSampleIcon.THEME_DIR_NAME);
    
    @DataProvider
    private static final Object[][] _ctor_FailWithNull_Data() {
        return new Object[][] {
//                {
//                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
//                    new File("src/test/resources/" + PSampleIcon.THEME_DIR_NAME),
//                },
                {
                    null,
                    BASE_DIR_PATH,
                },
                {
                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
                    null,
                },
                {
                    null,
                    null,
                },
        };
    }
    
    @Test(dataProvider = "_ctor_FailWithNull_Data",
            expectedExceptions = NullPointerException.class)
    public void ctor_FailWithNull(PImmutableDimension fixedDimension, File baseDirPath)
    throws PathException {
        new PThemeIconLoaderFixedDimensionFromPngFile(fixedDimension, baseDirPath);
    }
    
    @Test
    public void ctor_FailWithPathNotExist()
    throws PathException {
        try {
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromDefaultWidthAndHeight(),
                new File(UUID.randomUUID().toString()));
        }
        catch (PathException e) {
            Assert.assertEquals(
                e.getReason(),
                PathException.PathExceptionReason.PATH_DOES_NOT_EXIST);
        }
    }
    
    @Test
    public void ctor_FailWithPathIsFile()
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        path.createNewFile();
        try {
            new PThemeIconLoaderFixedDimensionFromPngFile(
                    PImmutableDimension.getSharedFromDefaultWidthAndHeight(),
                    path);
        }
        catch (PathException e) {
            Assert.assertEquals(
                e.getReason(),
                PathException.PathExceptionReason.PATH_IS_FILE);
        }
        finally {
            PathArgsTest.safeRm(path);
        }
    }
    
    @Test
    public void ctor_Pass()
    throws PathException {
        PImmutableDimension d = PImmutableDimension.getSharedFromDefaultWidthAndHeight();
        PThemeIconLoaderFixedDimensionFromPngFile x =
            new PThemeIconLoaderFixedDimensionFromPngFile(d, BASE_DIR_PATH);
        Assert.assertEquals(x.getFixedDimension(), d);
        Assert.assertEquals(
            x.getBaseDirPath().getAbsoluteFile(),
            BASE_DIR_PATH.getAbsoluteFile());
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconLoaderFixedDimensionFromPngFile.ignoreIconLoadErrors
    //
    
    @Test
    public void ignoreIconLoadErrors_Pass()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromDefaultWidthAndHeight(),
                BASE_DIR_PATH);
        boolean b = y.ignoreIconLoadErrors();
        Assert.assertEquals(
            b,
            PThemeIconLoaderFixedDimensionFromPngFile.DEFAULT_IGNORE_ICON_LOAD_ERRORS);
        y.ignoreIconLoadErrors(b);
        boolean b2 = y.ignoreIconLoadErrors();
        Assert.assertEquals(b2, b);
        y.ignoreIconLoadErrors(!b2);
        b2 = y.ignoreIconLoadErrors();
        Assert.assertEquals(b2, !b);
        y.ignoreIconLoadErrors(!b2);
        b2 = y.ignoreIconLoadErrors();
        Assert.assertEquals(b2, b);
    }
    
    @Test
    public void ignoreIconLoadErrors_Pass2()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.ignoreIconLoadErrors(true);
        PThemeImageIcon icon = y.getIcon(PSampleIcon.EDIT_REDO_32x32.themeIconName);
        Assert.assertEquals(icon.ignoreIconLoadErrors(), true);
        
        y = new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.ignoreIconLoadErrors(false);
        icon = y.getIcon(PSampleIcon.EDIT_REDO_22x22.themeIconName);
        Assert.assertEquals(icon.ignoreIconLoadErrors(), false);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconLoaderFixedDimensionFromPngFile.getIcon
    //
    
    @Test
    public void getIcon_Pass()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.getIcon(PSampleIcon.EDIT_REDO_32x32.themeIconName);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getIcon_FailWithBadDim()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
//                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.getIcon(PSampleIcon.EDIT_REDO_32x32.themeIconName);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void getIcon_FailWithNull()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
//                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.getIcon(null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconLoaderFixedDimensionFromPngFile.tryGetIcon
    //
    
    @Test
    public void tryGetIcon_Pass()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        PThemeImageIcon icon = y.tryGetIcon(PSampleIcon.EDIT_REDO_32x32.themeIconName);
        Assert.assertEquals(
            icon.getIconWidth(),
            PSampleIcon.EDIT_REDO_32x32.imageDimension.width);
        Assert.assertEquals(
            icon.getIconHeight(),
            PSampleIcon.EDIT_REDO_32x32.imageDimension.height);
        Assert.assertEquals(
            icon.getExpectedDimension(),
            PSampleIcon.EDIT_REDO_32x32.imageDimension);
        Assert.assertEquals(
            icon.getThemeIconName(),
            PSampleIcon.EDIT_REDO_32x32.themeIconName);
    }
    
    @Test
    public void tryGetIcon_Pass2()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
//                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        PThemeImageIcon icon = y.tryGetIcon(PSampleIcon.EDIT_REDO_32x32.themeIconName);
        Assert.assertNull(icon);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void tryGetIcon_FailWithNull()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
//                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.tryGetIcon(null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconLoaderFixedDimensionFromPngFile.iconExists
    //
    
    @Test
    public void iconExists_Pass()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        Assert.assertTrue(y.iconExists(PSampleIcon.EDIT_REDO_32x32.themeIconName));
    }
    
    @Test
    public void iconExists_Pass2()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
//                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        Assert.assertTrue(!y.iconExists(PSampleIcon.EDIT_REDO_32x32.themeIconName));
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void iconExists_FailWithNull()
    throws PathException {
        PThemeIconLoaderFixedDimensionFromPngFile y =
            new PThemeIconLoaderFixedDimensionFromPngFile(
//                PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444),
                PSampleIcon.EDIT_REDO_32x32.imageDimension,
                BASE_DIR_PATH);
        y.iconExists(null);
    }
}
