package model;

public class WhileStmt implements Stmt {
    private Expr condition;
    private Stmt statement;

    public WhileStmt(Expr givenCondition, Stmt givenStatement) {
        condition = givenCondition;
        statement = givenStatement;
    }

    public PrgState execute(PrgState state) {
        if (condition.evaluate(state.getSymTbl(), state.getHeap()) != 0) {
            state.getStack().push(this);
            state.getStack().push(statement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "while(".concat(condition.toString()).concat(") {").concat(statement.toString()).concat("}");
    }
}
