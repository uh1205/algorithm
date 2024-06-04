def solution(sizes):
    w, h = 0, 0
    
    for a, b in sizes:
        if a < b:
            a, b = b, a
        if w < a:
            w = a
        if h < b:
            h = b
            
    return w * h