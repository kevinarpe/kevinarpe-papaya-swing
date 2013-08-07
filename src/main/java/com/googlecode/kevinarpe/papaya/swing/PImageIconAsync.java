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
import java.awt.Window;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.accessibility.AccessibleContext;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.google.common.io.ByteStreams;
import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.PathArgs;
import com.googlecode.kevinarpe.papaya.exception.ClassResourceNotFoundException;
import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.exception.PathException.PathExceptionReason;
import com.googlecode.kevinarpe.papaya.swing.theme.PThemeIconLoaderAbstract;
import com.googlecode.kevinarpe.papaya.swing.theme.PThemeImageIcon;

/**
 * Extends {@link ImageIcon} to load images asynchronously (non-blocking).  The JDK implementation,
 * {@code ImageIcon}, blocks on construction.  This version does not block on construction, but
 * <i>may</i> block when any method from interface {@link Icon} is called:
 * <ul>
 *   <li>{@link #getIconWidth()}</li>
 *   <li>{@link #getIconHeight()}</li>
 *   <li>{@link #paintIcon(Component, Graphics, int, int)}</li>
 * </ul>
 * <p>
 * Opportunites for blocking can be further reduced by setting the expected image dimensions with
 * {@link #setExpectedDimension(PImmutableDimension)}.  Before the image is done loading,
 * {@link #getIconWidth()} and {@link #getIconHeight()} will return expected (instead of actual)
 * image dimensions.  This is important to prevent blocking during initial layout, trigger by
 * {@link Window#pack()}.  (Class {@link Window} is also inherited by class {@link JFrame}).  The
 * expected image dimensions are set automatically when using subclass {@link PThemeImageIcon}.
 * <p>
 * In many cases, the time between construction and layout/paint is sufficiently long to load the
 * image in a background thread.  If insufficient, the image will block. 
 * <p>
 * Query the image load status with {@link #updateImageLoadStatus()}, and wait for the image to
 * finish loading with {@link #waitForLoad(long)}.
 * <p>
 * To control whether or not exceptions are thrown if the image fails to load, call
 * {@link #ignoreIconLoadErrors(boolean)}.  By default, unchecked (runtime) exceptions <b>are</b>
 * thrown.
 * <p>
 * Expanding upon the base class, new constructors are available to load (i) from byte streams
 * ({@link InputStream}) or (ii) from classpath resources embedded in a JAR.
 * <ul>
 *   <li>{@link #PImageIconAsync(InputStream)}</li>
 *   <li>{@link #PImageIconAsync(InputStream, String)} (with description)</li>
 *   <li>{@link #PImageIconAsync(Class, String)}</li>
 *   <li>{@link #PImageIconAsync(Class, String, String)} (with description)</li>
 * </ul>
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see ImageIcon
 * @see PThemeImageIcon
 */
@FullyTested
@SuppressWarnings("serial")
public class PImageIconAsync
extends ImageIcon {
    
    // setIcon(Icon): tab is (1) focused or (2) not
    // setRolloverIcon(Icon): (3)
    // setPressedIcon(Icon): (4)
    
    // (1) Original image
    // (2) Light gray
    // (3) 10% Lighter
    // (4) 20% Darker
    
    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon()}.
     */
    public PImageIconAsync() {
        super();
        PImageIconAsyncInit();
        _mediaTrackerId = -1;
        _imageLoadStatus = PMediaTrackerLoadStatus.INITIAL;
        setIconWidth(DEFAULT_WIDTH);
        setIconHeight(DEFAULT_HEIGHT);
    }
    
    /**
     * Reads all bytes from an {@link InputStream}, then calls
     * {@link #PImageIconAsync(byte[], String)}.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @param in
     *        stream of bytes for image data
     * 
     * @param optDescription
     *        see {@link #setDescription(String)}.  May be {@code null}
     * 
     * @throws NullPointerException
     *         if {@code in} is {@code null}
     * @throws IOException
     *         if an I/O error occurs when reading stream
     * 
     * @see #PImageIconAsync(InputStream)
     * @see #PImageIconAsync(Class, String, String)
     */
    public PImageIconAsync(InputStream in, String optDescription)
    throws IOException {
        this(in);
        setDescription(optDescription);
    }

    /**
     * Reads all bytes from an {@link InputStream}, then calls {@link #PImageIconAsync(byte[])}.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @param in
     *        stream of bytes for image data
     * 
     * @throws NullPointerException
     *         if {@code in} is {@code null}
     * @throws IOException
     *         if an I/O error occurs when reading stream
     * 
     * @see #PImageIconAsync(byte[])
     * @see #PImageIconAsync(InputStream, String)
     * @see #PImageIconAsync(Class, String)
     */
    public PImageIconAsync(InputStream in)
    throws IOException {
        super(ByteStreams.toByteArray(ObjectArgs.checkNotNull(in, "in")));
        PImageIconAsyncInit();
    }
    
    /**
     * Finds a classpath resource by its path, creates an {@link InputStream}, then calls
     * {@link #PImageIconAsync(InputStream, String)}.  This constructor is useful is loading images
     * from classpath resources embedded in a JAR.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @param clazz
     *        {@link Class} used to find resource
     * @param classResourcePathname
     *        path to class resource.  Read more here:
     *        {@link PathArgs#checkClassResourceAsStreamExists(Class, String, String)}
     * @param optDescription
     *        see {@link #setDescription(String)}.  May be {@code null}
     * 
     * @throws NullPointerException
     *         if {@code clazz} or {@code classResourcePathname} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code classResourcePathname} is empty
     * @throws ClassResourceNotFoundException
     *         if resource is not found
     * @throws IOException
     *         if an I/O error occurs when reading stream
     * 
     * @see #PImageIconAsync(Class, String)
     */
    public PImageIconAsync(Class<?> clazz, String classResourcePathname, String optDescription)
    throws ClassResourceNotFoundException, IOException {
        this(clazz, classResourcePathname);
        setDescription(optDescription);
    }
    
    /**
     * Finds a classpath resource by its path, creates an {@link InputStream}, then calls
     * {@link #PImageIconAsync(InputStream)}.  This constructor is useful is loading images from
     * classpath resources embedded in a JAR.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @param clazz
     *        {@link Class} used to find resource
     * @param classResourcePathname
     *        path to class resource.  Read more here:
     *        {@link PathArgs#checkClassResourceAsStreamExists(Class, String, String)}
     * 
     * @throws NullPointerException
     *         if {@code clazz} or {@code classResourcePathname} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code classResourcePathname} is empty
     * @throws ClassResourceNotFoundException
     *         if resource is not found
     * @throws IOException
     *         if an I/O error occurs when reading stream
     * 
     * @see #PImageIconAsync(Class, String)
     */
    public PImageIconAsync(Class<?> clazz, String classResourcePathname)
    throws ClassResourceNotFoundException, IOException {
        this(
            PathArgs.checkClassResourceAsStreamExists(
                clazz, classResourcePathname, "classResourcePathname"));
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(byte[], String)}.
     * <p>
     * Parameter {@code optDescription} may be {@code null}.
     * 
     * @throws NullPointerException]
     *         if {@code imageData} is {@code null}
     */
    public PImageIconAsync(byte[] imageData, String optDescription) {
        super(
            ObjectArgs.checkNotNull(imageData, "imageData"),
            optDescription);
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(byte[])}.
     * 
     * @throws NullPointerException]
     *         if {@code imageData} is {@code null}
     */
    public PImageIconAsync(byte[] imageData) {
        super(ObjectArgs.checkNotNull(imageData, "imageData"));
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(Image, String)}.
     * <p>
     * Parameter {@code optDescription} may be {@code null}.
     * 
     * @throws NullPointerException]
     *         if {@code image} is {@code null}
     */
    public PImageIconAsync(Image image, String optDescription) {
        super(
            ObjectArgs.checkNotNull(image, "image"),
            optDescription);
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(Image)}.
     * 
     * @throws NullPointerException]
     *         if {@code image} is {@code null}
     */
    public PImageIconAsync(Image image) {
        super(ObjectArgs.checkNotNull(image, "image"));
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(String, String)}.
     * <p>
     * Parameter {@code optDescription} may be {@code null}.
     * 
     * @throws NullPointerException
     *         if {@code pathname} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code pathname} is empty
     * @throws PathException
     * <ul>
     *   <li>with reason {@link PathExceptionReason#PATH_DOES_NOT_EXIST}
     *   if {@code pathname} does not exist</li>
     *   <li>with reason {@link PathExceptionReason#PATH_IS_DIRECTORY}
     *   if {@code pathname} exists, but is not a file</li>
     * </ul>
     */
    public PImageIconAsync(String pathname, String optDescription)
    throws PathException {
        this(pathname);
        setDescription(optDescription);
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(String)}.
     * 
     * @throws NullPointerException
     *         if {@code pathname} is {@code null}
     * @throws IllegalArgumentException
     *         if {@code pathname} is empty
     * @throws PathException
     * <ul>
     *   <li>with reason {@link PathExceptionReason#PATH_DOES_NOT_EXIST}
     *   if {@code pathname} does not exist</li>
     *   <li>with reason {@link PathExceptionReason#PATH_IS_DIRECTORY}
     *   if {@code pathname} exists, but is not a file</li>
     * </ul>
     */
    public PImageIconAsync(String pathname)
    throws PathException {
        super(PathArgs.checkFileExists(pathname, "pathname").getAbsolutePath());
        PImageIconAsyncInit();
    }
    
    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(String)} where
     * {@code pathname} is {@code path.getAbsolutePath()}.
     * <p>
     * Parameter {@code optDescription} may be {@code null}.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @throws NullPointerException
     *         if {@code path} is {@code null}
     * @throws PathException
     * <ul>
     *   <li>with reason {@link PathExceptionReason#PATH_DOES_NOT_EXIST}
     *   if {@code path} does not exist</li>
     *   <li>with reason {@link PathExceptionReason#PATH_IS_DIRECTORY}
     *   if {@code path} exists, but is not a file</li>
     * </ul>
     */
    public PImageIconAsync(File path, String optDescription)
    throws PathException {
        this(path);
        setDescription(optDescription);
    }
    
    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(String)} where
     * {@code pathname} is {@code path.getAbsolutePath()}.
     * <p>
     * This constructor is not available in base class {@link ImageIcon}.
     * 
     * @throws NullPointerException
     *         if {@code path} is {@code null}
     * @throws PathException
     * <ul>
     *   <li>with reason {@link PathExceptionReason#PATH_DOES_NOT_EXIST}
     *   if {@code path} does not exist</li>
     *   <li>with reason {@link PathExceptionReason#PATH_IS_DIRECTORY}
     *   if {@code path} exists, but is not a file</li>
     * </ul>
     */
    public PImageIconAsync(File path)
    throws PathException {
        super(PathArgs.checkFileExists(path, "path").getAbsolutePath());
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(URL, String)}.
     * <p>
     * Parameter {@code optDescription} may be {@code null}.
     * 
     * @throws NullPointerException
     *         if {@code location} is {@code null}
     */
    public PImageIconAsync(URL location, String optDescription) {
        super(
            ObjectArgs.checkNotNull(location, "location"),
            optDescription);
        PImageIconAsyncInit();
    }

    /**
     * This is a convenience constructor to call {@link ImageIcon#ImageIcon(URL)}.
     * 
     * @throws NullPointerException
     *         if {@code location} is {@code null}
     */
    public PImageIconAsync(URL location) {
        super(ObjectArgs.checkNotNull(location, "location"));
        PImageIconAsyncInit();
    }
    
    /**
     * Called by all constructors.
     */
    protected void PImageIconAsyncInit() {
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
     * Default value for {@link #getIconWidth()}: -1
     */
    public static final int DEFAULT_WIDTH = -1;
    
    private int _width;
    
    /**
     * Default value for {@link #getIconHeight()}: -1
     */
    public static final int DEFAULT_HEIGHT = -1;
    
    private int _height;
    
    /**
     * Default value for {@link #ignoreIconLoadErrors()}: {@code false}
     */
    public static final boolean DEFAULT_IGNORE_ICON_LOAD_ERRORS = false;
    
    private boolean _ignoreIconLoadErrors;
    
    private PImmutableDimension _optExpectedDimension;
    
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
     * Retrieves the expected number of pixels for image width and height.  An image file may be
     * stored in a directory called "32x32", so the expected dimension is 32x32.  However, after
     * loading, the actual size may be different.
     * <p>
     * If this value is set before the image is done loading, methods {@link #getIconWidth()} and
     * {@link #getIconHeight()} will not block.
     * <p>
     * The default setting is {@code null}.
     * 
     * @return may be {@code null} if unset 
     * 
     * @see #setExpectedDimension(PImmutableDimension)
     * @see #getIconWidth()
     * @see #getIconHeight()
     */
    public PImmutableDimension getExpectedDimension() {
        return _optExpectedDimension;
    }
    
    /**
     * Sets the expected number of pixels for image width and height.  An image file may be stored
     * in a directory called "32x32", so the expected dimension is 32x32.  However, after loading,
     * the actual size may be different.
     * <p>
     * If this value is set before the image is done loading, methods {@link #getIconWidth()} and
     * {@link #getIconHeight()} will not block.
     * <p>
     * The default setting is {@code null}.
     * 
     * @param optDim
     *        optional expected number of pixels for image width and height.  May be {@code null}
     * 
     * @see #getExpectedDimension()
     * @see #getIconWidth()
     * @see #getIconHeight()
     */
    public void setExpectedDimension(PImmutableDimension optDim) {
        _optExpectedDimension = optDim;
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
     *         if {@link #ignoreIconLoadErrors()} is {@code true} <b>and</b> any case below:
     * <ul>
     *   <li>if {@link #waitForLoad()} throws {@link InterruptedException}</li>
     *   <li>if {@link #getImageLoadStatus()} is not {@link PMediaTrackerLoadStatus#COMPLETE}</li>
     *   <li>if {@link #getExpectedDimension()} is not {@code null} and does not match actual
     *   loaded image dimensions</li>
     * </ul>
     * 
     * @see #checkImageLoadDone()
     */
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        checkImageLoadDone();
        super.paintIcon(c, g, x, y);
    }
    
    /**
     * Call by all methods from interface {@link Icon}.  This method will block if the image is not
     * done loading.
     * 
     * @throws IllegalStateException
     *         if {@link #ignoreIconLoadErrors()} is {@code true} <b>and</b> any case below:
     * <ul>
     *   <li>if {@link #waitForLoad()} throws {@link InterruptedException}</li>
     *   <li>if {@link #getImageLoadStatus()} is not {@link PMediaTrackerLoadStatus#COMPLETE}</li>
     *   <li>if {@link #getExpectedDimension()} is not {@code null} and does not match actual
     *   loaded image dimensions</li>
     * </ul>
     * 
     * @see #getIconWidth()
     * @see #getIconHeight()
     * @see #paintIcon(Component, Graphics, int, int)
     */
    protected void checkImageLoadDone() {
        final boolean ignoreErrors = ignoreIconLoadErrors();
        PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        if (!status.isDone) {
            try {
                status = waitForLoad();
            }
            catch (InterruptedException e) {
                if (!ignoreErrors) {
                    String msg = formatError("Failed to wait for image to load");
                    throw new IllegalStateException(msg, e);
                }
            }
        }
        if (!ignoreErrors) {
            // Might be PMediaTrackerLoadStatus.ABORTED or .ERRORED
            if (PMediaTrackerLoadStatus.COMPLETE != status) {
                String msg = formatError("Failed to load image");
                throw new IllegalStateException(msg);
            }
            // Check expected vs. actual image dimensions
            final PImmutableDimension optExpectedDim = getExpectedDimension();
            if (null != optExpectedDim) {
                final int actualWidth = getIconWidthCore();
                final int actualHeight = getIconHeightCore();
                if (actualWidth != optExpectedDim.width || actualHeight != optExpectedDim.height) {
                    String msg = formatError("Expected dimensions (%s) do not match actual: %dx%d",
                        optExpectedDim.getDescription(), actualWidth, actualHeight);
                    throw new IllegalStateException(msg);
                }
            }
        }
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
        PImmutableDimension dim = getExpectedDimension();
        String dimStr = (null == dim ? "null" : String.format("'%s'", dim.getDescription()));
        String x = String.format(
            "%n\tgetIconWidth(): %d"
            + "%n\tgetIconHeight(): %d"
            + "%n\tgetImageLoadStatusAsEnum(): %s"
            + "%n\tgetDescription(): '%s'"
            + "%n\tgetExpectedDimension(): %s",
            getIconWidthCore(),
            getIconHeightCore(),
            statusStr,
            getDescription(),
            dimStr);
        return x;
    }
    
    /**
     * Optimised override to reduce blocking opportunities.  If the expected dimensions were set
     * with {@link #setExpectedDimension(PImmutableDimension)}, this method will not block before
     * the image is done loading.
     * 
     * @throws IllegalStateException
     *         if {@link #getExpectedDimension()} is {@code null},
     *         <b>and</b> if {@link #ignoreIconLoadErrors()} is {@code true},
     *         <b>and</b> any case below:
     * <ul>
     *   <li>if {@link #waitForLoad()} throws {@link InterruptedException}</li>
     *   <li>if {@link #getImageLoadStatus()} is not {@link PMediaTrackerLoadStatus#COMPLETE}</li>
     * </ul>
     * 
     * @see #setExpectedDimension(PImmutableDimension)
     * @see #checkImageLoadDone()
     * @see #getIconHeight()
     * @see #setIconWidth(int)
     */
    @Override
    public int getIconWidth() {
        final int width = getIconWidthCore();
        // Small optimization to reduce number of calls to checkImageLoadDone().  During layout,
        // this method is called many times!
        if (DEFAULT_WIDTH == width) {
            PImmutableDimension optDim = getExpectedDimension();
            if (null != optDim) {
                return optDim.width;
            }
            checkImageLoadDone();
        }
        return width;
    }
    
    /**
     * For subclasses to access members.  Unlike {@link #getIconWidth()}, this method is guaranteed
     * not to block.
     * 
     * @see #getIconWidth()
     * @see #getIconHeightCore()
     */
    protected int getIconWidthCore() {
        return _width;
    }
    
    /**
     * Optimised override to reduce blocking opportunities.  If the expected dimensions were set
     * with {@link #setExpectedDimension(PImmutableDimension)}, this method will not block before
     * the image is done loading.
     * 
     * @throws IllegalStateException
     *         if {@link #getExpectedDimension()} is {@code null},
     *         <b>and</b> if {@link #ignoreIconLoadErrors()} is {@code true},
     *         <b>and</b> any case below:
     * <ul>
     *   <li>if {@link #waitForLoad()} throws {@link InterruptedException}</li>
     *   <li>if {@link #getImageLoadStatus()} is not {@link PMediaTrackerLoadStatus#COMPLETE}</li>
     * </ul>
     * 
     * @see #setExpectedDimension(PImmutableDimension)
     * @see #checkImageLoadDone()
     * @see #getIconWidth()
     * @see #setIconHeight(int)
     */
    @Override
    public int getIconHeight() {
        final int height = getIconHeightCore();
        // Small optimization to reduce number of calls to checkImageLoadDone().  During layout,
        // this method is called many times!
        if (DEFAULT_HEIGHT == height) {
            PImmutableDimension optDim = getExpectedDimension();
            if (null != optDim) {
                return optDim.height;
            }
            checkImageLoadDone();
        }
        return height;
    }
    
    /**
     * For subclasses to access members.  Unlike {@link #getIconHeight()}, this method is
     * guaranteed not to block.
     * 
     * @see #getIconHeight()
     * @see #getIconWidthCore()
     */
    protected int getIconHeightCore() {
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
