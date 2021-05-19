is_composite(N) :- prime(N1), N2 is N1 * N1 - 1, N > N2, 0 is mod(N, N1), !.

init(MAX_N) :- range(2, MAX_N).

range(C, N) :-
		C =< N,
		\+ is_composite(C),
		assert(prime(C)),
		C1 is C + 1,
		range(C1, N).

range(C, N) :-
		C =< N,
		is_composite(C),
		assert(composite(C)),
		C1 is C + 1,
		range(C1, N).

prime_divisors(N, D) :- equals(N, D, 2).

equals(N, [], _) :- N = 1, !.
equals(N, [H | T], Div) :-
		N > 1,
		0 is mod(N, Div),
		Div = H,
		N1 is div(N, Div),
		equals(N1, T, Div).

equals(N, D, Div) :-
		N > 1,
		\+ 0 is mod(N, Div),
		next_prime(Div, Div1),
		equals(N, D, Div1).

next_prime(C, N) :- prime(N), N > C, !.

square_divisors(N, D) :- N1 is N * N, prime_divisors(N1, D).



