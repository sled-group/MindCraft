package net.minecraft.server;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public abstract class ScoreboardTeamBase {

    public ScoreboardTeamBase() {}

    public boolean isAlly(@Nullable ScoreboardTeamBase scoreboardteambase) {
        return scoreboardteambase == null ? false : this == scoreboardteambase;
    }

    public abstract String getName();

    public abstract IChatBaseComponent getFormattedName(IChatBaseComponent ichatbasecomponent);

    public abstract boolean allowFriendlyFire();

    public abstract EnumChatFormat getColor();

    public abstract Collection<String> getPlayerNameSet();

    public abstract ScoreboardTeamBase.EnumNameTagVisibility getDeathMessageVisibility();

    public abstract ScoreboardTeamBase.EnumTeamPush getCollisionRule();

    public static enum EnumTeamPush {

        ALWAYS("always", 0), NEVER("never", 1), PUSH_OTHER_TEAMS("pushOtherTeams", 2), PUSH_OWN_TEAM("pushOwnTeam", 3);

        private static final Map<String, ScoreboardTeamBase.EnumTeamPush> g = (Map) Arrays.stream(values()).collect(Collectors.toMap((scoreboardteambase_enumteampush) -> {
            return scoreboardteambase_enumteampush.e;
        }, (scoreboardteambase_enumteampush) -> {
            return scoreboardteambase_enumteampush;
        }));
        public final String e;
        public final int f;

        @Nullable
        public static ScoreboardTeamBase.EnumTeamPush a(String s) {
            return (ScoreboardTeamBase.EnumTeamPush) ScoreboardTeamBase.EnumTeamPush.g.get(s);
        }

        private EnumTeamPush(String s, int i) {
            this.e = s;
            this.f = i;
        }

        public IChatBaseComponent b() {
            return new ChatMessage("team.collision." + this.e, new Object[0]);
        }
    }

    public static enum EnumNameTagVisibility {

        ALWAYS("always", 0), NEVER("never", 1), HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2), HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

        private static final Map<String, ScoreboardTeamBase.EnumNameTagVisibility> g = (Map) Arrays.stream(values()).collect(Collectors.toMap((scoreboardteambase_enumnametagvisibility) -> {
            return scoreboardteambase_enumnametagvisibility.e;
        }, (scoreboardteambase_enumnametagvisibility) -> {
            return scoreboardteambase_enumnametagvisibility;
        }));
        public final String e;
        public final int f;

        @Nullable
        public static ScoreboardTeamBase.EnumNameTagVisibility a(String s) {
            return (ScoreboardTeamBase.EnumNameTagVisibility) ScoreboardTeamBase.EnumNameTagVisibility.g.get(s);
        }

        private EnumNameTagVisibility(String s, int i) {
            this.e = s;
            this.f = i;
        }

        public IChatBaseComponent b() {
            return new ChatMessage("team.visibility." + this.e, new Object[0]);
        }
    }
}
