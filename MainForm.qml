import QtQuick 2.6

import AdMobQml 1.0
Rectangle {
    property alias mouseArea: mouseArea
    id: rectMain
    anchors.fill: parent

    MouseArea {
        id: mouseArea
        anchors.fill: parent
    }

    Rectangle {
        anchors.centerIn: parent
        width: childrenRect.width
        height: childrenRect.height
        onXChanged: {
            console.log("parent x changed to " + x);
        }

    AdMobQml {
        id: adMob
        width: rectMain.width
        height: 150
        Rectangle {
            color: "red"
            anchors.fill: parent
        }

        onVisibleChanged: {
            console.log("onVisibleChanged qml " + visible)
        }

        onXChanged: {
            console.log("x changed to " + x);
        }
    }
    }
    Text {
        anchors.centerIn: parent
        text: adMob.message
    }
}
