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

import javax.swing.Action;
import javax.swing.Icon;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.test.PDummyIconImpl;

public class PJCheckBoxTest {
    
    @DataProvider
    private static final Object[][] _setText_Pass_Data() {
        return PTextLabelTest.setText_Pass_Data();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(<empty>)
    //

    @Test
    public void ctorEmpty_Pass() {
        PJCheckBox x = new PJCheckBox();
        checkBasicAttrs(
            x,
            PJCheckBox.DEFAULTS.text,
            PJCheckBox.DEFAULTS.text,
            PJCheckBox.DEFAULTS.mnemonicKeyCode,
            PJCheckBox.DEFAULTS.displayedMnemonicIndex,
            (Icon) null,
            PJCheckBox.DEFAULTS.horizontalAlignment,
            PJCheckBox.DEFAULTS.verticalAlignment,
            PJCheckBox.DEFAULTS.horizontalTextPosition,
            PJCheckBox.DEFAULTS.verticalTextPosition,
            PJCheckBox.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(Icon)
    //

    @Test
    public void ctorIcon_Pass() {
        PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE);
        checkBasicAttrs(
            x,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.text,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.text,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.mnemonicKeyCode,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
            PDummyIconImpl.INSTANCE,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition,
            PJCheckBox.DEFAULTS_FOR_ICON_ONLY.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorString_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJCheckBox x = new PJCheckBox(textBeforeParse);
        checkBasicAttrs(
            x,
            textBeforeParse,
            (null == textAfterParse ? "" : textAfterParse),
            mnemonicKeyCode,
            mnemonicIndex,
            (Icon) null,
            PJCheckBox.DEFAULTS.horizontalAlignment,
            PJCheckBox.DEFAULTS.verticalAlignment,
            PJCheckBox.DEFAULTS.horizontalTextPosition,
            PJCheckBox.DEFAULTS.verticalTextPosition,
            PJCheckBox.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(Action)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorAction_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        Action a = new PTextLabelTest.SampleAction(textBeforeParse);
        PJCheckBox x = new PJCheckBox(a);
        checkBasicAttrs(
            x,
            textBeforeParse,
            textAfterParse,
            mnemonicKeyCode,
            mnemonicIndex,
            (Icon) null,
            PJCheckBox.DEFAULTS_FOR_ACTION.horizontalAlignment,
            PJCheckBox.DEFAULTS_FOR_ACTION.verticalAlignment,
            PJCheckBox.DEFAULTS_FOR_ACTION.horizontalTextPosition,
            PJCheckBox.DEFAULTS_FOR_ACTION.verticalTextPosition,
            PJCheckBox.DEFAULTS_FOR_ACTION.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(Icon, boolean)
    //

    @Test
    public void ctorIconBoolean_Pass() {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE, isSelected);
            checkBasicAttrs(
                x,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.text,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.text,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.mnemonicKeyCode,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                PDummyIconImpl.INSTANCE,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJCheckBox.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition,
                isSelected);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(String, boolean)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringBoolean_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJCheckBox x = new PJCheckBox(textBeforeParse, isSelected);
            checkBasicAttrs(
                x,
                textBeforeParse,
                (null == textAfterParse ? "" : textAfterParse),
                mnemonicKeyCode,
                mnemonicIndex,
                (Icon) null,
                PJCheckBox.DEFAULTS.horizontalAlignment,
                PJCheckBox.DEFAULTS.verticalAlignment,
                PJCheckBox.DEFAULTS.horizontalTextPosition,
                PJCheckBox.DEFAULTS.verticalTextPosition,
                isSelected);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(String, Icon)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIcon_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJCheckBox x = new PJCheckBox(textBeforeParse, PDummyIconImpl.INSTANCE);
        checkBasicAttrs(
            x,
            textBeforeParse,
            (null == textAfterParse ? "" : textAfterParse),
            mnemonicKeyCode,
            mnemonicIndex,
            PDummyIconImpl.INSTANCE,
            PJCheckBox.DEFAULTS.horizontalAlignment,
            PJCheckBox.DEFAULTS.verticalAlignment,
            PJCheckBox.DEFAULTS.horizontalTextPosition,
            PJCheckBox.DEFAULTS.verticalTextPosition,
            PJCheckBox.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.ctor(String, Icon, boolean)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconBoolean_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJCheckBox x = new PJCheckBox(textBeforeParse, PDummyIconImpl.INSTANCE, isSelected);
            checkBasicAttrs(
                x,
                textBeforeParse,
                (null == textAfterParse ? "" : textAfterParse),
                mnemonicKeyCode,
                mnemonicIndex,
                PDummyIconImpl.INSTANCE,
                PJCheckBox.DEFAULTS.horizontalAlignment,
                PJCheckBox.DEFAULTS.verticalAlignment,
                PJCheckBox.DEFAULTS.horizontalTextPosition,
                PJCheckBox.DEFAULTS.verticalTextPosition,
                isSelected);
        }
    }
    
    public static void checkBasicAttrs(
            PTextLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex,
            Icon image,
            PHorizontalAlignment halign,
            PVerticalAlignment valign,
            PHorizontalAlignment htextPos,
            PVerticalAlignment vtextPos,
            boolean isSelected) {
        PTextLabelTest.checkBasicAttrs(
            x,
            textBeforeParse,
            textAfterParse,
            mnemonicKeyCode,
            mnemonicIndex,
            image,
            halign,
            valign,
            htextPos,
            vtextPos);
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.setText(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void setText_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJCheckBox x = new PJCheckBox();
        x.setText(textBeforeParse);
        PTextLabelTest.coreSetText_Pass(
            x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.setHorizontalAlignment(PHorizontalAlignment)/.getHorizontalAlignmentAsEnum()
    //
    
    @Test
    public void setHorizontalAlignment_Pass() {
        PJCheckBox x = new PJCheckBox();
        _setHorizontalAlignment_Pass(x);
        
        PJCheckBox y = new PJCheckBox(PDummyIconImpl.INSTANCE);
        _setHorizontalAlignment_Pass(y);
    }
    
    public void _setHorizontalAlignment_Pass(PJCheckBox x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalAlignment(halign);
            Assert.assertEquals(x.getHorizontalAlignment(), halign.value);
            Assert.assertEquals(x.getHorizontalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull() {
        PJCheckBox x = new PJCheckBox();
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull2() {
        PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE);
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.setVerticalAlignment(PVerticalAlignment)/.getVerticalAlignmentAsEnum()
    //
    
    @Test
    public void setVerticalAlignment_Pass() {
        PJCheckBox x = new PJCheckBox();
        _setVerticalAlignment_Pass(x);
        
        PJCheckBox y = new PJCheckBox(PDummyIconImpl.INSTANCE);
        _setVerticalAlignment_Pass(y);
    }
    
    public void _setVerticalAlignment_Pass(PJCheckBox x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalAlignment(halign);
            Assert.assertEquals(x.getVerticalAlignment(), halign.value);
            Assert.assertEquals(x.getVerticalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull() {
        PJCheckBox x = new PJCheckBox();
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull2() {
        PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE);
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.setHorizontalTextPosition(PHorizontalAlignment)/.getHorizontalTextPositionAsEnum()
    //
    
    @Test
    public void setHorizontalTextPosition_Pass() {
        PJCheckBox x = new PJCheckBox();
        _setHorizontalTextPosition_Pass(x);
        
        PJCheckBox y = new PJCheckBox(PDummyIconImpl.INSTANCE);
        _setHorizontalTextPosition_Pass(y);
    }
    
    public void _setHorizontalTextPosition_Pass(PJCheckBox x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalTextPosition(halign);
            Assert.assertEquals(x.getHorizontalTextPosition(), halign.value);
            Assert.assertEquals(x.getHorizontalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull() {
        PJCheckBox x = new PJCheckBox();
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull2() {
        PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE);
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJCheckBox.setVerticalTextPosition(PVerticalAlignment)/.getVerticalTextPositionAsEnum()
    //
    
    @Test
    public void setVerticalTextPosition_Pass() {
        PJCheckBox x = new PJCheckBox();
        _setVerticalTextPosition_Pass(x);
        
        PJCheckBox y = new PJCheckBox(PDummyIconImpl.INSTANCE);
        _setVerticalTextPosition_Pass(y);
    }
    
    public void _setVerticalTextPosition_Pass(PJCheckBox x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalTextPosition(halign);
            Assert.assertEquals(x.getVerticalTextPosition(), halign.value);
            Assert.assertEquals(x.getVerticalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull() {
        PJCheckBox x = new PJCheckBox();
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull2() {
        PJCheckBox x = new PJCheckBox(PDummyIconImpl.INSTANCE);
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
}
