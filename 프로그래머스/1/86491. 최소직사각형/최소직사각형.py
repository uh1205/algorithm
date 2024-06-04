def solution(sizes):
    w = 0
    h = 0
    
    for s in sizes:
        if w < max(s):
            w = max(s)
        if h < min(s):
            h = min(s)
            
    return w * h