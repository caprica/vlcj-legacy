![vlcj](https://github.com/caprica/vlcj/raw/master/etc/vlcj-logo.png "vlcj")

vlcj-legacy
===========

The vlcj project ultimately will move to Java FFM for its native bindings rather than relying on the currently-used, and
undoubtedly excellent, JNA project.

This small project provides a bridge to a few JNA native methods that are currently still needed by vlcj, even when
using FFM, and that are not straightforward or are otherwise difficult to replace.

This is intended to be a short-term project to support the decoupling of JNA from vlcj, and the end-goal is to provide
appropriate replacements for these still used JNA methods.

This dependency is not intended to be used directly by client applications.

## Build status

[![Continuous Integration](https://github.com/caprica/vlcj-legacy/actions/workflows/maven.yml/badge.svg)](https://github.com/caprica/vlcj-legacy/actions/workflows/maven.yml)

[![Maven Central](https://img.shields.io/maven-central/v/uk.co.caprica/vlcj-legacy.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22uk.co.caprica%22%20AND%20a:%22vlcj-legacy%22)

## JNA native methods

The native methods listed here are the last vestiges of JNA used by the vlcj project in its FFI form.

Critically required by vlcj on Linux and Windows (for embedding of native video output in Java Swing/AWT components):

 - Native.getComponentID

Optionally required by vlcj on Linux (for the native X11 full-screen strategy implementation):

 - Native.getWindowID

Note that for `getComponentID` and `getWindowID` these two methods are actually the same, both ultimately ending up in a
call to the JNA native method `getWindowHandle0`. That method uses JNI to provide the necessary functionality, and that
means a JNI shared library is needed on all platforms - clearly this is not desirable since one of the goals of moving
to FFM is to have a pure Java solution with no extra libraries needed.

The FFM project will ship its own native library providing the same functionality without the JNA dependency.

## Present status and possible ways forward

For the native component ID method replacements, this is currently at an impasse and suggestions would be welcomed.

Long ago in the early days of vlcj, various reflection tricks were used to grab the native window peer handle from the
Java classes themselves. Those tricks were different on every platform of course. This approach may yet be feasible,
however even if that might work (and it is not 100% certain that it will) there is the added complication of having to
use the Java Module System and whatever constraints it may impose on the solution.

## Contributions are welcome

If you have ideas for ways to proceed here, or code to suggest, please feel free to do so via issues or merge requests.

Please note however that for any contribution to be accepted, you must assign all copyright in that contribution to the
project, so it will not hinder our ability to offer commercial licenses for vlcj.
