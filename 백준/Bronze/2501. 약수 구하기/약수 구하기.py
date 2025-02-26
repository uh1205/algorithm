N, K = map(int, input().split())
div = []
for i in range(1, N + 1):  # 1 부터 N 까지
    if N % i == 0:
        div.append(i)
try:
    print(div[K - 1])
except:
    print(0)
