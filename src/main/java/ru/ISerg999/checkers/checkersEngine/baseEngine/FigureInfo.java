package ru.ISerg999.checkers.checkersEngine.baseEngine;

import java.util.List;

/**
 * Интерфейс общей информации о фигурах.
 */
public interface FigureInfo {
    /**
     * Получение максимального количества цветов игровых фигур
     * @return максимальное количество цветов
     */
    int maxNumberColorFigures();

    /**
     * Получение максимального количества видов фигур.
     * @return максимальное количество видов фигур
     */
    int maxNumberFigures();

    /**
     * Список всех вожможных состояний поля на игровой доске.
     * @return cписок всех вожможных состояний.
     */
    List<Integer> allAvailableFieldStates();

    /**
     * На основе цвета данной фигуры полцчает следующий активный цвет.
     * @param figure фигура или цвет текущей активности
     * @return следующий активный цвет
     */
    int nextColorFigures(int figure);

    /**
     * На основе заданной фигуры получает её цвте.
     * @param figure код фигуры
     * @return цвет фигуры
     */
    int colorGivenFigure(int figure);

    /**
     * На основе заданной фигуры получает её вид.
     * @param figure код фигуры
     * @return вид фигуры
     */
    int typeGivenFigure(int figure);
}
