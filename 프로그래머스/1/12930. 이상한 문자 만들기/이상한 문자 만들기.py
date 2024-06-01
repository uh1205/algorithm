def solution(s):
    res = []
    
    for x in s.split(' '):
        word = ''
        for i in range(len(x)):
            c = x[i].upper() if i % 2 == 0 else x[i].lower()
            word = word + c
        res.append(word)
        
    return ' '.join(res)