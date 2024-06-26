def solution(n, m):
    gcd = lambda a, b : b if a % b == 0 else gcd(b, a % b)
    lcm = lambda a, b : a * b // gcd(a, b)
    
    return [gcd(n, m), lcm(n, m)]