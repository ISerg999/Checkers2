package ru.ISerg999.checkers.configs;

import ru.ISerg999.checkers.utils.CPair;
import ru.ISerg999.checkers.utils.UtilsCollection;

/**
 * Класс Возвращающий готовые объекты и их конфигурирования.
 */
public class CheckersConfig {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public static CheckersConfig getInst() { return CheckersConfigHolder.INSTANCE; }

    public CPair<Integer, Integer> intPair(int x, int y) { return new CPair<>(x, y); }
    public UtilsCollection getUtils() { return utilsCollection; }

    // ---------------------------------------------------- Private ----------------------------------------------------

    private UtilsCollection utilsCollection = new UtilsCollection();
    private static class CheckersConfigHolder {
        private static final CheckersConfig INSTANCE = new CheckersConfig();
    }
    private CheckersConfig() {}

}
