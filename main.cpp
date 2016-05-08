#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include "qtExtension/src/admob/admobqml.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;

    EAdSize::declareQML();
    AdMobQml::declareQML();
    engine.load(QUrl(QStringLiteral("qrc:/main.qml")));
    return app.exec();
}
