package ru.ISerg999.checkers.configs;

import ru.ISerg999.checkers.FiniteAutomatonSystem;
import ru.ISerg999.checkers.checkersEngine.FigureInfoImp;
import ru.ISerg999.checkers.utils.CPair;
import ru.ISerg999.checkers.utils.UtilsCollection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс Возвращающий готовые объекты и их конфигурирования.
 */
public class CheckersConfig {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public static CheckersConfig getInst() { return CheckersConfigHolder.INSTANCE; }

    /**
     * Возвращает паур целых значений
     * @param x первое значение
     * @param y второе значение
     * @return пара целых значений
     */
    public CPair<Integer, Integer> intPair(int x, int y) { return new CPair<>(x, y); }

    /**
     * Возвращает объект утилит.
     * @return объект утилит
     */
    public UtilsCollection getUtils() { return utilsCollection; }

    /**
     * Возвращает объект конечных автоматов.
     * @return объект конечных автоматов
     */
    public FiniteAutomatonSystem finiteAutomatonSystem() {
        if (null == finiteAS) {
            finiteAS = new FiniteAutomatonSystem(
                    automationSystemStates.get(0),
                    automationSystemStates,
                    automationSystemActions,
                    automationSystemActionPerformed()
            );
        }
        return finiteAS;
    }

    /**
     * Возвращает объект основной информации о фигурах.
     * @return объект основной информации о фигурах
     */
    public FigureInfoImp figureInfo() { return figureInfoImp; }

    // ---------------------------------------------------- Private ----------------------------------------------------

    // Объект коллекции утилит.
    private final UtilsCollection utilsCollection = new UtilsCollection();
    // Список возможных состояний для конечного автомата.
    private final List<String> automationSystemStates = Arrays.asList(
            "None",         // Состояние в момент начальной инициализации. А также указывает, либо действие состояние не меняет, либо что действие выполняется при любом состоянии.
            "Base",         // Базовое состояние.
            "Editing",      // Состояние редактирования игровой доски.
            "Game"          // Состояние игры в шашки.
    );
    // Список возможных команд для конечного автомата.
    private final List<String> automationSystemActions = Arrays.asList(
//                "ToInit",                   // Базовая инициализация.
//                "ToBase",                   // Переход в базовое состояние.
//                "SetBoardBase",             // Делает текущую доску базовой.
//                "ToAbout",                  // Вывод модального диалогового окна о программе.
//                "ToExit",                   // Выход из игры.
//                "ToBoardBase",              // Базовое расположениедоски.
//                "SelectedStepWhite",        // Выбор хода за белых.
//                "SelectedStepBlack",        // Выбор хода за чёрных.
//                "ToEditing",                // Переход в режим редактирования игровой доски.
//                "SetBasicPosition",         // Установка фигур в базовое расположение.
//                "ToBoardClear",             // Очистка игровой доски.
////                "ToLoad",                     // Загрузка игрового состояния.
////            "ToLoadOk",                   // Сообщение о завершении загрузки игрового состоняия.
////                "ToSave",                     // Сохранение игрового состояния.
////            "ToSaveOk",                   // Сообщение о завершении сохранении игрового состояния.
////                "ToExitEditing"
////                "StepsGameBackspace",         // Откат на 1 шаг назад.
////                "ToGameStart",                // Переход в режим игры с очисткой списка ходов.
////                "ToGameContinue",             // Переход в режим игры с продолжением предыдущей игры.
////            "ToNextStepGame",             // Переход к следующему ходу.
////            "ToComputerGameStep",         // Передать ход компьютеру.
////                "ToGameStoppage",             // Остановка игры.
////            "ToEndGameDraw",              // Окончание игры. Ничья.
////            "ToEndGameWhile",             // Окончание игры. Белые выйграли.
////            "ToEndGameBlack",             // Окончание игры. Чёрные выйграли.
////                "SelectedWhitePlayer",        // Выбор ходов за белых для пользователя.
////                "SelectedWhiteComp",          // Выбор ходов за белых для компьютера.
////                "SelectedBlackPlayer",        // Выбор ходов за чёрных для пользователя.
////                "SelectedBlackComp"           // Выбор ходов за чёрных для компьютера.
        "---"
    );
    private static class CheckersConfigHolder {
        private static final CheckersConfig INSTANCE = new CheckersConfig();
    }
    private CheckersConfig() {}

    /**
     * Создаёт таблицу действий конечного автомата.
     * @return таблица действий
     */
    private final Map<CPair<String, String>, String[]> automationSystemActionPerformed() {
        // Словарь конечных автоматов. Ключ - пара значений: текущее состояние, команда.
        // Результат - список строк, у которых 0-ый элемент - новое состояние, 1 - ый элемент это общее название выполняемого метода,
        // дальше указываются объекты и методы, которые должны выполниться.
        // Каждый элемент, который должен выполниться моежт иметь формат:
        // * имя_объекта: - выполняется общий, указанный, метод для объекта заданного "имя_объекта".
        // * имя_объекта:метод - выполняется "метод" для объекта заданного "имя_объекта".
        // * :команда - команда таблицы конечного автомата, выполняется после того, как выполнятся все обычные команды объектов и система конечных автоматов определит новый статус.
        Map<CPair<String, String>, String[]> actionPerformed  = new HashMap<>();
        String tmp;
        // В состоянии None, либо для всех состояний.
        tmp = "None";
//        actionPerformed.put(new CPair<>(tmp, "ToInit"), new String[] { "None", "rightPanelGaming:initRightPanelGaming", "rightPanelEdition:initRightPanelEdition", "viewBoard:initBoard", "jMainWindow:initWindow", ":ToBase"});
//        actionPerformed.put(new CPair<>(tmp, "ToBase"), new String[] { "Base", "viewBoard:allToBase", "jMainWindow:allToBase"});
//        actionPerformed.put(new CPair<>(tmp, "ToExit"), new String[] { "None", "jMainWindow:exitWindow" });
//        actionPerformed.put(new CPair<>(tmp, "ToAbout"), new String[] { "None", "jMainWindow:viewDialogAbout" });
        // В состоянии Base
        tmp = "Base";
//        actionPerformed.put(new CPair<>(tmp, "ToBoardBase"), new String[] { "None", "jMainWindow:boardToBase", "viewBoard:rePaint"});
//        actionPerformed.put(new CPair<>(tmp, "SetBoardBase"), new String[] { "None", "jMainWindow:setBoardBase" });
//        actionPerformed.put(new CPair<>(tmp, "SelectedStepWhite"), new String[] { "None", "jMainWindow:stepToWhite" });
//        actionPerformed.put(new CPair<>(tmp, "SelectedStepBlack"), new String[] { "None", "jMainWindow:stepToBlack" });
//        actionPerformed.put(new CPair<>(tmp, "ToEditing"), new String[] { "Editing", "jMainWindow:toEdition", "viewBoard:toEdition"});
        // В состоянии Editing
        tmp = "Editing";
//        actionPerformed.put(new CPair<>(tmp, "SetBasicPosition"), new String[] { "None", "viewBoard:setBasicPosition" });
//        actionPerformed.put(new CPair<>(tmp, "ToBoardClear"), new String[] { "None", "viewBoard:boardClear" });
        // В состоянии Game
        tmp = "Game";

//////        tmpAction.put("ToSave", new String[] {"None", "JMainWindow:viewDialogSave", "CControl:saveBoard",});
//////        tmpAction.put("ToSaveOk", new String[] {"None", "CViewBoard:repaint", "JMainWindow:viewSaveFileOK",});
//////        tmpAction.put("ToLoad", new String[] {"None", "JMainWindow:viewDialogLoad", "CControl:loadBoard",});
//////        tmpAction.put("ToLoadOk", new String[] {"None", "CViewBoard:repaint", "JMainWindow:viewLoadFileOk",});
//////        tmpAction.put("SelectedWhitePlayer", new String[] {"None", "CControl:playWhiteFromPlayer", "JMainWindow:playWhiteFromPlayer",});
//////        tmpAction.put("SelectedWhiteComp", new String[] {"None", "CControl:playWhiteFromComp", "JMainWindow:playWhiteFromComp",});
//////        tmpAction.put("SelectedBlackPlayer", new String[] {"None", "CControl:playBlackFromPlayer", "JMainWindow:playBlackFromPlayer",});
//////        tmpAction.put("SelectedBlackComp", new String[] {"None", "CControl:playBlackFromComp", "JMainWindow:playBlackFromComp",});

//////        tmpAction.put("ToGameStart", new String[] {"Game", "JMainWindow:gameStart", "CControl:gameStart", ":ToNextStepGame",});
//////        tmpAction.put("ToGameContinue", new String[] {"Game", "JMainWindow:gameStart", "CControl:gameContinue", ":ToNextStepGame",});

//////        tmpAction.put("ToBoardPlacemant", new  String[] {"None", "CControl:toBoardPlacemant", "CViewBoard:rePaint",});
//////        tmpAction.put("ToBoardClear", new  String[] {"None", "CControl:toBoardClear", "CViewBoard:rePaint",});
//////        tmpAction.put("StepsGameBackspace", new  String[] {"None", "CControl:stepsGameBackspace", "CViewBoard:rePaint",});
//////        tmpAction.put("ToBase", new  String[] {"Base", "CControl:closeEdition", "JMainWindow:closeEdition",});

//////        tmpAction.put("ToNextStepGame", new String[] {"None", "CControl:nextStepGame", "JMainWindow:nextStepGame", "CViewBoard:nextStepGame",});
//////        tmpAction.put("StepsGameBackspace", new String[] {"None", "CControl:stepsGameBackspace", "CViewBoard:nextStepGame", "CRightPanelGaming:rePaint",});
//////        tmpAction.put("ToGameStoppage", new String[] {"Base", "CControl:stopGamePlay", "JMainWindow:gameStoppage", "CViewBoard:clearBoardSpacesColor", "CViewBoard:rePaint", "JMainWindow:allToBase",});
//////        tmpAction.put("ToEndGameDraw", new String[] {"Base", "JMainWindow:endGameDraw", "CViewBoard:clearBoardSpacesColor", "CViewBoard:rePaint", "JMainWindow:allToBase",});
//////        tmpAction.put("ToEndGameWhile", new String[] {"Base", "JMainWindow:endGameWhile", "CViewBoard:clearBoardSpacesColor", "CViewBoard:rePaint", "JMainWindow:allToBase",});
//////        tmpAction.put("ToEndGameBlack", new String[] {"Base", "JMainWindow:endGameBlack", "CViewBoard:clearBoardSpacesColor", "CViewBoard:rePaint", "JMainWindow:allToBase",});

////        actionPerformed.put(new CPair<>(tmp, ""), new String[] { });
        return actionPerformed;
    }
    // Объект конечных автоматов.
    private FiniteAutomatonSystem finiteAS = null;
    // Объект основной информации по игровым фигурам.
    private final FigureInfoImp figureInfoImp = new FigureInfoImp();
}
