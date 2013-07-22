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

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

public enum InputEventModifier {
    
    SHIFT_DOWN_MASK(ModifierType.KEY, InputEvent.SHIFT_DOWN_MASK),
    CTRL_DOWN_MASK(ModifierType.KEY, InputEvent.CTRL_DOWN_MASK),
    META_DOWN_MASK(ModifierType.KEY, InputEvent.META_DOWN_MASK),
    ALT_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_DOWN_MASK),
    ALT_GRAPH_DOWN_MASK(ModifierType.KEY, InputEvent.ALT_GRAPH_DOWN_MASK),
    BUTTON1_DOWN_MASK(ModifierType.BUTTON, InputEvent.BUTTON1_DOWN_MASK),
    BUTTON2_DOWN_MASK(ModifierType.BUTTON, InputEvent.BUTTON2_DOWN_MASK),
    BUTTON3_DOWN_MASK(ModifierType.BUTTON, InputEvent.BUTTON3_DOWN_MASK),
    ;
    
    public static enum ModifierType {
        KEY,
        BUTTON;
    }
    
    public static final int ALL_MASK_BITS;
    
    public static final int KEY_MASK_BITS;
    
    public static final int BUTTON_MASK_BITS;
    
    static {
        int allBits = 0;
        int keyBits = 0;
        int buttonBits = 0;
        for (InputEventModifier mod: values()) {
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
    
    public final ModifierType modifierType;
    public final int bits;
    
    private InputEventModifier(ModifierType modifierType, int bits) {
        this.modifierType = ObjectArgs.checkNotNull(modifierType, "modifierType");
        this.bits = bits;
    }
    
    public final static int bitwiseOr(EnumSet<InputEventModifier> set) {
        ObjectArgs.checkNotNull(set, "set");
        
        int bits = 0;
        for (InputEventModifier mod: set) {
            bits |= mod.bits;
        }
        return bits;
    }
}
