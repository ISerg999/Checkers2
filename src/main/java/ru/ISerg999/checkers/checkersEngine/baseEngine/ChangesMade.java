package ru.ISerg999.checkers.checkersEngine.baseEngine;

import ru.ISerg999.checkers.checkersEngine.intermediates.DataConversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс проделанных изменений на игровой доске.
 */
public class ChangesMade implements DataConversion {
    // ---------------------------------------------------- Public -----------------------------------------------------

    public ChangesMade() {
        changes = new ArrayList<>();
        sep = '#';
        clear();
    }
    public ChangesMade(char sep) {
        changes = new ArrayList<>();
        this.sep = sep;
        clear();
    }
    public ChangesMade(String strChanges, char sep) {
        changes = new ArrayList<>();
        this.sep = sep;
        clear();
        dataFromStr(strChanges);
    }
    public ChangesMade(ChangesMade other) {
        changes = new ArrayList<>();
        changes.addAll(other.changes);
        sep = other.sep;
        curPos = other.curPos;
        maxPos = other.maxPos;
    }

    /**
     * Очистка всех изменений.
     * @return ссылка на текущий объект
     */
    public ChangesMade clear() {
        changes.clear();
        curPos = -1;
        maxPos = 0;
        return this;
    }

    /**
     * Возвращает содержимое текущего изменения.
     * @return текущее изменение
     */
    public String getCurChange() {
        if (curPos >= 0) return changes.get(curPos);
        return null;
    }

    public char getSep() { return sep; }

    /**
     * Добавление нового изменения.
     * @param change новое изменение
     * @return ссылка на текущий объект
     */
    public ChangesMade add(String change) {
        curPos += 1;
        if (curPos == changes.size()) changes.add(change);
        else changes.set(curPos, change);
        maxPos = curPos + 1;
        return this;
    }

    /**
     * Откат на одно изменение назад.
     * @return true, если получилось, иначе false
     */
    public boolean undo() {
        if (curPos < 0) return false;
        curPos -= 1;
        return true;
    }

    /**
     * Возврат на одно изменение вперед.
     * @return true, если получилось, иначе false
     */
    public boolean redo() {
        if (curPos + 1 >= maxPos) return false;
        curPos += 1;
        return true;
    }

    public final List<String> getChanges() { return changes.subList(0, curPos + 1); }

    @Override
    public String dataToStr() {
        if (curPos < 0) return null;
        String res = "" + changes.get(0);
        for (String elem: changes.subList(1, curPos + 1)) {
            res = res + sep + elem;
        }
        return res;
    }

    @Override
    public boolean dataFromStr(String strData) {
        if (null == strData || strData.isEmpty()) return false;
        List<String> newChanges = Arrays.asList(strData.split(String.valueOf(sep)));
        int i = 0;
        curPos += 1;
        while (curPos < changes.size() && i < newChanges.size()) {
            changes.set(curPos + i, newChanges.get(i));
            i += 1;
            curPos += 1;
        }
        newChanges = newChanges.subList(i, newChanges.size());
        if (newChanges.size() > 0) {
            changes.addAll(newChanges);
            curPos = changes.size() - 1;
        }
        maxPos = curPos + 1;
        return true;
    }

    // ---------------------------------------------------- Private ----------------------------------------------------

    // Список проделанных изменений.
    private List<String> changes;
    // Текущая позиция проделанных изменений и максимальное число изменений в списке.
    private int curPos, maxPos;
    // Разделитель строк.
    private char sep;
}
