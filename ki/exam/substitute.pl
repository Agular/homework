subst1(X + Y, Answer, A, B):- subst1(X, Ans1, A, B), subst1(Y, Ans2, A, B), Answer = Ans1 + Ans2.
subst1(X * Y, Answer, A, B):- subst1(X, Ans1, A, B), subst1(Y, Ans2, A, B), Answer = Ans1 * Ans2.
subst1(C * X, C*X, _, _):- number(C).
subst1(X, Answer, A, B):- atom(X), X==A, Answer = B.
subst1(X, X, A, _):- (atom(X), X\==A).
