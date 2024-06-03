def solution(t, p):
    cnt = 0
    # s = [t[i : len(p) + i] for i in range(len(t) - len(p) + 1)]
    
    for i in range(len(t) - len(p) + 1):
        if int(p) >= int(t[i:i+len(p)]):
            cnt += 1
    
    return cnt
