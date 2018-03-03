/*package view;

import model.*;

import java.util.Scanner;

public class TextView {
    static protected void showMenu(){
        System.out.println("Please enter a command:\n");
        System.out.println("1-Input a program\n");
        System.out.println("2-One-step evaluation\n");
        System.out.println("3-Complete evaluation\n");
        System.out.println("0-Exit\n");
    }
    static protected void showMenu1(){
        System.out.println("Please chose statement type:\n");
        System.out.println("1-Arithmetic expression\n");
        System.out.println("2-Arithmetic expression\n");
    }
    static protected Stmt Assign(){
        Stmt s = null;
        Scanner reader = new Scanner(System.in);
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
            s = new AssignStmt(id, e);
        }
        if (r2 == 2) {
            Expr e1;
            System.out.println("Please enter constant: \n");
            int cnst = reader.nextInt();
            e1 = new ConstExpr(cnst);

            s = new AssignStmt(id,e1);
        }
        if(r2 == 3){
            Expr e1;
            System.out.println("Please enter variable: \n");
            String var = reader.next();
            e1 = new VarExpr(var);

            s = new AssignStmt(id,e1);
        }
        return s;

    }
    static protected Stmt Print(){
        Stmt s = null;
        Scanner reader = new Scanner(System.in);
        System.out.println("Please select the type of the expression:\n");
        System.out.println("1-Arithmetic \n");
        System.out.println("2-Constant \n");
        System.out.println("3-Variable \n");
        int r2 = reader.nextInt();

        Expr e1 = null, e2 = null;

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
            s = new PrintStmt(e);
        }
        if (r2 == 2) {

            System.out.println("Please enter constant: \n");
            int cnst = reader.nextInt();
            e1 = new ConstExpr(cnst);
            s = new PrintStmt(e1);

        }
        if (r2 == 3) {

            System.out.println("Please enter variable: \n");
            String var = reader.next();
            e1 = new VarExpr(var);
            s = new PrintStmt(e1);
        }
        return s;
    }
    static protected Stmt Stmt_not_compound(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please select the type of the statement:\n");
        System.out.println("1-Assign Statement\n");
        System.out.println("2-If Statement\n");                         //TODO
        System.out.println("3-Print Statement\n");
        int q = reader.nextInt();
        Stmt s=null;
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
                s = new AssignStmt(id, e);

            }
            if (r2 == 2) {
                Expr e1;
                System.out.println("Please enter constant: \n");
                int cnst = reader.nextInt();
                e1 = new ConstExpr(cnst);

                s = new AssignStmt(id, e1);

            }
            if (r2 == 3) {
                Expr e1;
                System.out.println("Please enter variable: \n");
                String var = reader.next();
                e1 = new VarExpr(var);

                s = new AssignStmt(id, e1);

            }
        }
        //======================IF STMT===========================
        else if (q == 2) {         //doar pentru assignstmt

            System.out.println("Please enter a variable:\n");
            //int var = reader.nextInt();       TODO

            Stmt stmt1 = TextView.Assign();
            Stmt stmt2 = TextView.Assign();

            //s = new IfStmt(var,stmt1,stmt2);


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
                s = new PrintStmt(e);
            }
            if (r2 == 2) {

                System.out.println("Please enter constant: \n");
                int cnst = reader.nextInt();
                e1 = new ConstExpr(cnst);
                s = new PrintStmt(e1);

            }
            if (r2 == 3) {

                System.out.println("Please enter variable: \n");
                String var2 = reader.next();
                e1 = new VarExpr(var2);
                s = new PrintStmt(e1);

            }
        }
        return s;
    }
    static protected Stmt Stmt_with_compound(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please select the type of the statement:\n");
        System.out.println("1-Assign Statement\n");
        System.out.println("2-If Statement\n");                         //TODO
        System.out.println("3-Print Statement\n");
        System.out.println("4-Print Statement\n");
        int q = reader.nextInt();
        Stmt s=null;
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
                s = new AssignStmt(id, e);

            }
            if (r2 == 2) {
                Expr e1;
                System.out.println("Please enter constant: \n");
                int cnst = reader.nextInt();
                e1 = new ConstExpr(cnst);

                s = new AssignStmt(id, e1);

            }
            if (r2 == 3) {
                Expr e1;
                System.out.println("Please enter variable: \n");
                String var = reader.next();
                e1 = new VarExpr(var);

                s = new AssignStmt(id, e1);

            }
        }
        //======================IF STMT===========================
        else if (q == 2) {         //doar pentru assignstmt

            System.out.println("Please enter the value of the variable:\n");
            int var = reader.nextInt();

            Stmt stmt1 = TextView.Assign();
            Stmt stmt2 = TextView.Assign();

            //s = new IfStmt(var,stmt1,stmt2);              TODO


        }
        //======================PRINT STMT===========================
        else if (q == 3) {
            System.out.println("Please select the type of the expression:\n");
            System.out.println("1-Arithmetic \n");
            System.out.println("2-Constant \n");
            System.out.println("3-Variable \n");
            System.out.println("4-Variable \n");
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
                s = new PrintStmt(e);
            }
            if (r2 == 2) {

                System.out.println("Please enter constant: \n");
                int cnst = reader.nextInt();
                e1 = new ConstExpr(cnst);
                s = new PrintStmt(e1);

            }
            if (r2 == 3) {

                System.out.println("Please enter variable: \n");
                String var2 = reader.next();
                e1 = new VarExpr(var2);
                s = new PrintStmt(e1);

            }
        }
        if (q==4){
            Stmt s1 = Stmt_not_compound();
            Stmt s2 = Stmt_with_compound();

            s = new CompStmt(s1,s2);
        }
        return s;

    }
}*/
