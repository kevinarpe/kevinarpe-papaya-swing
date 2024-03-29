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

import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;

/**
 * Constants used for {@link BufferedImage} transparency.  Unlike integer constants from
 * {@link Transparency}, this enum restricts the range of permissible values.  Used judiciously,
 * it may help to reduce runtime errors and increase source code readability.
 * <p>
 * Example methods:
 * <ul>
 *   <li></li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see BufferedImage
 */
@NotFullyTested
public enum PTransparency {
    
    /**
     * @see Transparency#OPAQUE
     */
    OPAQUE(Transparency.OPAQUE),
    
    /**
     * @see Transparency#BITMASK
     */
    BITMASK(Transparency.BITMASK),
    
    /**
     * @see Transparency#TRANSLUCENT
     */
    TRANSLUCENT(Transparency.TRANSLUCENT),
    ;
    
    public static void main(String[] argArr) {
        System.out.println(PTransparency.TRANSLUCENT);
    }
  
    private static final Map<Integer, PTransparency> _INT_VALUE_TO_ENUM_VALUE_MAP;
  
    static {
        ImmutableMap.Builder<Integer, PTransparency> x = ImmutableMap.builder();
        for (PTransparency e: PTransparency.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
  
    /**
     * Converts an integer constant from {@link Transparency}, e.g.,
     * {@link Transparency#TRANSLUCENT}, to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link Transparency}, e.g., {@link Transparency#TRANSLUCENT}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PTransparency valueOf(int value) {
        PTransparency x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PTransparency.class.getSimpleName(), value));
        }
        return x;
    }

    /**
     * Corresponding integer constant from {@link Transparency}, e.g.,
     * {@link Transparency#TRANSLUCENT}.
     */
    public final int value;
    
    private PTransparency(int value) {
        this.value = value;
    }
    
    /**
     * Converts this reference to a debugger-friendly string.  Do not depend upon this method, nor
     * implicit {@link String} conversion.  Instead, if only the name is required, call
     * {@link #name()} directly.
     * <hr>
     * Docs from {@link Enum#toString()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String x = String.format(
            "enum %s: ["
            + "%n\tname(): '%s'"
            + "%n\tvalue: %d (%s.%s)"
            + "%n\t]",
            PTransparency.class.getCanonicalName(),
            name(),
            value,
            Transparency.class.getSimpleName(),
            name());
        return x;
    }
}
