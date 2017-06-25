:-[idas].
%h(Board, H):- goal(Goal), maplist(hamming, Board, Goal, Temp_list), flatten(Temp_list, Temp), sumlist(Temp,H).

h(Board, H):- goal(Goal),maplist(switch_list, Board, Board_pos), maplist(switch_list, Goal, Right_pos), maplist(eval_pos_list, Board_pos, Right_pos, Man_list),flatten(Man_list, Sum_list), sumlist(Sum_list, H).
eval_pos_list([A1, B1, C1], [A2, B2, C2], L):-
eval_pos(A1, A2, D1), PL1 = [D1],
eval_pos(B1, B2, D2), append(PL1, [D2] , PL2),
eval_pos(C1, C2, D3), append(PL2,[D3], L).

eval_pos((X1,Y1),(X2, Y2), D):-
	D is (abs(X2-X1) + abs(Y2-Y1)).

switch(Num, Pos):- Num == 1, Pos = (1,1).
switch(Num, Pos):- Num == 2, Pos = (2,1).
switch(Num, Pos):- Num == 3, Pos = (3,1).
switch(Num, Pos):- Num == 4, Pos = (1,2).
switch(Num, Pos):- Num == 5, Pos = (2,2).
switch(Num, Pos):- Num == 6, Pos = (3,2).
switch(Num, Pos):- Num == 7, Pos = (1,3).
switch(Num, Pos):- Num == 8, Pos = (2,3).
switch(Num, Pos):- Num == 9, Pos = (3,3).
switch_list([A1,B1,C1],[A2,B2,C2]):-
	switch(A1, A2),
	switch(B1, B2),
	switch(C1, C2).
hamming([A,B,C],[X,Y,Z],L):-
    ((A==X, PL1 =[0]);(A\==X, PL1 = [1])),
    ((B==Y, append([0], PL1, PL2));(B\==Y, append([1], PL1, PL2))),
    ((C==Z, append([0], PL2, L));(C\==Z, append([1], PL2, L))).

% Leerstelle in Zeile verschieben
sr( [9,A,B], [A,9,B]).
sr( [A,9,B], [A,B,9]).
shiftr(X,Y) :- sr(X,Y); sr(Y,X).

adjr( [X,  R2, R3], [Y,  R2, R3]) :- shiftr(X, Y).
adjr( [R1, X,  R3], [R1, Y,  R3]) :- shiftr(X, Y).
adjr( [R1, R2,  X], [R1, R2,  Y]) :- shiftr(X, Y).

% Leerstelle in Spalte verschieben
adjc( [[A1,B1,C1], [D1,E1,F1], [G1,H1,I1]],
      [[A2,B2,C2], [D2,E2,F2], [G2,H2,I2]] ) :-
	adjr( [[A1,D1,G1], [B1,E1,H1], [C1,F1,I1]],
	      [[A2,D2,G2], [B2,E2,H2], [C2,F2,I2]] ).

% Leerstelle in Zeile oder Spalte verschieben
adj(Board1, Board2) :-
	adjr(Board1, Board2);
	adjc(Board1, Board2).

% Zielstellung
goal( [[1,2,3],
       [4,5,6],
       [7,8,9]] ).

% Pretty-Printer
printB(Board) :- maplist(writeln, Board), write('\n').
print(Boards) :- maplist(printB, Boards).

solution(Start, Path):-goal(Goal),idas(Start, Goal, Path).
