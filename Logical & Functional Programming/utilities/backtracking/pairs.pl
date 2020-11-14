
/*
    pair(Number, List, Pair) = {
        [Number, l1], if Number < l1
        pair(Number, l2...ln, Pair)
    }
*/


% (i, i, o)
pair(Number, [H | _], [Number, H]) :-
    Number < H.

pair(Number, [_ | T], Pair) :-
    pair(Number, T, Pair).


/*
    all_pairs(List, ResultList) = {
        ResultList, n = 0
        
    }
*/