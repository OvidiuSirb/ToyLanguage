package repository;
import java.io.*;
import java.util.*;
import model.PrgState;
import utils.*;

public class PrgStateRepository implements Repository {
    private String logFilePath;
    private ArrayList<PrgState> repo = new ArrayList<PrgState>();
    private Map<Integer, PrgState> PIDList;

    @Override
    public PrgState getProgramStatementByPID(int PID) {
        return PIDList.get(PID);
    }

    public void addPrg(PrgState p){
        repo.add(p);
        if(!PIDList.containsKey(p.getPID()))
            PIDList.put(p.getPID(), p);
    }
    //public PrgStateRepository(String logFilePath){this.logFilePath = logFilePath;}

    public PrgStateRepository() {
    }

    public void logPrgStateExec(PrgState givenState) {
        PrintWriter logFile = null;
        try {

            logFile = new PrintWriter(new FileWriter(logFilePath, true));

            logFile.println(givenState.toString());
            logFile.println("---------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                logFile.close();
            } catch (Exception e) {
            }
        }
    }

    public PrgStateRepository(PrgState prg, String givenFilePath) {
        repo = new ArrayList<>();
        PIDList = new HashMap<>();
        addPrg(prg);
        logFilePath = givenFilePath;
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new FileWriter(logFilePath, false));
            logFile.print("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                logFile.close();
            } catch (Exception e) {
            }
        }
    }

    public int size(){
        return repo.size();
    }

    @Override
    public void logProgStateExec() {

        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));

        } catch (IOException e) {
            System.out.println("IOEXCEPTION");
        }
        assert logFile != null;
        logFile.append("ExeStack:\n");
        PrgState repo = getCrtPrg();
        IExeStack e = repo.getStack();
        logFile.append(e.toString());
        logFile.append("\n");
        logFile.append("SymTable:\n");
        logFile.append(repo.getSymTbl().toString());
        logFile.append("\n");
        logFile.append("Out:\n");
        logFile.append(repo.getOutput().toString());
        logFile.append("\n");
        logFile.append(repo.getFileTable().toString());
        logFile.append("\n");
        logFile.append(repo.getHeap().toString());
        logFile.append("\n");
        logFile.close();
    }
    @Override
    public PrgState getCrtPrg(){
        return repo.get(repo.size()-1);
    }

    @Override
    public ArrayList<PrgState> getProgList() {return repo;}

    public void setPrgList(ArrayList<PrgState> givenList) {
        repo = givenList;
        for(PrgState prg: givenList) {
            if(!PIDList.containsKey(prg.getPID()))
                PIDList.put(prg.getPID(), prg);
        }
    }




}
