#|
    traverse_left(binary_tree, number_of_vertices, number_of_edges) = {
        nil, if k == 0
        nil, if number_of_vertices == number_of_edges + 1
        l1 (+) l2 (+) traverse_left(l3...lk, number_of_vertices + 1, number_of_edges + l2)
    }
|#
(defun traverse_left (binary_tree number_of_vertices number_of_edges)
    (cond
        ((null binary_tree) nil)    
        ((= number_of_vertices (+ 1 number_of_edges)) nil)
        (t (cons (car binary_tree) (cons (cadr binary_tree) (traverse_left (cddr binary_tree) (+ 1 number_of_vertices) (+ (cadr binary_tree) number_of_edges)))))
    )
)


(defun go_left_branch (binary_tree)
    (traverse_left (cddr binary_tree) 0 0)
)

#|
     get_right_subtree(binary_tree, number_of_vertices, number_of_edges) = {
        (), if k == 0
        binary_tree, if number_of_vertices == number_of_edges + 1
         get_right_subtree(l3...lk, number_of_vertices + 1, number_of_edges + l2)
    }
|#
(defun get_right_subtree (binary_tree number_of_vertices number_of_edges)
    (cond
        ((null binary_tree) nil)    
        ((= number_of_vertices (+ 1 number_of_edges)) binary_tree)
        (t (get_right_subtree (cddr binary_tree) (+ 1 number_of_vertices) (+ (cadr binary_tree) number_of_edges)))
    )
)

(defun wrapper_get_right_subtree (binary_tree)
    (get_right_subtree (cddr binary_tree) 0 0)
)

#|
    get_level(b1...bk node level) = {
        -1, if k == 0
        level, if b1 == node
        get_level(go_right_branch(b1...bk), node level + 1), if get_level(go_left_branch(b1...bk) node level + 1) == -1
        get_level(go_left_branch(b1...bk)), otherwise
    }

|#
(defun get_level (binary_tree node level)
    (cond
        ((null binary_tree) -1)
        ((eq (car binary_tree) node) level)
        ((= -1 (get_level (go_left_branch binary_tree) node (+ 1 level))) (get_level (wrapper_get_right_subtree binary_tree) node (+ 1 level)))
        (t (get_level (go_left_branch binary_tree) node (+ 1 level)))
    )
)




; TEST 

(write (get_level '(a 2 b 2 c 1 i 0 f 1 g 0 d 2 e 0 h 0) 'f 0))

#|
        a
       /  \
      b    d 
     /\    /\
    c  f  e  h
   /  /
  i  g 
|#