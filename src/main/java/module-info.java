module uk.co.caprica.vlcj.legacy {
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires java.desktop;

    exports uk.co.caprica.vlcj.legacy to
        uk.co.caprica.vlcj,
        uk.co.caprica.vlcj.ffi;
}
