# Half interval method
eps = 0.00001
intervals = [[-1.275, -1.075], [-0.736, -0.536], [-0.226, -0.026], [0.061, 0.261], [0.456, 0.656], [0.673, 0.873], [2.936, 3.136]]
def func(x):
    return -278 * pow(x, 7) + 747 * pow(x, 6) + 625 * pow(x,5) - 966 * pow(x, 4) - 207 * pow(x, 3) + 275 * pow(x, 2) - 4 * x + 5

def find_root(a, b, e):
    i = 0
    while abs(b-a) > e:
        c = (b+a)/2
        f = func(c)
        if abs(func(c)) < e:
            return c
        elif func(a)*func(c) < 0:
            b = c
        else:
            a = c
        print("Iteration", i, " on interval [", a, ",", b, "]\n")
        i += 1
    return c

print("Method of half intervals\n")
for a,b in intervals:
    print("\n Interval [", a, ',', b, "] x=", find_root(a, b, eps), "\n")