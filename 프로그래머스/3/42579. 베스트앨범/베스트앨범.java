import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();
        for (String genre : genres) {
            genreMap.putIfAbsent(genre, new Genre(genre));
        }
        
        for (int i = 0; i < plays.length; i++) {
            Genre genre = genreMap.get(genres[i]);
            genre.add(new Song(i, plays[i]));
        }
        
        List<Genre> genreList = new ArrayList<>(genreMap.values());
        genreList.sort(null);
        
        List<Integer> answer = new ArrayList<>();
        for(Genre genre : genreList) {
            List<Song> songs = genre.songs;
            songs.sort(null);
            
            answer.add(songs.get(0).id);
            if (songs.size() > 1) {
                answer.add(songs.get(1).id);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    static class Song implements Comparable<Song> {
        int id;
        int plays;
        
        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        public int compareTo(Song o) {
            return o.plays - this.plays;
        }
    }
    
    static class Genre implements Comparable<Genre> {
        String name;
        int totalPlays = 0;
        List<Song> songs = new ArrayList<>();
        
        Genre(String name) {
            this.name = name;
        }
        
        public void add(Song song) {
            songs.add(song);
            totalPlays += song.plays;
        }
        
        public int compareTo(Genre o) {
            return o.totalPlays - this.totalPlays;
        }
    }
}