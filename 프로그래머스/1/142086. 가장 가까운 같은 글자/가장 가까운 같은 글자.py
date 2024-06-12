def solution(s):
    r = []
    l = []
    cnt = 0
    
    for c in s:
        if c in l:
            r.append(l.index(c) + 1)
            l.insert(0, c)
        else:
            r.append(-1)
            l.insert(0, c)
        
    return r