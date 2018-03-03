package view;

import controller.InterpretorController;
import model.*;
import repository.PrgStateRepository;
import utils.*;

import java.io.IOException;
import java.util.Scanner;
/*
public class View extends TextView {
    public static void main(String[] args) throws IOException {

        PrgStateRepository r = new PrgStateRepository();
        InterpretorController c = new InterpretorController(r);

        IExeStack<Stmt> stk = new ExeStack<>();
        ISymTable<String, Integer> symtbl = new SymTable<>();
        IOutput<Integer> out = new Output<>();
        IFileTable<String,Integer> fileT = new FileTable<>();

        PrgState crtPrgState = new PrgState(stk, symtbl, out, fileT);


        int n = 1;
        Scanner reader = new Scanner(System.in);
        while (n != 0) {
            showMenu();
            n = reader.nextInt();


            if (n == 1) {
                System.out.println("Please enter a command:\n");
                System.out.println("1-Use the default statement.\n");
                System.out.println("2-Input a custom program\n");
                int m = reader.nextInt();
                if (m == 1) {
                    //Stmt ex2 = new CompStmt(new AssignStmt("a", new ArithExpr('+', new ConstExpr(2), new
                      //      ArithExpr('*', new ConstExpr(3), new ConstExpr(5)))), new CompStmt(new AssignStmt("b", new ArithExpr('+', new VarExpr("a"), new
                     //       ConstExpr(1))), new PrintStmt(new VarExpr("b"))));
                    //stk.push(ex2);
                    //Stmt ex1=  new CompStmt(new AssignStmt("a", new ArithExpr('-',new ConstExpr(2), new
                    //        ConstExpr(2))),
                    //        new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)), new AssignStmt("v", new ConstExpr(3))), new PrintStmt(new VarExpr("v"))));
                    Stmt ex1 = new CompStmt(new openRFile(new VarExpr("var_f"),"test.in"),
                            new CompStmt(new readFile(new VarExpr("var_f"),new VarExpr("var_c")),
                                    new CompStmt(new PrintStmt(new VarExpr("var_c")),
                                            new CompStmt(
                                                    new IfStmt(new VarExpr("var_c"),new CompStmt(new readFile(new VarExpr("var_f"),new VarExpr("var_c")),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),
                                                    new closeRFile(new VarExpr("var_f"))))));
                    stk.push(ex1);

                    int x = 0;

                } else if (m == 2) {
                    System.out.println("Please select the type of the statement:\n");
                    System.out.println("1-Assign Statement\n");
                    System.out.println("2-If Statement\n");                         //TODO
                    System.out.println("3-Print Statement\n");
                    System.out.println("4-Compound Statement\n");
                    int q = reader.nextInt();
                    //======================ASSIGN STMT===========================
                    if (q == 1) {
                        System.out.println("Please select the type of the expression:\n");
                        System.out.println("1-Arithmetic \n");
                        System.out.println("2-Constant \n");
                        System.out.println("3-Variable \n");
                        int r2 = reader.nextInt();
                        System.out.println("Id= \n");
                        String id = reader.next();

                        if (r2 == 1) {
                            System.out.println("Please select the type of the operand:\n");
                            System.out.println("1-Constant \n");
                            System.out.println("2-Variable \n");
                            int type = reader.nextInt();
                            Expr e1 = null, e2 = null;
                            if (type == 1) {
                                System.out.println("Please enter constant: \n");
                                int cnst = reader.nextInt();
                                e1 = new ConstExpr(cnst);
                            } else if (type == 2) {
                                System.out.println("Please enter variable: \n");
                                String name = reader.next();
                                e1 = new VarExpr(name);
                            }
                            System.out.println("Please select the type of the operand:\n");
                            System.out.println("1-Constant \n");
                            System.out.println("2-Variable \n");
                            int type2 = reader.nextInt();
                            if (type2 == 1) {
                                System.out.println("Please enter constant: \n");
                                int cnst = reader.nextInt();
                                e2 = new ConstExpr(cnst);
                            } else if (type2 == 2) {
                                System.out.println("Please enter variable: \n");
                                String name = reader.next();
                                e2 = new VarExpr(name);
                            }
                            System.out.println("Please enter the operand:\n");
                            char op = reader.next().charAt(0);
                            Expr e = new ArithExpr(op, e1, e2);
                            Stmt s = new AssignStmt(id, e);
                            crtPrgState.getStack().push(s);
                        }
                        if (r2 == 2) {
                            Expr e1;
                            System.out.println("Please enter constant: \n");
                            int cnst = reader.nextInt();
                            e1 = new ConstExpr(cnst);

                            Stmt s = new AssignStmt(id, e1);
                            crtPrgState.getStack().push(s);
                        }
                        if (r2 == 3) {
                            Expr e1;
                            System.out.println("Please enter variable: \n");
                            String var = reader.next();
                            e1 = new VarExpr(var);

                            Stmt s = new AssignStmt(id, e1);
                            crtPrgState.getStack().push(s);
                        }
                    }
                    //======================IF STMT===========================
                    else if (q == 2) {         //doar pentru assignstmt

                        System.out.println("Please enter a variable:\n");
                        //int var = reader.nextInt();   TODO

                        Stmt stmt1 = TextView.Assign();
                        Stmt stmt2 = TextView.Assign();

                        //Stmt s = new IfStmt(var,stmt1,stmt2);
                        //crtPrgState.getStack().push(s);

                    }
                    //======================PRINT STMT===========================
                    else if (q == 3) {
                        System.out.println("Please select the type of the expression:\n");
                        System.out.println("1-Arithmetic \n");
                        System.out.println("2-Constant \n");
                        System.out.println("3-Variable \n");
                        int r2 = reader.nextInt();

                        Expr e1 = null,e2 = null;

                        if (r2 == 1) {
                            System.out.println("Please select the type of the operand:\n");
                            System.out.println("1-Constant \n");
                            System.out.println("2-Variable \n");
                            int type = reader.nextInt();
                            if (type == 1) {
                                System.out.println("Please enter constant: \n");
                                int cnst = reader.nextInt();
                                e1 = new ConstExpr(cnst);
                            } else if (type == 2) {
                                System.out.println("Please enter variable: \n");
                                String name = reader.next();
                                e1 = new VarExpr(name);
                            }
                            System.out.println("Please select the type of the operand:\n");
                            System.out.println("1-Constant \n");
                            System.out.println("2-Variable \n");
                            int type2 = reader.nextInt();
                            if (type2 == 1) {
                                System.out.println("Please enter constant: \n");
                                int cnst = reader.nextInt();
                                e2 = new ConstExpr(cnst);
                            } else if (type2 == 2) {
                                System.out.println("Please enter variable: \n");
                                String name = reader.next();
                                e2 = new VarExpr(name);
                            }
                            System.out.println("Please enter the operand:\n");
                            char op = reader.next().charAt(0);
                            Expr e = new ArithExpr(op, e1, e2);
                            Stmt s = new PrintStmt(e);

                            crtPrgState.getStack().push(s);;
                        }
                        if (r2 == 2) {

                            System.out.println("Please enter constant: \n");
                            int cnst = reader.nextInt();
                            e1 = new ConstExpr(cnst);
                            Stmt s = new PrintStmt(e1);
                            crtPrgState.getStack().push(s);
                        }
                        if (r2 == 3) {

                            System.out.println("Please enter variable: \n");
                            String var2 = reader.next();
                            e1 = new VarExpr(var2);
                            Stmt s = new PrintStmt(e1);
                            crtPrgState.getStack().push(s);
                        }
                    }
                    //======================CMP STMT===========================
                    else if (q == 4) {  //TODO
                        Stmt s1 = TextView.Stmt_not_compound();
                        Stmt s2 = TextView.Stmt_with_compound();
                        Stmt s = new CompStmt(s1,s2);
                        crtPrgState.getStack().push(s);
                        int x = 0;
                    }
                }
                c.add(crtPrgState);
            }
            if(n==2){
                c.oneStep();

            }
            if(n==3){
                c.executeAll();
            }
        }

    }

}
*/
