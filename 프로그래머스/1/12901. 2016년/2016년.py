def solution(a, b):
    dow = ['FRI', 'SAT', 'SUN', 'MON', 'TUE', 'WED', 'THU']
    end = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    
    return dow[(sum(end[:a-1]) + b - 1) % 7]