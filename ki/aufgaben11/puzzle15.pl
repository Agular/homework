:-[idas].
h(Board, H):- h_m(Board, H1), h_pq(Board, H2), H is H1+H2.

/*
The "hollas" algorithm.
*/
h_pq(Board, H):-goal(Goal), maplist(switch_list, Board, Board_pos), maplist(switch_list, Goal, Right_pos),maplist(targets_list, Board_pos,Right_pos, Target_pos), maplist(pq_list, Target_pos, Values), max_list(Values, H).

% Get where is the target position of the board tile
targets_list([A1,B1,C1,D1],[A2, B2, C2, D2], T):-
target(A1,A2,T1), PL1 = [T1],
target(B1,B2,T2), append(PL1,[T2],PL2),
target(C1,C2,T3), append(PL2,[T3],PL3),
target(D1,D2,T4), append(PL3,[T4],T).

target((3,3),(_,_),(10,10)).
target((_,_),(3,3),(10,10)).
target((X1,Y1),(X2,Y2),T):- DX is X1-X2, DY is Y1-Y2, T = (DX,DY).
pq_list([A,B,C,D], Value):-
pq(A,B,V1),
pq(A,C,V2),
pq(A,D,V3),
pq(B,C,V4),
pq(B,D,V5),
pq(C,D,V6),
max_list([V1, V2, V3, V4, V5, V6],Value).
pq((X1,Y1),(X2,Y2), Value):-
(Y1==0,Y2==0,X2<X1,Value = 2);(Y2\==0;Y1\==0 ; X2>=X1),Value = 0.
h_m(Board, H):- goal(Goal),maplist(switch_list, Board, Board_pos), maplist(switch_list, Goal, Right_pos), maplist(eval_pos_list, Board_pos, Right_pos, Man_list),flatten(Man_list, Sum_list), sumlist(Sum_list, H).
eval_pos_list([A1, B1, C1, D1], [A2, B2, C2, D2], L):-
eval_pos(A1, A2, X1), PL1 = [X1],
eval_pos(B1, B2, X2), append(PL1, [X2] , PL2),
eval_pos(C1, C2, X3), append(PL2,[X3], PL3),
eval_pos(D1, D2, X4), append(PL3, [X4], L).

eval_pos((X1,Y1),(X2, Y2), D):-
	D is (abs(X2-X1) + abs(Y2-Y1)).

switch(Num, Pos):- floor(Num / 4, Y), X is Num mod 4, Pos = (X, Y).
switch_list([A1,B1,C1,D1],[A2,B2,C2,D2]):-
	switch(A1, A2),
	switch(B1, B2),
	switch(C1, C2),
        switch(D1, D2).

% Leerstelle in Zeile verschieben
sr( [15,A,B,C], [A,15,B,C]).
sr( [A,15,B,C], [A,B,15,C]).
sr( [A,B,15,C], [A,B,C,15]).
shiftr(X,Y) :- sr(X,Y); sr(Y,X).
adjr( [X,  R2, R3, R4], [Y,  R2, R3, R4]) :- shiftr(X, Y).
adjr( [R1, X,  R3, R4], [R1, Y,  R3, R4]) :- shiftr(X, Y).
adjr( [R1, R2,  X, R4], [R1, R2,  Y, R4]) :- shiftr(X, Y).
adjr( [R1, R2, R3, X], [R1, R2, R3, Y]) :- shiftr(X, Y).
% Leerstelle in Spalte verschieben
adjc( [[A1,B1,C1], [D1,E1,F1], [G1,H1,I1]],
      [[A2,B2,C2], [D2,E2,F2], [G2,H2,I2]] ) :-
	adjr( [[A1,D1,G1], [B1,E1,H1], [C1,F1,I1]],
	      [[A2,D2,G2], [B2,E2,H2], [C2,F2,I2]] ).
adjc([[A1,B1,C1,D1], [E1,F1,G1,H1], [I1,J1,K1,L1],[M1,N1,O1,P1]],
     [[A2,B2,C2,D2], [E2,F2,G2,H2], [I2,J2,K2,L2],[M2,N2,O2,P2]]):-
        adjr( [[A1,E1,I1,M1],[B1,F1,J1,N1],[C1,G1,K1,O1],[D1,H1,L1,P1]],
	      [[A2,E2,I2,M2],[B2,F2,J2,N2],[C2,G2,K2,O2],[D2,H2,L2,P2]]).
% Leerstelle in Zeile oder Spalte verschieben
adj(Board1, Board2) :-
	adjr(Board1, Board2);
	adjc(Board1, Board2).
% Zielstellung
goal( [[ 0,  1,  2,  3 ],
       [ 4,  5,  6,  7 ],
       [ 8,  9, 10, 11],
       [12,  13, 14, 15]] ).
% Pretty-Printer
printB(Board) :- maplist(writeln, Board), write('\n').
print(Boards) :- maplist(printB, Boards).

solution(Start, Path):-goal(Goal),idas(Start, Goal, Path).









