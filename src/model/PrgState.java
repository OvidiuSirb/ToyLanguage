package model;

import utils.*;


public class PrgState {
    //private Stmt prg;
    private IExeStack<Stmt> exeStack;
    private ISymTable<String, Integer> symTbl;
    private IOutput<Integer> out;
    private IFileTable<Integer, FileData> fileTable;
    private IHeap<Integer> heap;
    private Stmt originalPrg;
    private SemaphoreTable<Integer> sem;
    private int id = 1;
    private int child = 1;

    public PrgState(IExeStack<Stmt> stk,ISymTable<String, Integer> symTbl, IOutput<Integer> out, Stmt givenPrg){       //am scos Stmt stmt
        this.exeStack = stk;
        this.symTbl = symTbl;
        this.out = out;
        this.fileTable = new FileTable<>();
        this.heap = new Heap<>();
        this.originalPrg = givenPrg;
        exeStack.push(originalPrg);
        //this.prg = stmt;
    }
    public PrgState(IExeStack<Stmt> givenExe, ISymTable<String, Integer> givenSymTable,
                        IFileTable<Integer, FileData> givenFileTable, IHeap<Integer> givenHeap,
                        IOutput<Integer> givenOut,
                        Stmt givenPrg, int parentChild) {
        exeStack = givenExe;
        symTbl = givenSymTable;
        out = givenOut;
        fileTable = givenFileTable;
        heap = givenHeap;
        originalPrg = givenPrg;
        exeStack.push(originalPrg);
        id = id * 10 * parentChild;
    }

    public int getChild(){
        return child++;
    }

    public int getPID() {
        return id;
    }

    //public Stmt getStmt (){return prg;}
    //public void setStmt (Stmt prg){this.prg = prg;}

    public IExeStack<Stmt> getStack (){return exeStack;}
    public void setStack (IExeStack<Stmt> stack){this.exeStack = stack;}

    public ISymTable<String,Integer> getSymTbl(){return symTbl;}
    void setTbl(ISymTable<String,Integer> tbl){symTbl=tbl;}

    public IOutput<Integer> getOutput(){return out;}
    public void setOutput(IOutput<Integer> out){this.out = out;}

    public IFileTable<Integer, FileData> getFileTable(){return fileTable;}

    public IHeap<Integer> getHeap(){ return heap;}
    public SemaphoreTable<Integer> getSem() {return sem;}
    public void setHeap(IHeap<Integer> heap){this.heap = heap;}

    public boolean isCompleted() {
        return exeStack.isEmpty();
    }

    public PrgState executeOneStep() {
        if (exeStack.isEmpty())
            throw new RuntimeException("ExeStack is empty");
        Stmt stmt = exeStack.pop();
        return stmt.execute(this);
    }

    @Override
    public String toString() {
        return "Program id: ".concat(
                Integer.toString(id)).concat(
                "\n").concat(
                exeStack.toString()).concat(
                symTbl.toString()).concat(
                out.toString()).concat(
                fileTable.toString()).concat(
                heap.toString());
    }

}
