package ru.inovus.jasonxml.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.inovus.jasonxml.servise.interfase.XMLtoJSONconversion;
import ru.inovus.jasonxml.utils.FileWrite;
import ru.inovus.jasonxml.utils.FileXMLValidator;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;



@Controller
public class MainController {


    @Autowired
    FileXMLValidator fileXMLValidator;

    @Autowired
    XMLtoJSONconversion xmLtoJSONconversion;


    @GetMapping("/index")
    public String indexPage(@ModelAttribute("model") ModelMap model) {
        FileWrite.cleanFile();
        setModelParam(model,"");
       return "index";
    }


    @PostMapping("/upload")
    public String  handleFileUpload(@RequestParam("file") MultipartFile multipartFile, @ModelAttribute("model") ModelMap model){
        if(fileXMLValidator.validate(multipartFile)) {
            try {
                xmLtoJSONconversion.convertFileXMLtoJSON(multipartFile);
            } catch (ParserConfigurationException e) {
                errWrite(e.getMessage());
            } catch (SAXException e) {
                errWrite(e.getMessage());
            } catch (IOException e) {
                errWrite(e.getMessage());
            }
        }
        else {
            errWrite("File type not validate");
        }
        setModelParam(model,FileWrite.textOut());
      return "index";
    }

    private void errWrite(String errMes)
    {
        FileWrite.cleanFile();
        FileWrite.writeErr(errMes);
    }

    private ModelMap setModelParam(ModelMap model, String outText)
    {
       return model.addAttribute("outText", outText);
    }
}
