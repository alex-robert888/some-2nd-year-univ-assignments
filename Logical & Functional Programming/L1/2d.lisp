

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

(write (member_list 3 '(1 2 3)))

#|
    list_to_set(l1...ln) = {
        [], if n == 0
        l1 (+) list_to_set(l2...ln), if not member_list(l1, l2...ln )
        list_to_set(l2...ln)
    }
|#
(defun list_to_set (lst)
    (cond 
        ((null lst) nil)
        ((not (member_list (car lst) (cdr lst))) (cons (car lst) (list_to_set (cdr lst))))
        (t (list_to_set (cdr lst)))
    )
)

(print (list_to_set '(1 2 2 3 1)))


