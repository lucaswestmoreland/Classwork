; This is the copy function that is implemented to avoid and alteration of the original source input
(define (copy list) 
	    (cond (null? list)
		(cons (copy (car list)) (copy (cdr list)))))


; This is the main function that replaces the value being searched for with the "replace-with" value. 
(define replace (lambda (source search-for replace-with)
                
				(if (equal? (copy source) search-for) 
                     replace-with

						(if (null? (copy source))
                        '()
                    
								(if (equal? search-for (car (copy source)))
                             	(cons replace-with (replace (cdr (copy source)) search-for replace-with)) ;checks to see if the first value in source is what we are searching for. 
																								          ;Will replace and then check the rest of source for any remaining matches
                             			(if (list? (car (copy source))) ;
										(cons (replace (car (copy source)) search-for replace-with) (replace (cdr (copy source)) search-for replace-with)) ;
                         				(cons (car (copy source)) (replace (cdr (copy source)) search-for replace-with))))))))
;Supplied tests
(display (replace 1 1 2))
(newline)
(display (replace '(a (b c) d) '(b c) '(x y)))
(newline)
(display (replace '(a (b c) (d (b c))) '(b c) '(x y)))
(newline)
(display (replace '(a b c) '(a b) '(x y)))
(newline)
(display (replace '(a b c) '(b c) '(x y)))
(newline)

;User tests
(display (replace '((b c) a d) '(b c) '(x y)))
(newline)
(display(replace '(a a b (c b a) d b) '(c b a) '(z y x)))
(newline)
(display(replace '(a d d b (c d) d c) '(d) '(x)))
(newline)
(display(replace '(a a a (a b b) b a b c c) '(a) '(z)))
