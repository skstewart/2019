
#ifndef NOTEPAD_H
#define NOTEPAD_H

#include <QMainWindow>

namespace Ui {
class Notepad;
}


class Notepad : public QMainWindow
{
    Q_OBJECT

public:
    explicit Notepad(QWidget *parent = 0);
    ~Notepad();

private slots:
    void newDocument();

    void open();

    void save();

    void exit();

    void copy();

    void cut();

    void paste();

    void undo();

    void redo();

private:
    Ui::Notepad *ui;
    QString currentFile;
};

#endif // NOTEPAD_H
