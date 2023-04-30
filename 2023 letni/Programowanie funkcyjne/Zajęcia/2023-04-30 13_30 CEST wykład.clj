(in-ns 'user)
(set! *warn-on-reflection* true)

(+ 4 5)

;; Środowisko uruchomieniowe chce wiedzieć, jaka jest wartość
;; reprezentowana przez symbol x.
;; Proces odkrywania, jaka wartość odpowiada symbolowi x
;; (jest przez niego reprezentowana) nazywamy WIĄZANIEM.
;; Proces próbuje ustalić, jaka jest wartość związana z symbolem (x).
(def x 5)
(+ x 3)

(quote y)
(class (quote y))
(name  (quote y))
(class (name  (quote y)))
(symbol "abc")

(quote y)
(name (identity 'y))
(name (identity `y))

(namespace (identity 'y))
(namespace (identity `y))

(defn eval-fn
  [arg s]
  (assert (string? arg))
  (assert (string?   s))
  (let [args (symbol    arg)
        form (read-string s)]

    (list (quote fn) (vector args) form)))

(def f1 (eval (eval-fn "x" "(+ x 1 2 3 4)")))

;; int main (void) {
;;   return 0;
;; }
