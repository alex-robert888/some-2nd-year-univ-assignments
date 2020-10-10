import Repository.RepositoryTrees;
import Controller.ControllerTrees;
import View.ViewTrees;


public class Application {
    public static void main(String[] args) {
        RepositoryTrees repositoryTrees = new RepositoryTrees();
        ControllerTrees controllerTrees = new ControllerTrees(repositoryTrees);
        ViewTrees viewTrees = new ViewTrees(controllerTrees);
        viewTrees.run();
    }
}
