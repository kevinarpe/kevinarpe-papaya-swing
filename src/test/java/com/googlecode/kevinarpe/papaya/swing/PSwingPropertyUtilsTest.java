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

import java.net.MalformedURLException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.FuncUtils;

public class PSwingPropertyUtilsTest {

    ///////////////////////////////////////////////////////////////////////////
    // PSwingPropertyUtils.get
    //
    
    @Test
    public void get_Pass() {
        Object key = new Object();
        Object value = PSwingPropertyUtils.get(key);
        Assert.assertNull(value);
        
        value = new Object();
        Object oldValue = PSwingPropertyUtils.put(key, value);
        Assert.assertNull(oldValue);
        
        Object value2 = PSwingPropertyUtils.get(key);
        Assert.assertEquals(value2, value);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void get_FailWithNull() {
        PSwingPropertyUtils.get(null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PSwingPropertyUtils.getAndPutIfMissing
    //
    
    public void getAndPutIfMissing_Pass() {
        Object key = new Object();
        Object value = PSwingPropertyUtils.get(key);
        Assert.assertNull(value);
        
        final Object value2 = new Object();
        FuncUtils.Func0<Object> createValueFunc = new FuncUtils.Func0<Object>() {
            @Override
            public Object call() {
                return value2;
            }
        };
        
        Object newValue = PSwingPropertyUtils.getAndPutIfMissing(key, createValueFunc);
        Assert.assertEquals(newValue, value2);
        
        value = PSwingPropertyUtils.get(key);
        Assert.assertEquals(value, newValue);
    }
    
    @DataProvider
    private static final Object[][] _getAndPutIfMissing_FailWithNull_Data()
    throws MalformedURLException {
        FuncUtils.Func0<Object> x =
            new FuncUtils.Func0<Object>() {
                    @Override
                    public Object call() {
                        return null;
                    }
            };
        return new Object[][] {
                {
                    null,
                    x,
                },
                {
                    new Object(),
                    null,
                },
                {
                    null,
                    null,
                },
        };
    }
    
    @Test(dataProvider = "_getAndPutIfMissing_FailWithNull_Data",
            expectedExceptions = NullPointerException.class)
    public void getAndPutIfMissing_FailWithNull(
            Object key, FuncUtils.Func0<Object> createValueFunc) {
        PSwingPropertyUtils.getAndPutIfMissing(key, createValueFunc);
    }

    ///////////////////////////////////////////////////////////////////////////
    // PSwingPropertyUtils.put
    //
    
    @Test
    public void put_Pass() {
        Object key = new Object();
        Object value = PSwingPropertyUtils.get(key);
        Assert.assertNull(value);
        
        value = new Object();
        Object oldValue = PSwingPropertyUtils.put(key, value);
        Assert.assertNull(oldValue);
        
        Object value2 = PSwingPropertyUtils.get(key);
        Assert.assertEquals(value2, value);
    }
    
    @DataProvider
    private static final Object[][] _put_FailWithNull_Data() {
        return new Object[][] {
                {
                    null,
                    new Object(),
                },
                {
                    new Object(),
                    null,
                },
                {
                    null,
                    null,
                },
        };
    }
    
    @Test(dataProvider = "_put_FailWithNull_Data",
            expectedExceptions = NullPointerException.class)
    public void put_FailWithNull(Object key, Object value) {
        PSwingPropertyUtils.put(key, value);
    }

    ///////////////////////////////////////////////////////////////////////////
    // PSwingPropertyUtils.remove
    //
    
    @Test
    public void remove_Pass() {
        Object key = new Object();
        Object value = PSwingPropertyUtils.get(key);
        Assert.assertNull(value);
        
        value = new Object();
        Object oldValue = PSwingPropertyUtils.put(key, value);
        Assert.assertNull(oldValue);
        
        Object value2 = PSwingPropertyUtils.get(key);
        Assert.assertEquals(value2, value);
        
        Object value3 = PSwingPropertyUtils.remove(key);
        Assert.assertEquals(value3, value2);
        
        value = PSwingPropertyUtils.get(key);
        Assert.assertNull(value);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void remove_Fail() {
        PSwingPropertyUtils.remove(null);
    }
}
