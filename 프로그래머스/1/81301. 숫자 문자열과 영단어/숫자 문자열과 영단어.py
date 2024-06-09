def solution(s):
    result = ''
    temp = ''
    n = {
        'zero':'0', 'one':'1',
        'two':'2', 'three':'3',
        'four':'4', 'five':'5',
        'six':'6', 'seven':'7',
        'eight':'8', 'nine':'9'
    }
    
    for c in s:
        if c.isdigit():
            result += c
        else:
            temp += c
            
        if temp in n:
            result += n[temp]
            temp = ''
    
    return int(result)
            