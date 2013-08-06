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

public class PJRadioButtonTest {
    
    @DataProvider
    private static final Object[][] _setText_Pass_Data() {
        return PTextLabelTest.setText_Pass_Data();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(<empty>)
    //

    @Test
    public void ctorEmpty_Pass() {
        PJRadioButton x = new PJRadioButton();
        checkBasicAttrs(
            x,
            PJRadioButton.DEFAULTS.text,
            PJRadioButton.DEFAULTS.text,
            PJRadioButton.DEFAULTS.mnemonicKeyCode,
            PJRadioButton.DEFAULTS.displayedMnemonicIndex,
            (Icon) null,
            PJRadioButton.DEFAULTS.horizontalAlignment,
            PJRadioButton.DEFAULTS.verticalAlignment,
            PJRadioButton.DEFAULTS.horizontalTextPosition,
            PJRadioButton.DEFAULTS.verticalTextPosition,
            PJRadioButton.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(Icon)
    //

    @Test
    public void ctorIcon_Pass() {
        PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE);
        checkBasicAttrs(
            x,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.text,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.text,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.mnemonicKeyCode,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
            PDummyIconImpl.INSTANCE,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition,
            PJRadioButton.DEFAULTS_FOR_ICON_ONLY.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorString_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJRadioButton x = new PJRadioButton(textBeforeParse);
        checkBasicAttrs(
            x,
            textBeforeParse,
            (null == textAfterParse ? "" : textAfterParse),
            mnemonicKeyCode,
            mnemonicIndex,
            (Icon) null,
            PJRadioButton.DEFAULTS.horizontalAlignment,
            PJRadioButton.DEFAULTS.verticalAlignment,
            PJRadioButton.DEFAULTS.horizontalTextPosition,
            PJRadioButton.DEFAULTS.verticalTextPosition,
            PJRadioButton.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(Action)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorAction_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        Action a = new PTextLabelTest.SampleAction(textBeforeParse);
        PJRadioButton x = new PJRadioButton(a);
        checkBasicAttrs(
            x,
            textBeforeParse,
            textAfterParse,
            mnemonicKeyCode,
            mnemonicIndex,
            (Icon) null,
            PJRadioButton.DEFAULTS_FOR_ACTION.horizontalAlignment,
            PJRadioButton.DEFAULTS_FOR_ACTION.verticalAlignment,
            PJRadioButton.DEFAULTS_FOR_ACTION.horizontalTextPosition,
            PJRadioButton.DEFAULTS_FOR_ACTION.verticalTextPosition,
            PJRadioButton.DEFAULTS_FOR_ACTION.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(Icon, boolean)
    //

    @Test
    public void ctorIconBoolean_Pass() {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE, isSelected);
            checkBasicAttrs(
                x,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.text,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.text,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.mnemonicKeyCode,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                PDummyIconImpl.INSTANCE,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJRadioButton.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition,
                isSelected);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(String, boolean)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringBoolean_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJRadioButton x = new PJRadioButton(textBeforeParse, isSelected);
            checkBasicAttrs(
                x,
                textBeforeParse,
                (null == textAfterParse ? "" : textAfterParse),
                mnemonicKeyCode,
                mnemonicIndex,
                (Icon) null,
                PJRadioButton.DEFAULTS.horizontalAlignment,
                PJRadioButton.DEFAULTS.verticalAlignment,
                PJRadioButton.DEFAULTS.horizontalTextPosition,
                PJRadioButton.DEFAULTS.verticalTextPosition,
                isSelected);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(String, Icon)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIcon_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJRadioButton x = new PJRadioButton(textBeforeParse, PDummyIconImpl.INSTANCE);
        checkBasicAttrs(
            x,
            textBeforeParse,
            (null == textAfterParse ? "" : textAfterParse),
            mnemonicKeyCode,
            mnemonicIndex,
            PDummyIconImpl.INSTANCE,
            PJRadioButton.DEFAULTS.horizontalAlignment,
            PJRadioButton.DEFAULTS.verticalAlignment,
            PJRadioButton.DEFAULTS.horizontalTextPosition,
            PJRadioButton.DEFAULTS.verticalTextPosition,
            PJRadioButton.DEFAULTS.isSelected);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.ctor(String, Icon, boolean)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconBoolean_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (boolean isSelected: new boolean[] { true, false }) {
            PJRadioButton x = new PJRadioButton(textBeforeParse, PDummyIconImpl.INSTANCE, isSelected);
            checkBasicAttrs(
                x,
                textBeforeParse,
                (null == textAfterParse ? "" : textAfterParse),
                mnemonicKeyCode,
                mnemonicIndex,
                PDummyIconImpl.INSTANCE,
                PJRadioButton.DEFAULTS.horizontalAlignment,
                PJRadioButton.DEFAULTS.verticalAlignment,
                PJRadioButton.DEFAULTS.horizontalTextPosition,
                PJRadioButton.DEFAULTS.verticalTextPosition,
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
    // PJRadioButton.setText(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void setText_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJRadioButton x = new PJRadioButton();
        x.setText(textBeforeParse);
        PTextLabelTest.coreSetText_Pass(
            x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.setHorizontalAlignment(PHorizontalAlignment)/.getHorizontalAlignmentAsEnum()
    //
    
    @Test
    public void setHorizontalAlignment_Pass() {
        PJRadioButton x = new PJRadioButton();
        _setHorizontalAlignment_Pass(x);
        
        PJRadioButton y = new PJRadioButton(PDummyIconImpl.INSTANCE);
        _setHorizontalAlignment_Pass(y);
    }
    
    public void _setHorizontalAlignment_Pass(PJRadioButton x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalAlignment(halign);
            Assert.assertEquals(x.getHorizontalAlignment(), halign.value);
            Assert.assertEquals(x.getHorizontalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull() {
        PJRadioButton x = new PJRadioButton();
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull2() {
        PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE);
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.setVerticalAlignment(PVerticalAlignment)/.getVerticalAlignmentAsEnum()
    //
    
    @Test
    public void setVerticalAlignment_Pass() {
        PJRadioButton x = new PJRadioButton();
        _setVerticalAlignment_Pass(x);
        
        PJRadioButton y = new PJRadioButton(PDummyIconImpl.INSTANCE);
        _setVerticalAlignment_Pass(y);
    }
    
    public void _setVerticalAlignment_Pass(PJRadioButton x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalAlignment(halign);
            Assert.assertEquals(x.getVerticalAlignment(), halign.value);
            Assert.assertEquals(x.getVerticalAlignmentAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull() {
        PJRadioButton x = new PJRadioButton();
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull2() {
        PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE);
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.setHorizontalTextPosition(PHorizontalAlignment)/.getHorizontalTextPositionAsEnum()
    //
    
    @Test
    public void setHorizontalTextPosition_Pass() {
        PJRadioButton x = new PJRadioButton();
        _setHorizontalTextPosition_Pass(x);
        
        PJRadioButton y = new PJRadioButton(PDummyIconImpl.INSTANCE);
        _setHorizontalTextPosition_Pass(y);
    }
    
    public void _setHorizontalTextPosition_Pass(PJRadioButton x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalTextPosition(halign);
            Assert.assertEquals(x.getHorizontalTextPosition(), halign.value);
            Assert.assertEquals(x.getHorizontalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull() {
        PJRadioButton x = new PJRadioButton();
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull2() {
        PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE);
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJRadioButton.setVerticalTextPosition(PVerticalAlignment)/.getVerticalTextPositionAsEnum()
    //
    
    @Test
    public void setVerticalTextPosition_Pass() {
        PJRadioButton x = new PJRadioButton();
        _setVerticalTextPosition_Pass(x);
        
        PJRadioButton y = new PJRadioButton(PDummyIconImpl.INSTANCE);
        _setVerticalTextPosition_Pass(y);
    }
    
    public void _setVerticalTextPosition_Pass(PJRadioButton x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalTextPosition(halign);
            Assert.assertEquals(x.getVerticalTextPosition(), halign.value);
            Assert.assertEquals(x.getVerticalTextPositionAsEnum(), halign);
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull() {
        PJRadioButton x = new PJRadioButton();
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull2() {
        PJRadioButton x = new PJRadioButton(PDummyIconImpl.INSTANCE);
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
}
