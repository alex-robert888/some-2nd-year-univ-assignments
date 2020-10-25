
/*
    SORTED LISTS MERGING FUNCTION (WITH NO DUPLICATES)

    Flow Model: (i, i, o)

    merge_sorted_lists_no_duplicates([l11...l1n], [l21...l2m], [m1...mp]) = {
        [], if n = 0 && m = 0

        merge_sorted_lists_no_duplicates([l12...l1n], [], OldMergedList, PrevElement), if m = 0 && l11 == PrevElement
        l11 (+) merge_sorted_lists_no_duplicates([l12...l1n], [], OldMergedList, l11), if m = 0 && l11 != PrevElement

        merge_sorted_lists_no_duplicates([]], [l22...l2m], OldMergedList, PrevElement), if m = 0 && l21 == PrevElement
        l21 (+) merge_sorted_lists_no_duplicates([], [l22...l2m], OldMergedList, l21), if m = 0 && l21 != PrevElement

        merge_sorted_lists_no_duplicates([l12...l1n], [l21...l2m], OldMergedList, PrevElement), l11 == PrevElement
        merge_sorted_lists_no_duplicates([l11...l1n], [l22...l2m], OldMergedList, PrevElement), l21 == PrevElement
        l11 (+) merge_sorted_lists_no_duplicates([l12...l1n], [l21...l2m], [m1...mp]), if l11 < l21
        l21 (+) merge_sorted_lists_no_duplicates([l11...l1n], [l22...l2m], [m1...mp]), if l11 > l21
        merge_sorted_lists_no_duplicates([l11...l1n], [l21...l2m], [m1...mp], otherwise
    }
*/


merge_sorted_lists_no_duplicates([], [], [], _) :- !.

merge_sorted_lists_no_duplicates([H1 | T1], [], MergedList, PrevElement) :-
    H1 =:= PrevElement, ! -> MergedList = OldMergedList, merge_sorted_lists_no_duplicates(T1, [], OldMergedList, PrevElement);
    !, MergedList = [H1 | OldMergedList], merge_sorted_lists_no_duplicates(T1, [], OldMergedList, H1).

merge_sorted_lists_no_duplicates([], [H2 | T2], MergedList, PrevElement) :- 
    H2 =:= PrevElement, ! -> MergedList = OldMergedList, merge_sorted_lists_no_duplicates([], T2, OldMergedList, PrevElement);
    !, MergedList = [H2 | OldMergedList], merge_sorted_lists_no_duplicates([], T2, OldMergedList, H2).

merge_sorted_lists_no_duplicates([H1 | T1], [H2 | T2], MergedList, PrevElement) :-
    H1 =:= PrevElement -> MergedList = OldMergedList, merge_sorted_lists_no_duplicates(T1, [H2 | T2], OldMergedList, PrevElement), !;
    H2 =:= PrevElement -> MergedList = OldMergedList, merge_sorted_lists_no_duplicates([H1 | T1], T2, OldMergedList, PrevElement), !;
    H1 =:= H2 -> MergedList = OldMergedList, merge_sorted_lists_no_duplicates([H1 | T1], T2, OldMergedList, PrevElement), !;
    H1 < H2 -> MergedList = [H1 | OldMergedList], merge_sorted_lists_no_duplicates(T1, [H2 | T2], OldMergedList, H1), !;
    H1 > H2 -> MergedList = [H2 | OldMergedList], merge_sorted_lists_no_duplicates([H1 | T1], T2, OldMergedList, H2).
