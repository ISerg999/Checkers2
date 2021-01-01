package ru.ISerg999.checkers.checkersEngine.baseEngine;

public abstract class ControlBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------

    /**
     * Получение текущей состоянии игры в формате JSON.
     * @return строка текущее состояние игры
     */
    public abstract String getStateGame();

    // --------------------------------------------------- Protected ---------------------------------------------------

    // Игровая доска, стартовая доска, временная доска.
    protected GameBoard board, boardStart, boardTmp;
    // Изменения на доске, изменения на временной доске.
    protected ChangesMade changes, changesTmp;
    // Текущие статусы фигур для текущей доске, для стартовой доске и для временной доске.
    protected int colorFigure, colorFigureStart, colorFigureTmp;
    // Класс информации по фигурам.
    protected FigureInfo fInfo;

    /**
     * Базовая инициализация.
     * @param boardStart стартовая доска
     */
    protected abstract void init(GameBoard boardStart);

    /**
     * Проверка существования на доске фигур заданного цвета и возможность у них передвигаться или атаковать.
     * @param curColor цвет проверяемых фигур
     * @param board    игровая доска
     * @return результат проверки: true - положительный, false - отрицательный
     */
    protected abstract boolean checkingSeeFigureOnBoard(int curColor, GameBoard board);


}
