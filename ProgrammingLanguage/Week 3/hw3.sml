(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern

datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu

fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

			       (**** you can put all your code here ****)
(*part 1 1_7*)

fun only_capitals (xs : string list)  =
    List.filter (fn (v) => Char.isUpper(String.sub(v,0))) xs

fun longest_string1 (xs : string list) =
    foldl (fn(x,y) => if String.size(x)>=String.size(y) then x else y ) "" xs

	  
fun longest_string2 (xs : string list) =
    foldl (fn(x,y) => if String.size(x)>String.size(y) then x else y ) "" xs


fun longest_string_helper f ( li: string list) s =
    foldl(fn(x,y) => if f(String.size(x),String.size(y)) then x else y) s li

fun longest_string3 (xs : string list) =
    longest_string_helper (fn (a:int,b:int) => if a> b then true else false) xs ""
			  
fun longest_string4 (xs : string list) =
    longest_string_helper (fn (a:int,b:int) => if a>=b
					      then true else false) xs ""
			 
fun longest_capitalized (xs : string list) =
    (longest_string2 o only_capitals) xs


fun rev_string (str : string) =
    (implode o rev o explode) str



(*part 2*)

fun first_answer f li =
    case li of
	[] => raise NoAnswer
      | x::xs' => let
	              val tmpAns =(f x)
		  in
		      if isSome tmpAns
		      then valOf tmpAns
		      else first_answer f xs'
		  end
		      
    

	
fun count_wildcards p =
    g (fn (x) => 1) (fn (x) => 0) p
    
    
fun count_wild_and_variable_lengths p =
    g (fn() => 1) (fn(x)=> String.size(x)) p

fun count_some_var (str,p) =
    g (fn() => 0) (fn(x) => if str = x then 1 else 0) p

fun check_pat p =
    let
	fun helper1 p =
	    case p of
		TupleP ps => (List.foldl (fn (a,b:string list) => b@(helper1 a)) [] ps)
	      | Variable x => [x]
	      | ConstructorP(x,s) => [x]@(helper1 s)
	      | _ => [] ;
	fun helper2 l =
	     case l of
		[] => true
	      | x::xs' =>
		let
		  val result = (List.exists (fn (now) => if now = x then true else false ) xs')
		in
		    if result=true
		    then
			false
		    else
			helper2 xs'
		end
    in
	let
	    val tmp:string list = helper1 p
	in
	    helper2 tmp
	end
    end
	
fun all_answers f li =
    let
	fun helper f li now = 
		   case li of
		       [] => if now = [] then NONE else SOME now
		      |x::xs'  =>
		       let
			   val tmpAns =(f x)
		       in
			   if isSome tmpAns
			   then helper f xs' (now@(valOf tmpAns))
			   else helper f xs' now
		       end
    in
	helper f li []
    end


	    
fun match(v, pat) =
    case (v, pat) of
         (_, Wildcard)	=> SOME []
	|(v, Variable s) => SOME [(s,v)]
	|(Unit , UnitP) =>SOME []
	|(Const i, ConstP j) => if i=j then SOME [] else NONE
	|(Tuple vt, TupleP pt) => if List.length vt = List.length pt
				  then all_answers match (ListPair.zip(vt, pt))
				  else NONE
        |(Constructor(sv , vv), ConstructorP(sp , pp)) => if sv=sp then match (vv, pp) else NONE
	| _ => NONE
 


fun first_match v pl =
    SOME (first_answer (match) (List.map(fn(x) => (v,x)) pl))
			handle NoAnswer => NONE
