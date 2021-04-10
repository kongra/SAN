(defun get-last (l result)
  (if l
      (get-last (rest l) (first l))

      result))

(defvar l3 (loop for i from 0 to 999999
                 collect i))
