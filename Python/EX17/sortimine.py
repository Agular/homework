import alienutils

gen = alienutils.AlienGenerator()
aliens_10 = gen.get_aliens(100)
alpha_aliens = (sorted(aliens_10, key=lambda alien: alien.name))
for i in alpha_aliens:
    print(i)
