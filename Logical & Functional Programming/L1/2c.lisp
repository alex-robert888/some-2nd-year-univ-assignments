
#|
    member_list(Atom, [l1...ln]) = {
        true, if Atom == l1
        false, if n == 0
        member_list(Atom, [l2...ln])
    }
|#
(defun member_list (number lst)
    (cond 
        ((null lst) nil)
        ((eq number (car lst)) t)
        (t (member_list number (cdr lst)))
    )
)

; Wrapper sort function
(defun sort_list (lst)
    (bubble_sort nil lst t))

#|
    bubble_sort([l1...ln], [c1...cm], is-done) = {
        bubble_sort([], [l1...ln], true), if m == 0 && is-done == true
        bubble_sort([l1...lnc1..cm], [], is-done), if m == 1
        bubble_sort([l1...ln], [c2...cm], is-done), if c1 == c2
        bubble_sort([l1...lnc1], [c2...cm], is-done), if c1 <= c2
        bubble_sort([l1...lnc2], [c3...cm], false), otherwise
    }
|#
(defun bubble_sort (lst current-list is-done)
    (cond 
        ((null current-list) (if is-done lst (bubble_sort nil lst t)))
        ((null (cdr current-list))(bubble_sort (append lst current-list) nil is-done))
        ((= (car current-list) (cadr current-list)) (bubble_sort lst (cdr current-list) is-done))
        ((<= (car current-list) (cadr current-list)) (bubble_sort (append lst (list (car current-list)))(cdr current-list) is-done))
		(t (bubble_sort (append lst (list (cadr current-list))) (cons (car current-list) (cddr current-list)) nil))
    )
)

(write (sort_list '(5 2 1 2 5 4 3 2 5 6 4 4 3)))  ; 2