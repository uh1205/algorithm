def solution(k, m, score):
    result = 0
    score.sort(reverse = True)
    for i in range(m - 1, len(score), m):
        result += score[i] * m

    return result