package ru.inovus.stateregnumber.interfaces.servise;

import ru.inovus.stateregnumber.models.Number;
import ru.inovus.stateregnumber.models.Series;


/*
 * Интерфейс для создания и получения Серии и номеров гос. номера
 * @author radik
 * @version 1.0
 */
public interface SeriesServise {
    /*
     * Метод принимает объект класса Series и сохраняет его
     * @author radik
     * @version 1.0
     */
    void saveSeries(Series series);

    /*
     * Метод принимает номера букв серии и запрашивает объект Series из БД
     * @return объект Series c дочерними объектами Number
     * @author radik
     * @version 1.0
     */
    Series getSeries(int serA, int serB, int serC);

    /*
     * Метод принимает в качетсве параметра объект класса Number и записывает его в БД
     * @author radik
     * @version 1.0
     */
    void addNumber(Number number);

    /*
     * Метод принимает номера букв серии и запрашивает объект Series из БД
     * проверяет есть ли еще не заполнены ли номера в серии
     * @return true если номера заполнены (все 1000 заняты) false если есть возможность добавить еще
     * @author radik
     * @version 1.0
     */
    boolean seriesIsFull(int serA, int serB, int serC);
}
