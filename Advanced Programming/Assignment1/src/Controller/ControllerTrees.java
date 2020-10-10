package Controller;


import Repository.*;
import View.ViewTrees;
import Model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ControllerTrees {
    IRepository repositoryTrees;

    public ControllerTrees(IRepository repositoryTrees) {
        this.repositoryTrees = repositoryTrees;
    }

    public void addTree(String type, int age) throws Exception{
        ITree tree = convertToTree(type, age);
        this.repositoryTrees.store(tree);
    }

    public void removeTree(String type, int age) throws Exception {
        ITree tree = convertToTree(type, age);
        this.repositoryTrees.delete(tree);
    }

    public ArrayList<String> filterTrees(int age) throws Exception {
        if (age < 0) throw new Exception("Age must be a natural number!");

        ITree[] stateCopy = this.repositoryTrees.getStateCopy();
        ArrayList<String> filteredTrees = new ArrayList<String>();
        for (ITree tree : stateCopy) {
            if (tree.getAge() > age) {
                filteredTrees.add(tree.convertToString());
            }
        }

        return filteredTrees;
    }

    private ITree convertToTree(String type, int age) throws Exception{
        return switch (type) {
            case "apple" -> new TreeApple(age);
            case "pear" -> new TreePear(age);
            case "cherry" -> new TreeCherry(age);
            default -> throw new Exception("Wrong tree type");
        };
    }
}
