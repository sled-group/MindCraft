package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandTrigger {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.trigger.failed.unprimed", new Object[0]));
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.trigger.failed.invalid", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) CommandDispatcher.a("trigger").then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).suggests((commandcontext, suggestionsbuilder) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), suggestionsbuilder);
        }).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(((CommandListenerWrapper) commandcontext.getSource()).h(), ArgumentScoreboardObjective.a(commandcontext, "objective")));
        })).then(CommandDispatcher.a("add").then(CommandDispatcher.a("value", (ArgumentType) IntegerArgumentType.integer()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), a(((CommandListenerWrapper) commandcontext.getSource()).h(), ArgumentScoreboardObjective.a(commandcontext, "objective")), IntegerArgumentType.getInteger(commandcontext, "value"));
        })))).then(CommandDispatcher.a("set").then(CommandDispatcher.a("value", (ArgumentType) IntegerArgumentType.integer()).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), a(((CommandListenerWrapper) commandcontext.getSource()).h(), ArgumentScoreboardObjective.a(commandcontext, "objective")), IntegerArgumentType.getInteger(commandcontext, "value"));
        })))));
    }

    public static CompletableFuture<Suggestions> a(CommandListenerWrapper commandlistenerwrapper, SuggestionsBuilder suggestionsbuilder) {
        Entity entity = commandlistenerwrapper.getEntity();
        List<String> list = Lists.newArrayList();

        if (entity != null) {
            ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
            String s = entity.getName();
            Iterator iterator = scoreboardserver.getObjectives().iterator();

            while (iterator.hasNext()) {
                ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

                if (scoreboardobjective.getCriteria() == IScoreboardCriteria.TRIGGER && scoreboardserver.b(s, scoreboardobjective)) {
                    ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);

                    if (!scoreboardscore.g()) {
                        list.add(scoreboardobjective.getName());
                    }
                }
            }
        }

        return ICompletionProvider.b((Iterable) list, suggestionsbuilder);
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardScore scoreboardscore, int i) {
        scoreboardscore.addScore(i);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.trigger.add.success", new Object[]{scoreboardscore.getObjective().e(), i}), true);
        return scoreboardscore.getScore();
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, ScoreboardScore scoreboardscore, int i) {
        scoreboardscore.setScore(i);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.trigger.set.success", new Object[]{scoreboardscore.getObjective().e(), i}), true);
        return i;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardScore scoreboardscore) {
        scoreboardscore.addScore(1);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.trigger.simple.success", new Object[]{scoreboardscore.getObjective().e()}), true);
        return scoreboardscore.getScore();
    }

    private static ScoreboardScore a(EntityPlayer entityplayer, ScoreboardObjective scoreboardobjective) throws CommandSyntaxException {
        if (scoreboardobjective.getCriteria() != IScoreboardCriteria.TRIGGER) {
            throw CommandTrigger.b.create();
        } else {
            Scoreboard scoreboard = entityplayer.getScoreboard();
            String s = entityplayer.getName();

            if (!scoreboard.b(s, scoreboardobjective)) {
                throw CommandTrigger.a.create();
            } else {
                ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(s, scoreboardobjective);

                if (scoreboardscore.g()) {
                    throw CommandTrigger.a.create();
                } else {
                    scoreboardscore.a(true);
                    return scoreboardscore;
                }
            }
        }
    }
}
