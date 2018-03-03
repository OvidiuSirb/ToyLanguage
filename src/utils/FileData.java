package utils;

import java.io.BufferedReader;

public class FileData{
    private String fileName;
    private BufferedReader reader;

    public FileData(String givenName, BufferedReader givenReader) {
        fileName = givenName;
        reader = givenReader;
    }

    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
