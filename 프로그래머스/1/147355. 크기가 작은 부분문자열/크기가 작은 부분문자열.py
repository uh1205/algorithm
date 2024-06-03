def solution(t, p):
    cnt = 0
    s = [t[i : len(p) + i] for i in range(len(t) - len(p) + 1)]
    
    for c in s:
        if int(c) <= int(p):
            cnt += 1
    
    return cnt