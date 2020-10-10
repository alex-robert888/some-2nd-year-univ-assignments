package Model;

import com.sun.source.tree.Tree;

public class TreeApple implements ITree{
    int age;

    public TreeApple(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String convertToString() {
        return "apple tree of age: " + Integer.toString(age);
    }
}
