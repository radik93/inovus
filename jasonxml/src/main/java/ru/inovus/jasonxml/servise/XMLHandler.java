package ru.inovus.jasonxml.servise;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import ru.inovus.jasonxml.utils.FileWrite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private StringBuffer partXML;

    private int pageCount=0;

    private int count = 1000;

    private String firstNode;

    private List<String> nodes = new ArrayList<>();

    private List<String> delNodes = new ArrayList<>();

    private JSONObject jsonObject;


    private String temp;

    public XMLHandler(){
        partXML= new StringBuffer();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        pageCount++;
        nodes.add(qName);
        firstNode=nodes.get(0);
        partXML.append("<" + qName + ">");
        for(int i = 0; i < attributes.getLength(); i++)
            partXML.append("<" + attributes.getQName(i) +
                    ">"+attributes.getValue(i)+"</" +
                    attributes.getQName(i) + ">");
}

    @Override
    public void endElement(String uri, String localName, String qName) {
        partXML.append("</" + qName + ">");
        nodes.remove(qName);
        if(pageCount>count||firstNode.equals(qName))
        {
                convertJSON();
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
       String information = new String(chars, start, length);
        partXML.append(information);
    }

    public void convertJSON()  {

        for(int i = nodes.size()-1; i > -1; i--)
        {
            partXML.append("</" + nodes.get(i) + ">");
        }

        jsonObject = XML.toJSONObject(partXML.toString());

        partXML= templatePreparation();

        pageCount=0;
        delNodes=nodes;

        FileWrite.writeJSONFile(partJSON());
    }

    private StringBuffer templatePreparation()
    {
        StringBuffer templateXML = new StringBuffer();
        for(int i = 0; i < nodes.size(); i++)
        {
            templateXML.append("<" + nodes.get(i) + ">");
        }
        return templateXML;
    }

    private String partJSON()
    {
        temp=jsonObject.toString();

        temp = temp.replace ("{\""+ firstNode +"\":", "");
        for(int i = 1; i < delNodes.size(); i++)
        {
            temp = temp.replace ("{\""+ delNodes.get(i) +"\":", "");
        }
        return temp;
    }
}
