package Model;

public class TreeCherry implements ITree{
    int age;

    public TreeCherry(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String convertToString() {
        return "cherry tree of age: " + Integer.toString(age);
    }
}
