"""
A codeclub excercise to practice datetimes.

@author: raluga
"""


def sort_file(contents, *, insep=None, inend=None, outsep=None, outend=None):
    if insep == None:
        insep = ","
    if inend == None:
        inend = "\n"
    if outsep == None:
        outsep = insep
    if outend == None:
        outend = inend
    contents = contents.split(inend)
    maks = 0
    for content in contents:
        if len(content.split(insep)) > maks:
            maks = len(content.split(insep))
    contents = [string for string in contents if string != "" and string[0] != "#" and len(string.split(insep)) == maks]
    contents.sort()
    if insep != outsep:
        for i in range(len(contents)):
            contents[i] = outsep.join(contents[i].split(insep))
        return outend.join(contents)
    else:
        return outend.join(contents)


if __name__ == "__main__":
    print(sort_file("2,3,d\n1,4,d\n8,2,z\n2,4,x\n2,4,a"))
