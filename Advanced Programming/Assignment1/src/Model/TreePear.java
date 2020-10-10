package Model;

public class TreePear implements ITree{
    int age;

    public TreePear(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String convertToString() {
        return "pear tree of age: " + Integer.toString(age);
    }
}
