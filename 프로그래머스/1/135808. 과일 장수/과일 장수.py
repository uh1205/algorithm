def solution(k, m, score):
    result = 0
    score.sort(reverse=True)
    
    for i in range(0, len(score) - len(score) % m, m):
        box = score[i:i + m]
        result += box[-1] * m
    
    return result