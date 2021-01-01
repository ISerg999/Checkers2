package ru.ISerg999.checkers.checkersEngine.intermediates;

import ru.ISerg999.checkers.checkersEngine.ETypeState;

import java.util.ArrayList;
import java.util.List;

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

    public final ETypeState getStatusGame() { return statusGame; }
    public final String getGameBoard() { return gameBoard; }
    public final int getColorFigure() { return colorFigure; }
    public final List<String> getChanges() { return changes; }
    public final int getStateBoard() { return stateBoard; }

    public void setStatusGame(ETypeState statusGame) { this.statusGame = statusGame; }
    public void setGameBoard(String gameBoard) { this.gameBoard = gameBoard; }
    public void setColorFigure(int colorFigure) { this.colorFigure = colorFigure; }
    public void setChanges(List<String> changes) {
        this.changes = new ArrayList<>();
        this.changes.addAll(changes);
    }
    public void setStateBoard(int stateBoard) { this.stateBoard = stateBoard; }

    // ---------------------------------------------------- Private ----------------------------------------------------

    // Состояние игры.
    private ETypeState statusGame;
    // Текущее расположение фигур на доске.
    private String gameBoard;
    // Цвет игрока для текущего хода.
    private int colorFigure;
    // Изменения проделанные к текущему моменту.
    private List<String > changes;
    // Результат проверки состояния доски:
    // * отрицательное - неопределенность
    // * 0 - на доски есть все цвета фигур, и у каждого есть хотя бы один ход или атака.
    // * 1 - на доске есть хотя бы одна чёрная фигура и у чёрных есть хотя бы один ход или атака, у белых либо отсутствуют фигуры, либо они ходить не могут и не могут атаковать.
    // * 2 - на доске у белых есть хотя бы одна фигура и белые могут сделать хотя бы один ход или атаку, у чёрных либо отсутствуют фигуры, либо они не могут ходить и не могут атаковать.
    // * 3 = 1 + 2 - на доске как у белых, так и у чёрных либо нет фигур, либо есть, но ходить или атаковать не могут.
    private int stateBoard;
    // TODO: В режиме игры. Список всех позиций фигур, которые могут атаковать на текущем ходу, либо null.
    // TODO: В режиме игры. Список всех позиций фигур, которые могут ходить, если нету атакующих фигур, иначе null.

}
