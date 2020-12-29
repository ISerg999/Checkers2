package ru.ISerg999.checkers.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UtilsCollection {

    // ---------------------------------------------------- Public -----------------------------------------------------

    public UtilsCollection() {
        cacheImage = new HashMap<>();
        property = new Properties();
    }

    /**
     * Очистка кэша изображений.
     */
    public void clearMapImages() { cacheImage.clear(); }

    /**
     * Преобразование строки числа в целое значение. Десятичные числа должны начинаться с # или 0x, двоичные числа должны начинаться с b.
     * @param numStr строка с числом.
     * @return целое, значение строки.
     */
    public int strToInt(String numStr) {
        if (null == numStr || numStr.isEmpty()) return 0;
        int nSign = 1;
        numStr = numStr.toLowerCase();
        if ('-' == numStr.charAt(0)) {
            nSign = -1;
            numStr = numStr.substring(1);
        }
        int base = 10;
        if ('#' == numStr.charAt(0)) {
            base = 16;
            numStr = numStr.substring(1);
        } else if (numStr.length() > 2 && "0x".equals(numStr.substring(0, 2))) {
            base = 16;
            numStr = numStr.substring(2);
        } else if ('b' == numStr.charAt(0)) {
            base = 2;
            numStr = numStr.substring(1);
        }
        return nSign * Integer.parseInt(numStr, base);
    }

    /**
     * Преобразование кодировки для файла свойств.
     * @param str строка из файла свойств
     * @return строка на русском языке
     */
    public String convertCoding(String str) {
        String res = "";
        try {
            res = new String(str.getBytes("ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Возвращает иконку на основе ключа кэша.
     * @param nameKey ключ имени иконки
     * @return изображение иконки, или null
     */
    public final ImageIcon getImage(String nameKey) {
        return cacheImage.getOrDefault(nameKey, null);
    }

    /**
     * Добавление в кэш изображения, или группу изображений.
     * @param nameImage   путь с именем к изображению
     * @param paramImages строка состоящая из имени ключа в кэше и параметров изображения или для группы изображений
     */
    public void putImage(String nameImage, String paramImages) {
        String strKey;
        int x, y, w, h;
        BufferedImage bImg;
        Graphics g;
        if (null == nameImage || nameImage.isEmpty() || null == paramImages || paramImages.isEmpty()) return;
        String fullPathName = getClass().getClassLoader().getResource(nameImage).getPath();
        ImageIcon fullImage = new ImageIcon(fullPathName);
        String[] mParameters = paramImages.split(" ");
        for (int i = 0; i < mParameters.length; ) {
            strKey = mParameters[i++];
            x = strToInt(mParameters[i++]);
            y = strToInt(mParameters[i++]);
            w = strToInt(mParameters[i++]);
            h = strToInt(mParameters[i++]);
            bImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            g = bImg.getGraphics();
            g.drawImage(fullImage.getImage(), 0, 0, w, h, x, y, x + w, y + h, null);
            g.dispose();
            cacheImage.put(strKey, new ImageIcon(bImg));
        }
    }

    /**
     * Задание файла свойств.
     * @param fileProperties файл свойств
     */
    public void setFileProperties(String fileProperties) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(basePath + fileProperties);
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public String getProperty(String key) { return property.getProperty(key); }

    // ---------------------------------------------------- Private ----------------------------------------------------

    // Кэш запрашиваемых изображений.
    private Map<String, ImageIcon> cacheImage;
    // Свойства.
    private Properties property;
    private final String basePath = "src/main/resources/";

}
