package com.googlecode.kevinarpe.papaya.swing.theme;

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

import java.awt.MediaTracker;
import java.net.URL;

import com.googlecode.kevinarpe.papaya.StringUtils;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PImageIconAsync;
import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;
import com.googlecode.kevinarpe.papaya.swing.PMediaTrackerLoadStatus;

/**
 * Extends {@link PImageIconAsync} for standard themes.  This class is intended to be 
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconLoaderAbstract
 */
@NotFullyTested
@SuppressWarnings("serial")
public class PThemeImageIcon
extends PImageIconAsync {
    
    private final PImmutableDimension _expectedDimension;
    private final PThemeIconName _themeIconName;
    private final URL _url;
    
    /**
     * Queues the {@code image} in a shared {@link MediaTracker} for loading.  This constructor
     * does not block and wait for the icon to load.
     * <p>
     * Normally, this constructor should be called indirectly by
     * {@link PThemeIconLoaderAbstract#tryGetIcon(PThemeIconName)} or
     * {@link PThemeIconLoaderAbstract#getIcon(PThemeIconName)}.
     * 
     * @param expectedDimension
     *        see {@link #getExpectedDimension()}.  Must not be {@code null}
     * @param name
     *        see {@link #getThemeIconName()}.  Must not be {@code null}
     * @param url
     *        see {@link #getUrl()}.  Must not be {@code null}
     * 
     * @throws NullPointerException
     *         if {@code expectedDimension}, {@code name}, or {@code url} is {@code null}
     * 
     * @see PThemeIconLoaderAbstract
     * @see #updateImageLoadStatus()
     * @see #waitForLoad(long)
     */
    public PThemeImageIcon(PImmutableDimension expectedDimension, PThemeIconName name, URL url) {
        super(url, _createDescription(expectedDimension, name));
        this._expectedDimension = expectedDimension;
        this._themeIconName = name;
        this._url = ObjectArgs.checkNotNull(url, "url");
    }
    
    private static String _createDescription(
            PImmutableDimension expectedDimension, PThemeIconName name) {
        ObjectArgs.checkNotNull(expectedDimension, "expectedDimension");
        ObjectArgs.checkNotNull(name, "name");
        
        String x = String.format("%s:%s", name.baseFileName, expectedDimension.description);
        return x;
    }
    
    /**
     * Expected number of pixels for icon width and height.  An image file may reside in a
     * directory called "32x32", thus the expected dimension is 32x32, but after loading, the
     * actual size may be different.
     * 
     * @see #getIconWidth()
     * @see #getIconHeight()
     */
    public PImmutableDimension getExpectedDimension() {
        return _expectedDimension;
    }
    
    /**
     * id of icon to load
     */
    public PThemeIconName getThemeIconName() {
        return _themeIconName;
    }
    
    /**
     * URL of icon to load.  May be a file path or a classpath resource path.
     */
    public URL getUrl() {
        return _url;
    }
    
    @Override
    protected String attrsToString() {
        PThemeIconName name = getThemeIconName();
        String nameStr =
            StringUtils.addPrefixPerLine(
                name.toString(),
                "\t",
                StringUtils.TextProcessorOption.SKIP_FIRST_LINE);
        final PMediaTrackerLoadStatus status = getImageLoadStatusAsEnum();
        String statusStr =
            StringUtils.addPrefixPerLine(
                status.toString(),
                "\t",
                StringUtils.TextProcessorOption.SKIP_FIRST_LINE);
        PImmutableDimension dim = getExpectedDimension();
        String x = String.format(
            "%n\tgetThemeIconName(): %s"
            + "%n\turl: '%s'"
            + "%n\tgetExpectedDimension(): %s"
            + "%n\tgetIconWidth(): %d"
            + "%n\tgetIconHeight(): %d"
            + "%n\tgetImageLoadStatusAsEnum(): %s"
            + "%n\tgetDescription(): '%s'",
            nameStr,
            _url,
            dim.description,
            getIconWidth(),
            getIconHeight(),
            statusStr,
            getDescription());
        return x;
    }
}
