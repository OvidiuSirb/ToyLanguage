package repository;

import javafx.scene.layout.Priority;
import model.PrgState;

import java.io.IOException;
import java.util.ArrayList;

public interface Repository {

    void addPrg(PrgState p);

    void setPrgList(ArrayList<PrgState> givenList);

    void logProgStateExec() throws IOException;

    PrgState getCrtPrg();

    ArrayList<PrgState> getProgList();

    int size();

    PrgState getProgramStatementByPID(int PID);
}
