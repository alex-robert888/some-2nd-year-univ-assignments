package View;

import Controller.Controller_Interpreter;
import Model.ADT.ADTDictionary;
import Model.ADT.ADTList;
import Model.ADT.ADTStack;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;

import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter_View {
    ViewState viewState;
    Map<String, IStatement> programsMap;
    String loadedProgramName;
    Controller_Interpreter controller_interpreter;

    public Interpreter_View(Controller_Interpreter controller_interpreter) {
        this.viewState = ViewState.RUNNING;
        this.loadedProgramName = "";
        this.programsMap = new HashMap<>();
        this.controller_interpreter = controller_interpreter;

        this.programsMap.put("ex1",
                new CompoundStatement(
                    new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("v",
                                        new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        this.programsMap.put("ex2",
                new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                        ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                        new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new
                                                ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b")))))));

        this.programsMap.put("ex3",
                new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                    new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                            new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                    new CompoundStatement(new IfElseStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                            IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                            VariableExpression("v")))))));

        this.programsMap.put("ex4",
                new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                        new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                new CompoundStatement(new OpenFileStatement(new VariableExpression("varf")),
                                        new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                    new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFileStatement(new VariableExpression("varf"))
                                                        )))))))));
    }

    public void run() {
        System.out.println("View initiated. Program is ready to use. \nType 'help' for list of commands");
        int exitStatus = this.runEventLoop();
        System.out.println("Program terminated with exit code " + exitStatus);
    }

    private int runEventLoop() {
        while(this.viewState == ViewState.RUNNING) {
            Scanner scannerCommand = new Scanner(System.in);
            String rawCommand = scannerCommand.nextLine();
            int commandExitStatus = this.executeCommand(rawCommand);
            if (commandExitStatus != 0) {
                System.out.println("Invalid command");
            }
        }
        return 0;
    }

    private void loadProgram(String[] splitRawCommand) throws Exception{
        if (splitRawCommand.length != 2 || !this.programsMap.containsKey(splitRawCommand[1])) {
            throw new Exception("Invalid command parameters");
        }
        this.loadedProgramName = splitRawCommand[1];
        ProgramState programState = new ProgramState(new ADTStack<>(), new ADTDictionary<>(), new ADTList<>(), new ADTDictionary<>(), this.programsMap.get(this.loadedProgramName));
        this.controller_interpreter.addProgramStateToRepository(programState);
    }



    private int executeCommand(String rawCommand) {
         String[] splitRawCommand = rawCommand.split("\\s+");
         try {
             switch (splitRawCommand[0]) {
                 case "quit" -> this.viewState = ViewState.QUIT;
                 case "load" -> this.loadProgram(splitRawCommand);
                 case "onestep" -> this.controller_interpreter.runOneStep(splitRawCommand);
                 case "allstep" -> this.controller_interpreter.runAllStep(splitRawCommand);
                 default -> {
                     return -1;
                 }
             }
         }
         catch (Exception exception) {
             System.out.println(exception.getMessage());
         }
         return 0;
    }
}
