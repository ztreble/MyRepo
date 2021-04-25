fun is_older(xs : (int*int*int), ys :(int*int*int)) =
    if #1 xs < #1 ys
    then  true
    else
	if #1 xs > #1 ys
	then false
	else
	    if #2 xs < #2 ys
	    then  true
	    else
		if #2 xs > #2 ys
		then false
		else
		     if #3 xs < #3 ys
		     then  true
		     else  false			 
	
									    
fun number_in_month(dates : (int*int*int) list , months : int) =
    if null dates
    then 0
    else
	if #2(hd dates)=months
	then 1+number_in_month(tl dates,months)
	else 0+number_in_month(tl dates,months)

fun number_in_months(dates : (int*int*int) list , months : int list) =
    if null months
    then 0
    else number_in_month(dates,hd months)+number_in_months(dates,tl months)
	
fun dates_in_month(dates : (int*int*int) list , months : int) =
    if null dates
    then []
    else
	if #2(hd dates)=months
	then (hd dates)::dates_in_month(tl dates,months)
	else dates_in_month(tl dates,months)
	
    
fun dates_in_months(dates : (int*int*int) list , months : int list) =
    if null months
    then []
    else
	dates_in_month(dates,hd months)@dates_in_months(dates,tl months)

fun get_nth(strings : string list , n : int) =
    if n = 1
    then (hd strings)
    else
	get_nth(tl strings , n-1)
	       

fun date_to_string(year : int , month : int , day : int) =
    case month of
	1 =>  "January "  ^Int.toString(day)^", "^Int.toString(year)
      | 2 =>  "February " ^Int.toString(day)^", "^Int.toString(year)
      | 3 =>  "March "    ^Int.toString(day)^", "^Int.toString(year)
      | 4 =>  "April "    ^Int.toString(day)^", "^Int.toString(year)
      | 5 =>  "May "      ^Int.toString(day)^", "^Int.toString(year)
      | 6 =>  "June "     ^Int.toString(day)^", "^Int.toString(year)
      | 7 =>  "July "     ^Int.toString(day)^", "^Int.toString(year)
      | 8 =>  "August "   ^Int.toString(day)^", "^Int.toString(year)
      | 9 =>  "September "^Int.toString(day)^", "^Int.toString(year)
      | 10 => "October "  ^Int.toString(day)^", "^Int.toString(year)
      | 11 => "November " ^Int.toString(day)^", "^Int.toString(year)
      | 12 => "December " ^Int.toString(day)^", "^Int.toString(year);
    

fun number_before_reaching_sum(sum : int ,lists : int list) =
    if null lists orelse sum <= (hd lists) 
    then 0
    else number_before_reaching_sum(sum-(hd lists),(tl lists))+1

								   
fun what_month(day : int) =
   number_before_reaching_sum(day,[31,28,31,30,31,30,31,31,30,31,30,31])+1;

fun month_range(day1 : int , day2 : int) =
    if day1 > day2
    then []
    else what_month(day1)::month_range(day1+1,day2);

	     
fun oldest(d : (int*int*int) list) = 
    if null d
    then NONE
    else
	let val tmp_ans = oldest(tl d)
	in
	    if isSome tmp_ans andalso is_older(hd d,(valOf tmp_ans))
	    then tmp_ans 
	    else SOME (hd d)
	end


