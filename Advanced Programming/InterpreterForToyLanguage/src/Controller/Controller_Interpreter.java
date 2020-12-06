package Controller;

import Model.ProgramState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.RepositoryMemoryBased_Interpreter;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller_Interpreter {
    private final RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter;
    private ExecutorService executor;

    public Controller_Interpreter(RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter) {
        this.repositoryMemoryBased_interpreter = repositoryMemoryBased_interpreter;
    }

    private Map<Integer, IValue> garbageCollector(List<Integer> symTableAddr, Map<Integer,IValue> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()) || heap.containsKey(e.getKey())) // to correct this
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public  ArrayList<ProgramState> removeCompletedProgramState(ArrayList<ProgramState> programStatesList) {
        return (ArrayList<ProgramState>) programStatesList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    private Set<IValue> getSetOfSymbolTablesValues(ArrayList<ProgramState> programStatesList) {
        Set<IValue> mergedSet = new HashSet<IValue>();
        programStatesList.forEach(programState -> {mergedSet.addAll(programState.getSymbolTable().getContent().values());});
        return mergedSet;
    }

    public void runAllStep() throws Exception {
        this.executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        ArrayList<ProgramState> prgList=removeCompletedProgramState(repositoryMemoryBased_interpreter.getProgramStatesList());
        while(prgList.size() > 0){
            garbageCollector(getAddrFromSymTable(getSetOfSymbolTablesValues(prgList)), prgList.get(0).getHeap().getContent());
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedProgramState(repositoryMemoryBased_interpreter.getProgramStatesList());
        }
        executor.shutdownNow();

        // update the repository state
        this.repositoryMemoryBased_interpreter.setProgramStatesList(prgList);
    }

    void oneStepForAllPrg(ArrayList<ProgramState> programStatesList) throws Exception {
        //before the execution, print the PrgState List into the log file
/*        programStatesList.forEach(prg -> {
            try {
                repositoryMemoryBased_interpreter.logProgramState(prg);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });*/

        //prepare the list of callables
        List<Callable<ProgramState>> callList = programStatesList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.runOneStep();}))
                .collect(Collectors.toList());

        //start the execution of the callables
        //it returns the list of new created ProgramStates (namely threads)
        List<ProgramState> newPrgList = this.executor.invokeAll(callList). stream()
                . map(future -> {
                    try { return future.get();}
                    catch(Exception exc) {
                        exc.printStackTrace();
                    }
                    return null;
                })
                .filter(p -> p!=null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        programStatesList.addAll(newPrgList);
        //------------------------------------------------------------------------------

        //after the execution, print the ProgramState List into the log file
        programStatesList.forEach(prg -> {
            try {
                repositoryMemoryBased_interpreter.logProgramState(prg);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        //Save the current programs in the repository
        repositoryMemoryBased_interpreter.setProgramStatesList(programStatesList);
    }
}
