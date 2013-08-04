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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.test.TestAssertUtils;

public class PThemeIconNameTest {

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.context
    //
    
    @Test
    public void context_Pass() {
        for (PThemeIconName e: PThemeIconName.values()) {
            Assert.assertNotNull(e.context);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.context
    //
    
    @Test
    public void baseFileName_Pass() {
        for (PThemeIconName e: PThemeIconName.values()) {
            Assert.assertNotNull(e.baseFileName);
            Assert.assertTrue(!e.baseFileName.isEmpty(), "!isEmpty");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.toString()
    //
    
    @Test
    public void toString_Pass() {
        TestAssertUtils.assertToStringMethodValid(PThemeIconName.values());
    }
}
