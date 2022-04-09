package ru.inovus.stateregnumber.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inovus.stateregnumber.interfaces.crud.NumberModelRepositoris;
import ru.inovus.stateregnumber.interfaces.crud.SeriesModelRepositoris;
import ru.inovus.stateregnumber.interfaces.servise.SeriesServise;
import ru.inovus.stateregnumber.models.Number;
import ru.inovus.stateregnumber.models.Series;


@Service
public class SeriesServiseImpl implements SeriesServise {

    @Autowired
    private SeriesModelRepositoris seriesModelRepositoris;

    @Autowired
    NumberModelRepositoris numberModelRepositoris;




    @Override
    public void saveSeries(Series series)
    {

        for (Number number:series.getNumbers())
        {
            numberModelRepositoris.save(number);
        }
        seriesModelRepositoris.save(series);
    }

    @Override
    public Series getSeries(int serA, int serB, int serC)
    {
        return seriesModelRepositoris.findByLettersSeries1AndLettersSeries2AndLettersSeries3(
                serA,
                serB,
                serC
        ).orElseGet(()->null);
    }

    @Override
    public void addNumber(Number number){
         numberModelRepositoris.save(number);
    }

    @Override
    public boolean seriesIsFull(int serA, int serB, int serC){
        return getSeries(serA,serB,serC).getNumbers().size()==1000;
    }

}
