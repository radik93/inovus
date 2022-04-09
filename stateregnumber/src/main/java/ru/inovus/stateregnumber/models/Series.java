package ru.inovus.stateregnumber.models;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Table(name="series")
@NamedQuery(name = "Series.getSeries", query = "select series from Series series " +
        "where series.lettersSeries1 = :lettersSeries1 " +
        "and series.lettersSeries2=:lettersSeries2 " +
        "and series.lettersSeries3=:lettersSeries3")
@Entity
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "lettersSeries1")
    private int lettersSeries1;
    @Column(name = "lettersSeries2")
    private int lettersSeries2;
    @Column(name = "lettersSeries3")
    private int lettersSeries3;

    public Series(int lettersSeries1, int lettersSeries2, int lettersSeries3)
    {
        this.lettersSeries1=lettersSeries1;
        this.lettersSeries2=lettersSeries2;
        this.lettersSeries3=lettersSeries3;
    }

    public Series()
    {

    }

    @OneToMany(targetEntity=Number.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name="series_id")
    private List<Number> numbers;



}
