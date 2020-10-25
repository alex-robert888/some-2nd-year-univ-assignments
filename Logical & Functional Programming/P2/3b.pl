

/*
    list_member(List, Atom) = {
        false, if n == 0
        true, if Atom == l1
        list_member([l2...ln], Atom)
    }
    Flow Model: list_member(i, i)     
*/
my_list_member([], _) :-
    fail(), !.

my_list_member([Atom | _], Atom) :- !.

my_list_member([_ | T], Atom) :-
    my_list_member(T, Atom).

/*
    NOTE: calling this function, we must make sure that List2 contains no duplicates
    merge_2_lists_no_duplicates(List1, List2, MergedList) = {
        [l21...l2m], if n = 0
        l11 (+) merge_2_lists_no_duplicates([l12...l1n], [l21...l2m], MergedList), if !list_member(MergedList, l11)
        merge_2_lists_no_duplicates([l12...l1n], [l21...l2m], MergedList), if list_member(MergedList, l11)
    }
    Flow Model: merge_2_lists_no_duplicates(i, i, o)
*/
merge_2_lists_no_duplicates([], List2, List2) :- !.

merge_2_lists_no_duplicates([H_List1 | T_List1], List2, [H_List1 | OldMergedList]) :-
    merge_2_lists_no_duplicates(T_List1, List2, OldMergedList),
    \+my_list_member(OldMergedList, H_List1), !.

merge_2_lists_no_duplicates([H_List1 | T_List1], List2, OldMergedList) :-
    merge_2_lists_no_duplicates(T_List1, List2, OldMergedList).



/*
    merge_all_sublists(HetList, MergedList) = {
        [], if HetList is empty
        merge_all_sublists([h2...hn], MergedList), if h1 not a list
        merge_all_sublists([h2...hn], merge_2_lists_no_duplicates(h1, [m1...mp])), if h1 is a list
    }    

    Flow Model: (i, o)
*/
merge_all_sublists([], []).

merge_all_sublists([H_HetList | T_HetList], MergedList) :-
    \+is_list(H_HetList), !,
    merge_all_sublists(T_HetList, MergedList).

merge_all_sublists([H_HetList | T_HetList], Result) :-
    is_list(H_HetList),
    merge_all_sublists(T_HetList, OldMergedList),
    merge_2_lists_no_duplicates(H_HetList, OldMergedList, Result).

test_3b :-
    merge_all_sublists([1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5, [1, 1, 11], 8], X),
    write(X).
