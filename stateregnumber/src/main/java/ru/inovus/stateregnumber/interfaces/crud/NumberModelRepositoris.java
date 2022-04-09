package ru.inovus.stateregnumber.interfaces.crud;

import org.springframework.data.repository.CrudRepository;
import ru.inovus.stateregnumber.models.Number;

/*
 * Интерфейс для работы с таблицей БД numbers
 * @author radik
 * @version 1.0
 */
public interface NumberModelRepositoris extends CrudRepository<Number,Long> {

}
