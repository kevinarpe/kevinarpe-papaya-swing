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

import javax.swing.JSplitPane;
import com.google.common.collect.ImmutableMap;

public enum PJSplitPaneOrientation {

    /**
     * The splitter divides two widgets between left and right.
     * 
     * @see JSplitPane#HORIZONTAL_SPLIT
     */
    HORIZONTAL_SPLIT(JSplitPane.HORIZONTAL_SPLIT),
    
    /**
     * The splitter divides two widgets between top and bottom.
     * 
     * @see JSplitPane#VERTICAL_SPLIT
     */
    VERTICAL_SPLIT(JSplitPane.VERTICAL_SPLIT);
    
    public static final Map<Integer, PJSplitPaneOrientation> _INT_VALUE_TO_ENUM_VALUE_MAP;
    
    static {
        ImmutableMap.Builder<Integer, PJSplitPaneOrientation> x = ImmutableMap.builder();
        for (PJSplitPaneOrientation e: PJSplitPaneOrientation.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
    
    /**
     * Converts an integer constant from {@link JSplitPane}, e.g.,
     * {@link JSplitPane#HORIZONTAL_SPLIT}, to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link JSplitPane}, e.g., {@link JSplitPane#HORIZONTAL_SPLIT}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PJSplitPaneOrientation valueOf(int value) {
        PJSplitPaneOrientation x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PJSplitPaneOrientation.class.getSimpleName(), value));
        }
        return x;
    }
    
    /**
     * Corresponding integer constant from {@link JSplitPane}, e.g.,
     * {@link JSplitPane#HORIZONTAL_SPLIT}.
     */
    public final int value;
    
    private PJSplitPaneOrientation(int value) {
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
            PJSplitPaneOrientation.class.getCanonicalName(),
            name(),
            value,
            JSplitPane.class.getSimpleName(),
            name());
        return x;
    }
}
