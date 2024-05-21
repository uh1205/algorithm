def solution(s):
    m = int(len(s) / 2)
    
    if len(s) % 2 == 0:
        return s[m - 1 : m + 1]
    else:
        return s[m]