import QtQuick 2.6
import QtQuick.Controls 1.4

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
        id: adMobTop
        anchors.top: parent.top
        anchors.left: parent.left

        width: rectMain.width
        height: 50
        adUnitId: "ca-app-pub-3940256099942544/6300978111"
        adSize: AdSize.SMART_BANNER
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
        height: 50
        adUnitId: "ca-app-pub-3940256099942544/6300978111"
        adSize: AdSize.BANNER
        Rectangle {
            color: "#11111111"
            anchors.fill: parent
        }
    }
    ListModel {
        id: listmodel
        ListElement {
            name: "Bill Smith"
            number: "555 3264"
        }
        ListElement {
            name: "John Brown"
            number: "555 8426"
        }
        ListElement {
            name: "Sam Wise"
            number: "555 0473"
        }
    }

    ListView {
        width: 180; height: 200
        anchors.top: adMobTop.bottom
        model: listmodel
        delegate: Text {
            text: name + ": " + number
        }
    }
}
