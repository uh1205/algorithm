def solution(number, limit, power):
    # 각 숫자의 약수 개수를 저장할 배열
    divisors_count = [0] * (number + 1)
    
    # 배수를 이용해 약수 개수 계산
    for i in range(1, number + 1):
        for j in range(i, number + 1, i):
            divisors_count[j] += 1
    
    # 총 필요한 철의 무게 계산
    total_weight = 0
    for k in range(1, number + 1):
        if divisors_count[k] > limit:
            total_weight += power
        else:
            total_weight += divisors_count[k]
    
    return total_weight

