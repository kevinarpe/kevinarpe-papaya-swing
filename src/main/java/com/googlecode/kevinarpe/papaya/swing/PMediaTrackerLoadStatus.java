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

import java.awt.MediaTracker;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;

/**
 * Constants used for {@link MediaTracker} load status.  Unlike {@code MediaTracker}, which only
 * holds integer values, this enum restricts the range of permissible values.  Used judiciously, it
 * may help to reduce runtime errors and increase source code readability.
 * <p>
 * Example methods:
 * <ul>
 *   <li>{@link PImageIconAsync#getImageLoadStatusAsEnum()}</li>
 *   <li>{@link PImageIconAsync#updateImageLoadStatus()}</li>
 *   <li>{@link PImageIconAsync#waitForLoad(long)}</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see MediaTracker
 * @see #value
 * @see #valueOf(int)
 * @see #isDone
 */
@FullyTested
public enum PMediaTrackerLoadStatus {

    /**
     * <i>An image that hasn't started loading has zero as its status.</i>
     * <p>
     * Field {@link #isDone} is {@code false}.
     * 
     * @see MediaTracker#statusID(int, boolean)
     */
    INITIAL(0, false),

    /**
     * Field {@link #isDone} is {@code false}.
     * 
     * @see MediaTracker#LOADING
     */
    LOADING(MediaTracker.LOADING, false),

    /**
     * Field {@link #isDone} is {@code true}.
     * 
     * @see MediaTracker#ABORTED
     */
    ABORTED(MediaTracker.ABORTED, true),

    /**
     * Field {@link #isDone} is {@code true}.
     * 
     * @see MediaTracker#ERRORED
     */
    ERRORED(MediaTracker.ERRORED, true),

    /**
     * Field {@link #isDone} is {@code true}.
     * 
     * @see MediaTracker#COMPLETE
     */
    COMPLETE(MediaTracker.COMPLETE, true),
    ;
    
//    public static void main(String[] argArr) {
//        System.out.println(PMediaTrackerLoadStatus.COMPLETE);
//    }
    
    private static final Map<Integer, PMediaTrackerLoadStatus> _INT_VALUE_TO_ENUM_VALUE_MAP;
    
    static {
        ImmutableMap.Builder<Integer, PMediaTrackerLoadStatus> x = ImmutableMap.builder();
        for (PMediaTrackerLoadStatus e: PMediaTrackerLoadStatus.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
    
    /**
     * Converts an integer constant from {@link MediaTracker}, e.g., {@link MediaTracker#COMPLETE},
     * to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link MediaTracker}, e.g., {@link MediaTracker#COMPLETE}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PMediaTrackerLoadStatus valueOf(int value) {
        PMediaTrackerLoadStatus x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PMediaTrackerLoadStatus.class.getSimpleName(), value));
        }
        return x;
    }
    
    /**
     * Corresponding integer constant from {@link MediaTracker}, e.g.,
     * {@link MediaTracker#COMPLETE}.
     */
    public final int value;
    
    /**
     * If {@code true}, the load has finished, but may not be successful.
     * <ul>
     *   <li>{@link #ABORTED}: finished, but not successful</li>
     *   <li>{@link #ERRORED}: finished, but not successful</li>
     *   <li>{@link #COMPLETE}: finished and successful</li>
     * </ul>
     */
    public final boolean isDone;
    
    private PMediaTrackerLoadStatus(int value, boolean isDone) {
        this.value = value;
        this.isDone = isDone;
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
        String valueStr = "";
        if (this != INITIAL) {
            valueStr = String.format(" (%s.%s)",
                MediaTracker.class.getSimpleName(),
                name());
        }
        String x = String.format(
            "enum %s: ["
            + "%n\tname(): '%s'"
            + "%n\tvalue: %d%s"
            + "%n\tisDone: %b"
            + "%n\t]",
            PMediaTrackerLoadStatus.class.getCanonicalName(),
            name(),
            value,
            valueStr,
            isDone);
        return x;
    }
}
