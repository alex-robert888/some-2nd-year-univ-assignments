
/*
    list_increment_all(InitialList, FinalList) = {
        [], if n = 0
        (i1 + 1) (+) list_increment_all([i2...in], [f1...fm])
    }

    Flow Model: (i, o)
*/

list_increment_all([], []) :- !.

list_increment_all([H_InitialList | T_InitialList], FinalList) :-
    IncrementedValue is H_InitialList + 1, 
    FinalList = [IncrementedValue | OldFinalList],
    list_increment_all(T_InitialList, OldFinalList).