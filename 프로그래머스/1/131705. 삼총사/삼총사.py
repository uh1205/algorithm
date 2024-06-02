def solution(number):
    from itertools import combinations
    count = 0
    
    for t in combinations(number, 3):
        if sum(t) == 0:
            count += 1
        
    return count