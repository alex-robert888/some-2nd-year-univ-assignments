
#|

(1 2 (3 (4 5) 6 (7)) 3)

(1, (2))

depth(l1...ln, maxx) = {
    0, if n = 0
    depth(l2...ln, maxx), if l1 number
    1 + depth(l2...ln, max(maxx, depth(l1))))

|#
(defun depth (lst maxx)
    (cond 
        ((null lst) 0)
        ((numberp (car lst)) (depth (cdr lst) maxx))
        (t (+ 1 (depth (cdr lst) (max maxx (depth (car lst) 0)))))
    )
)

(print (depth '(1 (2 3) (2 (3)) 5) 0))
(print (depth '(1 5 (1)) 0))
