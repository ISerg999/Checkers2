package ru.ISerg999.checkers.checkersEngine;

import ru.ISerg999.checkers.checkersEngine.baseEngine.ControlBoard;
import ru.ISerg999.checkers.checkersEngine.baseEngine.GameBoard;
import ru.ISerg999.checkers.checkersEngine.intermediates.StatusBoard;
import ru.ISerg999.checkers.configs.CheckersConfig;
import ru.ISerg999.checkers.utils.UtilsCollection;

public class ControlBoardCheckers extends ControlBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------
    public ControlBoardCheckers() {
        cCfg = CheckersConfig.getInst();
        ui = cCfg.getUtils();
        fInfo = cCfg.figureInfo();
        init(null);
    }

    @Override
    public String getStateGame() {
        int tmp = 0;
        StatusBoard sb = cCfg.statusBoard();
        sb.statusGame = status;
        sb.gameBoard = board.dataToStr();
        sb.colorFigure = colorFigure;
        if (ETypeState.BaseModeInterrupt == status || ETypeState.GameMode == status || ETypeState.EditMode == status) {
            sb.changes = changes.getChanges();
            if (ETypeState.BaseModeInterrupt != status) {
                if (!checkingSeeFigureOnBoard(FigureInfoImp.CONST_COLOR_WHITE, board)) tmp = 1;
                if (!checkingSeeFigureOnBoard(FigureInfoImp.CONST_COLOR_BLACK, board)) tmp += 2;
                sb.stateBoard = tmp;
                if (ETypeState.GameMode == status) {
                    // TODO: Заполнить вожможные атаки текущими фигурами, если таковы есть.
                    // TODO: Заполнить вожможные ходы текущими фигурами, если нет атак.
                }
            }
        }
        return ui.ObjToJson(sb);
    }
    // --------------------------------------------------- Protected ---------------------------------------------------

    // Объект конфигурирования.
    protected CheckersConfig cCfg;
    // Объект набора утитлит.
    protected UtilsCollection ui;
    // Статус текущего состояния и временное состояние статуса.
    protected ETypeState status, statusTmp;

    @Override
    protected void init(GameBoard boardStart) {
        if (null != boardStart) this.boardStart = cCfg.gameBoardCheckers((GameBoardCheckers) boardStart);
        else {
            this.boardStart = cCfg.gameBoardCheckers();
            this.boardStart.setBasePosition();
        }
        board = cCfg.gameBoardCheckers((GameBoardCheckers) this.boardStart);
        changes = cCfg.changesMade();
        boardTmp = null;
        changesTmp = null;
        status = ETypeState.BaseModeStart;
        colorFigure = 1;
        colorFigureStart = 1;
        colorFigureTmp = 1;
    }

    @Override
    protected boolean checkingSeeFigureOnBoard(int curColor, GameBoard board) {
        curColor = curColor % fInfo.maxNumberColorFigures();
        // TODO: Метод проверяющий есть ли хоть одна фигура заданного цвета, и если есть, то есть ли возможность для фигур заданного цвета сделать ход или атаковать. Если есть то true, иначе false.
        // TODO: Временный метод:
        int[][] b = board.getBoard();
        for (int[] row: b) {
            for (int f: row) {
                if (f % fInfo.maxNumberColorFigures() == curColor) return true;
            }
        }
        return false;
    }
}
