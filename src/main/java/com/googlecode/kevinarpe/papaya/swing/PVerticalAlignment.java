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

import java.util.Map;

import javax.swing.SwingConstants;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.swing.widget.PJLabel;

/**
 * Constants used for widget vertical alignment.  Unlike {@link SwingConstants}, which only holds
 * integer values, this enum restricts the range of permissible values.  Used judiciously, it may
 * help to reduce runtime errors and increase source code readability.
 * <p>
 * Example methods:
 * <ul>
 *   <li>{@link PJLabel#setVerticalAlignment(PVerticalAlignment)}</li>
 *   <li>{@link PJLabel#setVerticalTextPosition(PVerticalAlignment)}</li>
 *   <li>{@link PJLabel#getVerticalAlignmentAsEnum()}</li>
 *   <li>{@link PJLabel#getVerticalTextPositionAsEnum()}</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see SwingConstants
 * @see #value
 * @see #valueOf(int)
 * @see PHorizontalAlignment
 */
@FullyTested
public enum PVerticalAlignment {

    /**
     * @see SwingConstants#TOP
     */
    TOP(SwingConstants.TOP),

    /**
     * @see SwingConstants#CENTER
     */
    CENTER(SwingConstants.CENTER),

    /**
     * @see SwingConstants#BOTTOM
     */
    BOTTOM(SwingConstants.BOTTOM),
    ;
    
//    public static void main(String[] argArr) {
//        System.out.println(PVerticalAlignment.TOP);
//    }
    
    public static final Map<Integer, PVerticalAlignment> _INT_VALUE_TO_ENUM_VALUE_MAP;
    
    static {
        ImmutableMap.Builder<Integer, PVerticalAlignment> x = ImmutableMap.builder();
        for (PVerticalAlignment e: PVerticalAlignment.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
    
    /**
     * Converts an integer constant from {@link SwingConstants}, e.g., {@link SwingConstants#TOP},
     * to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link SwingConstants}, e.g., {@link SwingConstants#TOP}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PVerticalAlignment valueOf(int value) {
        PVerticalAlignment x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PVerticalAlignment.class.getSimpleName(), value));
        }
        return x;
    }
    
    /**
     * Corresponding integer constant from {@link SwingConstants}, e.g.,
     * {@link SwingConstants#TOP}.
     */
    public final int value;
    
    private PVerticalAlignment(int value) {
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
            PVerticalAlignment.class.getCanonicalName(),
            name(),
            value,
            SwingConstants.class.getSimpleName(),
            name());
        return x;
    }
}
