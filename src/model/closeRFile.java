package model;

import utils.FileData;


public class closeRFile implements Stmt {
    private String fileDescriptor;

    public closeRFile(String fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    @Override
    public PrgState execute(PrgState state) {

        Integer id;
        FileData data;

        if (state.getSymTbl().has(fileDescriptor))
            id = state.getSymTbl().getValue(fileDescriptor);
        else
            throw new StatementException("Invalid file id for file descriptor: ".concat(fileDescriptor));

        if (state.getFileTable().has(id))
            data = state.getFileTable().getValue(id);
        else
            throw new StatementException("Invalid file data for file descriptor: ".concat(fileDescriptor));

        try {
            data.getReader().close();
        } catch (Exception e) {
            throw new StatementException("Couldn't close file ".concat(data.toString()));
        }
        state.getFileTable().remove(id);
        return null;
    }

    @Override
    public String toString() {
        return "CloseRFile(".concat(fileDescriptor).concat(")");
    }
}
