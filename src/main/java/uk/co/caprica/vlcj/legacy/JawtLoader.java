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
 * Copyright 2022 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.legacy;

import java.awt.Component;
import java.awt.Window;

/**
 * This component implements the initialisation-on-demand holder idiom to lazily load the jawt library the first time it
 * is needed.
 * <p>
 * When using the {@link LegacyUtils#getComponentId(Component)} or {@link LegacyUtils#getWindowId(Window)} methods, this
 * internally triggers a native API call to libjawt. However, it is possible that libjawt has not been loaded yet.
 * <p>
 * To mitigate this, those functions use this component to trigger an explicit lazy-loading of libjawt if needed.
 * <p>
 * In this way, the library load is only triggered if the application actually needs it - which would not be the case
 * for example if using JavaFX rather than Swing/AWT.
 */
final class JawtLoader {

    static JawtLoader jawtLoader() {
        return JawtLoaderHolder.INSTANCE;
    }

    public void loadJawt() {}

    private static class JawtLoaderHolder {
        static final JawtLoader INSTANCE = new JawtLoader();
    }

    private JawtLoader() {
        System.loadLibrary("jawt");
    }
}
