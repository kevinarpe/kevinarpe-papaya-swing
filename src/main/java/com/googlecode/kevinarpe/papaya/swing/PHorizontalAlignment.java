package com.googlecode.kevinarpe.papaya.swing;

import java.util.Map;

import javax.swing.SwingConstants;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.swing.widget.PJLabel;

/**
 * Constants used for widget horizontal alignment.  Unlike {@link SwingConstants}, which only holds
 * integer values, this enum restricts the range of permissible values.  Used judiciously, it may
 * help to reduce runtime errors.
 * <p>
 * Example methods:
 * <ul>
 *   <li>{@link PJLabel#setHorizontalAlignment(PHorizontalAlignment)}</li>
 *   <li>{@link PJLabel#setHorizontalTextPosition(PHorizontalAlignment)}</li>
 *   <li>{@link PJLabel#getHorizontalAlignmentAsEnum()}</li>
 *   <li>{@link PJLabel#getHorizontalTextPositionAsEnum()}</li>
 *   <li></li>
 *   <li></li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see SwingConstants
 * @see #value
 * @see #valueOf(int)
 */
public enum PHorizontalAlignment {

    LEFT(SwingConstants.LEFT),
    CENTER(SwingConstants.CENTER),
    RIGHT(SwingConstants.RIGHT),
    LEADING(SwingConstants.LEADING),
    TRAILING(SwingConstants.TRAILING),
    ;
    
//    public static void main(String[] argArr) {
//        System.out.println(PHorizontalAlignment.LEADING);
//    }
    
    /**
     * Immutable map between {@link #value} and the corresponding enum ref, e.g.,
     * {@link #LEFT} -> {@link SwingConstants#LEFT}.
     */
    private static final Map<Integer, PHorizontalAlignment> _INT_VALUE_TO_ENUM_VALUE_MAP;
    
    static {
        ImmutableMap.Builder<Integer, PHorizontalAlignment> x = ImmutableMap.builder();
        for (PHorizontalAlignment e: PHorizontalAlignment.values()) {
            x.put(e.value, e);
        }
        _INT_VALUE_TO_ENUM_VALUE_MAP = x.build();
    }
    
    /**
     * Converts an integer constant from {@link SwingConstants}, e.g., {@link SwingConstants#LEFT},
     * to the corresponding enum value ref.
     * 
     * @param value
     *        integer constant from {@link SwingConstants}, e.g., {@link SwingConstants#LEFT}
     * 
     * @return enum value ref where {@link #value} equals the parameter, {@code value}
     * 
     * @throws IllegalArgumentException
     *         if {@code value} does not match field {@link #value} for any enum value
     * 
     * @see #value
     * @see #valueOf(String)
     */
    public static PHorizontalAlignment valueOf(int value) {
        PHorizontalAlignment x = _INT_VALUE_TO_ENUM_VALUE_MAP.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PHorizontalAlignment.class.getSimpleName(), value));
        }
        return x;
    }
    
    /**
     * Corresponding integer constant from {@link SwingConstants}, e.g.,
     * {@link SwingConstants#LEFT}.
     */
    public final int value;
    
    private PHorizontalAlignment(int value) {
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
            PHorizontalAlignment.class.getCanonicalName(),
            name(),
            value,
            SwingConstants.class.getSimpleName(),
            name());
        return x;
    }
}
