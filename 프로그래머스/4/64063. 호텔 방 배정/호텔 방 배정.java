import java.util.*;

class Solution {
    // 각 방의 "다음 빈 방" 정보를 저장할 해시맵
    Map<Long, Long> parent = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            // 해당 고객에게 배정된 방 번호를 찾아 저장
            answer[i] = findEmptyRoom(room_number[i]);
        }

        return answer;
    }

    private long findEmptyRoom(long room) {
        // 1. 방이 비어있는 경우 (맵에 정보가 없는 경우)
        if (!parent.containsKey(room)) {
            // 현재 방을 배정하고, 다음 빈 방 후보로 room + 1을 지목
            parent.put(room, room + 1);
            return room;
        }

        // 2. 방이 이미 찬 경우, 재귀적으로 다음 빈 방을 탐색
        long next = parent.get(room);
        long emptyRoom = findEmptyRoom(next);
        
        // 3. 경로 압축: 탐색 과정에서 거친 방들이 바로 emptyRoom + 1을 가리키도록 갱신
        parent.put(room, emptyRoom + 1);
        
        return emptyRoom;
    }
}