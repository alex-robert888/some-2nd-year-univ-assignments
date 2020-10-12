package Model;

public class TreeCherry implements ITree{
    int age;

    public TreeCherry(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String convertToString() {
        return "cherry tree of age: " + Integer.toString(age);
    }
}
