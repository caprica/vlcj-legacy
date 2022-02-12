package uk.co.caprica.vlcj.legacy;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

import java.awt.Component;
import java.awt.Window;

public class LegacyUtils {

    public static long getComponentId(Component component) {
        return Native.getComponentID(component);
    }

    public static long getWindowId(Window window) {
        return Native.getWindowID(window);
    }

    public static String getRegistryLocalMachineStringValue(String key, String value) {
        return Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, value);
    }

    private LegacyUtils() {}
}
