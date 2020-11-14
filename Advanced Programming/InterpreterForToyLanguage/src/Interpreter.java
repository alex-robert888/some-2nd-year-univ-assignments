import Controller.Controller_Interpreter;
import Model.ADT.ADTDictionary;
import Model.ADT.ADTList;
import Model.ADT.ADTStack;
import Model.ADT.IADTStack;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.RepositoryMemoryBased_Interpreter;
import View.Command.ExitCommand;
import View.Command.RunAllStepsCommand;
/*import View.Interpreter_View;*/
import View.ViewTextMenuBased;

import java.util.Deque;
import java.util.LinkedList;

public class Interpreter {
    public static void main(String[] args) {
        // Create statements
        IStatement ex1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("v",
                                        new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                );

        IStatement ex2 =
                new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)), new
                                        ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                        new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new
                                                ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStatement ex3 =
                new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(new IfElseStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new
                                                IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                                VariableExpression("v"))))));

        IStatement ex4 =
                new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                        new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                new CompoundStatement(new OpenFileStatement(new VariableExpression("varf")),
                                        new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                                new CloseFileStatement(new VariableExpression("varf"))
                                                                        ))))))));

        // Create program states
        ProgramState programState1 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), ex1);
        ProgramState programState2 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), ex2);
        ProgramState programState3 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), ex3);
        ProgramState programState4 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), ex4);

        // Create controllers
        Controller_Interpreter controller_1 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState1, "log_ex1.txt"));
        Controller_Interpreter controller_2 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState2, "log_ex2.txt"));
        Controller_Interpreter controller_3 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState3, "log_ex3.txt"));
        Controller_Interpreter controller_4 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState4, "log_ex4.txt"));

        // Run view
        ViewTextMenuBased viewTextMenuBased = new ViewTextMenuBased();
        viewTextMenuBased.addCommand(new ExitCommand("exit", "exit"));
        viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex1", "allstep ex1", controller_1));
        viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex2", "allstep ex2", controller_2));
        viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex3", "allstep ex3", controller_3));
        viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex4", "allstep ex4", controller_4));
        viewTextMenuBased.show();
    }
}
