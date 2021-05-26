%node(2, b, node(1, a, null, null), node(3, c, null, null)).

count(null, 0).
count(node(_, _, _, _, C), C).

map_build(L, N) :- length(L, C), count(N, C), travel(L, [], N), !.

travel(List, List, null).

travel(List, Pref, node(X, Y, L, R, C)) :-
	C1 is div(C, 2),
	C2 is C - C1 - 1,
	count(L, C1),
	count(R, C2),
	travel(List1, Pref, R),
	travel(List, [(X, Y) | List1], L).

map_get(node(K, V, _, _, _), K, V) :- !.
map_get(node(X, _, L, _, _), K, V) :- K < X, !, map_get(L, K, V).
map_get(node(X, _, _, R, _), K, V) :- K > X, !, map_get(R, K, V).

%map_containsKey(node(K, _, _, _, _), K) :- !.
%map_containsKey(node(K1, _, L, _, _), K) :- K < K1, !, map_get(L, K).
%map_containsKey(node(K1, _, _, R, _), K) :- K > 

map_containsKey(M, K) :- map_get(M, K, _), !.
%map_containsValue(M, V) :- map_get(M, _, V), !.

map_containsValue(node(_, V, _, _, _), V) :- !.
map_containsValue(node(_, _, L, _, _), V) :- map_containsValue(L, V).
map_containsValue(node(_, _, _, R, _), V) :- map_containsValue(R, V).

%node(2,b,node(1,a,null,null,1),node(3,c,null,null,1),3)

