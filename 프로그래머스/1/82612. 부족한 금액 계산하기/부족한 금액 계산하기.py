def solution(price, money, count):
    fee = sum(price * i for i in range(1, count + 1))
    return 0 if money > fee else fee - money