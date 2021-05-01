

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)

(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

	      (* put your solutions for problem 2 here *)

(*1.a*)
fun all_except_option(str,xs) =
    case xs of
	[] => NONE
      | y::ys' => if same_string(str,y) then
		      if null ys'
		      then
			    SOME []
		      else
		          SOME ys'
		  else
		      case all_except_option(str,ys') of
			  NONE => NONE
			 |_  => let val tmp = all_except_option(str,ys')
			  in
			      SOME (y::(valOf tmp))
			  end
				    
				   
(*1.b*)
fun get_substitutions1(li:(string list)list,s) =
    case li of
	[] => []
      | y::ys' =>let val tmp = all_except_option(s,y)
		 in
		     if isSome tmp
		     then (valOf tmp)@get_substitutions1(ys',s)
		     else []@get_substitutions1(ys',s)
		 end 
		     

(*1.c It's a trade-off*)
fun get_substitutions2(lli:(string list)list,ss:string) =
    let fun helper(li,s)=
	case li of
		      [] => (tl s)
		    | y::ys' => let val tmp = all_except_option(hd s,y)
				in
				    if isSome tmp
				    then helper(ys',s@(valOf tmp))
				    else helper(ys',s)
				end
				    
     in
	 helper(lli,[ss])
     end

				    
				  
		      
(*1.d *)
 (*
    val similar_names = fn : string list list * {first:string, last:string, middle:string}-> {first:string, last:string, middle:string} list
 *)

fun similar_names(li,s:{first:string, last:string, middle:string})=
    let fun fix(xs,s:{first:string, last:string, middle:string}) =
	    case xs of
		[] => []
	      | x::xs' => [case s of {first=_,last=b,middle=c}=>{first=x,last=b,middle=c}]@fix(xs',s)
    in
	[s]@fix(get_substitutions1(li,(case s of {first=a,last=_,middle=_}=>a)),s)
    end
	

(*
card_color = fn : card -> color
card_value = fn : card -> int
remove_card = fn : card list * card * exn -> card list
all_same_color = fn : card list -> bool
sum_cards = fn : card list -> int
score = fn : card list * int -> int
officiate = fn : card list * move list * int -> int
*)
	
(*2.a*)
	
fun card_color(cd:card) =
    case cd of
	(Spades,_) => Black
      | (Clubs,_) => Black
      | (Diamonds,_) => Red
      | (Hearts,_) => Red

(*2.b*)
(*	card_value = fn : card -> int	*)	  
fun card_value(cd:card):int =
    case cd of
	(_,Num(a)) => a
     | (_,Ace) => 11
     | (_,_) => 10

(*2.c*)
		     
fun remove_card(xs:card list,c,e)=
    let fun check(xs:card list,c)=
	    case xs of
		[] => false
	      | y::ys' => if y=c then true
			  else check(ys',c);
	fun getValue(xs:card list,c)=
	    case xs of
		[] => []
	      | y::ys' => if y=c then ys'
  
			  else
			      y::getValue(ys',c)
    in
	let val flag = check(xs,c)
	in
	    if false=flag then
		raise e
	    else getValue(xs,c)
	end
    end
	
	    
(*2.d*)
fun all_same_color(xs:card list)=
    case xs of
	[] => true
      | y::ys' => case ys' of
		      [] => true
		    | k::ks' => if  card_color(y)=card_color(k)
			       then
				   all_same_color(ys')
			       else
				   false;

				       
(*2.e*)

fun sum_cards(li:card list) =
    let fun helper(li:card list,sum:int)=
	    case li of
		[] => sum
	      | y::ys' => helper(ys',sum+(card_value(y)))
    in
	helper(li,0)
    end;


(*2.f score = fn : card list * int -> int
officiate = fn : card list * move list * int -> int*)

fun score(li:card list,goal:int ) =
    let val sum = sum_cards(li);val jud = all_same_color(li)
    in
	let val pre_score =(if sum>goal then 3*(sum-goal) else (goal-sum))
	in
	    case jud of
		false => pre_score
	      | true =>  pre_score div 2
	end
    end
; 

  
  (*2.g*)


fun officiate(li:card list,nx:move list,goal : int) =
    let
	fun helper(tmpList:card list,append:card) = tmpList@[append];
	fun runGame(lli:card list,nnx:move list,myList:card list) =
	    if sum_cards(myList)>goal then score(myList,goal)
	    else
	      case nnx of
		 [] =>  score(myList,goal)(*no operation*)
	       | y::ys' => case y of
			       Draw =>  (case lli of
					  [] => score(myList,goal) (*no card*)
					| z :: zs' => runGame(zs',ys',myList@[(z:card)]) (*continue*))
	                     | Discard(a,b) =>  runGame(lli,ys',remove_card(myList,(a,b),IllegalMove))
      in
	  runGame(li,nx,[])
      end
	  


	(*3.a*)
