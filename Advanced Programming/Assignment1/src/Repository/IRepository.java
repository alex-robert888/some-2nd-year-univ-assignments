package Repository;

import Model.ITree;
import Repository.Exceptions.*;

public interface IRepository {
    void store(ITree tree) throws RepositoryFullException;
    void delete(ITree tree) throws NotExistingTreeException;
    ITree[] getStateCopy();
}
