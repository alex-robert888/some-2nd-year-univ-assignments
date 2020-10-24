
/*
    member_list(Atom, [l1...ln]) = {
        true, if Atom == l1
        false, if n == 0
        member_list(Atom, [l2...ln])
    }
*/

member_list(Atom, [Atom | T]) :- !.

member_list(Atom, [H | T]) :-
    member_list(Atom, T).