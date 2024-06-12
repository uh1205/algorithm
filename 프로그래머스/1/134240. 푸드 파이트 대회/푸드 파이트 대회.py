def solution(food):
    result = ''.join(str(i) * (v // 2) for i, v in enumerate(food))
    
    return result + '0' + result[::-1]