/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2022-2025 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.legacy;

import com.sun.jna.Native;

import java.awt.Component;
import java.awt.Window;

import static uk.co.caprica.vlcj.legacy.JawtLoader.jawtLoader;

/**
 * Note that the following methods will attempt to call native API in libjawt, if this library has not been loaded
 * then the call will fail:
 *
 * <ul>
 *   <li>{@link #getComponentId(Component)}
 *   <li>{@link #getWindowId(Window)}
 * </ul>
 *
 * To prevent this, any call to either of those methods will trigger a one-time lazy initialisation of a component that
 * loads the jawt library.
 *
 * @see JawtLoader
 */
public class LegacyUtils {

    /**
     * Get the native component identifier for the given component.
     *
     * @param component component
     * @return native component identifier
     */
    public static long getComponentId(Component component) {
        jawtLoader().loadJawt();
        return Native.getComponentID(component);
    }

    /**
     * Get the native window identifier for the given component.
     *
     * @param window window
     * @return native component identifier
     */
    public static long getWindowId(Window window) {
        jawtLoader().loadJawt();
        return Native.getWindowID(window);
    }

    private LegacyUtils() {}
}
