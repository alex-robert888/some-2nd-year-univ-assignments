

% remove_atom(List, NewList, Atom)
% Predicate to check whether two atoms are equal
atoms_equal(Atom, Atom).


% print_list([]).
% print_list(NewList) :-
%     Head = NewList,
%     Head = [H | T],
%     format('~w', [H]),
%     print_list(T).



% try_append(H, NewList, H) :-
%     write('t').
    

% append_or_not(H, NewList, Atom) :- 
%     try_append(H, NewList, Atom);
%     write('f').


% remove_atom([], NewList, Atom):-
%     write(NewList), nl.

% remove_atom([H|T], NewList, Atom) :- 
%     append_or_not(H, NewList, Atom),
%     remove_atom(T, NewList, Atom).


/*
    mathematical model
    remove_atom(OldList, NewList, Atom) = {
        []], if OldList is empty
        remove_atom([ol2, ol3, ..., oln], NewList, Atom), if ol1 == Atom (the NewList should not get updated if equals to Atom)
        remove_atom([ol2, ol3, ..., oln]], [nl1, nl2, ..., ol1], Atom), otherwise 
    }
*/

remove_atom([], [], Atom) :-
    write(NewList).

remove_atom([H | T], NewList, Atom) :- 
    H == Atom,!,
    remove_atom(T, NewList, Atom).

remove_atom([H | T], [H | NewList], Atom) :-
    remove_atom(T, NewList, Atom).


test_1 :- 
    remove_atom([1, 2, 1, 3, 1, 1, 5], X, 1),
    write(X), nl.

test_2 :- 
    remove_atom([1, 1, 1], X, 1),
    write(X), nl.

test_3 :-
    remove_atom([], X, 5),
    write(X), nl.
