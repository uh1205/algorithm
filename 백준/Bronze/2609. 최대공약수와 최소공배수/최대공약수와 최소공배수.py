a, b = map(int, input().split())

div = 0
for n in range(1, min(a, b) + 1):
    if a % n == 0 and b % n == 0:
        div = max(div, n)

print(div, int(a*b/div), sep='\n')