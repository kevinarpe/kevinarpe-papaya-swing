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
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.accessibility.AccessibleContext;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.ByteStreams;
import com.googlecode.kevinarpe.papaya.FuncUtils;
import com.googlecode.kevinarpe.papaya.argument.PathArgsTest;
import com.googlecode.kevinarpe.papaya.exception.ClassResourceNotFoundException;
import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.test.PSampleIcon;

public class PImageIconAsyncTest {
    
    private static final List<String> DESC_LIST =
        Collections.unmodifiableList(Arrays.asList(null, "", "   ", "abc", "東京から", "从重庆"));
    
    private static Object[][] _sampleAndDesc_DataCache = null;
    
    @DataProvider
    private static final Object[][] _sampleAndDesc_Data() {
        if (null == _sampleAndDesc_DataCache) {
            int size = PSampleIcon.LIST.size() * DESC_LIST.size();
            Object[][] arrArr = new Object[size][];
            int i = 0;
            for (PSampleIcon x: PSampleIcon.LIST) {
                for (String d: DESC_LIST) {
                    arrArr[i] = new Object[] { x, d };
                    ++i;
                }
            }
            _sampleAndDesc_DataCache = arrArr;
        }
        return _sampleAndDesc_DataCache;
    }
    
    private static Object[][] _sample_DataCache = null;
    
    @DataProvider
    private static final Object[][] _sample_Data() {
        if (null == _sample_DataCache) {
            int size = PSampleIcon.LIST.size();
            Object[][] arrArr = new Object[size][];
            int i = 0;
            for (PSampleIcon x: PSampleIcon.LIST) {
                arrArr[i] = new Object[] { x };
                ++i;
            }
            _sample_DataCache = arrArr;
        }
        return _sample_DataCache;
    }
    
    private static Object[][] _desc_DataCache = null;
    
    @DataProvider
    private static final Object[][] _desc_Data() {
        if (null == _desc_DataCache) {
            int size = DESC_LIST.size();
            Object[][] arrArr = new Object[size][];
            int i = 0;
            for (String d: DESC_LIST) {
                arrArr[i] = new Object[] { d };
                ++i;
            }
            _desc_DataCache = arrArr;
        }
        return _desc_DataCache;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(<empty>)
    //

    @Test
    public void ctorEmpty_Pass() {
        new PImageIconAsync();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(InputStream, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorInputStreamString_Pass(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync(new FileInputStream(x.filePath), desc);
        new PImageIconAsync(
            PImageIconAsyncTest.class.getResourceAsStream(x.resourcePathname),
            desc);
    }

    @Test(dataProvider = "_sampleAndDesc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorInputStreamString_FailWithNull(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync((InputStream) null, desc);
    }

    @Test(dataProvider = "_sampleAndDesc_Data",
            expectedExceptions = IOException.class)
    public void ctorInputStreamString_FailWithClosedInputStream(PSampleIcon x, String desc)
    throws IOException {
        InputStream in = new FileInputStream(x.filePath);
        in.close();
        new PImageIconAsync(in, desc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(InputStream)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorInputStream_Pass(PSampleIcon x)
    throws IOException {
        new PImageIconAsync(new FileInputStream(x.filePath));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorInputStream_FailWithNull()
    throws IOException {
        new PImageIconAsync((InputStream) null);
    }

    @Test(dataProvider = "_sample_Data",
            expectedExceptions = IOException.class)
    public void ctorInputStream_FailWithClosedInputStream(PSampleIcon x)
    throws IOException {
        InputStream in = new FileInputStream(x.filePath);
        in.close();
        new PImageIconAsync(in);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(Class, String, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorClassStringString_Pass(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, x.resourcePathname, desc);
    }

    @Test(dataProvider = "_sampleAndDesc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorClassStringString_FailWithNull(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync((Class<?>) null, x.resourcePathname, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorClassStringString_FailWithNull2(String desc)
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, (String) null, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorClassStringString_FailWithNull3(String desc)
    throws IOException {
        new PImageIconAsync((Class<?>) null, (String) null, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = IllegalArgumentException.class)
    public void ctorClassStringString_FailWithEmptyResourcePath(String desc)
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, "", desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = ClassResourceNotFoundException.class)
    public void ctorClassStringString_FailWithBadResourcePath(String desc)
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, UUID.randomUUID().toString(), desc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(Class, String)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorClassString_Pass(PSampleIcon x)
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, x.resourcePathname);
    }

    @Test(dataProvider = "_sample_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorClassString_FailWithNull(PSampleIcon x)
    throws IOException {
        new PImageIconAsync((Class<?>) null, x.resourcePathname);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorClassString_FailWithNull2()
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, (String) null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorClassString_FailWithNull3()
    throws IOException {
        new PImageIconAsync((Class<?>) null, (String) null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void ctorClassString_FailWithEmptyResourcePath()
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, "");
    }

    @Test(expectedExceptions = ClassResourceNotFoundException.class)
    public void ctorClassString_FailWithBadResourcePath()
    throws IOException {
        new PImageIconAsync(PImageIconAsyncTest.class, UUID.randomUUID().toString());
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(byte[], String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorByteArrString_Pass(PSampleIcon x, String desc)
    throws IOException {
        byte[] arr = ByteStreams.toByteArray(new FileInputStream(x.filePath));
        new PImageIconAsync(arr, desc);
        arr = ByteStreams.toByteArray(
                PImageIconAsyncTest.class.getResourceAsStream(x.resourcePathname));
        new PImageIconAsync(arr, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorByteArrString_FailWithNull(String desc)
    throws IOException {
        new PImageIconAsync((byte[]) null, desc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(byte[])
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorByteArr_Pass(PSampleIcon x)
    throws IOException {
        byte[] arr = ByteStreams.toByteArray(new FileInputStream(x.filePath));
        new PImageIconAsync(arr);
        arr = ByteStreams.toByteArray(
                PImageIconAsyncTest.class.getResourceAsStream(x.resourcePathname));
        new PImageIconAsync(arr);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorByteArr_FailWithNull()
    throws IOException {
        new PImageIconAsync((byte[]) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(Image, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorImageString_Pass(PSampleIcon x, String desc)
    throws IOException {
        byte[] arr = ByteStreams.toByteArray(new FileInputStream(x.filePath));
        Image img = Toolkit.getDefaultToolkit().createImage(arr);
        new PImageIconAsync(img, desc);
        arr = ByteStreams.toByteArray(
                PImageIconAsyncTest.class.getResourceAsStream(x.resourcePathname));
        img = Toolkit.getDefaultToolkit().createImage(arr);
        new PImageIconAsync(img, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorImageString_FailWithNull(String desc)
    throws IOException {
        new PImageIconAsync((Image) null, desc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(Image)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorImage_Pass(PSampleIcon x)
    throws IOException {
        byte[] arr = ByteStreams.toByteArray(new FileInputStream(x.filePath));
        Image img = Toolkit.getDefaultToolkit().createImage(arr);
        new PImageIconAsync(img);
        arr = ByteStreams.toByteArray(
                PImageIconAsyncTest.class.getResourceAsStream(x.resourcePathname));
        img = Toolkit.getDefaultToolkit().createImage(arr);
        new PImageIconAsync(img);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorImage_FailWithNull()
    throws IOException {
        new PImageIconAsync((Image) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(String, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorStringString_Pass(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync(x.filePath.getAbsolutePath(), desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorStringString_FailWithNull(String desc)
    throws IOException {
        new PImageIconAsync((String) null, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = IllegalArgumentException.class)
    public void ctorStringString_FailWithEmptyPathname(String desc)
    throws IOException {
        new PImageIconAsync("", desc);
    }

    @Test(dataProvider = "_desc_Data")
    public void ctorStringString_FailWithPathnameNotExist(String desc)
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        try {
            new PImageIconAsync(path.getAbsolutePath(), desc);
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_DOES_NOT_EXIST);
            }
        }
    }

    @Test(dataProvider = "_desc_Data")
    public void ctorStringString_FailWithPathnameIsDirectory(String desc)
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        PathArgsTest.safeMkdir(path);
        try {
            new PImageIconAsync(path.getAbsolutePath(), desc);
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_IS_DIRECTORY);
            }
        }
        finally {
            PathArgsTest.safeRmdir(path);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(String)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorString_Pass(PSampleIcon x)
    throws IOException {
        new PImageIconAsync(x.filePath.getAbsolutePath());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorString_FailWithNull()
    throws IOException {
        new PImageIconAsync((String) null);
    }
    

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void ctorString_FailWithEmptyPathname()
    throws IOException {
        new PImageIconAsync("");
    }

    @Test
    public void ctorString_FailWithPathnameNotExist()
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        try {
            new PImageIconAsync(path.getAbsoluteFile());
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_DOES_NOT_EXIST);
            }
        }
    }

    @Test
    public void ctorString_FailWithPathnameIsDirectory()
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        PathArgsTest.safeMkdir(path);
        try {
            new PImageIconAsync(path.getAbsolutePath());
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_IS_DIRECTORY);
            }
        }
        finally {
            PathArgsTest.safeRmdir(path);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(File, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorFileString_Pass(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync(x.filePath, desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorFileString_FailWithNull(String desc)
    throws IOException {
        new PImageIconAsync((File) null, desc);
    }
    
    @Test(dataProvider = "_desc_Data")
    public void ctorFileString_FailWithPathnameNotExist(String desc)
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        try {
            new PImageIconAsync(path.getAbsoluteFile(), desc);
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_DOES_NOT_EXIST);
            }
        }
    }

    @Test(dataProvider = "_desc_Data")
    public void ctorFileString_FailWithPathnameIsDirectory(String desc)
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        PathArgsTest.safeMkdir(path);
        try {
            new PImageIconAsync(path.getAbsoluteFile(), desc);
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_IS_DIRECTORY);
            }
        }
        finally {
            PathArgsTest.safeRmdir(path);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(File)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorFile_Pass(PSampleIcon x)
    throws IOException {
        new PImageIconAsync(x.filePath);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorFile_FailWithNull()
    throws IOException {
        new PImageIconAsync((File) null);
    }
    
    @Test
    public void ctorFileString_FailWithPathnameNotExist()
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        try {
            new PImageIconAsync(path.getAbsoluteFile());
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_DOES_NOT_EXIST);
            }
        }
    }

    @Test
    public void ctorFileString_FailWithPathnameIsDirectory()
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        PathArgsTest.safeMkdir(path);
        try {
            new PImageIconAsync(path.getAbsoluteFile());
        }
        catch (Exception e) {
            if (e instanceof PathException) {
                PathException e2 = (PathException) e;
                Assert.assertEquals(
                    e2.getReason(),
                    PathException.PathExceptionReason.PATH_IS_DIRECTORY);
            }
        }
        finally {
            PathArgsTest.safeRmdir(path);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(URL, String)
    //

    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ctorUrlString_Pass(PSampleIcon x, String desc)
    throws IOException {
        new PImageIconAsync(x.filePath.toURI().toURL(), desc);
    }

    @Test(dataProvider = "_desc_Data",
            expectedExceptions = NullPointerException.class)
    public void ctorUrlString_FailWithNull(String desc)
    throws IOException {
        new PImageIconAsync((URL) null, desc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ctor(URL)
    //

    @Test(dataProvider = "_sample_Data")
    public void ctorUrl_Pass(PSampleIcon x)
    throws IOException {
        new PImageIconAsync(x.filePath.toURI().toURL());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctorUrl_FailWithNull()
    throws IOException {
        new PImageIconAsync((URL) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.getImageLoadStatus/.getImageLoadStatusAsEnum/.updateImageLoadStatus
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void getImageLoadStatus_Pass(PSampleIcon x, String desc)
    throws PathException, InterruptedException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        
        int a = y.getImageLoadStatus();
        PMediaTrackerLoadStatus b = y.getImageLoadStatusAsEnum();
        Assert.assertEquals(a, b.value);
        Assert.assertEquals(b, PMediaTrackerLoadStatus.INITIAL);
        
        PMediaTrackerLoadStatus b2 = y.updateImageLoadStatus();
        a = y.getImageLoadStatus();
        b = y.getImageLoadStatusAsEnum();
        Assert.assertEquals(a, b.value);
        Assert.assertEquals(b, b2);
        // No guarantee here what the actual load status will be!
        
        y.waitForLoad();
        a = y.getImageLoadStatus();
        b = y.getImageLoadStatusAsEnum();
        Assert.assertEquals(a, b.value);
        Assert.assertEquals(b, PMediaTrackerLoadStatus.COMPLETE);
        
        b2 = y.updateImageLoadStatus();
        a = y.getImageLoadStatus();
        b = y.getImageLoadStatusAsEnum();
        Assert.assertEquals(a, b.value);
        Assert.assertEquals(b, b2);
        Assert.assertEquals(b, PMediaTrackerLoadStatus.COMPLETE);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.ignoreIconLoadErrors
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void ignoreIconLoadErrors_Pass(PSampleIcon x, String desc)
    throws PathException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        boolean b = y.ignoreIconLoadErrors();
        Assert.assertEquals(b, PImageIconAsync.DEFAULT_IGNORE_ICON_LOAD_ERRORS);
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
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.getExpectedDimension/.setExpectedDimension
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void getSetExpectedDimension_Pass(PSampleIcon x, String desc)
    throws PathException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        PImmutableDimension d = y.getExpectedDimension();
        Assert.assertNull(d);
        
        y.setExpectedDimension(null);
        d = y.getExpectedDimension();
        Assert.assertNull(d);
        
        final int width = 17;
        final int height = 31;
        PImmutableDimension d2 = PImmutableDimension.getSharedFromWidthAndHeight(width, height);
        y.setExpectedDimension(d2);
        d = y.getExpectedDimension();
        Assert.assertEquals(d, d2);
        Assert.assertEquals(width, y.getIconWidth());
        Assert.assertEquals(height, y.getIconHeight());
        
        final int width2 = 170;
        final int height2 = 310;
        PImmutableDimension d3 = PImmutableDimension.getSharedFromWidthAndHeight(width2, height2);
        y.setExpectedDimension(d3);
        d = y.getExpectedDimension();
        Assert.assertEquals(d, d3);
        Assert.assertEquals(width2, y.getIconWidth());
        Assert.assertEquals(height2, y.getIconHeight());
        
        y.setExpectedDimension(null);
        d = y.getExpectedDimension();
        Assert.assertNull(d);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.waitForLoad
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void waitForLoad_Pass(PSampleIcon x, String desc)
    throws PathException, InterruptedException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        
        long timeoutMillis = 1;
        PMediaTrackerLoadStatus s = y.waitForLoad(timeoutMillis);
        PMediaTrackerLoadStatus s2 = y.waitForLoad(timeoutMillis);
        PMediaTrackerLoadStatus s3 = y.waitForLoad(timeoutMillis);
        if (s.isDone) {
            Assert.assertEquals(s2, s);
            Assert.assertEquals(s3, s);
        }
        PMediaTrackerLoadStatus s4 = y.waitForLoad();
        Assert.assertTrue(s4.isDone);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.toString
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void toString_Pass(PSampleIcon x, String desc)
    throws PathException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        String s = y.toString();
        Assert.assertNotNull(s);
        Assert.assertTrue(!s.isEmpty(), "!isEmpty");
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.getIconWidth/.getIconHeight
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void getIconWidthAndHeight_Pass(PSampleIcon x, String desc)
    throws PathException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        y.setExpectedDimension(x.imageDimension);
        Assert.assertEquals(y.getIconWidth(), x.imageDimension.width);
        Assert.assertEquals(y.getIconHeight(), x.imageDimension.height);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void getIconWidth_FailWithEmptyFile()
    throws IOException {
        _FailWithEmptyFile(new FuncUtils.Func1<Void, PImageIconAsync>() {
            @Override
            public Void call(PImageIconAsync x) {
                x.getIconWidth();
                return null;
            }
        });
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void getIconHeight_FailWithEmptyFile()
    throws IOException {
        _FailWithEmptyFile(new FuncUtils.Func1<Void, PImageIconAsync>() {
            @Override
            public Void call(PImageIconAsync x) {
                x.getIconHeight();
                return null;
            }
        });
    }

    private void _FailWithEmptyFile(FuncUtils.Func1<Void, PImageIconAsync> iconCallback)
    throws IOException {
        File path = new File(UUID.randomUUID().toString());
        path.createNewFile();
        PImageIconAsync y = new PImageIconAsync(path);
        try {
            iconCallback.call(y);
        }
        finally {
            PathArgsTest.safeRm(path);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.paintIcon
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data",
            expectedExceptions = IllegalStateException.class)
    public void paintIcon_FailWithNonMatchingImageDimensions(PSampleIcon x, String desc)
    throws PathException, InterruptedException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        y.setExpectedDimension(PImmutableDimension.getSharedFromWidthAndHeight(33333, 44444));
        y.paintIcon(null, null, -1, -1);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void paintIcon_FailWithEmptyFile()
    throws IOException {
        _FailWithEmptyFile(new FuncUtils.Func1<Void, PImageIconAsync>() {
            @Override
            public Void call(PImageIconAsync x) {
                x.paintIcon(null, null, -1, -1);
                return null;
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImageIconAsync.getAccessibleContext
    //
    
    @Test(dataProvider = "_sampleAndDesc_Data")
    public void getAccessibleContext_Pass(PSampleIcon x, String desc)
    throws PathException {
        PImageIconAsync y = new PImageIconAsync(x.filePath, desc);
        AccessibleContext z = y.getAccessibleContext();
        Assert.assertNotNull(z);
    }
}
