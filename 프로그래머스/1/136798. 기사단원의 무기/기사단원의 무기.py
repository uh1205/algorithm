def solution(number, limit, power):
    count = [0] * (number + 1)
    result = 0
    
    for i in range(1, number + 1):
        for j in range(i, number + 1, i):
            count[j] += 1
    
    for i in range(1, number + 1):
        if count[i] > limit:
            result += power
        else:
            result += count[i]
    
    return result