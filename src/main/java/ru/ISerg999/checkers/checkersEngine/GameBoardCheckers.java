package ru.ISerg999.checkers.checkersEngine;

import ru.ISerg999.checkers.checkersEngine.baseEngine.GameBoard;

public class GameBoardCheckers extends GameBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public GameBoardCheckers() { super(8, 8); }
    public GameBoardCheckers(String strGameBoard) {
        super(8, 8);
        dataFromStr(strGameBoard);
    }
    public GameBoardCheckers(GameBoard gameBoard) {
        super(8, 8);
        dataFromStr(gameBoard.dataToStr());
    }

    @Override
    public void setBasePosition() {
        clear();
        for (int y = 0; y < 3; y += 1) {
            for (int x = 0; x < width; x += 1) {
                if (checkCoordinatesOutBoard(x, y)) board[7 - y][x] = figureInfo.allAvailableFieldStates().get(1);
                else board[y][x] = figureInfo.allAvailableFieldStates().get(3);
            }
        }
    }

    @Override
    public boolean dataFromStr(String strBoard) {
        String res = "";
        int i = 0;
        for (int y = 0; y < height; y += 1) {
            for (int x = 0; x < width; x += 1) {
                if (0 == (x + y) % 2) res = res + "0";
                else {
                    res = res + strBoard.substring(i, i + 1);
                    i += 1;
                }
            }
        }
        return super.dataFromStr(res);
    }

    @Override
    public String dataToStr() {
        String res = "", old = super.dataToStr();
        int i = 0;
        for (int y = 0; y < height; y += 1) {
            for (int x = 0; x < width; x += 1) {
                if (0 != (x + y) % 2) res = res + old.substring(i, i + 1);
                i += 1;
            }
        }
        return res;
    }

    // --------------------------------------------------- Protected ---------------------------------------------------

    @Override
    protected boolean checkCoordinatesOutBoard(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height || (x + y) % 2 == 0;
    }
}
