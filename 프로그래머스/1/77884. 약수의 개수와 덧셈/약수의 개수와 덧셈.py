def countDivisor(n):
    if n == 1:
        return 1
    
    count = 2
    
    for i in range(2, n):
        if n % i == 0:
            count += 1
            
    return count


def solution(left, right):
    result = 0
    
    for n in range(left, right + 1):
        if countDivisor(n) % 2 == 0:
            result += n
        else:
            result -= n
            
    return result