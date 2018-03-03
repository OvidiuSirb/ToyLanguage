package controller;
import model.*;
import repository.PrgStateRepository;
import utils.*;
import utils.IExeStack;
import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class InterpretorController {

    private PrgStateRepository repo ;
    private ExecutorService executor;

    public InterpretorController(PrgStateRepository r){this.repo = r;}
    public PrgStateRepository getRepo(){return this.repo;}
    public void add(PrgState p){
        repo.addPrg(p);
    }

    public void executeAll() {
        executor = Executors.newFixedThreadPool(2);
        while (true) {
            List<PrgState> progList = removeCompletedPrg(repo.getProgList());
            if (progList.size() == 0)
                break;
            oneStepForAllPrg(progList);
        }
        executor.shutdownNow();
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> !p.isCompleted())
                .collect(Collectors.toList());
    }

    private void oneStepForAllPrg(List<PrgState> progList) {

        progList.forEach(prog -> repo.logPrgStateExec(prog));

        List<Callable<PrgState>> callList = progList.stream()
                .map(p -> ((Callable<PrgState>) () -> p.executeOneStep()))
                .collect(Collectors.toList());

        List<Future<PrgState>> list = null;

        try {
            list = executor.invokeAll(callList);
        } catch (Exception e) {

        }

        List<PrgState> newProgList =
                list.stream()
                        .map(future -> {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            return null;
                        })
                        .filter(p -> p != null)
                        .collect(Collectors.toList());

        progList.addAll(newProgList);

        progList.forEach(prg -> repo.logPrgStateExec(prg));

        repo.setPrgList((ArrayList<PrgState>) progList);
    }

    public int oneStep(){
        executor = Executors.newFixedThreadPool(2);
        ArrayList<PrgState> programStatements = (ArrayList) removeCompletedPrg(repo.getProgList());
        if (programStatements.size() == 0)
        {
            executor.shutdownNow();
            return 1;
        }
        else
            oneStepForAllPrg(programStatements);

        executor.shutdownNow();
        return 0;
    }

    public int allSteps(){
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> progList = removeCompletedPrg(repo.getProgList());
        while (progList.size() > 0) {
            progList = removeCompletedPrg(repo.getProgList());
            if (progList.size() == 0)
            {
                executor.shutdownNow();
                return 1;
            }
            oneStepForAllPrg(progList);
        }
        executor.shutdownNow();
        return  0;
    }

    private Map<Integer, Integer>
    conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    /*
    public void oneStep(PrgState state){
        IExeStack stk=state.getStack();
        //if(stk.isEmpty()) throws MyStmtExecException;
        Stmt crtStmt = new AssignStmt("a", new ArithExpr('-',new ConstExpr(2), new
                ConstExpr(2)));
        try {
            crtStmt = (Stmt) stk.pop();}
        catch (EmptyStackException e) {
            int x = 0;
        }

        state = crtStmt.execute(state);


    }



    public void executeAll() throws IOException {
        PrgState prg = repo.getCrtPrg();
        try{
            while (true) {
                oneStep(prg);
                prg.getHeap().setContent(conservativeGarbageCollector(
                        prg.getSymTbl().getContent().values(),
                        prg.getHeap().getContent()));
                System.out.println("Stack: ");
                System.out.println(this.repo.getCrtPrg().getStack());
                System.out.println("\n");
                System.out.println("SymTable: ");
                System.out.println(this.repo.getCrtPrg().getSymTbl());
                System.out.println("\n");
                System.out.println("Output: ");
                System.out.println(this.repo.getCrtPrg().getOutput());
                System.out.println("\n");
                System.out.println(this.repo.getCrtPrg().getFileTable());
                System.out.println("\n");
                System.out.println(this.repo.getCrtPrg().getHeap());
                System.out.println("\n");
                repo.logProgStateExec();
                if (prg.getStack().isEmpty())
                    break;
                //here you can display the prg state
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally
        {


            if(prg.getFileTable().size() != 0){
                IFileTable<Integer,FileData> ft = prg.getFileTable();
                HashMap<Integer,FileData> table = (HashMap<Integer, FileData>) prg.getFileTable().getContent();
                for (int key : table.keySet()){
                    ft.getValue(key).getReader().close();
                    ft.remove(key);
                }
            }
            
        }



    }
    */
}
