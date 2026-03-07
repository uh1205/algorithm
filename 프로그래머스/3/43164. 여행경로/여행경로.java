import java.util.*;

class Solution {

    Map<String, List<String>> graph = new HashMap<>();
    List<String> route = new ArrayList<>();
    int ticketCount;

    public String[] solution(String[][] tickets) {
        ticketCount = tickets.length;

        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(ticket[1]);
        }

        for (List<String> list : graph.values()) {
            Collections.sort(list);
        }

        route.add("ICN");
        dfs("ICN");

        return route.toArray(new String[0]);
    }

    private boolean dfs(String airport) {

        if (route.size() == ticketCount + 1) {
            return true;
        }

        if (!graph.containsKey(airport)) {
            return false;
        }

        List<String> destinations = graph.get(airport);

        for (int i = 0; i < destinations.size(); i++) {

            String next = destinations.remove(i);
            route.add(next);

            if (dfs(next)) {
                return true;
            }

            route.remove(route.size() - 1);
            destinations.add(i, next);
        }

        return false;
    }
}