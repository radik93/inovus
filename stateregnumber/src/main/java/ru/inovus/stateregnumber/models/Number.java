package ru.inovus.stateregnumber.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Table(name="numbers")
@Entity
public class Number {
    @Id
    private Long nid;

    @Column(name = "num", length = 3)
    private int num;


    @ManyToOne(targetEntity=Series.class)
    @JoinColumn(name="series_id")
    Series series;


    public Number(int num)
    {
        this.num=num;
    }

    public Number()
    {

    }

    @Override
    public boolean equals(Object obj) {
        Number number = (Number) obj;
        return this.num == number.num;
    }
}
