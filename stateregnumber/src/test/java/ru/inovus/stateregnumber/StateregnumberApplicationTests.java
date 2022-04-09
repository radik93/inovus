package ru.inovus.stateregnumber;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.inovus.stateregnumber.interfaces.servise.SeriesServise;
import ru.inovus.stateregnumber.models.Series;
import ru.inovus.stateregnumber.models.Number;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StateregnumberApplicationTests {

    private int serA;
    private int serB;
    private int serC;

    @Autowired
    private SeriesServise seriesServise;

    private Series series;

    private int nid;


    private Number number1;

    private List<Number> numbers = new ArrayList<>();




    public void SetParam()
    {
        serA=0;
        serB=0;
        serC=0;
        number1=new Number(0);
        nid=serA*10000000+serB*100000+serC*1000;
        number1.setNid((long) nid);
        numbers.add(number1);
        series=new Series(serA,serB,serC);
        series.setNumbers(numbers);
    }
    @Test
    public void contextLoads() {
    }

    @Test
    public void saveSeries()
    {
        SetParam();
        seriesServise.saveSeries(series);
    }

    @Test
    public void getSeries()
    {
        series=seriesServise.getSeries(serA, serB, serC);
        System.out.println("Номер первой буквы"+series.getLettersSeries1());
        System.out.println("Номер второй буквы"+series.getLettersSeries2());
        System.out.println("Номер третей буквы"+series.getLettersSeries3());
        for (Number number:series.getNumbers())
        {
            System.out.println("Номер в серии "+number.getNum());
        }
    }
}
