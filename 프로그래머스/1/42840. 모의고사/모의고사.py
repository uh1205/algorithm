def solution(answers):
    student1 = [1, 2, 3, 4, 5]
    student2 = [2, 1, 2, 3, 2, 4, 2, 5]
    student3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    count = [0, 0, 0]
    
    for i, v in enumerate(answers):
        if v == student1[i % 5]:
            count[0] += 1
        if v == student2[i % 8]:
            count[1] += 1
        if v == student3[i % 10]:
            count[2] += 1
    
    return [i + 1 for i, v in enumerate(count) if v == max(count)]
