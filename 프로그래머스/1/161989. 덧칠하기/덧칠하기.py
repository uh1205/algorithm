def solution(n, m, section):
    count = 0
    coloredWall = 0  # 마지막으로 페인트칠한 구역의 끝 번호
    for wall in section:
        if wall > coloredWall:
            coloredWall = wall + m - 1  # 새로운 페인트칠한 구역의 끝 번호 업데이트
            count += 1  # 페인트칠 횟수 증가
    return count