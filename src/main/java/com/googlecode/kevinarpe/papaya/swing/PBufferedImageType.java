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

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;

/**
 * Constants used for {@link BufferedImage} data storage.  Unlike integer constants from
 * {@code BufferedImage}, this enum restricts the range of permissible values.  Used judiciously,
 * it may help to reduce runtime errors and increase source code readability.
 * <p>
 * The most common type in AWT and Swing is {@link #TYPE_INT_ARGB}.
 * <p>
 * Example methods:
 * <ul>
 *   <li>{@link PImageUtils#convertToBufferedImage(java.awt.Image, PBufferedImageType)}</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see BufferedImage
 */
@FullyTested
public enum PBufferedImageType {
    
    /**
     * @see BufferedImage#TYPE_3BYTE_BGR
     */
    TYPE_3BYTE_BGR(BufferedImage.TYPE_3BYTE_BGR),
    
    /**
     * @see BufferedImage#TYPE_4BYTE_ABGR
     */
    TYPE_4BYTE_ABGR(BufferedImage.TYPE_4BYTE_ABGR),
    
    /**
     * @see BufferedImage#TYPE_4BYTE_ABGR_PRE
     */
    TYPE_4BYTE_ABGR_PRE(BufferedImage.TYPE_4BYTE_ABGR_PRE),
    
    /**
     * @see BufferedImage#TYPE_BYTE_BINARY
     */
    TYPE_BYTE_BINARY(BufferedImage.TYPE_BYTE_BINARY),
    
    /**
     * @see BufferedImage#TYPE_BYTE_GRAY
     */
    TYPE_BYTE_GRAY(BufferedImage.TYPE_BYTE_GRAY),
    
    /**
     * @see BufferedImage#TYPE_BYTE_INDEXED
     */
    TYPE_BYTE_INDEXED(BufferedImage.TYPE_BYTE_INDEXED),
    
    /**
     * @see BufferedImage#TYPE_CUSTOM
     */
    TYPE_CUSTOM(BufferedImage.TYPE_CUSTOM),
    
    /**
     * This is the most common type use in AWT and Swing.  The underlying {@link ColorModel} is
     * {@link ColorModel#getRGBdefault()}.
     * 
     * @see BufferedImage#TYPE_INT_ARGB
     */
    TYPE_INT_ARGB(BufferedImage.TYPE_INT_ARGB),
    
    /**
     * @see BufferedImage#TYPE_INT_ARGB_PRE
     */
    TYPE_INT_ARGB_PRE(BufferedImage.TYPE_INT_ARGB_PRE),
    
    /**
     * @see BufferedImage#TYPE_INT_BGR
     */
    TYPE_INT_BGR(BufferedImage.TYPE_INT_BGR),
    
    /**
     * @see BufferedImage#TYPE_INT_RGB
     */
    TYPE_INT_RGB(BufferedImage.TYPE_INT_RGB),
    
    /**
     * @see BufferedImage#TYPE_USHORT_555_RGB
     */
    TYPE_USHORT_555_RGB(BufferedImage.TYPE_USHORT_555_RGB),
    
    /**
     * @see BufferedImage#TYPE_USHORT_565_RGB
     */
    TYPE_USHORT_565_RGB(BufferedImage.TYPE_USHORT_565_RGB),
    
    /**
     * @see BufferedImage#TYPE_USHORT_GRAY
     */
    TYPE_USHORT_GRAY(BufferedImage.TYPE_USHORT_GRAY),
    ;
    
//    public static void main(String[] argArr) {
//        System.out.println(PBufferedImageType.TYPE_INT_ARGB);
//    }
  
    private static final Map<Integer, PBufferedImageType> _INT_VALUE_TO_ENUM_VALUE_MAP;
  
    static {
        ImmutableMap.Builder<Integer, PBufferedImageType> x = ImmutableMap.builder();
        for (PBufferedImageType e: PBufferedImageType.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
  
    /**
     * Converts an integer constant from {@link BufferedImage}, e.g.,
     * {@link BufferedImage#TYPE_INT_ARGB}, to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link BufferedImage}, e.g.,
     *        {@link BufferedImage#TYPE_INT_ARGB}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PBufferedImageType valueOf(int value) {
        PBufferedImageType x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PBufferedImageType.class.getSimpleName(), value));
        }
        return x;
    }

    /**
     * Corresponding integer constant from {@link BufferedImage}, e.g.,
     * {@link BufferedImage#TYPE_INT_ARGB}.
     */
    public final int value;
    
    private PBufferedImageType(int value) {
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
            PBufferedImageType.class.getCanonicalName(),
            name(),
            value,
            BufferedImage.class.getSimpleName(),
            name());
        return x;
    }
}
