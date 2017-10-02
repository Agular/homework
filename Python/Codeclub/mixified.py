def count_mixified(paragraph):
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    alphabet2 = "abcdefghijklmnopqrstuvwxyz"
    num_words = 0
    list_words = paragraph.strip().split()
    for word in list_words:
        if len(word) >= 3:
            counter = True
            for idx, char in enumerate(word):
                if str.isalpha(char):
                    if idx % 2 == 0:
                        if char not in alphabet:
                            counter = False
                    if idx % 2 == 1:
                        if char not in alphabet2:
                            counter = False
            if counter is True:
                num_words += 1
    return num_words


if __name__ == "__main__":
    x = count_mixified("JoU-U mis sa teed, EvA M4R1A")
    print(x)