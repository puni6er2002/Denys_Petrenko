#Chord method
eps = 0.00001
intervals = [[-1.275, -1.075], [-0.736, -0.536], [-0.226, -0.026], [0.061, 0.261], [0.456, 0.656], [0.673, 0.873], [2.936, 3.136]]

def func(x):
	return -278 * pow(x, 7) + 747 * pow(x, 6) + 625 * pow(x, 5) - 966 * pow(x, 4) - 207 * pow(x, 3) + 275 * pow(x, 2) - 4 * x - 5

def dfunc(x):
	return -278 * 7 * pow(x, 6)+747* 6 * pow(x, 5) + 625 * 5 * pow(x, 4) - 966 * 4 *pow(x, 3) - 207 * 3 * pow(x, 2) +275 * 2 * x - 4

def ddfunc(x):
	return -278 * 42 * pow(x, 5)+747* 30 * pow(x, 4) + 625 * 20 * pow(x, 3) - 966 * 12 *pow(x, 2) - 207 * 6 * x + 275 * 2

def find_root(a, b, e):
    i = 1
 while abs((b - a) / 2) > e:
    a = b - (b - a) * func(b) / (func(b) - func(a))
    b = a - (a - b) * func(a) / (func(a) - func(b))
    print("Iteration", i, " on interval [", a, ",", b, "]\n")
    i += 1
    return b

print("Method of hordes\n")
for a,b in intervals:
    print("\n Interval [", a, ',', b, "] x=", find_root(a, b, eps), "\n")