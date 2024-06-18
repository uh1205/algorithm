def solution(cards1, cards2, goal):
    from collections import deque
    
    queue1 = deque(cards1)
    queue2 = deque(cards2)
    
    for word in goal:
        if queue1 and queue1[0] == word:
            queue1.popleft()
        elif queue2 and queue2[0] == word:
            queue2.popleft()
        else:
            return "No"
    
    return "Yes"
