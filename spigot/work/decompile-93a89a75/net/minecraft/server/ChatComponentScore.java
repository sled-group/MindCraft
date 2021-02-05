package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import javax.annotation.Nullable;

public class ChatComponentScore extends ChatBaseComponent implements ChatComponentContextual {

    private final String b;
    @Nullable
    private final EntitySelector c;
    private final String d;
    private String e = "";

    public ChatComponentScore(String s, String s1) {
        this.b = s;
        this.d = s1;
        EntitySelector entityselector = null;

        try {
            ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(new StringReader(s));

            entityselector = argumentparserselector.parse();
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        this.c = entityselector;
    }

    public String i() {
        return this.b;
    }

    public String k() {
        return this.d;
    }

    public void b(String s) {
        this.e = s;
    }

    @Override
    public String getText() {
        return this.e;
    }

    private void b(CommandListenerWrapper commandlistenerwrapper) {
        MinecraftServer minecraftserver = commandlistenerwrapper.getServer();

        if (minecraftserver != null && minecraftserver.F() && UtilColor.b(this.e)) {
            ScoreboardServer scoreboardserver = minecraftserver.getScoreboard();
            ScoreboardObjective scoreboardobjective = scoreboardserver.getObjective(this.d);

            if (scoreboardserver.b(this.b, scoreboardobjective)) {
                ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(this.b, scoreboardobjective);

                this.b(String.format("%d", scoreboardscore.getScore()));
            } else {
                this.e = "";
            }
        }

    }

    @Override
    public ChatComponentScore g() {
        ChatComponentScore chatcomponentscore = new ChatComponentScore(this.b, this.d);

        chatcomponentscore.b(this.e);
        return chatcomponentscore;
    }

    @Override
    public IChatBaseComponent a(@Nullable CommandListenerWrapper commandlistenerwrapper, @Nullable Entity entity, int i) throws CommandSyntaxException {
        if (commandlistenerwrapper == null) {
            return this.g();
        } else {
            String s;

            if (this.c != null) {
                List<? extends Entity> list = this.c.getEntities(commandlistenerwrapper);

                if (list.isEmpty()) {
                    s = this.b;
                } else {
                    if (list.size() != 1) {
                        throw ArgumentEntity.a.create();
                    }

                    s = ((Entity) list.get(0)).getName();
                }
            } else {
                s = this.b;
            }

            String s1 = entity != null && s.equals("*") ? entity.getName() : s;
            ChatComponentScore chatcomponentscore = new ChatComponentScore(s1, this.d);

            chatcomponentscore.b(this.e);
            chatcomponentscore.b(commandlistenerwrapper);
            return chatcomponentscore;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatComponentScore)) {
            return false;
        } else {
            ChatComponentScore chatcomponentscore = (ChatComponentScore) object;

            return this.b.equals(chatcomponentscore.b) && this.d.equals(chatcomponentscore.d) && super.equals(object);
        }
    }

    @Override
    public String toString() {
        return "ScoreComponent{name='" + this.b + '\'' + "objective='" + this.d + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
    }
}
