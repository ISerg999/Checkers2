package ru.ISerg999.checkers;

import ru.ISerg999.checkers.utils.CPair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * истема конечных автоматов.
 */
public class FiniteAutomatonSystem {
    // ---------------------------------------------------- Public -----------------------------------------------------

    /**
     * Конструктор.
     * @param state           базовое состояние
     * @param fullStates      список возможных состояний
     * @param fullActions     список возможных действий
     * @param actionPerformed таблица конечных автоматов
     */
    public FiniteAutomatonSystem(String state, List<String> fullStates, List<String> fullActions, Map<CPair<String, String>, String[]> actionPerformed) {
        this.state = state;
        this.fullStates = fullStates;
        this.fullActions = fullActions;
        this.actionPerformed = actionPerformed;
        this.mapObjects = new HashMap<>();
        stackAction = new Stack<>();
    }

    /**
     * Установка словаря обрабатываемых объектов.
     * @param mapObjects словарь названий объектов и соответственно самих объектов, которые будут выполнять действия
     */
    public void setMapObjects(Map<String, Object> mapObjects) {
        this.mapObjects = mapObjects;
    }

    public String getState() { return state; }

    /**
     * Выполнение действий с учётом таблицы конечных автоматов.
     * @param action выполняемое действие
     */
    public void takeAction(String action) {
        String[] selectionA, selectionB;
        String newState;
        stackAction.push(action);
        while (!stackAction.isEmpty()) {
            action = stackAction.pop();
            if (!fullActions.contains(action)) continue;
            selectionA = null;
            selectionB = actionPerformed.getOrDefault(new CPair<>(fullStates.get(0), action), null);
            if (!fullStates.get(0).equals(state)) {
                selectionA = actionPerformed.getOrDefault(new CPair<>(state, action), null);
            }
            newState = state;
            if (null != selectionB && selectionB.length > 1) {
                newState = null == selectionB[0] || fullStates.get(0).equals(selectionB[0])? newState: selectionB[0];
                commonMethod = selectionB[1];
                runActions(selectionB);
            }
            if (null != selectionA && selectionA.length > 1) {
                newState = null == selectionA[0] || fullStates.get(0).equals(selectionA[0])? newState: selectionA[0];
                commonMethod = selectionA[1];
                runActions(selectionA);
            }
            state = newState;
        }
    }

    // ---------------------------------------------------- Private ----------------------------------------------------

    // Список всех возможных состояний.
    private List<String> fullStates;
    // Список всех возможных действий.
    private List<String> fullActions;
    // Список исполняемых объектов.
    private Map<String, Object> mapObjects;
    // Словарь конечных автоматов. Ключ - пара значений: текущее состояние, команда.
    // Результат - список строк, у которых 0-ый элемент - новое состояние, 1 - ый элемент это общее название выполняемого метода,
    // дальше указываются объекты и методы, которые должны выполниться.
    // Каждый элемент, который должен выполниться моежт иметь формат:
    // * имя_объекта: - выполняется общий, указанный, метод для объекта заданного "имя_объекта".
    // * имя_объекта:метод - выполняется "метод" для объекта заданного "имя_объекта".
    // * :команда - команда таблицы конечного автомата, выполняется после того, как выполнятся все обычные команды объектов и система конечных автоматов определит новый статус.
    private Map<CPair<String, String>, String[]> actionPerformed;
    // Теукущее состояние.
    private String state;
    // Стек ввыполнения команд.
    private Stack<String> stackAction;
    // Общий метод.
    private String commonMethod;

    /**
     * Выполнение команд из заданных классов.
     * @param mAction список класс:метод начиная со 2-го элемента
     */
    private void runActions(String[] mAction) {
        if (null == mAction || mAction.length < 3) return;
        String cmd;
        String[] tmpList;
        Object obj;
        for (int i = 2; i < mAction.length; i++) {
            cmd = mAction[i];
            if (null == cmd) continue;
            tmpList = parser(cmd);
            if (null == tmpList) continue;
            if (null == tmpList[0]) {
                stackAction.push(tmpList[1]);
            } else {
                obj = mapObjects.getOrDefault(tmpList[0], null);
                if (null == obj) continue;
                try {
                    Method method = obj.getClass().getDeclaredMethod(tmpList[1]);
                    method.setAccessible(true);
                    method.invoke(obj);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Парсинг.
     * @param nameAction строка которую парсит
     * @return результат парсинга
     */
    private String[] parser(String nameAction) {
        if (null == nameAction || nameAction.isEmpty()) return null;
        String[] res = new String[2];
        res[0] = null;
        res[1] = null;
        if (':' == nameAction.charAt(0)) {
            res[1] = nameAction.substring(1);
            return res;
        } else {
            if (':' == nameAction.charAt(nameAction.length() - 1)) {
                res[0] = nameAction.substring(0, nameAction.length() - 1);
                res[1] = commonMethod;
            } else {
                res = nameAction.split(":");
            }
        }
        if (!mapObjects.containsKey(res[0])) return null;
        return res;
    }
}
