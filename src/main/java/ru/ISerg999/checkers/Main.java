package ru.ISerg999.checkers;

import ru.ISerg999.checkers.configs.CheckersConfig;
import ru.ISerg999.checkers.utils.UtilsCollection;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new Main().init();
        System.out.println("Конец!");
    }

    // ---------------------------------------------------- Public -----------------------------------------------------

    /**
     * Базовая инициализация.
     */
    public void init() {
        UtilsCollection ui = CheckersConfig.getInst().getUtils();
        ui.setFileProperties("checkers.properties");
        // Загружаем в кэш изпользуемые изображения.
        ui.putImage(ui.getProperty("Path.Icon.Window"), ui.getProperty("Info.Icon.Window"));
        ui.putImage(ui.getProperty("Path.Image.Board"), ui.getProperty("Info.Image.Board"));
        ui.putImage(ui.getProperty("Path.Image.Figures"), ui.getProperty("Info.Image.Figures"));

        // Инициализируем систему конечных автоматов.
        Map<String, Object> mapObjFas = new HashMap<>();
        String[] tmp;
//        tmp = jMainWindow.getClass().getName().split("\\.");
//        mapObjFas.put(tmp[tmp.length - 1], jMainWindow);
//        tmp = viewBoard.getClass().getName().split("\\.");
//        mapObjFas.put(tmp[tmp.length - 1], viewBoard);
//        tmp = rightPanelEdition.getClass().getName().split("\\.");
//        mapObjFas.put(tmp[tmp.length - 1], rightPanelEdition);
//        tmp = rightPanelGaming.getClass().getName().split("\\.");
//        mapObjFas.put(tmp[tmp.length - 1], rightPanelGaming);
//        fAS.setMapObjects(mapObjFas);
//        fAS.takeAction("ToInit");

    }
    // ---------------------------------------------------- Private ----------------------------------------------------

}
