
% First, cycle through the list
% Second, for each element of the list, 


/*
    number_atom(ListOfAtoms, ListOfPairs) = {
        print ListOfPairs, if number_atom([], ListOfPairs)
        addInListOfPairs()e
    }
*/

q1 :- 
    append([1, 2], [3], X),
    write(X).




% number_atoms(Atoms, Pairs)

% If we iterated through the whole list
number_atoms([], Pairs) :-
    nl, write('pairs: '),
    write(Pairs).

% If we hadn't iterated throught the whole list, we check if the current pair is to be modified
number_atoms([H_Atoms | T_Atoms], [[H_Pair | T_Pair] | T_Pairs]) :-
    H_Atoms == H_Pair,
    T_Pair_Incremented is T_Pair + 1,
    number_atoms(T_Atoms, [[H_Pair | [T_Pair_Incremented]] | T_Pairs]).

% Here the program gets whenever the current atom did not match the current pair 
% and all pairs have been checked
number_atoms([H_Atoms | T_Atoms], []) :-
    number_atoms(T_Atoms, [[H_Atoms, 1] | Pairs]).

% Here the program gets whenever the current atom did not match the current pair 
% and there are still pairs to be checked
% Hence, here we go to the next pair
number_atoms(Atoms, [H_Pairs | T_Pairs]) :-
    number_atoms(Atoms, T_Pairs).

test_number_atoms :- 
    number_atoms([1, 2, 1, 1, 3, 2], X),
    write(X).