package model;


import utils.FileData;

import java.io.BufferedReader;


public class readFile implements Stmt {
    private String variableName;
    private Expr fileDescriptor;

    public readFile(Expr fileDescriptor, String variableName) {
        this.variableName = variableName;
        this.fileDescriptor = fileDescriptor;
    }

    @Override
    public PrgState execute(PrgState state) {
        Integer id;
        FileData data;
        BufferedReader reader;

        try {
            id = fileDescriptor.evaluate(state.getSymTbl(),state.getHeap());                //TODO GETHEAP
        } catch (Exception e) {
            throw new RuntimeException("Invalid file id for file descriptor: ".concat(fileDescriptor.toString()));
        }

        if (state.getFileTable().has(id))
            data = state.getFileTable().getValue(id);
        else
            throw new RuntimeException("Invalid file data for file descriptor: ".concat(fileDescriptor.toString()));

        reader = data.getReader();

        try {
            String stringValue = reader.readLine();
            int value;

            if (stringValue == null)
                value = 0;
            else
                value = Integer.parseInt(stringValue);

            if (state.getSymTbl().has(variableName))
                state.getSymTbl().setValue(variableName, value);
            else
                state.getSymTbl().add(variableName, value);

        } catch (Exception e) {
            throw new StatementException("Error at reading data from file", e);
        }

        return null;
    }

    @Override
    public String toString() {
        return "ReadFile(".concat(fileDescriptor.toString()).concat(", ").concat(variableName).concat(")");
    }
}
