def charswap(argument):
    argument = str(argument)
    if len(argument) > 1:
        start = argument[0]
        end = argument[-1]
        new_string = end + argument[1:-1] + start
        return new_string
    else:
        return argument
if __name__ == "__main__":
    print(charswap(0))
