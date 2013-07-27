package com.googlecode.kevinarpe.papaya.swing;

import java.util.Map;

import javax.swing.SwingConstants;

import com.google.common.collect.ImmutableMap;

public enum PVerticalAlignment {

    TOP(SwingConstants.TOP),
    CENTER(SwingConstants.CENTER),
    BOTTOM(SwingConstants.BOTTOM),
    ;
    
    public static void main(String[] argArr) {
        System.out.println(PVerticalAlignment.TOP);
    }
    
    public static final Map<Integer, PVerticalAlignment> INT_VALUE_TO_ENUM_REF;
    
    static {
        ImmutableMap.Builder<Integer, PVerticalAlignment> x = ImmutableMap.builder();
        for (PVerticalAlignment e: PVerticalAlignment.values()) {
            x.put(e.value, e);
        }
        INT_VALUE_TO_ENUM_REF = x.build();
    }
    
    public static PVerticalAlignment valueOf(int value) {
        PVerticalAlignment x = INT_VALUE_TO_ENUM_REF.get(value);
        if (null == x) {
            throw new IllegalArgumentException(String.format("Failed to find %s for value %d",
                PVerticalAlignment.class.getSimpleName(), value));
        }
        return x;
    }
    
    public final int value;
    
    private PVerticalAlignment(int value) {
        this.value = value;
    }
    
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
