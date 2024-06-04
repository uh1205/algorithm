def solution(sizes):
    w = 0
    h = 0
    
    for a, b in sizes:
        a, b = max(a, b), min(a, b)
        if w < a:
            w = a
        if h < b:
            h = b
            
    return w * h