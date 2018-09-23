package com.velvn.projects.quiz.user;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private final String username;

    private Map<String, Double> bestGamesScores = new HashMap<>(0);

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, Map<String, Double> bestGamesScores) {
        this.username = username;
        if (bestGamesScores != null) this.bestGamesScores = bestGamesScores;
    }

    public String getUsername() {
        return username;
    }

    public void addBestGameScore(String game, Double score) {
        if (bestGamesScores.containsKey(game)) {
            if (bestGamesScores.get(game).compareTo(score) < 0) bestGamesScores.put(game, score);
        } else {
            bestGamesScores.put(game, score);
        }
    }

    public Double getBestScoreFor(String game) {
        return bestGamesScores.get(game);
    }
}
