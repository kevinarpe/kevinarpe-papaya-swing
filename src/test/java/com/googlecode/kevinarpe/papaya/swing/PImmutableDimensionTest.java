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

import java.awt.Dimension;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.testing.EqualsTester;

public class PImmutableDimensionTest {

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.getSharedFromDefaultWidthAndHeight
    //
    
    @Test
    public void getSharedFromDefaultWidthAndHeight_Pass() {
        PImmutableDimension x = PImmutableDimension.getSharedFromDefaultWidthAndHeight();
        Assert.assertEquals(x.width, PImmutableDimension.DEFAULT_WIDTH);
        Assert.assertEquals(x.height, PImmutableDimension.DEFAULT_HEIGHT);
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.getSharedFromWidthAndHeight
    //
    
    @Test
    public void getSharedFromWidthAndHeight_Pass() {
        PImmutableDimension x = PImmutableDimension.getSharedFromDefaultWidthAndHeight();
        PImmutableDimension x2 =
            PImmutableDimension.getSharedFromWidthAndHeight(
                PImmutableDimension.DEFAULT_WIDTH, PImmutableDimension.DEFAULT_HEIGHT);
        Assert.assertEquals(x.width, x2.width);
        Assert.assertEquals(x.height, x2.height);
        
        final int width = 17;
        final int height = 31;
        PImmutableDimension y = PImmutableDimension.getSharedFromWidthAndHeight(width, height);
        PImmutableDimension y2 = PImmutableDimension.getSharedFromWidthAndHeight(width, height);
        Assert.assertEquals(y.width, width);
        Assert.assertEquals(y2.width, width);
        Assert.assertEquals(y.height, height);
        Assert.assertEquals(y2.height, height);
        Assert.assertTrue(y == y2);
        Assert.assertEquals(y, y2);
        
        final int width2 = -17;
        final int height2 = -31;
        PImmutableDimension z = PImmutableDimension.getSharedFromWidthAndHeight(width2, height2);
        PImmutableDimension z2 = PImmutableDimension.getSharedFromWidthAndHeight(width2, height2);
        Assert.assertEquals(z.width, width2);
        Assert.assertEquals(z2.width, width2);
        Assert.assertEquals(z.height, height2);
        Assert.assertEquals(z2.height, height2);
        Assert.assertTrue(z == z2);
        Assert.assertEquals(z, z2);
        
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.createFromWidthAndHeight
    //
    
    @Test
    public void createFromWidthAndHeight_Pass() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.createFromWidthAndHeight(width3, height3);
                PImmutableDimension a2 =
                    PImmutableDimension.createFromWidthAndHeight(width3, height3);
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
                Assert.assertEquals(a2.width, width3);
                Assert.assertEquals(a2.height, height3);
                Assert.assertTrue(a != a2);
                Assert.assertEquals(a, a2);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.copyOfDimension
    //
    
    @Test
    public void copyOfDimension_Pass() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                Dimension dim = new Dimension(width3, height3);
                PImmutableDimension a = PImmutableDimension.copyOfDimension(dim);
                PImmutableDimension a2 = PImmutableDimension.copyOfDimension(dim);
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
                Assert.assertEquals(a2.width, width3);
                Assert.assertEquals(a2.height, height3);
                Assert.assertTrue(a != a2);
                Assert.assertEquals(a, a2);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.getWidth/.getHeight
    //
    
    @Test
    public void getWidthAndHeight_Pass() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
                Assert.assertEquals((int) a.getWidth(), width3);
                Assert.assertEquals((int) a.getHeight(), height3);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.setSize
    //
    
    @Test
    public void setSize_Fail() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
                try {
                    a.setSize(width3 * 17, height3 * 31);
                }
                catch (UnsupportedOperationException e) {
                    // ignore
                }
                Assert.assertEquals(a.width, width3);
                Assert.assertEquals(a.height, height3);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.getDescription
    //
    
    @Test
    public void getDescription_Pass() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                String s = a.getDescription();
                Assert.assertNotNull(s);
                Assert.assertTrue(!s.isEmpty(), "!isEmpty");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.toString
    //
    
    @Test
    public void toString_Pass() {
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                String s = a.toString();
                Assert.assertNotNull(s);
                Assert.assertTrue(!s.isEmpty(), "!isEmpty");
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PImmutableDimension.hashCode/.equals
    //

    @Test
    public void hashCodeAndEquals_Pass() {
        EqualsTester eq = new EqualsTester();
        for (int width3 = -100; width3 <= 100; width3 += 10) {
            for (int height3 = -15; height3 <= 15; height3 += 3) {
                PImmutableDimension a =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                PImmutableDimension a2 =
                    PImmutableDimension.getSharedFromWidthAndHeight(width3, height3);
                eq.addEqualityGroup(a, a2);
            }
        }
        eq.testEquals();
    }
}
