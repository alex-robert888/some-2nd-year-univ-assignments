
/*
    all_ascending_lists_starting_from(List, AscendingSublist) = {
        [a1...anl1], if 
    }
*/

% Flow model: (i, o)

% if we have 2 elements remaining in the list
all_ascending_sublists_starting_from([H1, H2], [H1, H2]).

% if the current element is NOT less than the next element
all_ascending_sublists_starting_from([H1, H2 | _], [H1]) :-
    H1 >= H2, !.

% if the current element is less than the next element
all_ascending_sublists_starting_from([H1, H2 | T], [H1 | AscendingSublist]) :-
    H1 < H2,
    all_ascending_sublists_starting_from([H2 | T], AscendingSublist).



% all_ascending_sublists(List, AscendingSublist)
all_ascending_sublists(List, AscendingSublist) :-
    all_ascending_sublists_starting_from(List, AscendingSublist).

all_ascending_sublists([_ | T], AscendingSublist) :-
    all_ascending_sublists(T, AscendingSublist).






