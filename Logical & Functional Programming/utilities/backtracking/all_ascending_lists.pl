

/*
    IDEA

    [1, 2, 1, 3, 4, 5, 4]
*/


/*
    all_ascending_lists_starting_from([l1...ln], [a1...am]) = {
        [l1l2], if n = 2 && l1 < l2
        l1 (+) all_ascending_lists_starting_from(l2...ln, [a1...am]), if (l1 < l2)
        all_ascending_lists_starting_from(l2...ln, [a1...am]), otherwise
    }
*/

% Flow model: (i, i) (i, o) non-deterministic

% if we have 2 elements remaining in the list
all_ascending_sublists_starting_from([H1, H2], [H1, H2]) :-
    H1 < H2.

% if the current element is NOT less than the next element
all_ascending_sublists_starting_from([H1, H2 | _], [H1]) :-
    H1 >= H2, !.

% all_ascending_sublists_starting_from([H1, H2 | T], [H1 | AscendingSublist]) :-
%     test_2_findall.

% if the current element is less than the next element
all_ascending_sublists_starting_from([H1, H2 | T], [H1 | AscendingSublist]) :-
    H1 < H2,
    all_ascending_sublists_starting_from([H2 | T], AscendingSublist).



/*
    all_ascending_sublists([l1...ln], [a1...am]) = {
        all_ascending_sublists_starting_from(l1...ln) (+) all_ascending_sublists([l2...ln], [a1...am])
    }
*/

% Flow model: (i, o)
all_ascending_sublists(List, AscendingSublist) :-
    all_ascending_sublists_starting_from(List, AscendingSublist).

all_ascending_sublists([_ | T], AscendingSublist) :-
    all_ascending_sublists(T, AscendingSublist).


test_1_findall :-
    findall(X, all_ascending_sublists([2, 1, 3, 2, 5], X), L),
    write(L).

test_2_findall :-
    findall(X, all_ascending_sublists([1, 2, 1, 3, 4, 5, 4], X), L),
    write(L).







