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
import java.util.EnumSet;

import javax.swing.SwingUtilities;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Key and mouse button modifiers used for input events.  This enumeration was created with a few
 * helper methods to clarify the masks provided in the interface {@link InputEvent}.
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
     */
    SHIFT_DOWN_MASK(ModifierType.KEY, InputEvent.SHIFT_DOWN_MASK),
    
    /**
     * Represents the Control key pressed down.
     * 
     * @see ModifierType#KEY
     * @see InputEvent#CTRL_DOWN_MASK
     */
    CTRL_DOWN_MASK(ModifierType.KEY, InputEvent.CTRL_DOWN_MASK),
    
    /**
     * Represents the Meta key pressed down.
     * <p>
     * Learn more about this key here: <a href="http://en.wikipedia.org/wiki/Meta_key">
     * http://en.wikipedia.org/wiki/Meta_key</a>
     * 
     * @see ModifierType#KEY
     * @see InputEvent#META_DOWN_MASK
     */
    META_DOWN_MASK(ModifierType.KEY, InputEvent.META_DOWN_MASK),
    
    /**
     * Represents the Alt key pressed down.
     * 
     * @see ModifierType#KEY
     * @see InputEvent#ALT_DOWN_MASK
     */
    ALT_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_DOWN_MASK),
    
    /**
     * Represents the Alt Graph key pressed down.
     * <p>
     * Learn more about this key here: <a href="http://en.wikipedia.org/wiki/AltGr_key">
     * http://en.wikipedia.org/wiki/AltGr_key</a>
     * 
     * @see ModifierType#KEY
     * @see InputEvent#ALT_GRAPH_DOWN_MASK
     */
    ALT_GRAPH_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_GRAPH_DOWN_MASK),
    
    /**
     * Represents the left mouse button pressed down.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON1_DOWN_MASK
     * @see SwingUtilities#isLeftMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON1_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON1_DOWN_MASK),
    
    /**
     * Represents the middle mouse button pressed down.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON2_DOWN_MASK
     * @see SwingUtilities#isMiddleMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON2_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON2_DOWN_MASK),
    
    /**
     * Represents the right mouse button pressed down.
     * 
     * @see ModifierType#MOUSE_BUTTON
     * @see InputEvent#BUTTON3_DOWN_MASK
     * @see SwingUtilities#isRightMouseButton(java.awt.event.MouseEvent)
     */
    BUTTON3_DOWN_MASK(ModifierType.MOUSE_BUTTON, InputEvent.BUTTON3_DOWN_MASK),
    ;
    
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
    
    static {
        int allBits = 0;
        int keyBits = 0;
        int buttonBits = 0;
        for (PInputEventModifier mod: values()) {
            allBits |= mod.bits;
            if (mod.modifierType == ModifierType.KEY) {
                keyBits |= mod.bits;
            }
            else if (mod.modifierType == ModifierType.KEY) {
                buttonBits |= mod.bits;
            }
        }
        ALL_MASK_BITS = allBits;
        KEY_MASK_BITS = keyBits;
        BUTTON_MASK_BITS = buttonBits;
    }
    
    /**
     * Is this modifier for keystrokes or mouse buttons?
     */
    public final ModifierType modifierType;
    
    /**
     * Bits from interface {@link InputEvent}
     */
    public final int bits;
    
    private PInputEventModifier(ModifierType modifierType, int bits) {
        this.modifierType = ObjectArgs.checkNotNull(modifierType, "modifierType");
        this.bits = bits;
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
        
        int bits = 0;
        for (PInputEventModifier mod: set) {
            bits |= mod.bits;
        }
        return bits;
    }
}
