package com.googlecode.kevinarpe.papaya.swing.widget;

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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JSplitPane;
import org.slf4j.Logger;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.DoubleArgs;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PJSplitPaneOrientation;
import com.googlecode.kevinarpe.papaya.swing.PSwingDebug;
import com.googlecode.kevinarpe.papaya.swing.PSwingUtils;

/**
 * Extension of {@link JSplitPane} to only use proportions for the divider location.  Additionally,
 * when the component is <b>not</b> showing (immediately following construction), setting the
 * divider location via {@link #setDividerLocation(double)} is correctly applied after the
 * component is shown next.  Normally, the standard JDK class, {@code JSplitPane} does not
 * correctly apply this setting. 
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 *
 * @see JSplitPane
 */
@NotFullyTested
@SuppressWarnings("serial")
public class PJSplitPaneProportional
extends JSplitPane {
    
    /**
     * @see JSplitPane#JSplitPane()
     */
    public PJSplitPaneProportional() {
        super();
        PJSplitPaneInit();
    }

    /**
     * It is better to use {@link #PJSplitPaneProportional(PJSplitPaneOrientation)}.
     */
    public PJSplitPaneProportional(int newOrientation) {
        super(newOrientation);
        PJSplitPaneInit();
    }

    /**
     * @see JSplitPane#JSplitPane(int)
     */
    public PJSplitPaneProportional(PJSplitPaneOrientation newOrientation) {
        super(ObjectArgs.checkNotNull(newOrientation, "newOrientation").value);
        PJSplitPaneInit();
    }

    /**
     * It is better to use {@link #PJSplitPaneProportional(PJSplitPaneOrientation, boolean)}.
     */
    public PJSplitPaneProportional(int newOrientation, boolean newContinuousLayout) {
        super(newOrientation, newContinuousLayout);
        PJSplitPaneInit();
    }

    /**
     * @see JSplitPane#JSplitPane(int, boolean)
     */
    public PJSplitPaneProportional(
            PJSplitPaneOrientation newOrientation, boolean newContinuousLayout) {
        super(
            ObjectArgs.checkNotNull(newOrientation, "newOrientation").value,
            newContinuousLayout);
        PJSplitPaneInit();
    }

    /**
     * It is better to use
     * {@link #PJSplitPaneProportional(PJSplitPaneOrientation, Component, Component)}.
     */
    public PJSplitPaneProportional(
            int newOrientation,
            Component newLeftComponent,
            Component newRightComponent) {
        super(newOrientation, newLeftComponent, newRightComponent);
        PJSplitPaneInit();
    }

    /**
     * @see JSplitPane#JSplitPane(int, Component, Component)
     */
    public PJSplitPaneProportional(
            PJSplitPaneOrientation newOrientation,
            Component newLeftComponent,
            Component newRightComponent) {
        super(
            ObjectArgs.checkNotNull(newOrientation, "newOrientation").value,
            newLeftComponent,
            newRightComponent);
        PJSplitPaneInit();
    }

    /**
     * It is better to use
     * {@link #PJSplitPaneProportional(PJSplitPaneOrientation, boolean, Component, Component)}.
     */
    public PJSplitPaneProportional(
            int newOrientation,
            boolean newContinuousLayout,
            Component newLeftComponent,
            Component newRightComponent) {
        super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
        PJSplitPaneInit();
    }

    /**
     * @see JSplitPane#JSplitPane(int, boolean, Component, Component)
     */
    public PJSplitPaneProportional(
            PJSplitPaneOrientation newOrientation,
            boolean newContinuousLayout,
            Component newLeftComponent,
            Component newRightComponent) {
        super(
            ObjectArgs.checkNotNull(newOrientation, "newOrientation").value,
            newContinuousLayout,
            newLeftComponent,
            newRightComponent);
        PJSplitPaneInit();
    }
    
    private InnerComponentListener _compListener;
    private InnerMouseListener _mouseListener;
    private Logger _debugLogger;
    
    /**
     * For subclasses to access members.
     */
    protected InnerComponentListener getInnerComponentListener() {
        return _compListener;
    }
    
    /**
     * For subclasses to access members.
     */
    protected InnerMouseListener getInnerMouseListener() {
        return _mouseListener;
    }
    
    /**
     * For subclasses to control creation of {@link InnerComponentListener} (or its subclass).
     * <p>
     * This method is called indirectly by all constructors.  Exercise care when overriding.
     */
    protected InnerComponentListener createInnerComponentListener() {
        InnerComponentListener x = new InnerComponentListener();
        return x;
    }
    
    /**
     * For subclasses to control creation of {@link InnerMouseListener} (or its subclass).
     * <p>
     * This method is called indirectly by all constructors.  Exercise care when overriding.
     */
    protected InnerMouseListener createInnerMouseListener() {
        InnerMouseListener x = new InnerMouseListener();
        return x;
    }
    
    /**
     * This method is called indirectly by all constructors.  Exercise care when overriding.
     */
    protected void PJSplitPaneInit() {
        _compListener = createInnerComponentListener();
        addComponentListener(_compListener);
        
        _mouseListener = createInnerMouseListener();
        final Component leftComp = getLeftComponent();
        final Component rightComp = getRightComponent();
        
        Component[] compArr = this.getComponents();
        for (Component comp: compArr) {
            // We are searching for the "third" component: the actual splitter widget.
            if (leftComp != comp && rightComp != comp) {
                comp.addMouseListener(_mouseListener);
            }
        }
        _debugLogger = PSwingDebug.getLogger(PJSplitPaneProportional.class);
    }

    private double _proportionalLocation = 1.0d / 3.0d;
    
    /**
     * Retrieves the proportional location for the divider.  This is between the inclusive range of
     * 0.0 to 1.0 (0% -> 100%).
     */
    public double getDividerProportionalLocation() {
        return _proportionalLocation;
    }
    
    /**
     * For subclasses to access members.
     * <p>
     * This method is called (indirectly) by {@link #setDividerLocation(double)}
     */
    protected void setDividerProportionalLocation(double proportionalLocation) {
        _proportionalLocation = proportionalLocation;
    }

    /**
     * Unlike the standard JDK class {@link JSplitPane}, this method may be called at any time when
     * the component is <b>not</b> showing.  When the component is next shown, the setting is
     * immediately applied.
     * <p>
     * As this subclass only uses proportional locations, do not use
     * {@link #setDividerLocation(int)}.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setDividerLocation(final double proportionalLocation) {
        if (PSwingUtils.isComponentShowing(this)) {
            setDividerLocationCore(proportionalLocation);
        }
        else {
            _debugLogger.debug(
                "setDividerLocation: Component is not showing: Queue request via runAfterNextShow: {}",
                this);
            PSwingUtils.runAfterNextShow(this, new Runnable() {
                @Override
                public void run() {
                    _debugLogger.debug("runAfterNextShow:BEGIN");
                    setDividerLocationCore(proportionalLocation);
                    _debugLogger.debug("runAfterNextShow:END");
                }
            });
        }
    }
    
    /**
     * This method is only called when the component is showing.
     */
    protected void setDividerLocationCore(double proportionalLocation) {
        DoubleArgs.checkValueRange(proportionalLocation, 0.0d, 1.0d, "proportionalLocation");
        
        _debugLogger.debug("setDividerLocation(double: {})", proportionalLocation);
        final int size = getDividerSize();
        final int orient = getOrientation();
        int newLocation = -1;
        if (JSplitPane.HORIZONTAL_SPLIT == orient) {
            int width = getWidth();
            newLocation = (int) (proportionalLocation * ((double) (width - size)));
        }
        else if (JSplitPane.VERTICAL_SPLIT == orient) {
            int height = getHeight();
            newLocation = (int) (proportionalLocation * ((double) (height - size)));
        }
        else {
            throw new IllegalStateException(String.format(
                "Unknown orientation (%d): Expected JSplitPane.HORIZONTAL_SPLIT (%d) or JSplitPane.VERTICAL_SPLIT (%d)",
                orient, JSplitPane.HORIZONTAL_SPLIT, JSplitPane.VERTICAL_SPLIT));
        }
        super.setDividerLocation(newLocation);
        setDividerProportionalLocation(proportionalLocation);
        _debugLogger.debug("setDividerLocation(double):END");
    }
    
    /**
     * As this subclass only uses proportional locations, only use
     * {@link #setDividerLocation(double)}.  All calls to this method are ignored.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setDividerLocation(int location) {
        if (_debugLogger.isDebugEnabled()) {
            StackTraceElement[] stackArr = (new Throwable()).getStackTrace();
            _debugLogger.debug(
                "setDividerLocation(int: {}) [current: {}] [caller: {}]",
                location, getDividerLocation(), stackArr[1]);
        }
        if (_mouseListener.isPressed()) {
            super.setDividerLocation(location);
            updateProportionalLocation();
        }
        else {
            _debugLogger.debug("Mouse not pressed: Ignore request");
        }
        _debugLogger.debug("setDividerLocation(int):END");
    }

    /**
     * This method is called only when the divider is dragged with the mouse.  Internally, mouse
     * drag events generate calls to {@link #setDividerLocation(int)}.
     */
    protected void updateProportionalLocation() {
        final int location = getDividerLocation();
        final int size = getDividerSize();
        final int orient = getOrientation();
        if (JSplitPane.HORIZONTAL_SPLIT == orient) {
            int width = getWidth();
            if (width > 0) {
                double newProportionalLocation = ((double) location) / ((double) (width - size));
                setDividerProportionalLocation(newProportionalLocation);
            }
        }
        else if (JSplitPane.VERTICAL_SPLIT == orient) {
            int height = getHeight();
            if (height > 0) {
                double newProportionalLocation = ((double) location) / ((double) (height - size));
                setDividerProportionalLocation(newProportionalLocation);
            }
        }
        else {
            throw new IllegalStateException(String.format(
                "Unknown orientation (%d): Expected JSplitPane.HORIZONTAL_SPLIT (%d) or JSplitPane.VERTICAL_SPLIT (%d)",
                orient, JSplitPane.HORIZONTAL_SPLIT, JSplitPane.VERTICAL_SPLIT));
        }
        _debugLogger.debug("updateProportionalLocation() -> {}", getDividerProportionalLocation());
    }
    
    /**
     * This is a convenience method to call {@link #setOrientation(int)} with
     * {@code orientation.value}.
     * <p>
     * The original Swing library was written before the {@code enum} type existed in Java.  As a
     * result, many legacy methods use integers and further check for valid values.  This method
     * simply replaces the integer parameter with a enumerated data type that is restricted to
     * values allowed by the method {@link #setOrientation(int)}.
     * 
     * @throws NullPointerException
     *         if {@code orientation} is {@code null}
     * 
     * @see #getOrientationAsEnum()
     */
    public void setOrientation(PJSplitPaneOrientation orientation) {
        ObjectArgs.checkNotNull(orientation, "orientation");

        setOrientation(orientation.value);
    }
    
    /**
     * This is a convenience method to call {@link #getOrientation()} and convert the result to
     * enum type {@link PJSplitPaneOrientation}.
     * 
     * @see #getOrientation()
     * @see #setOrientation(int)
     * @see #setOrientation(PJSplitPaneOrientation)
     */
    public PJSplitPaneOrientation getOrientationAsEnum() {
        int x = getOrientation();
        PJSplitPaneOrientation y = PJSplitPaneOrientation.valueOf(x);
        return y;
    }

    /**
     * This inner class is used to handle resize events.  The proportional divider location is
     * correctly maintained as this component is resized.
     * <p>
     * If this class is subclassed, an override for method
     * {@link PJSplitPaneProportional#createInnerComponentListener()} must be created.
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     * 
     * @see PJSplitPaneProportional#createInnerComponentListener()
     * @see InnerMouseListener
     */
    protected class InnerComponentListener
    implements ComponentListener {
        
        @Override
        public void componentResized(ComponentEvent e) {
            _debugLogger.debug("componentResized:BEGIN");
            Object source = e.getSource();
            if (PJSplitPaneProportional.this == source
                    && getDividerProportionalLocation() > 0.0) {
    //            updateProportionalLocation();
                setDividerLocation(_proportionalLocation);
            }
            _debugLogger.debug("componentResized:END");
        }
    
        @Override
        public void componentMoved(ComponentEvent e) {
            // empty
        }
    
        @Override
        public void componentShown(ComponentEvent e) {
            // empty
        }
    
        @Override
        public void componentHidden(ComponentEvent e) {
            // empty
        }
    }
    
    /**
     * This inner class is used to track the begin and end of mouse drag events for the splitter
     * component.
     * <p>
     * If this class is subclassed, an override for method
     * {@link PJSplitPaneProportional#createInnerMouseListener()} must be created.
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     * 
     * @see PJSplitPaneProportional#createInnerMouseListener()
     * @see InnerComponentListener
     */
    protected class InnerMouseListener
    implements MouseListener {
        
        private boolean _isPressed = false;
        
        /**
         * For subclasses to access members.
         */
        protected void setPressed(boolean b) {
            _isPressed = b;
        }
        
        /**
         * @return {@code true} if any mouse button(s) are pressed
         */
        public boolean isPressed() {
            return _isPressed;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // empty
        }

        /**
         * This method does not discriminated between mouse buttons: Any may begin the drag event.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            _isPressed = true;
            _debugLogger.debug("mousePressed");
        }

        /**
         * This method does not discriminated between mouse buttons: Any may end the drag event.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            _isPressed = false;
            _debugLogger.debug("mouseReleased");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // empty
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // empty
        }
    }
}
