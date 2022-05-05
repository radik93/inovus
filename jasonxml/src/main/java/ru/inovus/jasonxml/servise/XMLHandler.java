package ru.inovus.jasonxml.servise;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import ru.inovus.jasonxml.utils.FileWrite;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private int pageCount=-1;

    private int count = 0;

    private int amountBegin = 0;

    private int amountEnd = 0;

    private List<String> nodes  = new ArrayList<>();

    private List<Float> summa   = new ArrayList<>();

    private List<Integer> level = new ArrayList<>();

    private List<Long> topPoss = new ArrayList<>();


    private boolean readValue=false;


    float temp = 0;


    private int lastNode=-1;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        nodes.add(qName);
        pageCount++;
        readValue=true;
        count=count+2;
        count++;
        level.add(count);
        amountBegin++;
}

    @Override
    public void endElement(String uri, String localName, String qName) {

        amountEnd++;
        readValue=false;
        int index;
        index=nodes.indexOf(qName);

        addIndents();

        if(count<=level.get(level.size()-1)) {
            summNodesValue();
            count--;
        }

        grateText(level.get(level.size()-1),summa.get(index),qName, index,level.size());

        delUsedElem(qName, index);
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        if(readValue)
        grateListNumber(getNumber(new String(chars, start, length)));

    }




    private Float getNumber(String information)
    {
        float numbers=0;

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {

            information = information.replaceAll("\\D+","");
            try {
                numbers = Float.parseFloat(information);
            }
            catch(NumberFormatException e){
                numbers=0;
            }

        }
        return numbers;
    }

    private void grateListNumber(Float number)
    {
        if (lastNode!=pageCount)
        {
            summa.add(number);
            lastNode=pageCount;
        }
        else
        {
            temp=summa.get(pageCount)+number;
            summa.set(pageCount,temp);
        }
    }

    private void summNodesValue()
    {
       for (int i=summa.size()-2;i>=0;i--)
        {
            summa.set(i,summa.get(i)+summa.get(summa.size()-1));
        }
    }

    private void grateText(int lineNumber,float value, String qName, int index, int numberOfSpaces)
    {
    if(lineNumber>count)
    {
     FileWrite.writeFile(grateJSONformat(qName,
             value, false,numberOfSpaces)
             , FileWrite.getSizeFile());
    }
    else
    {
        FileWrite.writeFile(grateJSONformat(qName,
                value, true,numberOfSpaces)
                ,topPoss.get(index));
        topPoss.remove(index);

                FileWrite.writeFile(grateNumberOfSpaces(numberOfSpaces)+
                      "}\n",  FileWrite.getSizeFile());
    }

    }

    private void addIndents()
    {
        for(int i=0;i<amountBegin-amountEnd;i++)
        {
            topPoss.add(FileWrite.getSizeFile());
            FileWrite.addEmpty();
        }
        amountBegin=0;
        amountEnd=0;
    }

    private void delUsedElem(String qName, int index)
    {
        if(pageCount==(level.size()-1))
        {
            level.remove(pageCount);
        }
        nodes.remove(qName);
        summa.remove(index);
        pageCount--;
        lastNode--;
    }

    private String grateJSONformat(String qName, float value, boolean parentNode, int numberOfSpaces)
    {
        String temp;
        temp=grateNumberOfSpaces(numberOfSpaces)+"\""+qName+"\":{";
        if(parentNode)
            temp+="\n"+grateNumberOfSpaces(numberOfSpaces)+"\"value\":\""+value+"\",";
        else
            temp+="\"value\":\""+value+"\"},\n";
        return temp;
    }

    private String grateNumberOfSpaces(int numberOfSpaces)
    {
        String temp="";
        for (int i=0;i<numberOfSpaces;i++)
            temp+="   ";
            return temp;
    }


}
