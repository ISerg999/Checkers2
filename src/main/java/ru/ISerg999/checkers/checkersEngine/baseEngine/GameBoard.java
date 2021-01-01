package ru.ISerg999.checkers.checkersEngine.baseEngine;

import ru.ISerg999.checkers.checkersEngine.intermediates.DataConversion;
import ru.ISerg999.checkers.configs.CheckersConfig;
import ru.ISerg999.checkers.utils.CPair;

public abstract class GameBoard implements DataConversion {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[height][width];
        clear();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public final int[][] getBoard() { return board; }

    /**
     * Очистка игровой доски.
     */
    public void clear() {
        for (int y = 0; y < height; y += 1) {
            for (int x = 0; x < width; x += 1) {
                board[y][x] = 0;
            }
        }
    }

    /**
     * Базовая расстановка фигур на игровой доске.
     */
    public abstract void setBasePosition();

    @Override
    public String dataToStr() {
        String res = "";
        for (int[] row: board) {
            for (int f: row) {
                res = res + String.format("%x", f);
            }
        }
        return res;
    }

    @Override
    public boolean dataFromStr(String strData) {
        if (null == strData || strData.length() < height * width) return false;
        int[][] newBoard = new int[height][width];
        int i = 0;
        for (int y = 0; y < height; y += 1) {
            for (int x = 0; x < width; x += 1) {
                try {
                    newBoard[y][x] = Integer.parseUnsignedInt(String.valueOf(strData.charAt(i)), 16);
                    i += 1;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        board = newBoard;
        return true;
    }

    /**
     * Возвращает состояние поля в заданных координатах.
     * @param x координата x
     * @param y координата y
     * @return возвращает значение состояние поля: 0 - поле пустое, число больше 0 - фигура, число меньше 0 - ошибка
     */
    public int getPos(int x, int y) {
        if (checkCoordinatesOutBoard(x, y)) return -1;
        return board[y][x];
    }

    public int getPos(CPair<Integer, Integer> pos) { return getPos(pos.getX(), pos.getY()); }

    /**
     * Установка фигуры на игровом поле.
     * @param x координата x
     * @param y координата y
     * @param f устанавливаемая фигура
     * @return если больше или равно 0, то бывшее содержимое поля, иначе ошибка: -1 - неправельные координаты, -2 - несуществующая фигура
     */
    public int setPos(int x, int y, int f) {
        if (!figureInfo.allAvailableFieldStates().contains(f)) return -2;
        if (checkCoordinatesOutBoard(x, y)) return -1;
        int res = board[y][x];
        board[y][x] = f;
        return res;
    }

    public int setPos(CPair<Integer, Integer> pos, int f) { return setPos(pos.getX(), pos.getY(), f); }

    // --------------------------------------------------- Protected ---------------------------------------------------

    // Ссылка на объект класса с информацией о фигурах.
    protected FigureInfo figureInfo = CheckersConfig.getInst().figureInfo();
    // Рзамеры доски.
    protected int width, height;
    // Игровая доска.
    protected int[][] board;

    /**
     * Проверка выхода координат за пределы допустимых значений.
     * @param x координата x
     * @param y координата y
     * @return true - неправельные координаты, false - координаты правельные
     */
    protected abstract boolean checkCoordinatesOutBoard(int x, int y);
}
