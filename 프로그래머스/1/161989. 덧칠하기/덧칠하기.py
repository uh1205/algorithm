def solution(n, m, section):
    answer = 0
    # N 미터 벽 
    # 페인트가 벗겨져 칠하기로함
    # 룰러의 길이 M
    #   - 구역의 일부부만 칠하기 안됨

    # Greedy

    coloredWall = 0
    for wall in section:
        if wall > coloredWall:
            coloredWall = wall + m -1
            answer += 1
    return answer