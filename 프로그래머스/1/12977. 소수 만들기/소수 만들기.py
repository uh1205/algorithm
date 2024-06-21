def isprime(num):
    for i in range(2, num):
        if num % i == 0:
            return False
    return True

def solution(nums):
    result = 0
    from itertools import combinations
    
    for t in combinations(nums, 3):
        if isprime(sum(t)):
            result += 1
    
    return result