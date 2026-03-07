import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, List<Music>> genreMusic = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], 
                               genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            
            genreMusic.putIfAbsent(genres[i], new ArrayList<>());
            genreMusic.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        List<String> sortedGenre = new ArrayList<>(genrePlayCount.keySet());
        sortedGenre.sort((a, b) -> 
                         genrePlayCount.get(b).compareTo(genrePlayCount.get(a)));

        List<Integer> answer = new ArrayList<>();
        
        for (String genre : sortedGenre) {
            List<Music> musics = genreMusic.get(genre);
            
            musics.sort((a, b) -> {
                if (a.play == b.play) {
                    return a.id - b.id;
                }
                return b.play - a.play;
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