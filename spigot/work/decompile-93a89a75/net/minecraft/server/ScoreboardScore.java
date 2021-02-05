package net.minecraft.server;

import java.util.Comparator;
import javax.annotation.Nullable;

public class ScoreboardScore {

    public static final Comparator<ScoreboardScore> a = (scoreboardscore, scoreboardscore1) -> {
        return scoreboardscore.getScore() > scoreboardscore1.getScore() ? 1 : (scoreboardscore.getScore() < scoreboardscore1.getScore() ? -1 : scoreboardscore1.getPlayerName().compareToIgnoreCase(scoreboardscore.getPlayerName()));
    };
    private final Scoreboard b;
    @Nullable
    private final ScoreboardObjective c;
    private final String playerName;
    private int score;
    private boolean f;
    private boolean g;

    public ScoreboardScore(Scoreboard scoreboard, ScoreboardObjective scoreboardobjective, String s) {
        this.b = scoreboard;
        this.c = scoreboardobjective;
        this.playerName = s;
        this.f = true;
        this.g = true;
    }

    public void addScore(int i) {
        if (this.c.getCriteria().isReadOnly()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.setScore(this.getScore() + i);
        }
    }

    public void incrementScore() {
        this.addScore(1);
    }

    public int getScore() {
        return this.score;
    }

    public void c() {
        this.setScore(0);
    }

    public void setScore(int i) {
        int j = this.score;

        this.score = i;
        if (j != i || this.g) {
            this.g = false;
            this.f().handleScoreChanged(this);
        }

    }

    @Nullable
    public ScoreboardObjective getObjective() {
        return this.c;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Scoreboard f() {
        return this.b;
    }

    public boolean g() {
        return this.f;
    }

    public void a(boolean flag) {
        this.f = flag;
    }
}
