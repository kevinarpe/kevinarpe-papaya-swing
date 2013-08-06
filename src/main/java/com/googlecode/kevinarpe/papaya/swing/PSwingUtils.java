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
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Collection of static methods that builds upon {@link SwingUtilities}.
 * <p>
 * Note about argument types: Where possible the <b>least</b> derived class is used for method
 * arguments.  For widgets, this is frequently one of:
 * <ul>
 *   <li>{@link java.awt.Component}: from Abstract Window Toolkit (base class of most AWT/Swing
 *   widgets)</li>
 *   <li>{@link java.awt.Container}: also from Abstract Window Toolkit (derived from
 *   {@code Component})</li>
 *   <li>{@link javax.swing.JComponent}: from Swing toolkiet (derived from {@code Container})</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 */
public final class PSwingUtils {

    /**
     * Tests if a {@link Component} is showing, which is defined by three sub-tests:
     * <ol>
     *   <li>showing via {@link Component#isShowing()}</li>
     *   <li>has a parent window via {@link SwingUtilities#getWindowAncestor(Component)}</li>
     *   <li>the parent window is showing via {@link Component#isShowing()}</li>
     * </ol>
     * 
     * @param comp
     *        reference to an AWT/Swing widget
     * 
     * @return {@code true} if widget is showing
     * 
     * @throws NullPointerException
     *         if {@code comp} is {@code null}
     */
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
        ObjectArgs.checkNotNull(button, "button");
        
        runAfterNextShow(button, new Runnable() {
            @Override
            public void run() {
                button.doClick();
            }
        });
    }
    
    public static void requestFocusAfterNextShow(final Component comp) {
        ObjectArgs.checkNotNull(comp, "comp");
        
        runAfterNextShow(comp, new Runnable() {
            @Override
            public void run() {
                comp.requestFocus();
            }
        });
    }
    
    public static void buttonDoClickAsync(final AbstractButton button) {
        ObjectArgs.checkNotNull(button, "button");
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                button.doClick();
            }
        });
    }
    
    /**
     * Runs a callback only after the next show event.  One or two listeners are installed (and
     * later uninstalled) to correctly capture the show event.  (This is trickier than many
     * realize!)  The intent of this method is to provide a safe way to modify a {@link Component}
     * after its next show.  Example: The original {@link JSplitPane} did not allow proportional
     * divider location to be correctly set until the widget was shown.  What a pain!  (Grrr.)
     * <p>
     * Some caveats:
     * <ul>
     *   <li>The callback is <b>always</b> run from the
     *   <a href="http://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html">
     *   Event Dispatch Thread</a>.</li>
     *   <li>To better understand the term "showing", see
     *   {@link #isComponentShowing(Component)}.</li>
     *   <li>The term "next" is applied strictly: If the component is showing when this method is
     *   called, the code will not run until the widget is hidden, then shown again.  The method
     *   {@link #invokeLaterIfShowingOrRunAfterNextShow(Component, Runnable)} exists to handle this
     *   issue.</li>
     * </ul>
     * 
     * @param comp
     *        reference to an AWT/Swing widget
     * @param run
     *        callback to run
     * 
     * @throws NullPointerException
     *         if {@code comp} or {@code run} is {@code null}
     * 
     * @see #isComponentShowing(Component)
     * @see #invokeLaterIfShowingOrRunAfterNextShow(Component, Runnable)
     */
    public static void runAfterNextShow(final Component comp, final Runnable run) {
        ObjectArgs.checkNotNull(comp, "comp");
        ObjectArgs.checkNotNull(run, "run");
        
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
    
    /**
     * Given a callback:
     * <ul>
     *   <li>if widget is showing, run via {@link SwingUtilities#invokeLater(Runnable)}</li>
     *   <li>else, run via {@link #runAfterNextShow(Component, Runnable)}</li>
     * </ul>
     * <p>
     * Some caveats:
     * <ul>
     *   <li>The callback is <b>always</b> run from the
     *   <a href="http://docs.oracle.com/javase/tutorial/uiswing/concurrency/dispatch.html">
     *   Event Dispatch Thread</a>.</li>
     *   <li>To better understand the term "showing", see
     *   {@link #isComponentShowing(Component)}.</li>
     * </ul>
     * 
     * @param comp
     *        reference to an AWT/Swing widget
     * @param run
     *        callback to run
     * 
     * @return {@code true} is widget is showing
     * 
     * @throws NullPointerException
     *         if {@code comp} or {@code run} is {@code null}
     * 
     * @see #isComponentShowing(Component)
     * @see #runAfterNextShow(Component, Runnable)
     */
//    public static boolean invokeLaterIfShowingOrRunAfterNextShow(Component comp, Runnable run) {
//        ObjectArgs.checkNotNull(comp, "comp");
//        ObjectArgs.checkNotNull(run, "run");
//        
//        if (isComponentShowing(comp)) {
//            SwingUtilities.invokeLater(run);
//            return true;
//        }
//        else {
//            runAfterNextShow(comp, run);
//            return false;
//        }
//    }
//    
//    public static boolean runNowIfShowingOrRunAfterNextShow(Component comp, Runnable run) {
//        ObjectArgs.checkNotNull(comp, "comp");
//        ObjectArgs.checkNotNull(run, "run");
//        
//        if (isComponentShowing(comp)) {
//            run.run();
//            return true;
//        }
//        else {
//            runAfterNextShow(comp, run);
//            return false;
//        }
//    }
}
