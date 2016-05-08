#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include "qtExtension/src/admob/admobqml.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;

    qmlRegisterType<AdMobQml,1>("AdMobQml", 1, 0, "AdMobQml");
    engine.load(QUrl(QStringLiteral("qrc:/main.qml")));
    return app.exec();
}
