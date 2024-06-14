def solution(a, b):
    dow = ['FRI', 'SAT', 'SUN', 'MON', 'TUE', 'WED', 'THU']
    end = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30]
    
    return dow[(b + sum(end[:a])) % 7 - 1]