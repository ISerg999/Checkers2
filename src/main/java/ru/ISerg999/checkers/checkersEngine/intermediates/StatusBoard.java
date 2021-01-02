package ru.ISerg999.checkers.checkersEngine.intermediates;

import ru.ISerg999.checkers.checkersEngine.ETypeState;

public class StatusBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public StatusBoard() {
        statusGame = null;
        gameBoard = null;
        colorFigure = -1;
        changes = null;
        stateBoard = -1;
        // TODO: Набор атак заполнить в null.
        // TODO: Набор движения заполнить в null.
    }

    // Состояние игры.
    public ETypeState statusGame;
    // Текущее расположение фигур на доске.
    public String gameBoard;
    // Цвет игрока для текущего хода.
    public int colorFigure;
    // Изменения проделанные к текущему моменту.
    public String changes;
    // Результат проверки состояния доски:
    // * отрицательное - неопределенность
    // * 0 - на доски есть все цвета фигур, и у каждого есть хотя бы один ход или атака.
    // * 1 - на доске есть хотя бы одна чёрная фигура и у чёрных есть хотя бы один ход или атака, у белых либо отсутствуют фигуры, либо они ходить не могут и не могут атаковать.
    // * 2 - на доске у белых есть хотя бы одна фигура и белые могут сделать хотя бы один ход или атаку, у чёрных либо отсутствуют фигуры, либо они не могут ходить и не могут атаковать.
    // * 3 = 1 + 2 - на доске как у белых, так и у чёрных либо нет фигур, либо есть, но ходить или атаковать не могут.
    public int stateBoard;
    // TODO: В режиме игры. Список всех позиций фигур, которые могут атаковать на текущем ходу, либо null.
    // TODO: В режиме игры. Список всех позиций фигур, которые могут ходить, если нету атакующих фигур, иначе null.

}
