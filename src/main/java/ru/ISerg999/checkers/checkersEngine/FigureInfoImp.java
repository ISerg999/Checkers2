package ru.ISerg999.checkers.checkersEngine;

import ru.ISerg999.checkers.checkersEngine.baseEngine.FigureInfo;

import java.util.Arrays;
import java.util.List;

public class FigureInfoImp implements FigureInfo {
    // ---------------------------------------------------- Public -----------------------------------------------------

    // Константы.
    public final int CONST_MAX_COLOR = 2;
    public final int CONST_MAX_FIGURE = 2;
    public final int CONST_COLOR_WHITE = 1;
    public final int CONST_COLOR_BLACK = 0;
    public final int CONST_FIGURE_CHECKERS = 2;
    public final int CONST_FIGURE_QUEEN = 4;

    public int  maxNumberColorFigures() { return CONST_MAX_COLOR; }
    public int maxNumberFigures() { return CONST_MAX_FIGURE; }
    public final List<Integer> allAvailableFieldStates() {
        return Arrays.asList(0,
                CONST_COLOR_WHITE + CONST_FIGURE_CHECKERS,
                CONST_COLOR_WHITE + CONST_FIGURE_QUEEN,
                CONST_COLOR_BLACK + CONST_FIGURE_CHECKERS,
                CONST_COLOR_BLACK + CONST_FIGURE_QUEEN
        );
    }
    public int nextColorFigures(int figure) { return 1 - figure % CONST_MAX_COLOR; }
    public int colorGivenFigure(int figure) { return figure % CONST_MAX_COLOR; }
    public int typeGivenFigure(int figure) { return figure / CONST_MAX_COLOR; }

    // ---------------------------------------------------- Private ----------------------------------------------------
}
