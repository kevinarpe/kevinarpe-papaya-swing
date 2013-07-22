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
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

public final class PSwingUtilities {

    public static boolean isComponentShowing(Component comp) {
        ObjectArgs.checkNotNull(comp, "comp");
        
        if (comp.isShowing()) {
            Window w = SwingUtilities.getWindowAncestor(comp);
            if (null != w && w.isShowing()) {
                return true;
            }
        }
        return false;
    }
    
    public static void buttonDoClickAfterNextShow(final AbstractButton button) {
        runAfterNextShow(button, new Runnable() {
            @Override
            public void run() {
                button.doClick();
            }
        });
    }
    
    public static void requestFocusAfterNextShow(final Component comp) {
        runAfterNextShow(comp, new Runnable() {
            @Override
            public void run() {
                comp.requestFocus();
            }
        });
    }
    
    public static void buttonDoClickAsync(final AbstractButton button) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                button.doClick();
            }
        });
    }
    
    public static void runAfterNextShow(final Component comp, final Runnable run) {
        HierarchyListener hierListener = new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                Object source = e.getSource();
                if (comp == source) {
                    long flags = e.getChangeFlags();
                    if (0 != (flags & HierarchyEvent.SHOWING_CHANGED)) {
                        if (comp.isShowing()) {
                            comp.removeHierarchyListener(this);
                            Window w = SwingUtilities.getWindowAncestor(comp);
                            if (null != w) {
                                if (w.isShowing()) {
                                    run.run();
                                }
                                else {
                                    WindowListener winListener = _createWindowListener(comp, run);
                                    w.addWindowListener(winListener);
                                }
                            }
                        }
                    }
                }
            }
        };
        
        comp.addHierarchyListener(hierListener);
    }
    
    private static WindowListener _createWindowListener(final Component comp, final Runnable run) {
        WindowListener winListener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                // empty
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // empty
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // empty
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // empty
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // empty
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // empty
                Object source = e.getSource();
                if (source instanceof Window) {
                    Window w = (Window) source;
                    w.removeWindowListener(this);
                    run.run();
                }
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // empty
            }
        };
        
        return winListener;
    }
}
