package ru.inovus.jasonxml.servise.interfase;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/*
 * Интерфейс для конвертации XML в JSON
 * @author radik
 * @version 1.0
 */
public interface XMLtoJSONconversion {

       /*
        * Метод принимает загруженый файл и порциями считывает его конвертирует
        * @author radik
        * @version 1.0
        */
       void convertFileXMLtoJSON(MultipartFile multipartFile) throws ParserConfigurationException, SAXException, IOException;

}
