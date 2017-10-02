def get_distance(b_array1, b_array2):
    string1 = ''.join([bin(ch)[2:].zfill(8) for ch in b_array1])
    string2 = ''.join([bin(ch)[2:].zfill(8) for ch in b_array2])
    counter = 0
    for i in range(len(string1)):
        if string1[i] != string2[i]:
            counter += 1
    return counter


print(get_distance(b"123", b"abc"))
