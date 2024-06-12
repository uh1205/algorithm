def solution(a, b, n):
    result = 0
    
    while n // a:
        x = n // a
        n = n - x * a + x * b
        
        result += x * b
    
    return result