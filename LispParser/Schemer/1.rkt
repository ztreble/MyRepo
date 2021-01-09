#lang sicp
(define nth-element
  (lambda (lst n)
    (if (null? lst)
        (report-list-too-short n)
        (if (zero? n)
            (car lst)
            (nth-element (cdr lst) (- n 1))))))
(define report-list-too-short
  (lambda (n)
    (eopl:error 'nthelement
                "List too short by ~s elements.~%" (+ n 1))))