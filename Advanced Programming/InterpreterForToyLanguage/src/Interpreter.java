import Controller.Controller_Interpreter;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
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

        IStatement ex5 = new PrintStatement(new RelationalExpression(">", new ValueExpression(new IntValue(5)), new ValueExpression(new IntValue(3))));

        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v))
        IStatement ex6 =
            new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                        new CompoundStatement (new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                    new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                            new PrintStatement(new HeapReadingExpression(new VariableExpression("v")))
                                ))));

        // Create program states
        ProgramState programState1 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex1);
        ProgramState programState2 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex2);
        ProgramState programState3 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex3);
        ProgramState programState4 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex4);
        ProgramState programState5 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex5);
        ProgramState programState6 = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), new ADTDictionaryForHeap(), ex6);
        // Create controllers
        try {
            Controller_Interpreter controller_1 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState1, "log_ex1.txt"));
            Controller_Interpreter controller_2 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState2, "log_ex2.txt"));
            Controller_Interpreter controller_3 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState3, "log_ex3.txt"));
            Controller_Interpreter controller_4 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState4, "log_ex4.txt"));
            Controller_Interpreter controller_5 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState5, "log_ex5.txt"));
            Controller_Interpreter controller_6 = new Controller_Interpreter(new RepositoryMemoryBased_Interpreter(programState6, "log_ex6.txt"));


            // Run view
            ViewTextMenuBased viewTextMenuBased = new ViewTextMenuBased();
            viewTextMenuBased.addCommand(new ExitCommand("exit", "exit"));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex1", "allstep ex1", controller_1));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex2", "allstep ex2", controller_2));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex3", "allstep ex3", controller_3));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex4", "allstep ex4", controller_4));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex5", "allstep ex5", controller_5));
            viewTextMenuBased.addCommand(new RunAllStepsCommand("allstep ex6", "allstep ex6", controller_6));
            viewTextMenuBased.show();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

    }
}
