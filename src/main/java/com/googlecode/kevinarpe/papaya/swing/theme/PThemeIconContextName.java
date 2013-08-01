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

import com.googlecode.kevinarpe.papaya.argument.StringArgs;

/**
 * Standard theme icon contexts (categories) as defined by <a href="www.freedesktop.org"
 * >www.freedesktop.org</a>.
 * <p>
 * Ref: <a href="http://standards.freedesktop.org/icon-naming-spec/icon-naming-spec-latest.html"
 * >http://standards.freedesktop.org/icon-naming-spec/icon-naming-spec-latest.html</a>
 * <p>
 * Attribution notice: Most of the comments for enum values below are copied from the referenced
 * spec (Version 0.8.90).  Copyright for this content remains with that of the original author:
 * Rodney Dawes (dobey.pwns@gmail.com)
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PThemeIconName
 * @see PThemeIconName#context
 */
public enum PThemeIconContextName {

    /**
     * Actions: Icons which are generally used in menus and dialogs for interacting with the user. 
     * <p>
     * Directory: actions
     */
    ACTIONS("actions"),
    
    /**
     * Animations: Animated images used to represent loading web sites, or other background processing which may be less suited to more verbose progress reporting in the user interface. Animations should be a PNG with frames which are the size of the directory the animation is in, tiled in a WxH grid. Implementations should determine the number of frames by dividing the image into its frames, and iterating from left to right, wrapping to the first frame, after rendering the last. 
     * <p>
     * Directory: animations
     */
    ANIMATIONS("animations"),
    
    /**
     * Applications: Icons that describe what an application is, for use in the Programs menu, window decorations, and the task list. These may or may not be generic depending on the application and its purpose. Applications which are to be considered part of the base desktop, such as the calculator or terminal, should use the generic icons specified in this specification, while more advanced applications such as web browsers and office applications should use branded icons which still give the user an idea of what function the application provides. 
     * <p>
     * Directory: apps
     */
    APPLICATIONS("apps"),
    
    /**
     * Categories: Icons that are used for categories in the Programs menu, or the Control Center, for separating applications, preferences, and settings for display to the user. 
     * <p>
     * Directory: categories
     */
    CATEGORIES("categories"),
    
    /**
     * Devices: Icons for hardware that is contained within or connected to the computing device. Naming for extended devices in this group, is of the form <primary function>-<manufacturer>-<model>. This allows ease of fallback to the primary function device name, or ones more targeted for a specific series of models from a manufacturer. For example, a theme author may want to provide icons for different phones. The specific model icons could be named “phone-samsung-t809”, “phone-motorola-rokr”, and “phone-motorola-pebl”. However, the theme must provide a phone icon in the theme's style, so that devices not matching these models, will still have an appropriate icon. An exception to this rule is that the “media” icons do not need to include manufacturer names, as they are generic items, and may be available from many manufacturers. As a result, for media, the specific icons are to differentiate between different specific types of media. For exmaple, an artist may wish to provide icons for BluRay, DVD, HD-DVD, CD-ROM, and variations thereof. The specific media type icons should be named in the form, <primary function>-<specific format>. Some examples are “media-optical”, “media-optical-bd” and “media-optical-dvd”. 
     * <p>
     * Directory: devices
     */
    DEVICES("devices"),
    
    /**
     * Emblems: Icons for tags and properties of files, that are displayed in the file manager. This context contains emblems for such things as “read-only” or “photos”. 
     * <p>
     * Directory: emblems
     */
    EMBLEMS("emblems"),
    
    /**
     * Emotes: Icons for emotions that are expressed through text chat applications such as :-) or :-P in IRC or instant messengers. 
     * <p>
     * Directory: emotes
     */
    EMOTES("emotes"),
    
    /**
     * MimeTypes: Icons for different types of data, such as audio or image files. 
     * <p>
     * Directory: mimetypes
     */
    MIMETYPES("mimetypes"),
    
    /**
     * Places: Icons used to represent locations, either on the local filesystem, or through remote connections. Folders, trash, and workgroups are some examples. 
     * <p>
     * Directory: places
     */
    PLACES("places"),
    
    /**
     * Status: Icons for presenting status to the user. This context contains icons for warning and error dialogs, as well as for the current weather, appointment alarms, and battery status. 
     * <p>
     * Directory: status
     */
    STATUS("status"),
    ;
    
    public final String dirName;
    
    private PThemeIconContextName(String dirName) {
        this.dirName = StringArgs.checkNotEmptyOrWhitespace(dirName, "dirName");
    }
    
    /**
     * Converts this reference to a debugger-friendly string.  Do not depend upon this method, nor
     * implicit {@link String} conversion.  Instead, if only the name is required, call
     * {@link #name()} directly.
     * <hr>
     * Docs from {@link Enum#toString()}:
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String x = String.format(
            "enum %s: ["
            + "%n\tname(): '%s'"
            + "%n\tdirName: '%s'"
            + "%n\t]",
            PThemeIconContextName.class.getCanonicalName(),
            name(),
            dirName);
        return x;
    }
}
