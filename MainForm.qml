import QtQuick 2.6

import AdMobQml 1.0
import AdSize 1.0
Rectangle {
    property alias mouseArea: mouseArea
    id: rectMain
    anchors.fill: parent

    MouseArea {
        id: mouseArea
        anchors.fill: parent
    }


    AdMobQml {
        id: adMob
        anchors.top: parent.top
        anchors.left: parent.left

        width: rectMain.width
        height: 150
        adUnitId: "ca-app-pub-3940256099942544/6300978111"
        adSize: AdSize.BANNER
        Rectangle {
            color: "#11111111"
            anchors.fill: parent
        }
    }
    AdMobQml {
        id: adMobBottom
        anchors.bottom: parent.bottom
        anchors.left: parent.left

        width: rectMain.width
        height: 150
        adUnitId: "ca-app-pub-3940256099942544/6300978111"
        adSize: AdSize.BANNER
        Rectangle {
            color: "#11111111"
            anchors.fill: parent
        }
    }
    Text {
        anchors.centerIn: parent
        text: adMob.adUnitId
    }
}
