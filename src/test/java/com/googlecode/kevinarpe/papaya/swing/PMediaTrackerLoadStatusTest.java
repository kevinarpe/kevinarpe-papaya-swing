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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.test.TestAssertUtils;

public class PMediaTrackerLoadStatusTest {

    ///////////////////////////////////////////////////////////////////////////
    // PMediaTrackerLoadStatus.valueOf(int)
    //
    
    @Test
    public void valueOf_Pass() {
        for (PMediaTrackerLoadStatus e: PMediaTrackerLoadStatus.values()) {
            PMediaTrackerLoadStatus e2 = PMediaTrackerLoadStatus.valueOf(e.value);
            Assert.assertTrue(e == e2);
        }
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void valueOf_Fail() {
        for (PMediaTrackerLoadStatus e: PMediaTrackerLoadStatus.values()) {
            if (0 != e.value) {
                PMediaTrackerLoadStatus.valueOf(31 * e.value);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PMediaTrackerLoadStatus.toString()
    //
    
    @Test
    public void toString_Pass() {
        TestAssertUtils.assertToStringMethodValid(PMediaTrackerLoadStatus.values());
    }
}
