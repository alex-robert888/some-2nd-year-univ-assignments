package Model;

import com.sun.source.tree.Tree;

public class TreeApple implements ITree{
    int age;

    public TreeApple(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String convertToString() {
        return "apple tree of age: " + Integer.toString(age);
    }
}
