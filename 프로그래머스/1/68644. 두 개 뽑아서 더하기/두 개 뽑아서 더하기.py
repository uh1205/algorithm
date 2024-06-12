def solution(numbers):
    answer = []
    from itertools import combinations
    
    for t in combinations(numbers, 2):
        answer.append(sum(t))
    
    return sorted(list(set(answer)))