(defun sum-naturals (n i result)
  (if (> i n)
      result

      (sum-naturals n (1+ i) (+ result i))))
