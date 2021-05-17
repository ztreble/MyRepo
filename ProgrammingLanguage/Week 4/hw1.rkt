
#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file

;; put your code below

(define (sequence low high stride)
  (if (> low high)
      null
      (cons low (sequence (+ low stride) high stride))))


(define (string-append-map xs suffix)
  (map (lambda(x)
         (string-append x suffix)) xs))
  
(define (list-nth-mod xs n)
  (cond [(< n 0) error "list-nth-mod: negative number"]
        [(= (length xs) 0) error "list-nth-mod: empty list"]
        [#t (car (list-tail xs (remainder n (length xs))))]))

(define (stream-for-n-steps s n)
  (if (= n 0)
      null
      (let ([next (s)])
        (cons (car next) (stream-for-n-steps (cdr next) (- n 1))))))


(define funny-number-stream
  (letrec ([helper (lambda (x) (cons (cond [(= 0 remainder x 5) (* -1 x)]
                                 [#t x]) (lambda () (helper (+ 1 x)))))])
  (lambda () (helper 1))))


(define dan-then-dog
  (letrec ([helper (lambda(x) ((let ([tmp (cond [(= x "dog.jpg") "dan.jpg"]
                                          [#t "dog.jpg"])])
                               cons (tmp) (lambda() (helper(tmp))))))])
    (lambda () (helper "dog.jpg"))))

(define (stream-add-zero s)
  (lambda ()
    (let ([pr (s)])
      (cons (cons 0 (car pr)) (stream-add-zero (cdr pr))))))


;;8
(define (cycle-lists xs ys)
  (letrec ([lenx (length xs)]
        [leny (length ys)])
  (letrec ([helper (lambda(x) ((let ([tmp1 (remainder lenx x)]
                                     [tmp2 (remainder leny x)])
                                 (cons (cons (car (list-tail xs tmp1)) (car (list-tail ys tmp2)))
                                 (lambda() (helper(+ 1 x)))))))])
    (lambda () (helper 1)))))


;;9
(define (vector-assoc v vec)
  (letrec ([f (lambda (x)
                (if (< x (vector-length vec))
                    (let ([val (vector-ref vec x)])
                      (if (and (cons? val) (equal? v (car val)))
                          val
                          (f (+ x 1))))
                    #f))])
    (f 0)))


;;10
(define (cached-assoc xs n)
    (letrec ([memo (make-vector n #f)]
             [index 0])
    (lambda (x)
      ;;如果能在缓存序列中得到
        (let ([ans (vector-assoc x memo)])
            (if ans
                ans 
                (let ([new-ans (assoc x xs)])
                        (begin
                            (vector-set! memo index new-ans)
                            (set! index (remainder (+ index 1) (vector-length memo)))
                            new-ans)))))))



         