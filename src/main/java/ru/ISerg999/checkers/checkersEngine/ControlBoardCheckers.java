package ru.ISerg999.checkers.checkersEngine;

import ru.ISerg999.checkers.checkersEngine.baseEngine.ChangesMade;
import ru.ISerg999.checkers.checkersEngine.baseEngine.ControlBoard;
import ru.ISerg999.checkers.checkersEngine.baseEngine.GameBoard;
import ru.ISerg999.checkers.checkersEngine.intermediates.StatusBoard;
import ru.ISerg999.checkers.configs.CheckersConfig;
import ru.ISerg999.checkers.utils.UtilsCollection;

import java.util.Arrays;

public class ControlBoardCheckers extends ControlBoard {
    // ---------------------------------------------------- Public -----------------------------------------------------
    public ControlBoardCheckers() {
        cCfg = CheckersConfig.getInst();
        ui = cCfg.getUtils();
        fInfo = cCfg.figureInfo();

        init(null);
    }

    @Override
    public String commandExec(String cmd) {
        if (!(null == cmd || cmd.isEmpty())) {
            cmd = cmd.toLowerCase().trim();
            int x, y, f;
            String valeCmd, strTmp;
            if (cmd.startsWith(EListCommands.GetStatus.getCmd())) return getStateGame(); // Получаем статус команд
            else if (cmd.startsWith(EListCommands.GetFigure.getCmd())) { // Получение фигуры по её координатам.
                valeCmd = cmd.substring(EListCommands.GetFigure.getCmd().length()).trim();
                if (valeCmd.length() > 1) {
                    x = Integer.parseUnsignedInt(valeCmd.substring(0, 1), 16);
                    y = Integer.parseUnsignedInt(valeCmd.substring(1, 2), 16);
                    f = getPosition(x, y);
                    return f < 0? "-": String.format("%x", f);
                }
            }
            if (ETypeState.EditMode == status) { // В режиме редактирования.
                if (cmd.startsWith(EListCommands.CleaningBoardInEdit.getCmd())) { // Очистка доски.
                    if (clearBoard()) {
                        valeCmd = board.dataToStr();
                        changes.add(cmd + "@" + valeCmd);
                        return RESULT_OK;
                    }
                } else if (cmd.startsWith(EListCommands.BasicPositionInEdit.getCmd())) { // Расстановка фигур в базовую позицию.
                    if (basicPositionBoard()) {
                        valeCmd = board.dataToStr();
                        changes.add(cmd + "@" + valeCmd);
                        return RESULT_OK;
                    }
                } else if (cmd.startsWith(EListCommands.SetFigure.getCmd())) { // Установка фигуры. Возвращает бывшее значение данной позиции.
                    valeCmd = cmd.substring(EListCommands.SetFigure.getCmd().length()).trim();
                    if (valeCmd.length() > 2) {
                        x = Integer.parseUnsignedInt(valeCmd.substring(0, 1), 16);
                        y = Integer.parseUnsignedInt(valeCmd.substring(1, 2), 16);
                        f = Integer.parseUnsignedInt(valeCmd.substring(2, 3), 16);
                        f = setPosition(x, y, f);
                        if (f >= 0) {
                            strTmp = String.format("%x", f);
                            changes.add(cmd + "@" + strTmp);
                            return strTmp;
                        }
                    }
                } else if (cmd.startsWith(EListCommands.RemoveFigure.getCmd())) { // Удаление фигуры.
                    valeCmd = cmd.substring(EListCommands.RemoveFigure.getCmd().length()).trim();
                    if (valeCmd.length() > 1) {
                        x = Integer.parseUnsignedInt(valeCmd.substring(0, 1), 16);
                        y = Integer.parseUnsignedInt(valeCmd.substring(1, 2), 16);
                        f = removePosition(x, y);
                        if (f >= 0) {
                            strTmp = String.format("%x", f);
                            changes.add(cmd + "@" + strTmp);
                            return strTmp;
                        }
                    }
                } else if (cmd.startsWith(EListCommands.ClosingWithCancellation.getCmd())) { // Закрытие режима редактирования с отменой изменений.
                    // TODO: Закрытие с отменой.
                } else if (cmd.startsWith(EListCommands.ClosingWithAcceptance.getCmd())) { // Закрытие режима редактирования с принятием изменений.

                } else if (cmd.startsWith(EListCommands.Undo.getCmd())) { // Отмена предыдущего изменения.
                    // TODO: Отмена действия.
                } else if (cmd.startsWith(EListCommands.Redo.getCmd())) { // Возврат предыдущего отмененного изменения.
                    // TODO: Возврат отмененного действия.
                }
            } else if (ETypeState.GameMode == status) { // В режиме игры.
                // TODO: Выбор позиции фигуры для совершаемого действия.
                // TODO: Получить список возможных действий для фигуры по выбранной позиции.
                // TODO: Выбрать направление действия и совершить действие. Передать ход следующему игровку. Сделать начальный анализ состояния фигур.
                // TODO: <Проверка действий для текущего хода. Проверка на Ничья, Белые Выйграли, Чёрные выйграли. Ничья в случае, если в 50 ходов не было атаки или в 15 действий дамок не было действия шашек. Или что закончилось время.>
                // TODO: Окончание игры, если её прервали.
                // TODO: Получение оставшегося время.
            } else { // В базовом режиме.
                if (cmd.startsWith(EListCommands.BoardFromStart.getCmd())) { // Расстановка как на стартовой доске.
                    if (boardFromStart()) return getStateGame();
                } else if (cmd.startsWith(EListCommands.BoardToStart.getCmd())) { // Делает текущую доску стартовой.
                    if (boardToStart()) return RESULT_OK;
                } else if (cmd.startsWith(EListCommands.ChangeColorFigure.getCmd())) { // Задаёт текущий цвет фигур, или следующий цвет.
                    valeCmd = cmd.substring(EListCommands.ChangeColorFigure.getCmd().length()).trim();
                    if (valeCmd.isEmpty() || '-' == valeCmd.charAt(0)) f = -1;
                    else f = Integer.parseUnsignedInt(valeCmd, 16);
                    if (setColorFigure(f)) changes.add(cmd);
                } else if (cmd.startsWith(EListCommands.SetParamGame.getCmd())) { // Установка внешних параметров игры.
                    valeCmd = cmd.substring(EListCommands.SetParamGame.getCmd().length()).trim();
                    if (setParamGame(valeCmd)) return getStateGame();
                } else if (cmd.startsWith(EListCommands.StartEdition.getCmd())) { // Начать редактирование.
                    if (startEdition()) return RESULT_OK;
                }
                // TODO: Продолжить игру. Сделать начальный анализ состояния фигур.
                // TODO: Начать игру. Сделать начальный анализ состояния фигур.
                // TODO: Установка времени в секундах на один ход.
                // TODO: Сброс отводимого времени на ход.
            }
        }
        return RESULT_ERROR;
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

    @Override
    protected String getStateGame() {
        int tmp = 0;
        StatusBoard sb = cCfg.statusBoard();
        sb.statusGame = status;
        sb.gameBoard = board.dataToStr();
        sb.colorFigure = colorFigure;
        if (ETypeState.BaseModeStart == status || ETypeState.BaseModeInterrupt == status || ETypeState.GameMode == status || ETypeState.EditMode == status) {
            sb.changes = changes.dataToStr();
            if (ETypeState.GameMode == status || ETypeState.EditMode == status) {
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

    @Override
    protected int getPosition(int x, int y) { return board.getPos(x, y); }

    @Override
    protected boolean boardFromStart() {
        if (status == ETypeState.GameMode || status == ETypeState.EditMode) return false;
        board = new GameBoardCheckers((GameBoardCheckers) boardStart);
        colorFigure = colorFigureStart;
        changes.clear();
        status = ETypeState.BaseModeStart;
        return true;
    }

    @Override
    protected boolean boardToStart() {
        if (status == ETypeState.GameMode || status == ETypeState.EditMode) return false;
        boardStart = new GameBoardCheckers((GameBoardCheckers) board);
        colorFigureStart = colorFigure;
        return true;
    }

    @Override
    protected boolean setColorFigure(int cFigure) {
        if (ETypeState.EditMode == status) return false;
        if (cFigure < 0) colorFigure += 1;
        else colorFigure = cFigure;
        colorFigure = colorFigure % fInfo.maxNumberColorFigures();
        return true;
    }

    @Override
    protected boolean setParamGame(String value) {
        if (ETypeState.GameMode == status || ETypeState.EditMode == status) return false;
        StatusBoard sb = ui.ObjFromJson(value, StatusBoard.class);
        if (null == sb || sb.stateBoard != 0) return false;
        status = sb.statusGame;
        board = new GameBoardCheckers(sb.gameBoard);
        colorFigure = sb.colorFigure;
        changes.dataFromStr(sb.changes);
        // TODO: Заполнить возможные атаки текущими фигурами, если не null.
        // TODO: Заполнить возможные ходы текущими фигурами, если не null.
        return true;
    }

    @Override
    protected boolean startEdition() {
        if (ETypeState.EditMode == status || ETypeState.GameMode == status) return false;
        boardTmp = new GameBoardCheckers((GameBoardCheckers) board);
        changesTmp = new ChangesMade(changes);
        changes.clear();
        colorFigureTmp = colorFigure;
        status = ETypeState.EditMode;
        return true;
    }

    @Override
    protected boolean clearBoard() {
        if (ETypeState.EditMode != status) return false;
        board.clear();
        return true;
    }

    @Override
    protected boolean basicPositionBoard() {
        if (ETypeState.EditMode != status) return false;
        board.setBasePosition();
        return true;
    }

    @Override
    protected int setPosition(int x, int y, int f) {
        if (!(ETypeState.GameMode == status || ETypeState.EditMode == status) || f <= 0 || !fInfo.allAvailableFieldStates().contains(f) || board.getPos(x, y) < 0) return -1;
        return board.setPos(x, y, f);
    }

    @Override
    protected int removePosition(int x, int y) {
        if (!(ETypeState.GameMode == status || ETypeState.EditMode == status) || board.getPos(x, y) < 0) return -1;
        return board.setPos(x, y, 0);
    }
}
