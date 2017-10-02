data = []  # Buffer list
with open("ttuListid.txt", "rt") as the_file:
    for row in the_file:
        data.append(row)

    with open('ttuListidUrls.txt', 'w', newline='') as outfile:
        for header in data:
            outfile.write("http://tipikas.ee/mailman/admin/" + header)
