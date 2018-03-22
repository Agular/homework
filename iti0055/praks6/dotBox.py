import random
import sys

BOARD_MEDIUM = "m"
BOARD_SMALL = "s"
EMPTY = " "
LINE_V = "|"
LINE_H = "-"

INVALID_MOVE = "Your last move is not available, please try again!"
SCORING_MOVE = "You scored with your last move, you play again!"
COMPUTER_SCORING_MOVE = "Computer scored!"
PLAYER = "player"
COMPUTER = "computer"


def get_board_init_state(board_size):
    if board_size is BOARD_SMALL:
        n = 5
    else:
        n = 7
    init_state = [["*" if x % 2 == 1 and y % 2 is 0 else " " for x in range(1, n + 1)] for y in range(0, n)]
    return init_state


def print_game_state(game_state):
    print('\n'.join(' '.join(char) for char in game_state))


def get_available_moves(state):
    available_moves = []
    for r_idx, row in enumerate(state):
        for c_idx, col in enumerate(row):
            if r_idx % 2 is 0 and c_idx % 2 is 1:
                if state[r_idx][c_idx] == EMPTY:
                    available_moves.append((r_idx, c_idx))
            elif r_idx % 2 is 1 and c_idx % 2 is 0:
                if state[r_idx][c_idx] == EMPTY:
                    available_moves.append((r_idx, c_idx))
    return available_moves


def print_available_moves(available_moves):
    print(available_moves)


def is_scoring_move(move_x, move_y, state, initial):
    points = 0

    # move was horizontal line
    if move_x % 2 is 0:
        if move_x < len(state) - 1:
            side_down_has_line = state[move_x + 2][move_y] == LINE_H
            side_left_has_line = state[move_x + 1][move_y - 1] == LINE_V
            side_right_has_line = state[move_x + 1][move_y + 1] == LINE_V
            if side_down_has_line and side_left_has_line and side_right_has_line:
                points = points + 1
                state[move_x + 1][move_y] = initial
        if move_x is not 0:
            side_up_has_line = state[move_x - 2][move_y] == LINE_H
            side_left_has_line = state[move_x - 1][move_y - 1] == LINE_V
            side_right_has_line = state[move_x - 1][move_y + 1] == LINE_V
            if side_up_has_line and side_left_has_line and side_right_has_line:
                points = points + 1
                state[move_x - 1][move_y] = initial

    # move was vertical line
    else:
        if move_y < len(state[move_x]) - 1:
            side_up_has_line = state[move_x - 1][move_y + 1] == LINE_H
            side_down_has_line = state[move_x + 1][move_y + 1] == LINE_H
            side_right_has_line = state[move_x][move_y + 2] == LINE_V
            if side_up_has_line and side_down_has_line and side_right_has_line:
                points = points + 1
                state[move_x][move_y + 1] = initial
        if move_y is not 0:
            side_up_has_line = state[move_x - 1][move_y - 1] == LINE_H
            side_down_has_line = state[move_x + 1][move_y - 1] == LINE_H
            side_left_has_line = state[move_x][move_y - 2] == LINE_V
            if side_up_has_line and side_down_has_line and side_left_has_line:
                points = points + 1
                state[move_x][move_y - 1] = initial

    return points


def get_player_move_input(available_moves):
    while True:
        try:
            move_x, move_y = map(int, input("Your move: x,y --> ").split(","))
            if (move_x, move_y) not in available_moves:
                print(INVALID_MOVE)
            else:
                break
        except ValueError:
            print("Please enter your turn in correct format!")
    return move_x, move_y


def apply_move_to_state(move_x, move_y, state):
    if move_x % 2 is 0:
        state[move_x][move_y] = LINE_H
    else:
        state[move_x][move_y] = LINE_V


def get_state_heuristic_value(state):
    points = 0
    for row in state[1::2]:
        for value in row[1::2]:
            if value is EMPTY:
                pass
            elif value is "c":
                points += 1
            else:
                points -= 1
    return points


class MoveStats(object):

    def __init__(self, move, value):
        self.move = move
        self.value = value


def get_computer_alpha_beta_move(state, depth, a, b, maximizing_player, available_moves, last_move_scored):
    if depth is 0 or len(available_moves) is 0:
        return MoveStats(None, get_state_heuristic_value(state))

    elif maximizing_player:

        if last_move_scored:
            return get_computer_alpha_beta_move(state, depth - 1, a, b, False, available_moves, False)

        best_move = MoveStats(None, -sys.maxsize - 1)
        for move in available_moves:
            child_state = [i[:] for i in state]
            child_available_moves = [i for i in available_moves]
            child_available_moves.remove(move)
            apply_move_to_state(move[0], move[1], child_state)
            child_move_scored = is_scoring_move(move[0], move[1], child_state, "c") > 0
            child_move = get_computer_alpha_beta_move(child_state, depth - 1, a, b, False, child_available_moves, child_move_scored)
            if child_move.value > best_move.value:
                best_move = child_move
                best_move.move = move
                a = max(a, best_move.value)
            if b <= a:
                break
        return best_move

    else:

        if last_move_scored:
            return get_computer_alpha_beta_move(state, depth - 1, a, b, True, available_moves, False)

        best_move = MoveStats(None, sys.maxsize)
        for move in available_moves:
            child_state = [i[:] for i in state]
            child_available_moves = [i for i in available_moves]
            child_available_moves.remove(move)
            apply_move_to_state(move[0], move[1], child_state)
            child_move_scored = is_scoring_move(move[0], move[1], child_state, "p") > 0
            child_move = get_computer_alpha_beta_move(child_state, depth - 1, a, b, True, child_available_moves, child_move_scored)
            if child_move.value < best_move.value:
                best_move = child_move
                best_move.move = move
                b = min(b, best_move.value)
            if b <= a:
                break
        return best_move


def dot_box_game():
    game_size = ""
    player_initial = "p"
    computer_initial = "c"
    has_turn = PLAYER
    player_points = 0
    computer_points = 0

    # game input
    while game_size is not BOARD_SMALL and game_size is not BOARD_MEDIUM:
        game_size = input("Small or Medium size? (s/m): ")

    if game_size is BOARD_SMALL:
        state = get_board_init_state(BOARD_SMALL)
    else:
        state = get_board_init_state(BOARD_MEDIUM)

    available_moves = get_available_moves(state)
    special_message = EMPTY

    while len(available_moves) is not 0:
        print("\nCurrent state")
        print_game_state(state)
        if has_turn is PLAYER:
            print_available_moves(available_moves)
            if special_message is not EMPTY:
                print(special_message)
                special_message = EMPTY
            move_x, move_y = get_player_move_input(available_moves)
            available_moves.remove((move_x, move_y))
            apply_move_to_state(move_x, move_y, state)
            move_points = is_scoring_move(move_x, move_y, state, player_initial)
            if move_points > 0:
                player_points = player_points + move_points
                special_message = SCORING_MOVE
            else:
                has_turn = COMPUTER
        elif has_turn is COMPUTER:
            print("Computer plays!")
            move_stats = get_computer_alpha_beta_move(state, 6, -sys.maxsize - 1, sys.maxsize, True, available_moves, False)
            move_x, move_y = move_stats.move
            available_moves.remove((move_x, move_y))
            apply_move_to_state(move_x, move_y, state)
            move_points = is_scoring_move(move_x, move_y, state, computer_initial)
            if move_points > 0:
                computer_points = computer_points + move_points
                special_message = COMPUTER_SCORING_MOVE
            else:
                has_turn = PLAYER

    print("\nFinal state:")
    print_game_state(state)
    if player_points > computer_points:
        print("The player won " + str(player_points) + ":" + str(computer_points))
    elif computer_points > player_points:
        print("The computer won " + str(computer_points) + ":" + str(player_points))
    else:
        print("The player and computer tied " + str(player_points) + ":" + str(computer_points))


if __name__ == "__main__":
    dot_box_game()
