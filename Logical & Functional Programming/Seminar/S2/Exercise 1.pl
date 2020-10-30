
/*
    Mathematical model: 
        0, if n = 0
        1 + count_occurences(Atom, [l2, l3, .., ln], OldCounter), if Atom == l1
        count_occurences(Atom, [l2, l3, .., ln], OldCounter), if Atom == l1 
*/
count_occurences(_, [], 0) :- !.

count_occurences(H, [H | T], Counter) :-
    !,
    count_occurences(Atom, T, OldCounter),
    Counter is OldCounter + 1.

count_occurences(Atom, [H | T], _) :-
    count_occurences(Atom, T, _).

test_1_ex_1 :- count_occurences(1, [1, 2, 1, 1, 3, 4, 1], X).

/*
    Mathematical model: 
    
    count_occurences_with_accumulator(Atom, List, Accumulator) = {

    } 

*/
