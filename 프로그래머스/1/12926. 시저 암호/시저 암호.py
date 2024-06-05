def solution(s, n):
    result = ''
    
    for c in s:
        if c.isupper():
            result += chr((ord(c) + n - ord('A')) % 26 + ord('A'))
        elif c.islower():
            result += chr((ord(c) + n - ord('a')) % 26 + ord('a'))
        else:
            result += c
            
    return result
