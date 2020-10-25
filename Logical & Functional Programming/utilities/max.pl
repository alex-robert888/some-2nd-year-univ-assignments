
/*
    Flow model: (i, i, o)

    max(Number1, Number2) = {
        Number1, if Number1 > Number 2
        Number2, if Number2 > Number1
    }
*/

max(Number1, Number2, MaxNumber) :-
    Number1 >= Number2, !,
    MaxNumber is Number1.

max(Number1, Number2, MaxNumber) :-
    MaxNumber is Number2.