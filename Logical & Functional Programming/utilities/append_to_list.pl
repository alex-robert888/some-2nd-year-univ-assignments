
/*
    append_to_list([o1...on], [l1...ln], Atom) = {

    }

*/

% (i, o, i)
append_to_list([], [Atom], Atom) :- !.

append_to_list([H | T_OldList], [H | T_NewList], Atom) :-
    append_to_list(T_OldList, T_NewList, Atom).