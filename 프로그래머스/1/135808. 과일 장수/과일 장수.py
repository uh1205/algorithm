def solution(k, m, score):
    result = 0
    score.sort(reverse=True)
    # 3322 111 : 0 4 : 7
    
    for i in range(0, len(score) - m + 2, m):
        l = score[i : i + m]
        if len(l) == m:
            result += l[-1] * m
    
    return result
    