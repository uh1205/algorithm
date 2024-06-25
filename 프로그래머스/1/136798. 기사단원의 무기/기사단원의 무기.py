def countDivisor(n):
    count = 0
    for i in range(1, int(n ** 0.5) + 1):
        if n % i == 0:
            count += 1
            if i * i != n:
                count += 1
    return count

def solution(number, limit, power):
    divisors = []
    for i in range(1, number + 1):
        c = countDivisor(i)
        divisors.append(power if c > limit else c)
    return sum(divisors)
    
    