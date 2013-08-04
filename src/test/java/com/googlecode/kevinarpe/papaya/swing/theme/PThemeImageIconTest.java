package com.googlecode.kevinarpe.papaya.swing.theme;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;
import com.googlecode.kevinarpe.papaya.swing.test.PSampleIcon;

public class PThemeImageIconTest {

    ///////////////////////////////////////////////////////////////////////////
    // PThemeImageIcon.ctor
    //
    
    @DataProvider
    private static final Object[][] _ctor_FailWithNull_Data()
    throws MalformedURLException {
        return new Object[][] {
//                {
//                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
//                    PSampleIcon.EDIT_REDO_16x16.themeIconName,
//                    PSampleIcon.EDIT_REDO_16x16.filePath.toURI().toURL(),
//                },
                {
                    null,
                    PSampleIcon.EDIT_REDO_16x16.themeIconName,
                    PSampleIcon.EDIT_REDO_16x16.filePath.toURI().toURL(),
                },
                {
                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
                    null,
                    PSampleIcon.EDIT_REDO_16x16.filePath.toURI().toURL(),
                },
                {
                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
                    PSampleIcon.EDIT_REDO_16x16.themeIconName,
                    null,
                },
                {
                    null,
                    null,
                    PSampleIcon.EDIT_REDO_16x16.filePath.toURI().toURL(),
                },
                {
                    null,
                    PSampleIcon.EDIT_REDO_16x16.themeIconName,
                    null,
                },
                {
                    PSampleIcon.EDIT_REDO_16x16.imageDimension,
                    null,
                    null,
                },
                {
                    null,
                    null,
                    null,
                },
        };
    }
    
    @Test(dataProvider = "_ctor_FailWithNull_Data",
            expectedExceptions = NullPointerException.class)
    public void ctor_FailWithNull(
            PImmutableDimension expectedDimension, PThemeIconName name, URL url) {
        new PThemeImageIcon(expectedDimension, name, url);
    }
    
    @Test
    public void ctor_Pass()
    throws MalformedURLException {
        for (PSampleIcon x: PSampleIcon.LIST) {
            URL url = x.filePath.toURI().toURL();
            PThemeImageIcon y =
                new PThemeImageIcon(x.imageDimension, x.themeIconName, url);
            Assert.assertEquals(y.getExpectedDimension(), x.imageDimension);
            Assert.assertEquals(y.getThemeIconName(), x.themeIconName);
            Assert.assertEquals(y.getUrl(), url);
            String s = y.toString();
            Assert.assertNotNull(s);
            Assert.assertTrue(!s.isEmpty(), "!isEmpty");
        }
    }
}
