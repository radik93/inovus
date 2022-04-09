package ru.inovus.stateregnumber;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.inovus.stateregnumber.models.Number;

public class TestModels {


    private Number number0;
    private Number number1;
    private Number number2;

    private int num0;
    private int num1;
    private int num2;

    @Before
    public void setParam() {
        num0 = 0;
        num1 = 0;
        num2 = 2;

        number0=new Number(num0);
        number1=new Number(num1);
        number2=new Number(num2);
    }


    @Test
    public void compareNumberTrue(){
        Assert.assertEquals(number0, number1);
    }

    @Test
    public void compareNumberFalse(){
        Assert.assertEquals(number0, number2);
    }
}
