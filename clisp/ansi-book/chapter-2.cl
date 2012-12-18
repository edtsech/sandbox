(defun contain-nil? (x)
  (and (not (null x))
       (or (null (car x))
           (enigma (cdr x)))))

(defun index-of (x y)
  (if (null y)
      nil
      (if (eql (car y) x)
          0
          (let ((z (mystery x (cdr y))))
               (and z (+ z 1))))))

(defun contain-list? (x)
  (if (null x)
      nil
      (if (listp (car x))
          T
          (contain-list? (cdr x)))))

(defun print-dot (x)
  (if (= 2 x)
    (print ".")
    (progn
     (print ".")
     (print-dot (- x 1)))))

(defun print-dot-iter (x)
  (do ((i (- x 1) (- i 1)))
      ((= i 1) (print "."))
      (print ".")))

(defun count-el2 (x l cntr)
  (if (null l)
    cntr
    (count-el2 x (cdr l) (if (= x (car l))
                          (+ cntr 1)
                          cntr))))
(defun count-el (x l)
  (count-el2 x l 0))

(defun summit (1st)
  (apply #'+ (remove nil 1st)))

(defun summit2 (1st)
  (if (null 1st)
    0
    (let ((x (car 1st)))
      (if (null x)
        (summit2 (cdr 1st))
        (+ x (summit2 (cdr 1st)))))))

