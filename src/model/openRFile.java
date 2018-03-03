package model;

import model.PrgState;
import model.StatementException;
import model.Stmt;
import utils.FileData;
import utils.IdGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class openRFile implements Stmt {

    private String fileDescriptor, fileName;

    public openRFile(String fileDescriptor, String fileName) {
        this.fileDescriptor = fileDescriptor;
        this.fileName = fileName;
    }

    @Override
    public PrgState execute(PrgState state) {
        if (state.getSymTbl().has(fileDescriptor))
            throw new StatementException("File ".concat(fileName).concat(" already opened"));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            int id = IdGenerator.generate();
            state.getSymTbl().add(fileDescriptor, id);
            state.getFileTable().add(id, new FileData(fileName, reader));
        } catch (Exception e) {
            throw new StatementException("Couldn't open ".concat(fileName).concat(" for reading"));
        }

        return null;
    }

    @Override
    public String toString() {
        return "OpenRFile(".concat(fileDescriptor).concat(")");
    }
}