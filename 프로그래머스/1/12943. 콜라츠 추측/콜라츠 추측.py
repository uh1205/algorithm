def solution(num):
    if num == 1:
        return 0
    
    count = 1
    
    for _ in range(500):
        if num % 2 == 0:
            num /= 2
        else:
            num = num * 3 + 1
            
        if num == 1:
            return count
        else:
            count += 1
            
    return -1