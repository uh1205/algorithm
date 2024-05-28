def solution(arr1, arr2):
    return [[i + j for i, j in zip(a1, a2)] for a1, a2 in zip(arr1, arr2)]
