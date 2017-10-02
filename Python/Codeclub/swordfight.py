def i_draw_my_sword(hilt_lenght, blade_lenght, blade_material):
    if hilt_lenght < 1 or blade_lenght < 1:
        return False
    else:
        return "O" + "=" * hilt_lenght + "||" + blade_material * blade_lenght + ">"


if __name__ == "__main__":
    x = i_draw_my_sword(3, 3, "CHOCOLATE")
    print(x)
