package ru.inovus.stateregnumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inovus.stateregnumber.interfaces.servise.GrateGosNumber;
import ru.inovus.stateregnumber.interfaces.servise.SeriesServise;
import ru.inovus.stateregnumber.models.Number;
import ru.inovus.stateregnumber.models.Series;
import ru.inovus.stateregnumber.utilities.GrateLetters;
import ru.inovus.stateregnumber.utilities.GratePerformance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GrateGosNumberImpl implements GrateGosNumber {
    private int serA=0;
    private int serB=0;
    private int serC=0;
    private int gosNum=0;
    private Series series;
    private Number number;
    List<Number> numbers=new ArrayList<>();

    @Autowired
    private SeriesServise seriesServise;



    @Override
    public String randomSeries() {
        Random random = new Random();
        serA = random.nextInt(GrateLetters.lettersSeriesSize());
        serB = random.nextInt(GrateLetters.lettersSeriesSize());
        serC = random.nextInt(GrateLetters.lettersSeriesSize());
        gosNum=(int)(1 + (Math.random() * (999 - 1)));
        return generatorNextGosNomber();
    }

    @Override
    public String nextSeries() {
        return generatorNextGosNomber();
    }

    private String generatorNextGosNomber()
    {
        series = seriesServise.getSeries(serA,serB,serC);

        if(series==null)
        {
            addNewSeriesInDB(serA,serB,serC,gosNum);
        }
        else
        {
            getSeriesNumber(series);
            if(grateNextNumber(series)) {
                number = new Number(gosNum);
                number.setNid(grateNid(series.getId()));
                number.setSeries(series);
                series.getNumbers().add(number);
                seriesServise.addNumber(number);
            }
        }
   return GratePerformance.getPerformance(serA,serB,serC,gosNum);
    }

    private void addNewSeriesInDB(int serA, int serB, int serC, int gosNum)
    {
        List<Number> numbers = new ArrayList<>();
        series=new Series(serA,serB,serC);
        number=new Number(gosNum);
        number.setNid(grateNid(series.getId()));
        numbers.add(number);
        series.setNumbers(numbers);
        seriesServise.saveSeries(series);
    }

    private boolean grateNextNumber(Series series)
    {
        numbers = series.getNumbers();
        while (numbers.contains(new Number(gosNum)))
        {
            gosNum++;
            if(gosNum==1000)
            {
                if(grateNextSeries())
                {
                    return false;
                }
            }

        }
        return true;
    }

    private void getSeriesNumber(Series series)
    {
        serA=series.getLettersSeries1();
        serB=series.getLettersSeries2();
        serC=series.getLettersSeries3();
    }

    private boolean grateNextSeries()
    {
            serC++;
            if (serC > GrateLetters.lettersSeriesSize()) {
                serC = 0;
                serB++;
                if (serB > GrateLetters.lettersSeriesSize()) {
                    serB = 0;
                    serA++;
                    if (serA > GrateLetters.lettersSeriesSize()) {
                        serA=0;
                    }
                }

            }
        if(seriesServise.seriesIsFull(serA,serB,serC))
        if(true)
        {
            grateNextSeries();
        }
           series=seriesServise.getSeries(serA,serB,serC);
            if(series==null)
            {
                gosNum=0;
                addNewSeriesInDB(serA,serB,serC,gosNum);
                return true;
            }
            else
            {
                numbers= series.getNumbers();
                gosNum=0;
                return false;
            }
    }

    private Long grateNid(long id)
    {
        return id*1000+gosNum;
    }
}
