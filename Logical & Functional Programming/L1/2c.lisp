
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


#|
    min_from_list(l1...l2, minn) = {
        l1, if n == 1
        min (min_from_list(l2...ln, min(minn, l1)), otherwise
    }
|#
(defun min_from_list (lst minn)
    (cond 
        ((eq 1 (list-length lst)) (car lst))
        (t (min minn (min_from_list (cdr lst) minn)))
    )
)

(write (min_from_list '(4 2 3 7) 1000))

#|
    sort(l1...ln) = {
        [], if n == 1
        l1 (+) sort(l2...ln)
    }
|#
; (defun sort (lst)
;     (cond 
;         (eq 1 (list-length (lst)) (car lst))
;         (t (min ))
;     )
; )