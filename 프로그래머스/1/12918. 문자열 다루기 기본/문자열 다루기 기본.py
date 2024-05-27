def solution(s):
    if len(s) in [4, 6]:
        return True if s.isdigit() else False
    else:
        return False