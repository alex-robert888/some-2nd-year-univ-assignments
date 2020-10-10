package Repository;

import Model.ITree;
import Repository.Exceptions.NotExistingTreeException;
import Repository.Exceptions.RepositoryFullException;


public class RepositoryTrees implements IRepository{
    ITree[] state;
    int size;
    final int CAPACITY = 1000;

    public RepositoryTrees() {
        state = new ITree[CAPACITY];
        size = 0;
    }

    public void store(ITree tree) throws RepositoryFullException {
        if (isFull()) {
            throw new RepositoryFullException("Repository is full!");
        }
        this.state[size++] = tree;
    }

    public void delete(ITree tree) throws NotExistingTreeException {
        for (int i = 0; i < this.size; ++i) {
            if (tree.getClass().equals(this.state[i].getClass()) && tree.getAge() == this.state[i].getAge()) {
                this.deleteFromIndex(i);
                return;
            }
        }
        throw new NotExistingTreeException("Specified tree does not exist!");
    }

    public ITree[] getStateCopy() {
        ITree[] copyState = new ITree[this.size];
        System.arraycopy(this.state, 0, copyState, 0, this.size);
        return copyState;
    }

    private boolean isFull() {
        return this.size == this.CAPACITY;
    }

    private void deleteFromIndex(int index) {
        for (int i = index; i < this.size - 1; ++i) {
            this.state[i] = this.state[i + 1];
        }
        --this.size;
    }
}
