def fc_entails(kb, q):
    agenda = []
    count = []
    inferred = {}
    fact_in_premise = {}
    for idx, fact in enumerate(kb):
        if len(fact) is 1 or type(fact) is str:
            if fact is q:
                return True
            agenda.append(fact)
            count.append(0)
            if fact not in inferred:
                inferred[fact] = False
            if fact not in fact_in_premise:
                fact_in_premise[fact] = []
        else:
            count.append(len(fact[0]))
            if fact[1] not in inferred:
                inferred[fact[1]] = False
            if fact[1] not in fact_in_premise:
                fact_in_premise[fact[1]] = []
            for symbol in fact[0]:
                if symbol not in inferred:
                    inferred[symbol] = False
                if symbol not in fact_in_premise:
                    fact_in_premise[symbol] = [idx]
                else:
                    fact_in_premise[symbol].append(idx)
    # print(agenda)
    # print(count)
    # print(inferred)
    # print(fact_in_premise)
    while agenda:
        fact = agenda.pop()
        if not inferred[fact]:
            inferred[fact] = True
        for clause_idx in fact_in_premise[fact]:
            count[clause_idx] -= 1
            if count[clause_idx] is 0:
                if kb[clause_idx][1] is q:
                    return True
                agenda.append(kb[clause_idx][1])
    return False


def generate_kb():
    moves = ["Rock", "Paper", "Scissors"]
    kb = []
    for move in moves:
        kb.append((["P-" + move, "PP-" + move], move))
        other_moves = [i for i in moves if i is not move]
        for i in range(len(other_moves) - 1, -1, -1):
            for j in range(i - 1, -len(other_moves) + i, -1):
                kb.append((["P-" + move, "PP-" + other_moves[i]], other_moves[j]))
    return kb


def kkp(moves):
    all_moves = ["Rock", "Paper", "Scissors"]
    counter_moves = ["Paper", "Scissors", "Rock"]
    kb = generate_kb()
    kb.append(str("P-" + moves[0]))
    kb.append(str("PP-" + moves[1]))

    enemy_next_moves = [fc_entails(kb, next_move) for next_move in all_moves]
    for i, enemy_move in enumerate(enemy_next_moves):
        if enemy_move:
            return counter_moves[i]


if __name__ == "__main__":
    kb = [
        (["P"], "Q"),
        (["L", "M"], "P"),
        (["B", "L"], "M"),
        (["A", "P"], "L"),
        (["A", "B"], "L"),
        "A", "B"]
    print(kkp(("Paper", "Rock")))
