subst(X + Y, Answer, A, B):- subst(X, Ans1, A, B), subst(Y, Ans2, A, B), Answer = Ans1 + Ans2.
subst(X * Y, Answer, A, B):- subst(X, Ans1, A, B), subst(Y, Ans2, A, B), Answer = Ans1 * Ans2.
subst(X, B, A, B):- X==A.
subst(X, X, A, _):- X\==A.
