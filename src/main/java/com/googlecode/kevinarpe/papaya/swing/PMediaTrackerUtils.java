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
import java.awt.MediaTracker;

import com.googlecode.kevinarpe.papaya.FuncUtils;
import com.googlecode.kevinarpe.papaya.annotation.FullyTested;

/**
 * Collection of static methods for {@link MediaTracker}.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see PSwingPropertyUtils
 * @see PMediaTrackerLoadStatus
 */
public final class PMediaTrackerUtils {
    
    // Disable default constructor
    private PMediaTrackerUtils() {
    }

    private static final Object MEDIA_TRACKER_APP_CONTEXT_KEY = new Object();
    private static int _mediaTrackerId = 0;
    
    private static final FuncUtils.Func0<Object> _createMediaTrackerFunc =
        new FuncUtils.Func0<Object>() {
            @Override
            public Object call() {
                @SuppressWarnings("serial")
                Component c = new Component() { };
                MediaTracker x = new MediaTracker(c);
                return x;
            }
    };
    
    /**
     * Retrieves the {@link MediaTracker} shared by the current thread's top-level group
     * ("application context").
     * 
     * @return reference to shared {@link MediaTracker}.  Never {@code null}.
     * 
     * @see PSwingPropertyUtils
     */
    @FullyTested
    public static MediaTracker getSharedMediaTracker() {
        MediaTracker x = (MediaTracker)
            PSwingPropertyUtils.getAndPutIfMissing(
                MEDIA_TRACKER_APP_CONTEXT_KEY, _createMediaTrackerFunc);
        return x;
    }

    /**
     * Retrieves the next avaiable id for loading icons via
     * {@link MediaTracker#addImage(java.awt.Image, int)}.  The first id is one (1).
     * <p>
     * Deep: This counter is shared across all top-level thread groups ("application contexts").
     * Read more here: {@link PSwingPropertyUtils}.
     */
    @FullyTested
    public static int getNextMediaTrackerId() {
        synchronized (MEDIA_TRACKER_APP_CONTEXT_KEY) {
            ++_mediaTrackerId;
            return _mediaTrackerId;
        }
    }
}
