package com.marktplaats.assignment.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessor {

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());

        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);

            fos.write(file.getBytes());
            fos.close();
        } catch (IOException ioException) {
            log.info("Invalid file - %d", file.getName());
        }
        return convFile;
    }

    public static FileType type(File file) {
        try {
            String fileName = file.getName();
            return FileType.valueOf(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
        } catch (Exception exception) {
            log.info("Invalid file format - %d", file.getName());
        }
        return null;
    }

    public static FileType type(String fileName) {
        try {
            return FileType.valueOf(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
        } catch (Exception exception) {
            log.info("Invalid file format - %d", fileName);
        }
        return null;
    }
}
