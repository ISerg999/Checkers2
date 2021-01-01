package ru.ISerg999.checkers.checkersEngine;

public enum ETypeState {
    BaseModeStart("bs"),        // Базовый или после редактирования.
    BaseModeInterrupt("bi"),    // Базовый, после прерывания игры.
    BaseModeDraw("bd"),         // Базовый, игра закончилась в нчью
    BaseModeWhiteWins("bww"),   // Базовый, белые выйграли.
    BaseModeBlackWins("bbw"),   // Базовый, Чёрные выйграли.
    EditMode("e"),              // Режим редактирования.
    GameMode("g");              // Режим игры.

    ETypeState(String strMode) {
        this.strMode = strMode;
    }

    public String getStrMode() { return this.strMode; }

    private String strMode;

}
