
/*
    append([o1, ..., on], [n1, ..., nm]) = {
        append([o2, ..., on], [n1, ..., nm, o1])
    }
*/

%(i, o)
copy_list([], []).

copy_list([H | T_OldList], [H | T_NewList]) :-
    copy_list(T_OldList, T_NewList).
