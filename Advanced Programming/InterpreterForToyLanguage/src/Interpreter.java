import Controller.Controller_Interpreter;
import Model.ADT.ADTStack;
import Model.ADT.IADTStack;
import Repository.RepositoryMemoryBased_Interpreter;
import View.Interpreter_View;

import java.util.Deque;
import java.util.LinkedList;

public class Interpreter {
    public static void main(String[] args) {
        RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter = new RepositoryMemoryBased_Interpreter();
        Controller_Interpreter controller_interpreter = new Controller_Interpreter(repositoryMemoryBased_interpreter);
        Interpreter_View interpreter_view = new Interpreter_View(controller_interpreter);
        interpreter_view.run();
    }
}
