import numpy as np
import math as m

A_m = [[13.2, -8.3, -4.4, 6.2, 6.8],
[8.3, 4.2, -5.6, 7.7, 12.4],
[5.8, -3.7, 12.4, -6.2, 8.7],
[3.5, 6.6, -13.8, -9.3, -10.8]]

A_s = A_m.copy()

def print_A(A):
    print("\t\t\tMatrix")
    for i in range(0, len(A)):
        print(*["%.2f " %A[i][j] for j in range(0, len(A)+1)])

def swap_rows(A, row1, row2):
    A[row1], A[row2] = A[row2], A[row1]

def sum_rows(A, row, source_row, coef):
    A[row] = [(a + k * coef) for a, k in zip(A[row], A[source_row])]

def div_row(A, row, divider):
    A[row] = [a / divider for a in A[row]]

def det_A(A):
    det = 1
    for i in range(0, len(A)):
        det *= A[i][i]
    return int(det)

def max_index(A, col):
    B = np.array(A)
    value_list1 = list(B[col:, col])
    value_list2 = list(B[:, col])
    e_max = max([m.fabs(ell) for ell in value_list1])
    i_max = ([m.fabs(ell) for ell in value_list2]).index(e_max)
    return i_max

def Rasidual_vector(A_s):
    A_x = 0
    X = Gauss(A_m)
    for i in range(0, len(A_s)):
        for j in range(0, len(A_s)):
            A_x += A_s[i][j] * X[j]
        print(f"b{i+1}(%.1f" %A_s[i][len(A_s)], f") - Ax*(%.2f" %A_x,") =", "%.5f" % (abs(A_s[i][len(A_s)] - A_x)))
    A_x = 0

def Gauss(A):
    column = 0

    while column < len(A):
        current_row = max_index(A, column)
        if current_row != column:
            swap_rows(A, current_row, column)
            
        div_row(A, column, A[column][column])
        
        for r in range(column + 1, len(A)):
            sum_rows(A, r, column, -A[r][column])
        column += 1

    X = [0 for i in range(len(A))]
    for k in range(len(A) - 1, -1, -1):
        X[k] = A[k][-1] - sum(x * a for x, a in zip(X[(k + 1):], A[k][(k + 1):]))
  
    return X

print_A(A_m)
X_values = Gauss(A_m)
print_A(A_m)
print("Determinante of A matrix:", det_A(A_m))

for i in range(0, len(X_values)):
    print(f"x{i+1} = %.5f" %X_values[i])

print("\nRasidual vector:")
Rasidual_vector(A_s)
