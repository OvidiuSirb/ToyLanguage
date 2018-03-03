package gui;

import controller.InterpretorController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import repository.PrgStateRepository;
import repository.Repository;
import utils.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class startGUI extends Application{
    private ListView<java.lang.String> exeList = new ListView<>();
    private ListView<Integer> outList = new ListView<>();

    private ListView<java.lang.String> progList = new ListView<>();
    private ListView<Integer> threadList = new ListView<>();            //TODO

    private TableView<Pair<java.lang.String,Integer>> symTable = new TableView<>();
    private TableView<Pair<Integer, java.lang.String>> fileTable = new TableView<Pair<Integer, java.lang.String>>();
    private TableView<Pair<Integer,Integer>> heapTable = new TableView<>();

    private HashMap<java.lang.String,InterpretorController> all = new HashMap<>();
    private InterpretorController selected;
    private PrgState current;

    private void loadOut(){
        IOutput<Integer> output = selected.getRepo().getProgList().get(0).getOutput();
        ObservableList<Integer> ObOut = FXCollections.observableArrayList();
        for( int line : output.getContent()){
            ObOut.add(line);
        }
        outList.setItems(ObOut);
    }

    private void loadProgList(){
        ObservableList<java.lang.String> obProgList = FXCollections.observableArrayList();
        for (Map.Entry<java.lang.String,InterpretorController> s : all.entrySet()){
            obProgList.add(s.getKey());
        }
        progList.setItems(obProgList);
    }

    private void loadThreads() {
        Repository repo = selected.getRepo();
        ObservableList<Integer> ObThreads = FXCollections.observableArrayList();
        for (PrgState s : repo.getProgList()) {
            ObThreads.add(s.getPID());
        }
        threadList.setItems(ObThreads);
    }


    private void loadExe(){
        IExeStack<Stmt> exe = current.getStack();
        ObservableList<java.lang.String> ObExe = FXCollections.observableArrayList();
        for (Stmt s : exe.getContent()){
            ObExe.add(s.toString());
        }
        FXCollections.reverse(ObExe);
        exeList.setItems(ObExe);
    }

    private void loadSymTable(){
        ISymTable<java.lang.String,Integer> sym = current.getSymTbl();
        ObservableList<Pair<java.lang.String,Integer>> ObSym = FXCollections.observableArrayList();
        for(Map.Entry<java.lang.String,Integer> entry : sym.getContent().entrySet()){
            ObSym.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symTable.setItems(ObSym);
    }

    private void loadHeap(){
        IHeap<Integer> heap = current.getHeap();
        ObservableList<Pair<Integer,Integer>> ObHeap = FXCollections.observableArrayList();
        for(Map.Entry<Integer,Integer> entry : heap.getContent().entrySet()){
            ObHeap.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        heapTable.setItems(ObHeap);
    }

    private void loadFileTable(){
        IFileTable<Integer,FileData> file = current.getFileTable();
        ObservableList<Pair<Integer, java.lang.String>> obFile = FXCollections.observableArrayList();
        for (Map.Entry<Integer,FileData> entry : file.getContent().entrySet()){
            obFile.add(new Pair<>(entry.getKey(),entry.getValue().toString()));
        }
        fileTable.setItems(obFile);         //Diferit todo
    }


    private void loadData(){
        loadOut();
        loadExe();
        loadThreads();
        loadSymTable();
        loadHeap();
        loadFileTable();
    }

    private void initW2(){
        //Window where we see the program properties

        VBox t1 = new VBox(5);//symbol table
        Label stl = new Label("Symbol table");
        TableColumn<Pair<String,Integer>, String> sym = new TableColumn<>("Symbol");
        sym.setCellValueFactory(
                new PropertyValueFactory<>("key"));
        TableColumn<Pair<String,Integer>,Integer> val = new TableColumn<>("Value");
        val.setCellValueFactory(
                new PropertyValueFactory<>("value"));
        symTable.getColumns().addAll(sym,val);
        symTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(symTable, Priority.ALWAYS);
        t1.setPadding(new Insets(10, 10, 10, 10));
        t1.getChildren().addAll(stl, symTable);

        //-------------------------------------

        VBox t2 = new VBox(5);//heap
        Label hl = new Label("Heap: ");
        TableColumn<Pair<Integer,Integer>,Integer> addr = new TableColumn<>("Address ");
        addr.setCellValueFactory(
            new PropertyValueFactory<>("key"));
        TableColumn<Pair<Integer,Integer>, Integer> hval = new TableColumn<>("Value");
        hval.setCellValueFactory(
                new PropertyValueFactory<>("value"));

        heapTable.getColumns().addAll(addr,hval);
        heapTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(heapTable,Priority.ALWAYS);
        t2.setPadding(new Insets(10,10,10,10));
        t2.getChildren().addAll(hl,heapTable);

        //-------------------------------------

        VBox t3 = new VBox(5);//file table
        Label ftl = new Label("File Table:");
        TableColumn<Pair<Integer,String>, Integer> fid = new TableColumn<>("ID");
        fid.setCellValueFactory(
                new PropertyValueFactory<>("key"));
        TableColumn<Pair<Integer,String>,String> fnm = new TableColumn<>("File");
        fnm.setCellValueFactory(
                new PropertyValueFactory<>("value"));

        fileTable.getColumns().addAll(fid,fnm);
        fileTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(fileTable,Priority.ALWAYS);
        t3.setPadding(new Insets(10,10,10,10));
        t3.getChildren().addAll(ftl,fileTable);
        //-------------------------------------

        VBox l1 = new VBox (5); //exe stack

        Label exestl = new Label ("Exe stack:");

        VBox.setVgrow(exeList,Priority.ALWAYS);
        l1.setPadding(new Insets(10,10,10,10));
        l1.getChildren().addAll(exestl,exeList);

        //-------------------------------------

        VBox l2 = new VBox(5);//output

        Label outl = new Label("Output:");

        VBox.setVgrow(outList, Priority.ALWAYS);
        l2.setPadding(new Insets(10, 10, 10, 10));
        l2.getChildren().addAll(outl, outList);

        //-------------------------------------

        HBox tables = new HBox(10);
        tables.setPadding(new Insets(20,10,10,20));
        tables.getChildren().addAll(t1,t2,t3);

        //-------------------------------------

        HBox lists = new HBox(20);//exe stack and output
        lists.setPadding(new Insets(20,10,10,20));
        lists.getChildren().addAll(l1,l2);

        //-------------------------------------

        HBox butts = new HBox(10);
        Button runOneStep = new Button ("Step");
        runOneStep.setOnAction(e -> {
            int a = selected.oneStep();
            if (a == 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Execution Finished");

                alert.showAndWait();
            }
            loadData();
        });

        Button runAllSteps = new Button ("Run");
        runAllSteps.setOnAction(e -> {
            int a = selected.allSteps();
            if (a == 1) {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Execution Finished");

                alert.showAndWait();
            }
            loadData();
        });

        butts.setPadding(new Insets(20, 10, 10, 20));
        butts.getChildren().addAll(runOneStep, runAllSteps);

        //----------------------------------------

        HBox l = new HBox(20); //lists
        l.setPadding(new Insets(10, 10, 10, 10));
        l.getChildren().addAll(lists);
        //----------------------------------------

        HBox b = new HBox(); // buttons
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(butts);

        //----------------------------------------

        VBox left = new VBox(20);
        left.setPadding(new Insets(10, 0, 0, 10));
        left.getChildren().addAll(tables, l, b);
        //----------------------------------------

        VBox right = new VBox(5);//program states
        Label prgl = new Label("States:");

        threadList.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> {
                    try {
                        current = selected.getRepo().getProgramStatementByPID(new_val);
                        loadData();
                    } catch (Exception e) {
                    }
                }
        );

        VBox.setVgrow(threadList, Priority.ALWAYS);
        right.setPadding(new Insets(10, 10, 10, 10));
        right.getChildren().addAll(prgl, threadList);

        //---------------------------------------

        HBox allContent = new HBox(10);

        allContent.setPadding(new Insets(10, 10, 10, 10));
        HBox.setHgrow(right, Priority.ALWAYS);
        allContent.getChildren().addAll(left, right);

        //-------------------------------------

        Stage other = new Stage();
        Scene s2 = new Scene(allContent, 1300, 600, Color.BLUE);

        other.setScene(s2);
        other.setTitle("Program");
        other.show();

    }
    @Override
    public void start(Stage stage) {
/*
        // { v = 10; a = 2 + 12;}
        Stmt statement1;
        statement1 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new AssignStmt("a", new ArithExpr('+', new ConstExpr(2), new ConstExpr(12))));

        PrgState programState1 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement1);

        PrgStateRepository repo1 = new PrgStateRepository(programState1, "prg1.txt");       //todo modificari
        InterpretorController cont1 = new InterpretorController(repo1);

        // { a = 2 - 2; { if(a) then v = 2 else v = 3; print(v); }}
        Stmt statement2;
        statement2 = new CompStmt(
                new AssignStmt("a", new ArithExpr('-', new ConstExpr(2), new ConstExpr(2))),
                new CompStmt(
                        new IfStmt(
                                new VarExpr("a"),
                                new AssignStmt("v", new ConstExpr(2)),
                                new AssignStmt("v", new ConstExpr(3))),
                        new PrintStmt(new VarExpr("v"))));

        PrgState programState2 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement2);

        PrgStateRepository repo2 = new PrgStateRepository(programState2, "prg2.txt");   //todo modificari
        InterpretorController cont2 = new InterpretorController(repo2);

        /*  openRFile(var_f,"test.in");
            readFile(var_f,var_c);
            print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
               else print(0));
            closeRFile(var_f) */
/*
        Stmt statement3;
        statement3 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile(new VarExpr("var_f"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(
                                                                new VarExpr("var_c"))),
                                                new PrintStmt(new ConstExpr(0))),
                                        new closeRFile("var_f")))));

        PrgState programState3 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement3);

        PrgStateRepository repo3 = new PrgStateRepository(programState3, "prg3.txt");   //todo modificari
        InterpretorController cont3 = new InterpretorController(repo3);

        /*
        openRFile(var_f,"test.in");
        readFile(var_f+2,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)
        */
/*
        Stmt statement4;
        statement4 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile(new ArithExpr('+', new VarExpr("var_f"), new ConstExpr(2)), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(
                                                                new VarExpr("var_c"))),
                                                new PrintStmt(new ConstExpr(0))),
                                        new closeRFile("var_f")))));

        PrgState programState4 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement4);

        PrgStateRepository repo4 = new PrgStateRepository(programState4, "prg4.txt");   //todo modificari
        InterpretorController cont4 = new InterpretorController(repo4);

        /*
        v=10;new(v,20);new(a,22);print(v)
         at the end of execution Heap={1->20, 2->22}, SymTable={v->1, a->2} and Out={1}
        */
/*
        Stmt statement5;
        statement5 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new NewHeap("v", new ConstExpr(20)),
                        new CompStmt(
                                new NewHeap("a", new ConstExpr(22)),
                                new PrintStmt(new VarExpr("v"))
                        )
                )
        );

        PrgState programState5 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement5);

        PrgStateRepository repo5 = new PrgStateRepository(programState5, "prg5.txt");
        InterpretorController cont5 = new InterpretorController((repo5));

        /*
        v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        at the end of execution Heap={1->20, 2->22}, SymTable={v->1, a->2} and Out={120,122}
        */
/*
        Stmt statement6;
        statement6 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new NewHeap("v", new ConstExpr(20)),
                        new CompStmt(
                                new NewHeap("a", new ConstExpr(22)),
                                new CompStmt(
                                        new PrintStmt(new ArithExpr('+', new ConstExpr(100), new HeapRead("v"))),
                                        new PrintStmt(new ArithExpr('+', new ConstExpr(100), new HeapRead("a")))
                                )
                        )
                )
        );

        PrgState programState6 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement6);

        PrgStateRepository repo6 = new PrgStateRepository(programState6, "prg6.txt");
        InterpretorController cont6 = new InterpretorController((repo6));

        /*
        v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
        at the end of execution Heap={1->20}, SymTable={v->1, a->0} and Out={2,30}
        */
/*
        Stmt statement7;
        statement7 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new NewHeap("v", new ConstExpr(20)),
                        new CompStmt(
                                new NewHeap("a", new ConstExpr(22)),
                                new CompStmt(new HeapWrite("a", new ConstExpr(30)),
                                        new CompStmt(
                                                new PrintStmt(new VarExpr("a")),
                                                new CompStmt(
                                                        new PrintStmt(new HeapRead("a")),
                                                        new AssignStmt("a", new ConstExpr(0))
                                                )
                                        )
                                )

                        )

                )

        );

        PrgState programState7 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement7);

        PrgStateRepository repo7 = new PrgStateRepository(programState7, "prg7.txt");
        InterpretorController cont7 = new InterpretorController((repo7));

        /*
        v=6; (while (v-4) print(v);v=v-1);print(v)
         */
/*
        Stmt statement8;
        statement8 = new CompStmt(
                new AssignStmt("v", new ConstExpr(6)),
                new CompStmt(
                        new WhileStmt(
                                new BooleanExpr(">", new ArithExpr('-', new VarExpr("v"), new ConstExpr(4)), new ConstExpr(0)),
                                new CompStmt(
                                        new PrintStmt(new VarExpr("v")),
                                        new AssignStmt("v", new ArithExpr('-', new VarExpr("v"), new ConstExpr(1)))
                                )
                        ),
                        new PrintStmt(new VarExpr("v"))
                )
        );

        PrgState programState8 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement8);

        PrgStateRepository repo8 = new PrgStateRepository(programState8, "prg8.txt");
        InterpretorController cont8 = new InterpretorController((repo8));

        /*  openRFile(var_f,"test.in");
            readFile(var_f,var_c);
            print(var_c);
            (if var_c then readFile(var_f,var_c);print(var_c)
               else print(0));
            closeRFile(var_f) */
/*
        Stmt statement11;
        statement11 = new CompStmt(
                new openRFile("var_f", "test.in"),
                new CompStmt(
                        new readFile(new VarExpr("var_f"), "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExpr("var_c")),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExpr("var_c"),
                                                new CompStmt(
                                                        new readFile(new VarExpr("var_f"), "var_c"),
                                                        new PrintStmt(
                                                                new VarExpr("var_c"))),
                                                new PrintStmt(new ConstExpr(0))),
                                        new closeRFile("var_f")))));

        PrgState programState11 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement3);

        Repository repo11 = new PrgStateRepository(programState3, "prg3.txt");
        InterpretorController cont11 = new InterpretorController(repo3);

        /*  v=10;new(a,22);
            fork(wH(a,30);v=32;print(v);print(rH(a)));
            print(v);print(rH(a))
        */
/*
        Stmt statement9;
        statement9 = new CompStmt(
                new AssignStmt("v", new ConstExpr(10)),
                new CompStmt(
                        new NewHeap("a", new ConstExpr(22)),
                        new CompStmt(
                                new ForkStmt(
                                        new CompStmt(
                                                new HeapWrite("a", new ConstExpr(30)),
                                                new CompStmt(
                                                        new AssignStmt("v", new ConstExpr(32)),
                                                        new CompStmt(
                                                                new PrintStmt(new VarExpr("v")),
                                                                new PrintStmt(new HeapRead("a"))
                                                        )
                                                )
                                        )
                                ),
                                new CompStmt(
                                        new PrintStmt(new VarExpr("v")),
                                        new PrintStmt(new HeapRead("a"))
                                )
                        )
                )
        );

        PrgState programState9 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement9);

        PrgStateRepository repo9 = new PrgStateRepository(programState9, "prg9.txt");
        InterpretorController cont9 = new InterpretorController((repo9));

        /*  a=1;b=2;
            c=a?100:200;
            print(c);
            c=(b-2)?100:200
        */

        Stmt statement10;
        statement10 = new CompStmt(
                new AssignStmt("a",new ConstExpr(1)),
                new CompStmt(
                        new AssignStmt("b",new ConstExpr(2)),
                        new CompStmt(
                                new CondStmt(
                                        new VarExpr("a"),
                                        new ConstExpr(100),
                                        new ConstExpr(200)
                                ),
                                new CompStmt(
                                        new PrintStmt(new VarExpr("v")),
                                        new CompStmt(
                                            new CondStmt(
                                                    new ArithExpr('-',new VarExpr("b"),new ConstExpr(2)),
                                                    new ConstExpr(100),
                                                    new ConstExpr(200)
                                            ),new PrintStmt(new VarExpr("v")))
                                )
                )

        ));

        PrgState programState10 = new PrgState(
                new ExeStack<Stmt>(),
                new SymTable<String, Integer>(),
                new Output<Integer>(),
                statement10);

        PrgStateRepository repo10 = new PrgStateRepository(programState10, "prg10.txt");
        InterpretorController cont10 = new InterpretorController((repo10));



        all.put(statement10.toString(),cont10);
        /*
        all.put(statement1.toString(), cont1);
        all.put(statement2.toString(), cont2);
        all.put(statement3.toString(), cont3);
        all.put(statement4.toString(), cont4);
        all.put(statement5.toString(), cont5);
        all.put(statement6.toString(), cont6);
        all.put(statement7.toString(), cont7);
        all.put(statement8.toString(), cont8);
        all.put(statement9.toString(), cont9);
        all.put(statement11.toString(), cont11);
    */

        loadProgList();
        //Window where we choose what program will be executed
        VBox lal = new VBox(10);

        Label prog_list_label = new Label("Programs List:");

        progList.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> selected = all.get(new_val));

        progList.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().toString().equals("ENTER")) {
                current = selected.getRepo().getProgList().get(0);
                loadData();
            }
        });

        progList.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() >= 2) {
                current = selected.getRepo().getProgList().get(0);
                loadData();
            }
        });

        Button load = new Button("Load");
        load.setOnAction(e -> {
            //step once before
            current = selected.getRepo().getProgList().get(0);
            loadData();

        });

        lal.setPadding(new Insets(10));
        lal.getChildren().addAll(prog_list_label, progList, load);
        VBox.setVgrow(progList, Priority.ALWAYS);

        lal.setPadding(new Insets(15, 15, 15, 15));

        Scene s1 = new Scene(lal, 500, 300, Color.BLUE);

        stage.setScene(s1);
        stage.setTitle("Programs");
        stage.show();
        initW2();
        //-------------------------------------
    }

    public static void main(String[] args) {


        launch(args);
    }
}

