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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EnumSet;

import javax.swing.SwingUtilities;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

// TODO: How does KeyListener get involved?
// Example: If Ctrl+Button1 begins the drag, if Ctrl is released, does the drag end?  It should.
public class PJDialogMouseDragHelper
implements MouseListener, MouseMotionListener {
    
    public enum CursorChangeTrigger {
        NEVER,
        MOUSE_PRESS,
        MOUSE_DRAG;
    }
    
    public enum CursorChangeTarget {
        NONE,
        COMPONENT,
        PARENT_WINDOW;
    }
    
    private final Component _comp;
    private final int _modifierBits;
//    private final int _complementaryModifierBits;
    private final CursorChangeTrigger _cursorChangeTrigger;
    private final CursorChangeTarget _cursorChangeTarget;
    private Cursor _moveCursor;
    private Cursor _cursorBeforeMousePressed;
    private Point _pointMousePressed;

    public PJDialogMouseDragHelper(
            Component comp,
            EnumSet<PInputEventModifier> modifierSet,
            CursorChangeTrigger cursorChangeTrigger,
            CursorChangeTarget cursorChangeTarget) {
        _comp = ObjectArgs.checkNotNull(comp, "comp");
        _modifierBits = PInputEventModifier.bitwiseOr(modifierSet);
        if (0 == (_modifierBits & PInputEventModifier.BUTTON_MASK_BITS)) {
            throw new IllegalArgumentException(
                "Argument 'modifierSet': Missing at least one button down mask");
        }
//        EnumSet<InputEventModifier> complementaryModifierSet = EnumSet.complementOf(modifierSet);
//        _complementaryModifierBits = InputEventModifier.bitwiseOr(complementaryModifierSet);
        _cursorChangeTrigger = ObjectArgs.checkNotNull(cursorChangeTrigger, "cursorChangeTrigger");
        _cursorChangeTarget = ObjectArgs.checkNotNull(cursorChangeTarget, "cursorChangeTarget");
        _moveCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        if (CursorChangeTrigger.NEVER == cursorChangeTrigger
                && CursorChangeTarget.NONE != cursorChangeTarget) {
            throw new IllegalArgumentException(String.format(
                    "If argument 'cursorChangeTrigger' is %s.%s, then argument 'cursorChangeTarget' must be %s.%s,"
                    + "%n\tbut is %s.%s",
                    CursorChangeTrigger.class.getSimpleName(),
                    cursorChangeTrigger.name(),
                    CursorChangeTarget.class.getSimpleName(),
                    CursorChangeTarget.NONE.name(),
                    CursorChangeTarget.class.getSimpleName(),
                    cursorChangeTarget.name()));
        }
        if (CursorChangeTarget.NONE == cursorChangeTarget
                && CursorChangeTrigger.NEVER != cursorChangeTrigger) {
            throw new IllegalArgumentException(String.format(
                    "If argument 'cursorChangeTarget' is %s.%s, then argument 'cursorChangeTrigger' must be %s.%s,"
                    + "%n\tbut is %s.%s",
                    CursorChangeTarget.class.getSimpleName(),
                    cursorChangeTarget.name(),
                    CursorChangeTrigger.class.getSimpleName(),
                    CursorChangeTrigger.NEVER.name(),
                    CursorChangeTarget.class.getSimpleName(),
                    cursorChangeTrigger.name()));
        }
    }
    
    public Cursor getMoveCursor() {
        return _moveCursor;
    }
    
    public void setMoveCursor(Cursor optCursor) {
        _moveCursor = optCursor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // empty
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == _comp) {
            int modifierBits = e.getModifiersEx();
            // This line is carefully crafted following the documentation of getModifiersEx().
            // Since new modifiers may be introduced in the future, this line of code is relatively
            // future-proof as it is written.
            if (_modifierBits == (PInputEventModifier.ALL_MASK_BITS & modifierBits)) {
                Component optTarget = getCursorChangeTarget();
                _cursorBeforeMousePressed = (null == optTarget ? null : optTarget.getCursor());
                _pointMousePressed = e.getPoint();
                mousePressedCore(e, optTarget);
            }
        }
    }
    
    protected void mousePressedCore(MouseEvent e, Component optTarget) {
        if (CursorChangeTrigger.MOUSE_PRESS == _cursorChangeTrigger) {
            setMoveCursor(optTarget);
        }
        _comp.addMouseMotionListener(this);
    }
    
    protected void setMoveCursor(Component optTarget) {
        if (null != optTarget) {
            optTarget.setCursor(_moveCursor);
        }
    }
    
    protected Component getCursorChangeTarget() {
        Component target = null;
        if (CursorChangeTarget.NONE == _cursorChangeTarget) {
            return null;
        }
        else if (CursorChangeTarget.COMPONENT == _cursorChangeTarget) {
            target = _comp;
        }
        else if (CursorChangeTarget.PARENT_WINDOW == _cursorChangeTarget) {
            target = SwingUtilities.getWindowAncestor(_comp);
            if (null == target) {
                throw new IllegalStateException(String.format(
                    "%s is %s, but component has no parent Window: %s",
                    CursorChangeTarget.class.getSimpleName(),
                    _cursorChangeTarget.name(),
                    _comp));
            }
        }
        else {
            throw new IllegalArgumentException(String.format("Unsupported %s: %s",
                CursorChangeTarget.class.getSimpleName(),
                _cursorChangeTarget.name()));
        }
        return target;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (_comp == source) {
            int modifierBits = e.getModifiersEx();
            int buttonBits = _modifierBits & PInputEventModifier.BUTTON_MASK_BITS;
            // Again, this line is very carefully coded.  At this moment, we only care if at least
            // mouse button (from _modifierBits) has been released.
            if (buttonBits != (buttonBits & modifierBits)) {
                Component optTarget = getCursorChangeTarget();
                mouseReleasedCore(e, optTarget);
                _cursorBeforeMousePressed = null;
                _pointMousePressed = null;
            }
        }
    }

    protected void mouseReleasedCore(MouseEvent e, Component optTarget) {
        if (null != optTarget) {
            optTarget.setCursor(_cursorBeforeMousePressed);
        }
        _comp.removeMouseMotionListener(this);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        // empty
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // empty
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Object source = e.getSource();
        if (source == _comp) {
            if (null != _pointMousePressed) {
                mouseDraggedCore(e);
            }
        }
    }
    
    protected void mouseDraggedCore(MouseEvent e) {
        // TODO: Does this get called multiple times (unnecessarily)?
        if (CursorChangeTrigger.MOUSE_DRAG == _cursorChangeTrigger) {
            Component optTarget = getCursorChangeTarget();
            setMoveCursor(optTarget);
        }
        Point eventPoint = e.getPoint();
        Point compPoint = _comp.getLocation();
        compPoint.x += (eventPoint.x - _pointMousePressed.x);
        compPoint.y += (eventPoint.y - _pointMousePressed.y);
        _comp.setLocation(compPoint);
        _comp.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // empty
    }
}
