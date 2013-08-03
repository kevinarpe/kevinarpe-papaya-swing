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

import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;
import com.googlecode.kevinarpe.papaya.swing.PImageIconAsync;

/**
 * Abstract base class to load theme icons.  Implementations can load from different sources, such
 * as file path (on disk) or classpath resource path (embedded in JAR).  Normally, an application
 * will employ a few different icon sizes, depending on the widget context.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconLoaderAbstractFixedDimension
 * @see PThemeIconLoaderFixedDimensionFromPngFile
 * @see #tryGetIcon(PThemeIconName)
 * @see #getIcon(PThemeIconName)
 * @see #iconExists(PThemeIconName)
 */
@NotFullyTested
public abstract class PThemeIconLoaderAbstract {

    private Map<PThemeIconName, PThemeImageIcon> _nameToIconMap;
    
    /**
     * Default value for {@link #ignoreIconLoadErrors()}: {@code false}
     */
    public static final boolean DEFAULT_IGNORE_ICON_LOAD_ERRORS =
        PImageIconAsync.DEFAULT_IGNORE_ICON_LOAD_ERRORS;
    
    private boolean _ignoreIconLoadErrors;
    
    /**
     * Creates a theme icon loader.
     */
    protected PThemeIconLoaderAbstract() {
        _nameToIconMap = new HashMap<PThemeIconName, PThemeImageIcon>();
        _ignoreIconLoadErrors = DEFAULT_IGNORE_ICON_LOAD_ERRORS;
    }
    
    /**
     * @see PThemeImageIcon#ignoreIconLoadErrors()
     */
    public boolean ignoreIconLoadErrors() {
        return _ignoreIconLoadErrors;
    }
    
    /**
     * @see PThemeImageIcon#ignoreIconLoadErrors(boolean)
     */
    public void ignoreIconLoadErrors(boolean flag) {
        _ignoreIconLoadErrors = flag;
    }
    
    /**
     * Retrieves an icon based on its name.  If the icon is not found, {@code null} is returned
     * with is allowed by most Swing widget {@code setIcon(Icon)} methods.  If the icon is found
     * and returned, it is not guaranteed to be loaded, as loading is an asynchronous operation.
     * Only when the {@link Icon} is painted does the thread block to wait for loading to complete.
     * This method is cached, so subsequent calls will return the same reference.
     * <p>
     * To guarantee the result is non-{@code null}, call {@link #getIcon(PThemeIconName)}.
     * 
     * @param name
     *        id of icon to retrieve.  Must not be {@code null}
     * 
     * @return {@code null} if icon not found, else {@link PThemeImageIcon} reference
     * 
     * @throws NullPointerException
     *         if {@code name} is {@code null}
     * 
     * @see #getIcon(PThemeIconName)
     * @see #iconExists(PThemeIconName)
     * @see #getUrl(PThemeIconName)
     * @see #createThemeImageIcon(PThemeIconName)
     */
    public PThemeImageIcon tryGetIcon(PThemeIconName name) {
        ObjectArgs.checkNotNull(name, "name");
        
        synchronized (_nameToIconMap) {
            // Use containsKey() & get() here to allow for null icons.
            if (_nameToIconMap.containsKey(name)) {
                PThemeImageIcon icon = _nameToIconMap.get(name);
                return icon;
            }
            PThemeImageIcon icon = createThemeImageIcon(name);
            if (null != icon) {
                boolean ignoreErrors = ignoreIconLoadErrors();
                icon.ignoreIconLoadErrors(ignoreErrors);
            }
            // icon may be null here.
            _nameToIconMap.put(name, icon);
            return icon;
        }
    }
    
    /**
     * Similar to {@link #tryGetIcon(PThemeIconName)}, but throws {@link IllegalArgumentException}
     * if icon not found.  This method is cached, so subsequent calls will return the same
     * reference.
     * <p>
     * If {@code null} result is acceptable, call {@link #tryGetIcon(PThemeIconName)}.
     * 
     * @param name
     *        id of icon to retrieve.  Must not be {@code null}
     * 
     * @return {@link PThemeImageIcon} reference, guaranteed non-{@code null}
     * 
     * @throws NullPointerException
     *         if {@code name} is {@code null}
     * @throws IllegalArgumentException
     *         if icon for {@code name} is not found
     * 
     * @see #tryGetIcon(PThemeIconName)
     * @see #iconExists(PThemeIconName)
     * @see #getUrl(PThemeIconName)
     * @see #createThemeImageIcon(PThemeIconName)
     */
    public PThemeImageIcon getIcon(PThemeIconName name) {
        PThemeImageIcon icon = tryGetIcon(name);
        if (null == icon) {
            throw new IllegalArgumentException(String.format(
                "Failed to find icon for name: %s",
                name));
        }
        return icon;
    }
    
    /**
     * Tests if an icon exists, but does not guarantee it can be successfully loaded.
     * 
     * @param name
     *        id of icon to retrieve.  Must not be {@code null}
     * 
     * @return {@code true} if icon exists
     * 
     * @throws NullPointerException
     *         if {@code name} is {@code null}
     * 
     * @see #tryGetIcon(PThemeIconName)
     * @see #getIcon(PThemeIconName)
     * @see #getUrl(PThemeIconName)
     */
    public boolean iconExists(PThemeIconName name) {
        ObjectArgs.checkNotNull(name, "name");
        
        URL url = getUrl(name);
        boolean x = (null != url);
        return x;
    }
    
    /**
     * Creates a URL for a theme icon.  Implementing classes may create URLs for file paths or
     * classpath resources.  The result is passed {@link Toolkit#getImage(URL)}.
     * <p>
     * Reference: <a href="Ref: http://standards.freedesktop.org/icon-theme-spec/icon-theme-spec-latest.html"
     * >Ref: http://standards.freedesktop.org/icon-theme-spec/icon-theme-spec-latest.html</a>
     * 
     * @param name
     *        id of icon to retrieve.  Must not be {@code null}
     * 
     * @return URL for a theme icon, or {@code null} if not exists
     * 
     * @throws NullPointerException
     *         if {@code name} is {@code null}
     * 
     * @see Toolkit#getImage(URL)
     * @see #tryGetIcon(PThemeIconName)
     * @see #getIcon(PThemeIconName)
     * @see #iconExists(PThemeIconName)
     */
    protected abstract URL getUrl(PThemeIconName name);
    
    /**
     * Creates a new instance of {@link PThemeImageIcon} from a name.  Implementations will
     * probably use {@link #getUrl(PThemeIconName)}.
     * 
     * @param name
     *        id of icon to retrieve.  Must not be {@code null}
     * 
     * @return new instance of {@link PThemeImageIcon}, or {@code null} if not exists
     * 
     * @throws NullPointerException
     *         if {@code name} is {@code null}
     */
    protected abstract PThemeImageIcon createThemeImageIcon(PThemeIconName name);
}
