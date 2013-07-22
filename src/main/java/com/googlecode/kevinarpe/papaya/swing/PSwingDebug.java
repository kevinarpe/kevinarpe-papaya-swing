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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

public final class PSwingDebug {
    
    private static final String DEBUG_VALUE = "debug";

    public static boolean isEnabled(Class<?> klass) {
        ObjectArgs.checkNotNull(klass, "klass");
        
        String key = klass.getCanonicalName();
        String value = System.getProperty(key);
        boolean b = (null != value && value.equals(DEBUG_VALUE));
        return b;
    }
    
    public static void setEnabled(Class<?> klass, boolean isEnabled) {
        ObjectArgs.checkNotNull(klass, "klass");
        
        String key = klass.getCanonicalName();
        if (isEnabled) {
            System.setProperty(key, DEBUG_VALUE);
        }
        else {
            System.clearProperty(key);
        }
    }
    
    public static Logger getLogger(Class<?> klass) {
        ObjectArgs.checkNotNull(klass, "klass");
        
        Logger logger = NOPLogger.NOP_LOGGER;;
        if (isEnabled(klass)) {
            logger = LoggerFactory.getLogger(klass);
        }
        return logger;
    }
}
