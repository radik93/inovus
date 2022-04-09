package ru.inovus.jasonxml.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileXMLValidator {
    private final String FILE_TYPES = "text/xml";

    public boolean validate(MultipartFile file) {
        return FILE_TYPES.contains(file.getContentType());
    }
}
