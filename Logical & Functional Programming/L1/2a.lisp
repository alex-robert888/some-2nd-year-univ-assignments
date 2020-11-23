
; (write (cons 1 (cons 2 (cons 3 nil))))
; (terpri)
; (write (cons 'a (cons 'b (cons 'c nil))))
; (terpri)


; prod(l11...l1n, l21...l2n) = {
;     0, if n == 0    
;     l11 * l21 (+) prod(l11...l1n, l21...l2n), otherwise
; }

(defun prod (l1 l2)
    (cond 
        ((null l1) nil)
        (l1 (cons (* (car l1) (car l2)) (prod (cdr l1) (cdr l2))))
    )
)

(write (prod '(1 2 3) '(1 2 3)))


; (defun dublare (l)
;     (cond 
;         ((null l) nil)
;         (l (cons (* 2 (car l)) (dublare (cdr l))))
;     )
; )

; (write (dublare '(1 2 3)))