def solution(k, score):
    answer = []
    honor = []
    
    for s in score:
        honor.append(s)

        if len(honor) > k:
            honor.remove(min(honor))

        answer.append(min(honor))
    
    return answer