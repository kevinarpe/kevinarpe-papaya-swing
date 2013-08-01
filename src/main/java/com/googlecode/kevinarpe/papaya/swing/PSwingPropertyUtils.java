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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import com.googlecode.kevinarpe.papaya.FuncUtils;
import com.googlecode.kevinarpe.papaya.annotation.NotFullyTested;
import com.googlecode.kevinarpe.papaya.argument.ObjectArgs;

/**
 * Collection of static methods to store and retrieve global properties.  These methods act as a
 * replacement for those in {@link sun.awt.AppContext}, as they are part of the Java non-public
 * API.  A separate map of key-value pairs are available for each top-level {@link ThreadGroup}.
 * As some Java experts now consider {@code ThreadGroup} to be most obsolete, it is used here only
 * as a convenient mechanism to separate threads from different application contexts.  (<a
 * href="http://stackoverflow.com/questions/3265640/why-threadgroup-is-being-criticised"
 * >Reference</a>)
 * <p>
 * Normally, in Swing applications, it is not safe to use mutable static members, as multiple Java
 * apps can run simultaneously from the same Java Virtual Machine.  Instead, mutable global
 * properties should be managed with this class.  To be clear: It is not necessary to use this
 * class to store <i>truly</i> immutable properties: final primitives or final references to final
 * class instances.
 * <p>
 * Quoting this
 * <a href="https://weblogs.java.net/blog/alexfromsun/archive/2012/02/09/swing-better-world-static-fields-vs-appcontext?force=554"
 * >reference</a>: <i>A Swing application is not necessarily run by its own Java Virtual Machine.
 * Applets or webstart applications placed on the same html page may share one JVM, which leads to
 * the common static data. A static field changed by one application may unexpectedly affect
 * another application.</i>
 * <p>
 * To improve performance, MRU (most recently used) caching is employed.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 * 
 * @see sun.awt.AppContext
 */
public final class PSwingPropertyUtils {

    // Disable default constructor
    private PSwingPropertyUtils() {
    }
    
    private static final WeakHashMap<ThreadGroup, Map<Object, Object>>
        _threadGroup_To_PropertyMap_Map =
            new WeakHashMap<ThreadGroup, Map<Object, Object>>();
    
    /**
     * Retieves the value associated with {@code key} for the current thread's top-level group.
     * These values may <b>never</b> be {@code null}, so a return value of {@code null}
     * <b>always</b> indicates {@code key} is unmapped.  This behavior is different from some
     * implementations in JDK that allow for {@code null} keys and values, such as {@link HashMap}.
     * <p>
     * In practice, it may be better to use
     * {@link #getAndPutIfMissing(Object, com.googlecode.kevinarpe.papaya.FuncUtils.Func0)} to
     * handle when {@code key} is unmapped, so a value must be created and inserted.  All this is
     * done in a thread-safe setting.
     * <p>
     * This method is thread-safe and replaces {@link sun.awt.AppContext#get(Object)}.
     * 
     * @param key
     *        a key to look-up.  Must not be {@code null}.
     * 
     * @return associated value or {@code null} to indicate {@code key} is unmapped
     * 
     * @throws NullPointerException
     *         if {@code key} is {@code null}
     *
     * @see #getAndPutIfMissing(Object, com.googlecode.kevinarpe.papaya.FuncUtils.Func0)
     * @see #put(Object, Object)
     * @see #remove(Object)
     * @see Thread#getThreadGroup()
     * @see sun.awt.AppContext#get(Object)
     */
    @NotFullyTested
    public static Object get(Object key) {
        ObjectArgs.checkNotNull(key, "key");
        
        boolean createIfMissing = true;
        synchronized (_threadGroup_To_PropertyMap_Map) {
            Map<Object, Object> propertyMap = _getPropertyMap(createIfMissing);
            Object value = propertyMap.get(key);
            return value;
        }
    }
    
    /**
     * Similar to {@link #get(Object)}, but if {@code key} is unmapped, create the value by
     * calling functor {@code createValueFunc}, then insert a new key-value pair.
     * <p>
     * This method is thread-safe (including calling functor {@code createValueFunc}) and replaces
     * {@link sun.awt.AppContext#get(Object)}.
     * 
     * @param key
     *        a key to look-up.  Must not be {@code null}.
     * 
     * @param createValueFunc
     * <ul>
     *   <li>functor to create value if {@code key} is unmapped</li>
     *   <li>must not be {@code null}</li>
     *   <li>if functor is invoked, must not return {@code null}</li>
     * </ul>
     * 
     * @return associated value.  Never {@code null}
     *
     * @throws NullPointerException
     *         if {@code key} or {@code createValueFunc} is {@code null}
     * @throws IllegalStateException
     *         if functor {@code createValueFunc} is invoked and returns {@code null}
     *
     * @see #get(Object)
     * @see #put(Object, Object)
     * @see #remove(Object)
     * @see sun.awt.AppContext#get(Object)
     */
    @NotFullyTested
    public static Object getAndPutIfMissing(Object key, FuncUtils.Func0<Object> createValueFunc) {
        ObjectArgs.checkNotNull(key, "key");
        ObjectArgs.checkNotNull(createValueFunc, "createValueFunc");
        
        boolean createIfMissing = true;
        synchronized (_threadGroup_To_PropertyMap_Map) {
            Map<Object, Object> propertyMap = _getPropertyMap(createIfMissing);
            Object value = propertyMap.get(key);
            if (null == value) {
                value = createValueFunc.call();
                if (null == value) {
                    throw new IllegalStateException(
                        "Null value returned by calling functor argument 'createValueFunc'");
                }
                propertyMap.put(key, value);
            }
            return value;
        }
    }
    
    /**
     * Associates {@code value} with {@code key} for the current thread's top-level group.  If an
     * existing mapping exists for {@code key}, it is replaced.
     * <p>
     * This method is thread-safe and replaces {@link sun.awt.AppContext#put(Object, Object)}.
     * 
     * @param key
     *        must not be {@code null}
     * @param value
     *        must not be {@code null}.  If weak reference is necessary, see {@link WeakReference}.
     * 
     * @return {@code null} is no previous mapping, else previous associated value
     * 
     * @throws NullPointerException
     *         if {@code key} or {@code value} is {@code null}
     * 
     * @see #get(Object)
     * @see #getAndPutIfMissing(Object, com.googlecode.kevinarpe.papaya.FuncUtils.Func0)
     * @see #remove(Object)
     * @see Thread#getThreadGroup()
     * @see sun.awt.AppContext#put(Object, Object)
     */
    @NotFullyTested
    public static Object put(Object key, Object value) {
        ObjectArgs.checkNotNull(key, "key");
        ObjectArgs.checkNotNull(value, "value");
        
        boolean createIfMissing = true;
        synchronized (_threadGroup_To_PropertyMap_Map) {
            Map<Object, Object> propertyMap = _getPropertyMap(createIfMissing);
            Object prevValue = propertyMap.put(key, value);
            return prevValue;
        }
    }
    
    /**
     * Removes a key-value pair for the current thread's top-level group.
     * <p>
     * This method is thread-safe and replaces {@link sun.awt.AppContext#remove(Object)}.
     * 
     * @param key
     *        a key to look-up.  Must not be {@code null}.
     * 
     * @return {@code null} if {@code key} not mapped, else associated value
     * 
     * @throws NullPointerException
     *         if {@code key} is {@code null}
     * 
     * @see #get(Object)
     * @see #getAndPutIfMissing(Object, com.googlecode.kevinarpe.papaya.FuncUtils.Func0)
     * @see #put(Object, Object)
     * @see Thread#getThreadGroup()
     * @see sun.awt.AppContext#remove(Object)
     */
    @NotFullyTested
    public static Object remove(Object key) {
        ObjectArgs.checkNotNull(key, "key");
        
        boolean createIfMissing = false;
        synchronized (_threadGroup_To_PropertyMap_Map) {
            Map<Object, Object> propertyMap = _getPropertyMap(createIfMissing);
            Object prevValue = (null == propertyMap ? null : propertyMap.remove(key));
            return prevValue;
        }
    }
    
    private static final class _LastThreadAndPropertyMap {
        
        public final Thread thread;
        public final Map<Object, Object> propertyMap;
        
        public _LastThreadAndPropertyMap(Thread thread, Map<Object, Object> propertyMap) {
            this.thread = thread;
            this.propertyMap = propertyMap;
        }
    }
    
    /**
     * Just when you thought mutable static members are unsafe, here we have one.  Watch carefully
     * how this member is used by _getPropertyMap(...).
     */
    private static _LastThreadAndPropertyMap _lastThreadAndPropertyMap = null;
    
    /**
     * The caller <b>must</b> synchronise {@link #_threadGroup_To_PropertyMap_Map} before calling
     * this method and maintain the lock until finish using the result.
     * <p>
     * Dear Programmer: I know this method looks very complicated.
     * You may rightly ask: "Won't it be slow?"
     * 1) There is MRU (most recently used) caching.
     * 2) Normally, only the first call for a top-level thread group is slow.
     */
    private static Map<Object, Object> _getPropertyMap(boolean createIfMissing) {
        final Thread thread = Thread.currentThread();
        // Make a local ref copy here to be thread-safe.  This read operation is atomic.
        _LastThreadAndPropertyMap last = _lastThreadAndPropertyMap;
        if (null != last && last.thread == thread) {
            return last.propertyMap;
        }
        
        final ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup groupIter = group;
        Map<Object, Object> propertyMap = null;
        
        do {
            propertyMap = _threadGroup_To_PropertyMap_Map.get(groupIter);
            if (null == propertyMap) {
                groupIter = groupIter.getParent();
            }
        }
        while (null == propertyMap && null != groupIter);
        
        if (null == propertyMap && createIfMissing) {
            int initialCapacity = 16;
            float loadFactor = 0.75f;
            boolean accessOrder = true;
            // Few are aware of this incredible feature in LinkedHashMap:
            // Here we are setting the 'accessOrder' flag that ensures the most recently accessed
            // entry (key-value pair) will be the first entry during look-up/iteration.
            propertyMap =
                new LinkedHashMap<Object, Object>(initialCapacity, loadFactor, accessOrder);
        }
        
        // Add all groups here to improve future look-ups.
        for (groupIter = group; null != groupIter; groupIter = groupIter.getParent()) {
            _threadGroup_To_PropertyMap_Map.put(groupIter, propertyMap);
        }
        
        // This write operation is atomic.
        _lastThreadAndPropertyMap = new _LastThreadAndPropertyMap(thread, propertyMap);
        return propertyMap;
    }
}
