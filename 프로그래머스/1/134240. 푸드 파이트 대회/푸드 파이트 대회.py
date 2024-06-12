def solution(food):
    result = ''
    
    for i, v in enumerate(food, 0):
        if i != 0:
            result += str(i) * (v // 2)
    
    return result + '0' + result[::-1]