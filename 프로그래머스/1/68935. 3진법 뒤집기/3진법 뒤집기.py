def solution(n):
    s = ''
    
    while n > 0:
        n, m = divmod(n, 3)
        s += str(m)
    
    print(s) # 0021
    print(s[::-1]) # 1200 -> 원본 변경 x
    print(''.join(reversed(s))) # 1200 -> 원본 변경 x
    
    return int(s, 3)