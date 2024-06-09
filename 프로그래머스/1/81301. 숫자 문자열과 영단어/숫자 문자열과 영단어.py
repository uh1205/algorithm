def solution(s):
    result = ''
    temp = ''
    n = ['zero', 'one', 'two', 'three', 'four', 
         'five', 'six', 'seven', 'eight', 'nine']
    
    for c in s:
        if c.isdigit():
            result += c
        else:
            temp += c
            
        if len(temp) >= 3 and temp in n:
            result += str(n.index(temp))
            temp = ''
    
    return int(result)
            