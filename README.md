![vlcj](https://github.com/caprica/vlcj/raw/master/etc/vlcj-logo.png "vlcj")

vlcj-legacy
===========

The vlcj project ultimately will move to Java FFI for its native bindings rather than relying on the currently-used, and
undoubtedly excellent, JNA project.

This small project provides a bridge to a few JNA native methods that are currently still needed by vlcj, even when
using FFI, and that are not straightforward or are otherwise difficult to replace.

This is intended to be a short-term project to support the decoupling of JNA from vlcj, and the end-goal is to provide
appropriate replacements for these still used JNA methods.

This dependency is not intended to be used directly by client applications.

## JNA native methods

The native methods listed here are the last vestiges of JNA used by the vlcj project in its FFI form.

Critically required by vlcj on Linux and Windows (for embedding of native video output in Java components:

 - Native.getComponentID

Optionally required by vlcj on Linux (for the native X11 full-screen strategy implementation):

 - Native.getWindowID

Note that for `getComponentID` and `getWindowID` these two methods are actually the same, both ultimately ending up in a
call to the JNA native method `getWindowHandle0`. That method uses JNI to provide the necessary functionality, and that
means a JNI shared library is needed on all platforms - clearly this is not desirable since one of the goals of moving
to FFI is to have a pure Java solution with no extra libraries needed.

Optionally required by vlcj on Windows (to discover the VLC install directory from the Windows Registry):

 - Advapi32Util.AdvapiUtil32.getRegistryLocalMachineStringValue

This registry method seems on its face that it should be easier to provide a replacement for, at least when compared
with the component ID methods. However, it seems that it is necessary to deal with issues like ASCII vs wide characters
in the Win32 API, making things a little more difficult. On the plus side, a general solution is not required here, it
is only necessary to get the VLC install path, nothing more.

## Present status and possible ways forward

For the native component ID method replacements, this is currently at an impasse and suggestions would be welcomed.

Long ago in the early days of vlcj, various reflection tricks were used to grab the native window peer handle from the
Java classes themselves. Those tricks were different on every platform of course. This approach may yet be feasible,
however even if that might work (and it is not 100% certain that it will) there is the added complication of having to
use the Java Module System and whatever constraints it may impose on the solution.

For the registry method replacement, this is certainly possible to do, but just requires someone to do the work, and
ideally someone that knows the Win32 API.

## Contributions are welcome

If you have ideas for ways to proceed here, or code to suggest, please feel free to do so via issues or merge requests.

Please note however that for any contribution to be accepted, you must assign all copyright in that contribution to the
project, so it will not hinder our ability to offer commercial licenses for vlcj.
