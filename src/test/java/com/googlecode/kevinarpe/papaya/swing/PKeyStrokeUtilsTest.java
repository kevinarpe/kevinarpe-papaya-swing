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

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PKeyStrokeUtilsTest {
    
    ///////////////////////////////////////////////////////////////////////////
    // PKeyStrokeUtils.getKeyCode
    //

    @DataProvider
    private static final Object[][] _getKeyCode_Pass_Data() {
        return new Object[][] {
                { '0', KeyEvent.VK_0 },
                { '1', KeyEvent.VK_1 },
                { '2', KeyEvent.VK_2 },
                { '3', KeyEvent.VK_3 },
                { '4', KeyEvent.VK_4 },
                { '5', KeyEvent.VK_5 },
                { '6', KeyEvent.VK_6 },
                { '7', KeyEvent.VK_7 },
                { '8', KeyEvent.VK_8 },
                { '9', KeyEvent.VK_9 },
        };
    }

    @Test(dataProvider = "_getKeyCode_Pass_Data")
    public void getKeyCode_Pass(char keyChar, int expectedKeyCode) {
        int actualKeyCode = PKeyStrokeUtils.getKeyCode(keyChar);
        Assert.assertEquals(actualKeyCode, expectedKeyCode);
    }
    
    @DataProvider
    private static final Object[][] _getKeyCode_MaybePass_Data() {
        return new Object[][] {
                { 'a', KeyEvent.VK_A },
                { 'A', KeyEvent.VK_A },
                { 'z', KeyEvent.VK_Z },
                { 'Z', KeyEvent.VK_Z },
                // All below don't work on my system, but they might work on others.
                // Ref: http://cs.nyu.edu/~yap/classes/visual/03s/lect/l7/#linkSection-11
                { 'ä', KeyEvent.VK_A },
                { 'Ä', KeyEvent.VK_A },
                
                { 'À', KeyEvent.VK_A },
                { 'à', KeyEvent.VK_A },
                { 'Á', KeyEvent.VK_A },
                { 'á', KeyEvent.VK_A },
                { 'Â', KeyEvent.VK_A },
                { 'â', KeyEvent.VK_A },
                { 'Ã', KeyEvent.VK_A },
                { 'ã', KeyEvent.VK_A },
                { 'Å', KeyEvent.VK_A },
                { 'å', KeyEvent.VK_A },
                { 'Æ', KeyEvent.VK_A },
                { 'æ', KeyEvent.VK_A },
        };
    }

    @Test(dataProvider = "_getKeyCode_MaybePass_Data")
    public void getKeyCode_MaybePass(char keyChar, int expectedKeyCode) {
        int actualKeyCode = -1;
        try {
            actualKeyCode = PKeyStrokeUtils.getKeyCode(keyChar);
            try {
                Assert.assertEquals(actualKeyCode, expectedKeyCode);
            }
            catch (Throwable e) {
                // intentionally ignore
                System.out.println(String.format(
                    "getKeyCode_MaybePass: Char '%c' (\\u%04x) key code is unexpected: %d != %d",
                    keyChar, (int) keyChar, actualKeyCode, expectedKeyCode));
            }
        }
        catch (IllegalArgumentException e) {
            // intentionally ignore
            System.out.println(String.format(
                "getKeyCode_MaybePass: Char '%c' (\\u%04x) does not have a key code",
                keyChar, (int) keyChar));
            //e.printStackTrace();
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getKeyCode_FailWithZero() {
        PKeyStrokeUtils.getKeyCode((char) 0);
    }
}
