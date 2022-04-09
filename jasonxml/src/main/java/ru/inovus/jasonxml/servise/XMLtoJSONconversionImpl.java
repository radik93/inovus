package ru.inovus.jasonxml.servise;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.inovus.jasonxml.servise.interfase.XMLtoJSONconversion;
import ru.inovus.jasonxml.utils.FileWrite;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

@Service
public class XMLtoJSONconversionImpl implements XMLtoJSONconversion {
    @Override
    public void convertFileXMLtoJSON(MultipartFile multipartFile) throws ParserConfigurationException, SAXException, IOException {
        FileWrite.cleanFile();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(multipartFile.getInputStream(), handler);

    }
}
