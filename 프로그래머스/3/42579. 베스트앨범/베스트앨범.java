import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, List<Music>> genreMusic = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            
            genreMusic.putIfAbsent(genres[i], new ArrayList<>());
            genreMusic.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        List<String> sortedGenre = genrePlayCount.keySet().stream()
            .sorted((k1, k2) -> genrePlayCount.get(k2).compareTo(genrePlayCount.get(k1)))
            .collect(Collectors.toList());
        
        List<Integer> answer = new ArrayList<>();
        for (String genre : sortedGenre) {
            List<Music> musics = genreMusic.get(genre);
            musics.sort((m1, m2) -> {
                if (m1.play == m2.play) {
                    return m1.id - m2.id;
                }
                return m2.play - m1.play;
            });
            answer.add(musics.get(0).id);
            if (musics.size() > 1) {
                answer.add(musics.get(1).id);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    static class Music {
        int id;
        int play;
        
        Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
}