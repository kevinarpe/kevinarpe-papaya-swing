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

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.EnumSet;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.google.common.collect.ImmutableMap;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Key and mouse button modifiers used for input events.  This enumeration was created with a few
 * helper methods to clarify the masks provided in the interface {@link InputEvent}.
 * <p>
 * Certain, obsolete values are intentionally missing from this enum:
 * <ul>
 *   <li>{@link InputEvent#SHIFT_MASK}</li>
 *   <li>{@link InputEvent#CTRL_MASK}</li>
 *   <li>{@link InputEvent#META_MASK}</li>
 *   <li>{@link InputEvent#ALT_MASK}</li>
 *   <li>{@link InputEvent#ALT_GRAPH_MASK}</li>
 * </ul>
 * All of these values are replaced by their "down" relatives, e.g.,
 * {@link InputEvent#SHIFT_DOWN_MASK}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see InputEvent
 */
public enum PInputEventModifier {
    
    /**
     * Represents the Shift key pressed down.
     *
     * @see ModifierType#KEY
     * @see InputEvent#SHIFT_DOWN_MASK
     * @see KeyEvent#VK_SHIFT
     */
    SHIFT_DOWN_MASK(ModifierType.KEY, InputEvent.SHIFT_DOWN_MASK, KeyEvent.VK_SHIFT),
    
    /**
     * Represents the Control key pressed down.
     * 
     * @see ModifierType#KEY
     * @see InputEvent#CTRL_DOWN_MASK
     * @see KeyEvent#VK_CONTROL
     */
    CTRL_DOWN_MASK(ModifierType.KEY, InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_CONTROL),
    
    /**
     * Represents the Meta key pressed down.
     * <p>
     * Learn more about this key here:
     * <ul>
     *   <li><a href="http://en.wikipedia.org/wiki/Meta_key"
     *   >http://en.wikipedia.org/wiki/Meta_key</a></li>
     *   <li><a href="http://en.wikipedia.org/wiki/Command_key"
     *   >http://en.wikipedia.org/wiki/Command_key</a></li>
     * </ul>
     * On the Apple OS X platform, this value corresponds to the Command key.
     * (<a href="https://developer.apple.com/library/mac/#documentation/Java/Conceptual/Java14Development/07-NativePlatformIntegration/NativePlatformIntegration.html#//apple_ref/doc/uid/TP40001909-212032"
     * >Reference</a>)
     * 
     * @see ModifierType#KEY
     * @see InputEvent#META_DOWN_MASK
     * @see KeyEvent#VK_META
     */
    META_DOWN_MASK(ModifierType.KEY, InputEvent.META_DOWN_MASK, KeyEvent.VK_META),
    
    /**
     * Represents the Alt key pressed down.
     * <p>
     * On the Apple OS X platform, this value corresponds to the Option key.
     * (<a href="https://developer.apple.com/library/mac/#documentation/Java/Conceptual/Java14Development/07-NativePlatformIntegration/NativePlatformIntegration.html#//apple_ref/doc/uid/TP40001909-212032"
     * >Reference</a>)
     * Learn more about this key here: <a href="http://en.wikipedia.org/wiki/Option_key"
     * >http://en.wikipedia.org/wiki/Option_key</a>
     * 
     * @see ModifierType#KEY
     * @see InputEvent#ALT_DOWN_MASK
     * @see KeyEvent#VK_ALT
     */
    ALT_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_DOWN_MASK, KeyEvent.VK_ALT),
    
    /**
     * Represents the Alt Graph key pressed down.
     * <p>
     * Learn more about this key here: <a href="http://en.wikipedia.org/wiki/AltGr_key">
     * http://en.wikipedia.org/wiki/AltGr_key</a>
     * 
     * @see ModifierType#KEY
     * @see InputEvent#ALT_GRAPH_DOWN_MASK
     * @see KeyEvent#VK_ALT_GRAPH
     */
    ALT_GRAPH_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_GRAPH_DOWN_MASK, KeyEvent.VK_ALT_GRAPH),
    
    /**
     * Represents the left mouse button pressed down.
     * <p>
     * As mouse button masks have no associated virtual key code, field {@link #keyCode} is zero.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON1_DOWN_MASK
     * @see SwingUtilities#isLeftMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON1_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON1_DOWN_MASK, 0),
    
    /**
     * Represents the middle mouse button pressed down.
     * <p>
     * As mouse button masks have no associated virtual key code, field {@link #keyCode} is zero.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON2_DOWN_MASK
     * @see SwingUtilities#isMiddleMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON2_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON2_DOWN_MASK, 0),
    
    /**
     * Represents the right mouse button pressed down.
     * <p>
     * As mouse button masks have no associated virtual key code, field {@link #keyCode} is zero.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON3_DOWN_MASK
     * @see SwingUtilities#isRightMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON3_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON3_DOWN_MASK, 0),
    ;
    
//    public static void main(String[] argArr) {
//        System.out.println(PInputEventModifier.ALT_DOWN_MASK);
//    }
    
    /**
     * Denotes the type of modifier: {@link #KEY} or {@link #MOUSE_BUTTON}
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     */
    public static enum ModifierType {
        
        /**
         * The associated {@link PInputEventModifier} is a mask associated with keystrokes, e.g.,
         * {@link PInputEventModifier#SHIFT_DOWN_MASK}.
         */
        KEY,
        
        /**
         * The associated {@link PInputEventModifier} is a mask associated with mouse buttons,
         * e.g., {@link PInputEventModifier#BUTTON1_DOWN_MASK}.
         */
        MOUSE_BUTTON;
        
        /**
         * Converts this reference to a debugger-friendly string.  Do not depend upon this method,
         * nor implicit {@link String} conversion.  Instead, if only the name is required, call
         * {@link #name()} directly.
         * <hr>
         * Docs from {@link Enum#toString()}:
         * <p>
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            String x = String.format(
                "enum %s: [name(): '%s']",
                ModifierType.class.getCanonicalName(),
                name());
            return x;
        }
    }
    
    /**
     * Created by the bitwise OR of all bit masks in this enum.
     */
    public static final int ALL_MASK_BITS;
    
    /**
     * Created by the bitwise OR of bit masks with {@link #modifierType} as
     * {@link ModifierType#KEY} in this enum.
     */
    public static final int KEY_MASK_BITS;
    
    /**
     * Created by the bitwise OR of bit masks with {@link #modifierType} as
     * {@link ModifierType#MOUSE_BUTTON} in this enum.
     */
    public static final int BUTTON_MASK_BITS;
    
    private static final Map<Integer, PInputEventModifier> _MASK_BITS_TO_ENUM_VALUE_MAP;
    private static final Map<Integer, PInputEventModifier> _KEY_CODE_TO_ENUM_VALUE_MAP;
    
    static {
        int allBits = 0;
        int keyBits = 0;
        int buttonBits = 0;
        
        ImmutableMap.Builder<Integer, PInputEventModifier> maskBitsMapBuilder =
            ImmutableMap.builder();
        ImmutableMap.Builder<Integer, PInputEventModifier> keyCodeMapBuilder =
            ImmutableMap.builder();
        
        for (PInputEventModifier mod: values()) {
            allBits |= mod.maskBits;
            if (mod.modifierType == ModifierType.KEY) {
                keyBits |= mod.maskBits;
            }
            else if (mod.modifierType == ModifierType.KEY) {
                buttonBits |= mod.maskBits;
            }
            maskBitsMapBuilder.put(mod.maskBits, mod);
            if (0 != mod.keyCode) {
                keyCodeMapBuilder.put(mod.keyCode, mod);
            }
        }
        ALL_MASK_BITS = allBits;
        KEY_MASK_BITS = keyBits;
        BUTTON_MASK_BITS = buttonBits;
        _MASK_BITS_TO_ENUM_VALUE_MAP = maskBitsMapBuilder.build();
        _KEY_CODE_TO_ENUM_VALUE_MAP = keyCodeMapBuilder.build();
    }
    
    /**
     * Converts an integer constant from {@link InputEvent}, e.g.,
     * {@link InputEvent#ALT_DOWN_MASK}, to the corresponding enum value ref.
     * 
     * @param maskBits
     *        integer constant from {@link InputEvent}, e.g., {@link InputEvent#ALT_DOWN_MASK}
     * 
     * @return enum value ref where {@link #maskBits} equals the parameter, {@code maskBits}
     * 
     * @throws IllegalArgumentException
     *         if {@code maskBits} does not match field {@link #maskBits} for any enum value
     * 
     * @see #maskBits
     * @see #valueOf(String)
     */
    public static PInputEventModifier valueOfMaskBits(int maskBits) {
        PInputEventModifier x = _MASK_BITS_TO_ENUM_VALUE_MAP.get(maskBits);
        if (null == x) {
            throw new IllegalArgumentException(String.format(
                "Failed to find %s for mask bits %d (0x%08x)",
                PInputEventModifier.class.getSimpleName(), maskBits, maskBits));
        }
        return x;
    }
    
    /**
     * Converts an integer constant from {@link KeyEvent}, e.g., {@link KeyEvent#VK_ALT}, to the
     * corresponding enum value ref.  This method only works for enum values where field
     * {@link #modifierType} is {@link ModifierType#KEY}.
     * 
     * @param keyCode
     *        integer constant from {@link KeyEvent}, e.g., {@link KeyEvent#VK_ALT}
     * 
     * @return enum value ref where {@link #keyCode} equals the parameter, {@code keyCode}
     * 
     * @throws IllegalArgumentException
     *         if {@code keyCode} does not match field {@link #keyCode} for any enum value
     * 
     * @see #keyCode
     * @see #valueOf(String)
     */
    public static PInputEventModifier valueOfKeyCode(int keyCode) {
        PInputEventModifier x = _KEY_CODE_TO_ENUM_VALUE_MAP.get(keyCode);
        if (null == x) {
            throw new IllegalArgumentException(String.format(
                "Failed to find %s for virtual key code %d",
                PInputEventModifier.class.getSimpleName(), keyCode));
        }
        return x;
    }
    
    /**
     * Is this modifier for keystrokes or mouse buttons?
     */
    public final ModifierType modifierType;
    
    /**
     * Bits from interface {@link InputEvent}
     * 
     * @see #valueOfMaskBits(int)
     */
    public final int maskBits;
    
    /**
     * Virtual key code from interface {@link KeyEvent}.  This field is zero when
     * {@link #modifierType} is {@link ModifierType#MOUSE_BUTTON}.
     * 
     * @see #valueOfKeyCode(int)
     */
    public final int keyCode;
    
    private PInputEventModifier(ModifierType modifierType, int maskBits, int keyCode) {
        this.modifierType = ObjectArgs.checkNotNull(modifierType, "modifierType");
        this.maskBits = maskBits;
        this.keyCode = keyCode;
    }
    
    /**
     * Given a set of {@link PInputEventModifier}, calculate the bitwise OR value.
     * 
     * @param set
     *        collection of {@link PInputEventModifier}.  May be empty, cannot be {@code null}
     * 
     * @return bitwise OR of all set values, or zero if set is empty
     * 
     * @throws NullPointerException
     *         if {@code set} is {@code null}
     */
    public final static int bitwiseOr(EnumSet<PInputEventModifier> set) {
        ObjectArgs.checkNotNull(set, "set");
        
        int maskBits = 0;
        for (PInputEventModifier mod: set) {
            maskBits |= mod.maskBits;
        }
        return maskBits;
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
            + "%n\tmodifierType: %s"
            + "%n\tmaskBits: %s / 0x%08x / %d (%s.%s)"
            + "%n\t]",
            PInputEventModifier.class.getCanonicalName(),
            name(),
            modifierType,
            Integer.toBinaryString(maskBits),
            maskBits,
            maskBits,
            InputEvent.class.getSimpleName(),
            name());
        return x;
    }
}
