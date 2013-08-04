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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.argument.PathArgs;
import com.googlecode.kevinarpe.papaya.exception.PathException;
import com.googlecode.kevinarpe.papaya.swing.PImmutableDimension;

/**
 * Implementation of {@link PThemeIconLoaderAbstractFixedDimension} to load PNG images from file.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconLoaderAbstractFixedDimension
 * @see PThemeIconLoaderAbstract
 */
@NotFullyTested
public class PThemeIconLoaderFixedDimensionFromPngFile
extends PThemeIconLoaderAbstractFixedDimension {
    
    /**
     * Does not include the dot -- only {@code "png"}.  Case matters in most file systems!
     */
    public static final String FILE_EXTENSION = "png";
    
    private final File _baseDirPath;

    /**
     * Creates a theme icon loader for PNG icons stored as files on disk.
     * 
     * @param fixedDimension
     *        dimension for icons to load.  Must not be {@code null}
     * @param baseDirPath
     *        base directory path to icon files on disk, e.g., {@code "resources/theme-xyz/icons"}.
     *        Must not be {@code null}
     * 
     * @throws NullPointerException
     *         if {@code fixedDimension} or {@code baseDirPath} is {@code null}
     * 
     * @throws PathException
     */
    public PThemeIconLoaderFixedDimensionFromPngFile(
            PImmutableDimension fixedDimension, File baseDirPath)
    throws PathException {
        super(fixedDimension);
        PathArgs.checkDirectoryExists(baseDirPath, "baseDirPath");
        this._baseDirPath = baseDirPath.getAbsoluteFile();
    }
    
    /**
     * Base directory path to icon files on disk, e.g., {@code "resources/theme-xyz/icons"}
     */
    public File getBaseDirPath() {
        return _baseDirPath;
    }

    /**
     * Creates a file path-based URL for the theme icon.  These are the file path parts:
     * <ol>
     *   <li>{@link #_baseDirPath}</li>
     *   <li>{@code getFixedDimension().description}</li>
     *   <ul>
     *     <li>{@link #getFixedDimension()}</li>
     *     <li>{@link PImmutableDimension#getDescription()}</li>
     *   </ul>
     *   <li>{@code name.context.dirName}</li>
     *   <ul>
     *     <li>{@link PThemeIconName#context}</li>
     *     <li>{@link PThemeIconContextName#dirName}</li>
     *   </ul>
     *   <li>{@code name.baseFileName + "."} + {@link #FILE_EXTENSION}</li>
     *   <ul>
     *     <li>{@link PThemeIconName#baseFileName}</li>
     *   </ul>
     * </ol>
     * Example: {@link PThemeIconName#ADDRESS_BOOK_NEW} may map to
     * {@code "img/32x32/actions/address-book-new.png"}
     * <hr>
     * Docs from {@link PThemeIconLoaderAbstract#getUrl(PThemeIconName)}:
     * <p>
     * {@inheritDoc}
     * 
     * @throws IllegalStateException
     *         if converting file path to URL fails
     */
    @Override
    protected URL getUrl(PThemeIconName name) {
        ObjectArgs.checkNotNull(name, "name");
        
        PImmutableDimension dim = getFixedDimension();
        File filePath =
            new File(
                new File(
                    new File(_baseDirPath, dim.getDescription()),
                    name.context.dirName),
                name.baseFileName + ".png");
        if (!filePath.exists()) {
            return null;
        }
        URI uri = filePath.toURI();
        try {
            URL url = uri.toURL();
            return url;
        }
        catch (MalformedURLException e) {
            String msg = String.format("Failed to convert file path to URL: '%s'",
                filePath.getAbsolutePath());
            throw new IllegalStateException(msg, e);
        }
    }

    /**
     * @throws IllegalStateException
     *         thrown by {@link #getUrl(PThemeIconName)} if converting file path to URL fails
     */
    @Override
    protected PThemeImageIcon createThemeImageIcon(PThemeIconName name) {
        ObjectArgs.checkNotNull(name, "name");
        
        URL url = getUrl(name);
        if (null == url) {
            return null;
        }
        PImmutableDimension dim = getFixedDimension();
        PThemeImageIcon x = new PThemeImageIcon(dim, name, url);
        return x;
    }
}
