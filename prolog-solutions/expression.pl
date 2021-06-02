
lookup(K, [(K, V) | _], V).
lookup(K, [_ | T], V) :- lookup(K, T, V).

operation(op_add, A, B, R) :- R is A + B.
operation(op_subtract, A, B, R) :- R is A - B.
operation(op_multiply, A, B, R) :- R is A * B.
operation(op_divide, A, B, R) :- R is A / B.
operation(op_negate, A, R) :- R is -A.

evaluate(const(V), _, V).

evaluate(variable(Name), Vars, R) :- lookup(Name, Vars, R).

evaluate(operation(Op, A), Vars, R) :-
	evaluate(A, Vars, AV), 
	operation(Op, AV, R).
	
evaluate(operation(Op, A, B), Vars, R) :- 
    evaluate(A, Vars, AV), 
    evaluate(B, Vars, BV), 
    operation(Op, AV, BV, R).


%(suffix_str Expression Atom)
%suffix_str(
%        operation(op_subtract,operation(op_multiply,const(2),variable(x)),const(3)),
%        '((2 x *) 3 -)'
%    )

%number_chars(10.0, C), atom_chars(A, C). 
%C / ['1','0','.','0']
%A / '10.0'

:- load_library('alice.tuprolog.lib.DCGLibrary').

flatten([], []).
flatten([H | T], R) :- append(H, TR, R), flatten(T, TR).

%expr_p(variable(Name)) --> {var(Name), print(here), nl}, ws_p(Aft), [Name], ws_p(Bef), { member(Name, [x, y, z]) }.
expr_p(variable(Name)) --> /*{nonvar(Name)}, */[Name], { member(Name, [x, y, z]) }.

expr_p(const(Value)) --> 
	{ nonvar(Value) , number_chars(Value, Chars) },
	Chars.

expr_p(const(Value)) -->
	{var(Value)},
	num_p(Chars),
	{ number_chars(Value, Chars) }.

ws_p([]) --> [].
ws_p([' ' | T]) --> 
	[' '],
	ws_p(T).

sign_p(['-']) --> ['-'].
sign_p([]) --> [].
frac_p(['.' | T]) --> ['.'], int_p(T).
frac_p([]) --> [].

num_p(Chars) -->
	sign_p(S), int_p(I), frac_p(F),
	{flatten([S, I, F], Chars)}.
	

int_p([H]) --> digit(H).
int_p([H | T]) --> 
  digit(H),
  int_p(T).

digit(H) -->
	{member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'])},
	[H].
  
op_p(op_add) --> ['+'].
op_p(op_subtract) --> ['-'].
op_p(op_multiply) --> ['*'].
op_p(op_divide) --> ['/'].
op_p(op_negate) --> ['n', 'e', 'g', 'a', 't', 'e'].

expr_p(operation(Op, A)) --> {ground(operation(Op, A))}, ['('], expr_p(A), [' '], op_p(Op), [')'].
expr_p(operation(Op, A)) --> 
	{\+ ground(operation(Op, A))},
	['('], ws_p(W2), expr_p(A), ws_p(W4), 
	[' '], op_p(Op), ws_p(W5), 
	[')'].
	 
expr_p(operation(Op, A, B)) --> {ground(operation(Op, A, B))}, ['('], expr_p(A), [' '], expr_p(B), [' '], op_p(Op), [')'].
expr_p(operation(Op, A, B)) --> 
	{\+ ground(operation(Op, A, B))},
	['('], ws_p(W1), expr_p(A), ws_p(W2),
	[' '], expr_p(B), ws_p(W4),
	[' '], op_p(Op), ws_p(W5), [')'].

value(E) --> ws_p(Aft), expr_p(E), ws_p(Bef).
%phrase(expr_p(operation(op_add, variable(x), const(4))), C).
%phrase(expr_p(operation(op_negate, variable(x))), C), atom_chars(A, C).

suffix_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C).
suffix_str(E, A) :-   atom(A), atom_chars(A, C), phrase(value(E), C).

%phrase(digits_p(['1', '2', '3']), C). phrase(digits_p(['1','2','3']),['1','2','3'])

%['(','7','8','1','.','0',' ','(','-','6','3','.','0',' ',y,' ','/',')',' ','*',')']




  







