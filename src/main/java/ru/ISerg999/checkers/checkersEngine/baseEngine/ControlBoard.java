package ru.ISerg999.checkers.checkersEngine.baseEngine;

import java.util.List;

public abstract class ControlBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public final String RESULT_ERROR = "ERROR";
    public final String RESULT_OK = "Ok";

    /**
     * Выполнение внешней команды.
     * @param cmd команда
     * @return результат выполнения
     */
    public abstract String commandExec(String cmd);

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

    /**
     * Получение текущей состоянии игры в формате JSON.
     * @return строка текущее состояние игры
     */
    protected abstract String getStateGame();

    /**
     * Получение фигуры находящейся в заданных координатах.
     * @param x координата x
     * @param y координата y
     * @return фигура, или отрицательное значение
     */
    protected abstract int getPosition(int x, int y);

    /**
     * Заполняет текущее расположение фигур на доске из базового расположения.
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean boardFromStart();

    /**
     * Устанавливает базовое расположение фигур на доске из текущего состояния.
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean boardToStart();

    /**
     * Задаёт цвет фигуры, которая будет ходить. Если значение отрицательное, то следующая фигура.
     * @param cFigure цвет фигуры или отрицательное значение
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean setColorFigure(int cFigure);

    /**
     * Установка параметров игры.
     * @param value объект с параметрами игры (StatusBoard)
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean setParamGame(String value);

    /**
     * Начать редактирование.
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean startEdition();

    /**
     * Очистка содержимого доски.
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean clearBoard();

    /**
     * Расстановка фигур в базовую позицию.
     * @return true - действие совершено, false - ошибка, действие не совершено
     */
    protected abstract boolean basicPositionBoard();

    /**
     * Установка фигуры в заданной позиции.
     * @param x координата x
     * @param y координата y
     * @param f устанавливаемая фигура
     * @return предыдущее значение поля, либо отрицательное значение, если ошибка
     */
    protected abstract int setPosition(int x, int y, int f);

    /**
     * Удаление фигуры в заданной позиции.
     * @param x координата x
     * @param y координата y
     * @return предыдущее значение поля, либо отрицательное значение,если ошибка
     */
    protected abstract int removePosition(int x, int y);
}
