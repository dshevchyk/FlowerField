TEMPLATE = app

QT += qml quick androidextras
CONFIG += c++11

HEADERS += \
    qtExtension/src/admob/admobqml.h \
    qtExtension/src/admob/admobimpl.h


SOURCES += main.cpp \
    qtExtension/src/admob/admobqml.cpp \



android {
    SOURCES += qtExtension/src/admob/android/admobimpl.cpp
}

ios {
    SOURCES += qtExtension/src/admob/ios/admobimpl.cpp
}
RESOURCES += qml.qrc

DESTDIR = builds/ios



# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH = qtExtension

# Default rules for deployment.
include(deployment.pri)

DISTFILES += \
    android/AndroidManifest.xml \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradlew \
    android/res/values/libs.xml \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew.bat \
    android/src/org/qtproject/example/MainActivity.java \
    android/src/org/qtproject/example/AdMob/* \
    qtExtension/*

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android
