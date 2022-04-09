package ru.inovus.stateregnumber.interfaces.crud;

import org.springframework.data.repository.CrudRepository;
import ru.inovus.stateregnumber.models.Series;

import java.util.Optional;

/*
 * Интерфейс для работы с таблицей БД series
 * @author radik
 * @version 1.0
 */
public interface SeriesModelRepositoris extends CrudRepository<Series,Long> {
    /*
     * Метод для получения серии по номерам букв
     * @return Везвращает объек класса Series обернутый в класс Optional
     * @author radik
     * @version 1.0
     */
    Optional<Series>  findByLettersSeries1AndLettersSeries2AndLettersSeries3(int lettersSeries1,
                                                                             int lettersSeries2,
                                                                             int lettersSeries3);
}
