package com.googlecode.kevinarpe.papaya.swing.widget;

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

import javax.swing.Icon;
import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.test.PDummyIconImpl;

public class PJLabelTest {
    
    @DataProvider
    private static final Object[][] _setText_Pass_Data() {
        return PTextLabelTest.setText_Pass_Data();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(<empty>)
    //

    @Test
    public void ctorEmpty_Pass() {
        PJLabel x = new PJLabel();
        PTextLabelTest.checkBasicAttrs(
            x,
            PJLabel.DEFAULTS.text,
            PJLabel.DEFAULTS.text,
            PJLabel.DEFAULTS.displayedMnemonicKeyCode,
            PJLabel.DEFAULTS.displayedMnemonicIndex,
            (Icon) null,
            PJLabel.DEFAULTS.horizontalAlignment,
            PJLabel.DEFAULTS.verticalAlignment,
            PJLabel.DEFAULTS.horizontalTextPosition,
            PJLabel.DEFAULTS.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon)
    //

    @Test
    public void ctorIcon_Pass() {
        PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE);
        PTextLabelTest.checkBasicAttrs(
            x,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
            PDummyIconImpl.INSTANCE,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon, int)
    //

    @Test
    public void ctorIconInt_Pass() {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE, halign.value);
            PTextLabelTest.checkBasicAttrs(
                x,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                PDummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon, PHorizontalAlignment)
    //

    @Test
    public void ctorIconPHorizontalAlignment_Pass() {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE, halign);
            PTextLabelTest.checkBasicAttrs(
                x,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                PDummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorString_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel(textBeforeParse);
        PTextLabelTest.checkBasicAttrs(
            x,
            textBeforeParse,
            textAfterParse,
            mnemonicKeyCode,
            mnemonicIndex,
            (Icon) null,
            PJLabel.DEFAULTS.horizontalAlignment,
            PJLabel.DEFAULTS.verticalAlignment,
            PJLabel.DEFAULTS.horizontalTextPosition,
            PJLabel.DEFAULTS.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, int)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringInt_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, halign.value);
            PTextLabelTest.checkBasicAttrs(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                (Icon) null,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, PHorizontalAlignment)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringPHorizontalAlignment_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, halign);
            PTextLabelTest.checkBasicAttrs(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                (Icon) null,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, Icon, int)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconInt_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, PDummyIconImpl.INSTANCE, halign.value);
            PTextLabelTest.checkBasicAttrs(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                PDummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, Icon, PHorizontalAlignment)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconPHorizontalAlignment_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, PDummyIconImpl.INSTANCE, halign);
            PTextLabelTest.checkBasicAttrs(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                PDummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setText(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void setText_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel();
        x.setText(textBeforeParse);
        PTextLabelTest.coreSetText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setHorizontalAlignment(PHorizontalAlignment)/.getHorizontalAlignmentAsEnum()
    //
    
    @Test
    public void setHorizontalAlignment_Pass() {
        PJLabel x = new PJLabel();
        _setHorizontalAlignment_Pass(x);
        
        PJLabel y = new PJLabel(PDummyIconImpl.INSTANCE);
        _setHorizontalAlignment_Pass(y);
    }
    
    public void _setHorizontalAlignment_Pass(PJLabel x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalAlignment(halign);
            Assert.assertEquals(x.getHorizontalAlignment(), halign.value);
            Assert.assertEquals(x.getHorizontalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull2() {
        PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE);
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setVerticalAlignment(PVerticalAlignment)/.getVerticalAlignmentAsEnum()
    //
    
    @Test
    public void setVerticalAlignment_Pass() {
        PJLabel x = new PJLabel();
        _setVerticalAlignment_Pass(x);
        
        PJLabel y = new PJLabel(PDummyIconImpl.INSTANCE);
        _setVerticalAlignment_Pass(y);
    }
    
    public void _setVerticalAlignment_Pass(PJLabel x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalAlignment(halign);
            Assert.assertEquals(x.getVerticalAlignment(), halign.value);
            Assert.assertEquals(x.getVerticalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull2() {
        PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE);
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setHorizontalTextPosition(PHorizontalAlignment)/.getHorizontalTextPositionAsEnum()
    //
    
    @Test
    public void setHorizontalTextPosition_Pass() {
        PJLabel x = new PJLabel();
        _setHorizontalTextPosition_Pass(x);
        
        PJLabel y = new PJLabel(PDummyIconImpl.INSTANCE);
        _setHorizontalTextPosition_Pass(y);
    }
    
    public void _setHorizontalTextPosition_Pass(PJLabel x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalTextPosition(halign);
            Assert.assertEquals(x.getHorizontalTextPosition(), halign.value);
            Assert.assertEquals(x.getHorizontalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull2() {
        PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE);
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setVerticalTextPosition(PVerticalAlignment)/.getVerticalTextPositionAsEnum()
    //
    
    @Test
    public void setVerticalTextPosition_Pass() {
        PJLabel x = new PJLabel();
        _setVerticalTextPosition_Pass(x);
        
        PJLabel y = new PJLabel(PDummyIconImpl.INSTANCE);
        _setVerticalTextPosition_Pass(y);
    }
    
    public void _setVerticalTextPosition_Pass(PJLabel x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalTextPosition(halign);
            Assert.assertEquals(x.getVerticalTextPosition(), halign.value);
            Assert.assertEquals(x.getVerticalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull2() {
        PJLabel x = new PJLabel(PDummyIconImpl.INSTANCE);
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
}
