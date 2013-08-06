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

import javax.swing.JComponent;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

public final class PJComponentUtils {
    
    // Upgrade to use generic getters and setter functors.
    public static enum ClientProperty {
        
        HTML_DISABLE("html.disable", Boolean.class),
        ;
        
        public final Object key;
        public final Class<?> valueClass;
        
        private ClientProperty(Object key, Class<?> valueClass) {
            this.key = key;
            this.valueClass = valueClass;
        }
    }
    
    @NotFullyTested
    public static void setHtmlDisabled(JComponent comp, boolean flag) {
        ObjectArgs.checkNotNull(comp, "comp");
        
        comp.putClientProperty(ClientProperty.HTML_DISABLE.key, (flag ? Boolean.TRUE : null));
    }
    
    @NotFullyTested
    public static boolean isHtmlDisabled(JComponent comp) {
        ObjectArgs.checkNotNull(comp, "comp");
        
        Object x = comp.getClientProperty(ClientProperty.HTML_DISABLE.key);
        boolean y = (x == Boolean.TRUE);
        return y;
    }
}
