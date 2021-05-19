
#lang racket
(provide (all-defined-out))

;; definition of structures for MUPL programs - Do NOT change
(struct var  (string) #:transparent)  ;; a variable, e.g., (var "foo")
(struct int  (num)    #:transparent)  ;; a constant number, e.g., (int 17)
(struct add  (e1 e2)  #:transparent)  ;; add two expressions
(struct ifgreater (e1 e2 e3 e4)    #:transparent) ;; if e1 > e2 then e3 else e4
(struct fun  (nameopt formal body) #:transparent) ;; a recursive(?) 1-argument function
(struct call (funexp actual)       #:transparent) ;; function call
(struct mlet (var e body) #:transparent) ;; a local binding (let var = e in body) 
(struct apair (e1 e2)     #:transparent) ;; make a new pair
(struct fst  (e)    #:transparent) ;; get first part of a pair
(struct snd  (e)    #:transparent) ;; get second part of a pair
(struct aunit ()    #:transparent) ;; unit value -- good for ending a list
(struct isaunit (e) #:transparent) ;; evaluate to 1 if e is unit else 0

(struct closure (env fun) #:transparent) 

(define (racketlist->mupllist rst)
  (if (null? rst)
      (aunit)
      (apair (car rst) (racketlist->mupllist (cdr rst)))))

(define (mupllist->racketlist pst)
  (cond [(aunit? pst) null]
        [(apair? pst) (cons (apair-e1 pst) (mupllist->racketlist (apair-e2 pst)))]))



;; lookup a variable in an environment
;; Do NOT change this function
(define (envlookup env str)
  (cond [(null? env) (error "unbound variable during evaluation" str)]
        [(equal? (car (car env)) str) (cdr (car env))]
        [#t (envlookup (cdr env) str)]))

;; Do NOT change the two cases given to you.  
;; DO add more cases for other kinds of MUPL expressions.
;; We will test eval-under-env by calling it directly even though
;; "in real life" it would be a helper function of eval-exp.
(define (eval-under-env e env)
  (cond [(var? e) 
         (envlookup env (var-string e))]
        [(int? e) e]
        [(add? e) 
         (let ([v1 (eval-under-env (add-e1 e) env)]
               [v2 (eval-under-env (add-e2 e) env)])
           (if (and (int? v1)
                    (int? v2))
               (int (+ (int-num v1) 
                       (int-num v2)))
               (error "MUPL addition applied to non-number")))]
        [(ifgreater? e)
         (let ([v1 (eval-under-env (ifgreater-e1 e) env)]
                [v2 (eval-under-env (ifgreater-e2 e) env)])
               (if (and (int? v1) (int? v2))
                   (if (> (int-num v1) (int-num v2))
                       (eval-under-env (ifgreater-e3 e) env)
                       (eval-under-env (ifgreater-e4 e) env))
                   (error "greater applied to non-number")))]
        [(fun? e) (closure env e)]
        [(closure? e) e]
        [(call? e)
         (let ([fc (eval-under-env (call-funexp e) env)])
           (if (closure? fc)
               (let ([argval (eval-under-env (call-actual e) env)]
                     [fucname (fun-nameopt (closure-fun fc))]
                     [argname (fun-formal  (closure-fun fc))]
                     [fucexpr (fun-body    (closure-fun fc))])
                 (if (string? fucname)
                     (eval-under-env fucexpr (cons (cons fucname fc) 
                                                           (cons (cons argname argval) (closure-env fc))))
                     (eval-under-env fucexpr (cons (cons argname argval) (closure-env fc))))) 
               (error "call first arg applied to non-closure")))]
        [(mlet? e)
         (if (string? (mlet-var e))
             (let ([str (mlet-var e)]
                   [val (eval-under-env (mlet-e e) env)])
               (eval-under-env (mlet-body e) (cons(cons str val ) env)))
             (error "mlet first arg applied to non-string"))]
        [(apair? e)
         (apair (eval-under-env (apair-e1 e) env)
                (eval-under-env (apair-e2 e) env))]
        [(fst? e)
         (let ([v (eval-under-env (fst-e e) env)])
           (if (apair? v)
               (apair-e1 v)
               (error "pair applied to non-pair")))]
        [(snd? e)
         (let ([v (eval-under-env (fst-e e) env)])
           (if (apair? v)
               (apair-e2 v)
               (error "pair applied to non-pair")))]
        [(isaunit? e)
           (if (aunit? (eval-under-env (isaunit-e e) env)) (int 1) (int 0))]
        [(aunit? e) (aunit)]
        [#t (error (format "bad MUPL expression: ~v" e))]))

;; Do NOT change
(define (eval-exp e)
  (eval-under-env e null))
        

(define (ifaunit e1 e2 e3) 
         (ifgreater (isaunit e1) (int 0) e2 e3))

(define (mlet* lstlst e2)
  (if (null? lstlst)
      e2
      (mlet (car (car lstlst)) (cdr (car lstlst)) (mlet* (cdr lstlst) e2))))

(define (ifeq e1 e2 e3 e4)
  (mlet "_x" e1 
        (mlet "_y" e2 
            (ifgreater (var "_x") (var "_y") e4 (ifgreater (var "_y") (var "_x") e4 e3)))))


(define mupl-map
    (fun #f "f" 
        (fun "calc" "lst" 
            (ifaunit (var "lst")
                (aunit)
                (apair (call (var "f") (fst (var "lst"))) (call (var "calc") (snd (var "lst"))))))))

(define mupl-mapAddN
    (fun #f "y"
        (call mupl-map (fun #f "x" (add (var "x") (var "y"))))))



(struct fun-challenge (nameopt formal body freevars) #:transparent) ;; a recursive(?) 1-argument function

;; We will test this function directly, so it must do
;; as described in the assignment
(define (compute-free-vars e) "CHANGE")

;; Do NOT share code with eval-under-env because that will make
;; auto-grading and peer assessment more difficult, so
;; copy most of your interpreter here and make minor changes
(define (eval-under-env-c e env) "CHANGE")

;; Do NOT change this
(define (eval-exp-c e)
  (eval-under-env-c (compute-free-vars e) null))
