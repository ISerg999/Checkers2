package ru.ISerg999.checkers.checkersEngine.intermediates;

/**
 * Интерфейс конвертация данных из строки в объект и обратно.
 */
public interface DataConversion {
    /**
     * Возвращает строку с текущими данными.
     * @return строка с текущими данными
     */
    String dataToStr();

    /**
     * Восстанавливает данные из заданной строки.
     * @param strData строка с данными
     * @return true - если восстановление прошло успешно, иначе false
     */
    boolean dataFromStr(String strData);
}
