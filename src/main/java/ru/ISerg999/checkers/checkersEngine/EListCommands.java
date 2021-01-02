package ru.ISerg999.checkers.checkersEngine;

public enum EListCommands {
    GetStatus("gs"),                // Получение статуса.
    BoardFromStart("bfb"),          // В базовом режиме. Расстановка фигур как на стартовой доске.
    BoardToStart("bts"),            // В базовом режиме. Сделать текущую расстановку фигур стартовой.
    ChangeColorFigure("ccf");       // В базовом режиме. Меняет цвет текущих фигур. После команда или цвет, или - - означающий следующий цвет.

    EListCommands(String cmd) { this.cmd = cmd; }

    public String getCmd() { return this.cmd; }

    private String cmd;
}
