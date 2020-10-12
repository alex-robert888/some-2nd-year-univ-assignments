package Model;

public class TreePear implements ITree{
    int age;

    public TreePear(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String convertToString() {
        return "pear tree of age: " + Integer.toString(age);
    }
}
