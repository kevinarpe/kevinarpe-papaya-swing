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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.accessibility.AccessibleContext;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.theme.PThemeIconLoaderAbstract;

/**
 * Extends {@link ImageIcon} to load images asynchronously (non-blocking).  The JDK implementation,
 * {@code ImageIcon}, blocks on construction.  This version does not block on construction, but
 * <i>may</i> block when the image is first painted via
 * {@link #paintIcon(Component, Graphics, int, int)}.
 * <p>
 * The assumption behind the asynchronous load is the time between (i) construction and (ii) first
 * show of complex Swing widgets (dialogs and windows) is sufficiently long to load the image in a
 * background thread.  If insufficient, and the image has not yet finished loading, the image will
 * block during its first paint. 
 * <p>
 * Query the image load status with {@link #updateImageLoadStatus()}, and wait for the image to
 * finish loading with {@link #waitForLoad(long)}.
 * <p>
 * To control whether or not exceptions are thrown if the image fails to load, call
 * {@link #ignoreIconLoadErrors(boolean)}.  By default, unchecked (runtime) exceptions <b>are</b>
 * thrown. 
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see ImageIcon
 */
@NotFullyTested
@SuppressWarnings("serial")
public class PImageIconAsync
extends ImageIcon {
    
    /**
     * @see ImageIcon#ImageIcon()
     */
    public PImageIconAsync() {
        super();
        PImageIconAsync_init();
        _mediaTrackerId = -1;
        _imageLoadStatus = PMediaTrackerLoadStatus.INITIAL;
        setIconWidth(DEFAULT_WIDTH);
        setIconHeight(DEFAULT_HEIGHT);
    }

    /**
     * @see ImageIcon#ImageIcon(byte[], String)
     */
    public PImageIconAsync(byte[] imageData, String description) {
        super(imageData, description);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(byte[])
     */
    public PImageIconAsync(byte[] imageData) {
        super(imageData);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(Image, String)
     */
    public PImageIconAsync(Image image, String description) {
        super(image, description);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(Image)
     */
    public PImageIconAsync(Image image) {
        super(image);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(String, String)
     */
    public PImageIconAsync(String filename, String description) {
        super(filename, description);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(String)
     */
    public PImageIconAsync(String filename) {
        super(filename);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(URL, String)
     */
    public PImageIconAsync(URL location, String description) {
        super(location, description);
        PImageIconAsync_init();
    }

    /**
     * @see ImageIcon#ImageIcon(URL)
     */
    public PImageIconAsync(URL location) {
        super(location);
        PImageIconAsync_init();
    }
    
    /**
     * Called by all constructors.
     */
    protected void PImageIconAsync_init() {
        _ignoreIconLoadErrors = DEFAULT_IGNORE_ICON_LOAD_ERRORS;
    }
    
    @Override
    protected void loadImage(Image image) {
        // Note: Very dangerous!  We are overriding a method called by most constructors.
        MediaTracker mt = PMediaTrackerUtils.getSharedMediaTracker();
        _mediaTrackerId = PMediaTrackerUtils.getNextMediaTrackerId();
        
        mt.addImage(image, _mediaTrackerId);
        _imageLoadStatus = PMediaTrackerLoadStatus.INITIAL;
        setIconWidth(DEFAULT_WIDTH);
        setIconHeight(DEFAULT_HEIGHT);
    }
    
    private int _mediaTrackerId;
    
    private PMediaTrackerLoadStatus _imageLoadStatus;
    
    /**
     * Default value for {@link #getIconWidth()}.
     */
    public static final int DEFAULT_WIDTH = -1;
    
    private int _width;
    
    /**
     * Default value for {@link #getIconHeight()}.
     */
    public static final int DEFAULT_HEIGHT = -1;
    
    private int _height;
    
    /**
     * Default value for {@link #ignoreIconLoadErrors()}.
     */
    public static final boolean DEFAULT_IGNORE_ICON_LOAD_ERRORS = false;
    
    private boolean _ignoreIconLoadErrors;
    
    /**
     * This is a convenience method to call {@link #getImageLoadStatusAsEnum()} and return the
     * integer value from {@link PMediaTrackerLoadStatus#value}.
     */
    @Override
    public int getImageLoadStatus() {
        PMediaTrackerLoadStatus x = getImageLoadStatusAsEnum();
        int y = x.value;
        return y;
    }
    
    /**
     * This method return the last known image loading status.  To update the status, call
     * {@link #updateImageLoadStatus()}.
     *
     * @return never {@code null}
     * <ul>
     *   <li>{@link PMediaTrackerLoadStatus#INITIAL}: if icon load has not yet started</li>
     *   <li>{@link PMediaTrackerLoadStatus#LOADING}: if icon load has started, but not yet
     *   finished, loading</li>
     *   <li>{@link PMediaTrackerLoadStatus#ABORTED}: considered failure by
     *   {@link #ignoreIconLoadErrors()}</li>
     *   <li>{@link PMediaTrackerLoadStatus#ERRORED}: also considered failure by
     *   {@link #ignoreIconLoadErrors()}</li>
     *   <li>{@link PMediaTrackerLoadStatus#COMPLETE}: considered successful by
     *   {@link #ignoreIconLoadErrors()}</li>
     * </ul>
     * 
     * @see #getImageLoadStatus()
     * @see #setImageLoadStatus(PMediaTrackerLoadStatus)
     */
    public PMediaTrackerLoadStatus getImageLoadStatusAsEnum() {
        return _imageLoadStatus;
    }
    
    /**
     * For subclasses to access members.
     * 
     * @see #getImageLoadStatus()
     * @see #setImageLoadStatus(PMediaTrackerLoadStatus)
     */
    protected void setImageLoadStatus(PMediaTrackerLoadStatus status) {
        _imageLoadStatus = ObjectArgs.checkNotNull(status, "status");
    }
    
    /**
     * Updates and retrieves the image load status.  To retrieve the last known status, call
     * {@link #getImageLoadStatus()}.  For a blocking update on load status, call
     * {@link #waitForLoad(long)}.
     * 
     * @return see {@link #getImageLoadStatus()}
     * 
     * @see #getImageLoadStatus()
     * @see #setImageLoadStatus(PMediaTrackerLoadStatus)
     * @see #waitForLoad(long)
     */
    public PMediaTrackerLoadStatus updateImageLoadStatus() {
        PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        if (!status.isDone) {
            final MediaTracker mt = PMediaTrackerUtils.getSharedMediaTracker();
            status = queryMediaTrackerForImageLoadStatus(mt);
        }
        return status;
    }
    
    /**
     * Retrieves whether or not {@link IllegalStateException} is throws by
     * {@link #paintIcon(Component, Graphics, int, int)} if this icon fails to load.
     * <p>
     * The default value is {@link #DEFAULT_IGNORE_ICON_LOAD_ERRORS}.
     *
     * @return if {@code true} and this icon fails to load,
     *         {@link #paintIcon(Component, Graphics, int, int)} will throw an exception
     * 
     * @see #ignoreIconLoadErrors(boolean)
     * @see PThemeIconLoaderAbstract#ignoreIconLoadErrors()
     */
    public boolean ignoreIconLoadErrors() {
        return _ignoreIconLoadErrors;
    }
    
    /**
     * Sets whether or not {@link IllegalStateException} is throws by
     * {@link #paintIcon(Component, Graphics, int, int)} if this icon fails to load.
     * <p>
     * The default value is {@link #DEFAULT_IGNORE_ICON_LOAD_ERRORS}.
     * 
     * @param flag
     *        if {@code true} and this icon fails to load,
     *        {@link #paintIcon(Component, Graphics, int, int)} will throw an exception
     * 
     * @see #ignoreIconLoadErrors()
     * @see PThemeIconLoaderAbstract#ignoreIconLoadErrors(boolean)
     */
    public void ignoreIconLoadErrors(boolean flag) {
        _ignoreIconLoadErrors = flag;
    }
    
    /**
     * This is a convenience method to call {@link #waitForLoad(long)} where {@code timeoutMillis}
     * is zero.
     */
    public PMediaTrackerLoadStatus waitForLoad()
    throws InterruptedException {
        PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        if (!status.isDone) {
            long timeoutMillis = 0;
            PMediaTrackerLoadStatus newStatus = waitForLoad(timeoutMillis);
            status = newStatus;
        }
        return status;
    }
    
    /**
     * Waits for the image to finish loading.  For a non-blocking update on load status, call
     * {@link #updateImageLoadStatus()}.
     * 
     * @param timeoutMillis
     * <ul>
     *   <li>maximum number of milliseconds to wait for image to load</li>
     *   <li>if zero, the wait is unlimited</li>
     * </ul>
     * 
     * @return see {@link #getImageLoadStatus()}
     * 
     * @throws InterruptedException
     *         if the load is interrupted
     * 
     * @see #waitForLoad()
     * @see #updateImageLoadStatus()
     * @see #getImageLoadStatus()
     */
    public PMediaTrackerLoadStatus waitForLoad(long timeoutMillis)
    throws InterruptedException {
        PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        if (!status.isDone) {
            MediaTracker mt = PMediaTrackerUtils.getSharedMediaTracker();
            @SuppressWarnings("unused")
            boolean isStatusComplete = mt.waitForID(_mediaTrackerId, timeoutMillis);
            status = queryMediaTrackerForImageLoadStatus(mt);
        }
        return status;
    }
    
    /**
     * Retrieves and updates the latest image load status from a {@link MediaTracker}.  If the load
     * is complete (successful or not), (i) remove image from {@code MediaTracker}, (ii) update
     * width and height, and (iii) clear the last media tracker id. 
     * 
     * @param mt
     *        reference to a {@link MediaTracker} to retrieve image load status update
     * 
     * @return updated image load status; matches {@link #getImageLoadStatusAsEnum()}
     * 
     * @throws NullPointerException
     *         if {@code mt} is {@code null}
     * 
     * @see #updateImageLoadStatus()
     * @see #waitForLoad(long)
     */
    protected PMediaTrackerLoadStatus queryMediaTrackerForImageLoadStatus(MediaTracker mt) {
        ObjectArgs.checkNotNull(mt, "mt");
        
        boolean beginLoadingImages = false;
        int statusValue = mt.statusID(_mediaTrackerId, beginLoadingImages);
        
        PMediaTrackerLoadStatus status = PMediaTrackerLoadStatus.valueOf(statusValue);
        setImageLoadStatus(status);
        
        if (status.isDone) {
            Image image = getImage();
            mt.removeImage(image, _mediaTrackerId);
            
            ImageObserver io = getImageObserver();
            int width = image.getWidth(io);
            setIconWidth(width);
            
            int height = image.getHeight(io);
            setIconHeight(height);
            
            _mediaTrackerId = -1;
        }
        return status;
    }

    /**
     * If {@link #ignoreIconLoadErrors()} is {@code true}, and icon failed to load, throw an
     * exception.  Repeatedly calling this method may throw the same {@link IllegalStateException}.
     * <hr>
     * Docs from {@link Icon#paintIcon(Component, Graphics, int, int)}:
     * <p>
     * {@inheritDoc}
     * 
     * @throws IllegalStateException
     * <ul>
     *   <li>if {@link #ignoreIconLoadErrors()} is {@code true}
     *   <b>and</b> if {@link #waitForLoad()} throws {@link InterruptedException}</li>
     *   <li>if {@link #ignoreIconLoadErrors()} is {@code true}
     *   <b>and</b> if {@link #getImageLoadStatus()} is not
     *   {@link PMediaTrackerLoadStatus#COMPLETE}</li>
     * </ul>
     */
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        final boolean ignoreErrors = ignoreIconLoadErrors();
        final PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        if (!status.isDone) {
            try {
                waitForLoad();
            }
            catch (InterruptedException e) {
                if (!ignoreErrors) {
                    String msg = formatError("Failed to wait for image to load");
                    throw new IllegalStateException(msg, e);
                }
            }
        }
        if (!ignoreErrors && PMediaTrackerLoadStatus.COMPLETE != status) {
            String msg = formatError("Failed to load image");
            throw new IllegalStateException(msg);
        }
        super.paintIcon(c, g, x, y);
    }
    
    /**
     * Wrapper for {@link String#format(String, Object...)} to create an error message when
     * throwing an exception after an image fails to load.
     * 
     * @see #attrsToString()
     */
    protected String formatError(String format, Object... argArr) {
        String x = (0 == argArr.length ? format : String.format(format, argArr));
        String y = attrsToString();
        String z = x + y;
        return z;
    }
    
    /**
     * @see #attrsToString()
     */
    @Override
    public String toString() {
        String x = String.format(
            "%s: [%s"
            + "%n\t]",
            PImageIconAsync.class.getCanonicalName(),
            attrsToString());
        return x;
    }
    
    /**
     * @see #formatError(String, Object...)
     * @see #toString()
     */
    protected String attrsToString() {
        final PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        String statusStr =
            StringUtils.addPrefixPerLine(
                status.toString(),
                "\t",
                StringUtils.TextProcessorOption.SKIP_FIRST_LINE);
        String x = String.format(
            "%n\tgetIconWidth(): %d"
            + "%n\tgetIconHeight(): %d"
            + "%n\tgetImageLoadStatusAsEnum(): %s"
            + "%n\tgetDescription(): '%s'",
            getIconWidth(),
            getIconHeight(),
            statusStr,
            getDescription());
        return x;
    }
    
    /**
     * @see #getIconHeight()
     * @see #setIconWidth(int)
     */
    @Override
    public int getIconWidth() {
        return _width;
    }
    
    /**
     * @see #getIconWidth()
     * @see #setIconHeight(int)
     */
    @Override
    public int getIconHeight() {
        return _height;
    }
    
    /**
     * For subclasses to access members.
     * 
     * @param width
     *        any value -- unchecked
     * 
     * @see #getIconWidth()
     * @see #setIconHeight(int)
     */
    protected void setIconWidth(int width) {
        _width = width;
    }
    
    /**
     * For subclasses to access members.
     * 
     * @param height
     *        any value -- unchecked
     * 
     * @see #getIconHeight()
     * @see #setIconWidth(int)
     */
    protected void setIconHeight(int height) {
        _height = height;
    }
    
    private AccessibleContext _accessibleContext;
    
    /**
     * Calls {@link #createAccessibleContext()}.
     * <hr>
     * Docs from {@link ImageIcon#getAccessibleContext()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public AccessibleContext getAccessibleContext() {
        if (null == _accessibleContext) {
            _accessibleContext = createAccessibleContext();
        }
        return _accessibleContext;
    }
    
    /**
     * Subclasses of {@link PImageIconAsync} that want to subclass {@link AccessibleThemeImageIcon}
     * will need to override this method.
     */
    protected AccessibleContext createAccessibleContext() {
        AccessibleThemeImageIcon x = new AccessibleThemeImageIcon();
        return x;
    }
    
    /**
     * It is possible to customize the behavior of this class through subclassing and overriding
     * {@link #createAccessibleContext()}.
     * 
     * @author Kevin Connor ARPE (kevinarpe@gmail.com)
     * 
     * @see ImageIcon.AccessibleImageIcon
     */
    protected class AccessibleThemeImageIcon
    extends ImageIcon.AccessibleImageIcon {
        
        @Override
        public int getAccessibleIconWidth() {
            int x = PImageIconAsync.this.getIconWidth();
            return x;
        }
        
        @Override
        public int getAccessibleIconHeight() {
            int x = PImageIconAsync.this.getIconHeight();
            return x;
        }
    }
}
