def solution(a, b, n):
    result = 0

    while n >= a:
        c = n // a * b
        result += c
        n = n % a + c
        
    return result