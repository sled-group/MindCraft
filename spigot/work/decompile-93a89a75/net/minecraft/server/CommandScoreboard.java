package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

public class CommandScoreboard {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.add.duplicate", new Object[0]));
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.display.alreadyEmpty", new Object[0]));
    private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.display.alreadySet", new Object[0]));
    private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.players.enable.failed", new Object[0]));
    private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.players.enable.invalid", new Object[0]));
    private static final Dynamic2CommandExceptionType f = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("commands.scoreboard.players.get.null", new Object[]{object, object1});
    });

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("scoreboard").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("objectives").then(CommandDispatcher.a("list").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource());
        }))).then(CommandDispatcher.a("add").then(CommandDispatcher.a("objective", (ArgumentType) StringArgumentType.word()).then(((RequiredArgumentBuilder) CommandDispatcher.a("criteria", (ArgumentType) ArgumentScoreboardCriteria.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), StringArgumentType.getString(commandcontext, "objective"), ArgumentScoreboardCriteria.a(commandcontext, "criteria"), new ChatComponentText(StringArgumentType.getString(commandcontext, "objective")));
        })).then(CommandDispatcher.a("displayName", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), StringArgumentType.getString(commandcontext, "objective"), ArgumentScoreboardCriteria.a(commandcontext, "criteria"), ArgumentChatComponent.a(commandcontext, "displayName"));
        })))))).then(CommandDispatcher.a("modify").then(((RequiredArgumentBuilder) CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("displayname").then(CommandDispatcher.a("displayName", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardObjective.a(commandcontext, "objective"), ArgumentChatComponent.a(commandcontext, "displayName"));
        })))).then(a())))).then(CommandDispatcher.a("remove").then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardObjective.a(commandcontext, "objective"));
        })))).then(CommandDispatcher.a("setdisplay").then(((RequiredArgumentBuilder) CommandDispatcher.a("slot", (ArgumentType) ArgumentScoreboardSlot.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardSlot.a(commandcontext, "slot"));
        })).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardSlot.a(commandcontext, "slot"), ArgumentScoreboardObjective.a(commandcontext, "objective"));
        })))))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("players").then(((LiteralArgumentBuilder) CommandDispatcher.a("list").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource());
        })).then(CommandDispatcher.a("target", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.a(commandcontext, "target"));
        })))).then(CommandDispatcher.a("set").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("score", (ArgumentType) IntegerArgumentType.integer()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.b(commandcontext, "objective"), IntegerArgumentType.getInteger(commandcontext, "score"));
        })))))).then(CommandDispatcher.a("get").then(CommandDispatcher.a("target", (ArgumentType) ArgumentScoreholder.a()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.a(commandcontext, "target"), ArgumentScoreboardObjective.a(commandcontext, "objective"));
        }))))).then(CommandDispatcher.a("add").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("score", (ArgumentType) IntegerArgumentType.integer(0)).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.b(commandcontext, "objective"), IntegerArgumentType.getInteger(commandcontext, "score"));
        })))))).then(CommandDispatcher.a("remove").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("score", (ArgumentType) IntegerArgumentType.integer(0)).executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.b(commandcontext, "objective"), IntegerArgumentType.getInteger(commandcontext, "score"));
        })))))).then(CommandDispatcher.a("reset").then(((RequiredArgumentBuilder) CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"));
        })).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.a(commandcontext, "objective"));
        }))))).then(CommandDispatcher.a("enable").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("objective", (ArgumentType) ArgumentScoreboardObjective.a()).suggests((commandcontext, suggestionsbuilder) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), suggestionsbuilder);
        }).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.a(commandcontext, "objective"));
        }))))).then(CommandDispatcher.a("operation").then(CommandDispatcher.a("targets", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("targetObjective", (ArgumentType) ArgumentScoreboardObjective.a()).then(CommandDispatcher.a("operation", (ArgumentType) ArgumentMathOperation.a()).then(CommandDispatcher.a("source", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).then(CommandDispatcher.a("sourceObjective", (ArgumentType) ArgumentScoreboardObjective.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "targets"), ArgumentScoreboardObjective.b(commandcontext, "targetObjective"), ArgumentMathOperation.a(commandcontext, "operation"), ArgumentScoreholder.c(commandcontext, "source"), ArgumentScoreboardObjective.a(commandcontext, "sourceObjective"));
        })))))))));
    }

    private static LiteralArgumentBuilder<CommandListenerWrapper> a() {
        LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = CommandDispatcher.a("rendertype");
        IScoreboardCriteria.EnumScoreboardHealthDisplay[] aiscoreboardcriteria_enumscoreboardhealthdisplay = IScoreboardCriteria.EnumScoreboardHealthDisplay.values();
        int i = aiscoreboardcriteria_enumscoreboardhealthdisplay.length;

        for (int j = 0; j < i; ++j) {
            IScoreboardCriteria.EnumScoreboardHealthDisplay iscoreboardcriteria_enumscoreboardhealthdisplay = aiscoreboardcriteria_enumscoreboardhealthdisplay[j];

            literalargumentbuilder.then(CommandDispatcher.a(iscoreboardcriteria_enumscoreboardhealthdisplay.a()).executes((commandcontext) -> {
                return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardObjective.a(commandcontext, "objective"), iscoreboardcriteria_enumscoreboardhealthdisplay);
            }));
        }

        return literalargumentbuilder;
    }

    private static CompletableFuture<Suggestions> a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, SuggestionsBuilder suggestionsbuilder) {
        List<String> list = Lists.newArrayList();
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = scoreboardserver.getObjectives().iterator();

        while (iterator.hasNext()) {
            ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

            if (scoreboardobjective.getCriteria() == IScoreboardCriteria.TRIGGER) {
                boolean flag = false;
                Iterator iterator1 = collection.iterator();

                while (true) {
                    if (iterator1.hasNext()) {
                        String s = (String) iterator1.next();

                        if (scoreboardserver.b(s, scoreboardobjective) && !scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective).g()) {
                            continue;
                        }

                        flag = true;
                    }

                    if (flag) {
                        list.add(scoreboardobjective.getName());
                    }
                    break;
                }
            }
        }

        return ICompletionProvider.b((Iterable) list, suggestionsbuilder);
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s, ScoreboardObjective scoreboardobjective) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        if (!scoreboardserver.b(s, scoreboardobjective)) {
            throw CommandScoreboard.f.create(scoreboardobjective.getName(), s);
        } else {
            ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.get.success", new Object[]{s, scoreboardscore.getScore(), scoreboardobjective.e()}), false);
            return scoreboardscore.getScore();
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective, ArgumentMathOperation.a argumentmathoperation_a, Collection<String> collection1, ScoreboardObjective scoreboardobjective1) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        int i = 0;

        ScoreboardScore scoreboardscore;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += scoreboardscore.getScore()) {
            String s = (String) iterator.next();

            scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);
            Iterator iterator1 = collection1.iterator();

            while (iterator1.hasNext()) {
                String s1 = (String) iterator1.next();
                ScoreboardScore scoreboardscore1 = scoreboardserver.getPlayerScoreForObjective(s1, scoreboardobjective1);

                argumentmathoperation_a.apply(scoreboardscore, scoreboardscore1);
            }
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.operation.success.single", new Object[]{scoreboardobjective.e(), collection.iterator().next(), i}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.operation.success.multiple", new Object[]{scoreboardobjective.e(), collection.size()}), true);
        }

        return i;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective) throws CommandSyntaxException {
        if (scoreboardobjective.getCriteria() != IScoreboardCriteria.TRIGGER) {
            throw CommandScoreboard.e.create();
        } else {
            ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
            int i = 0;
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);

                if (scoreboardscore.g()) {
                    scoreboardscore.a(false);
                    ++i;
                }
            }

            if (i == 0) {
                throw CommandScoreboard.d.create();
            } else {
                if (collection.size() == 1) {
                    commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.enable.success.single", new Object[]{scoreboardobjective.e(), collection.iterator().next()}), true);
                } else {
                    commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.enable.success.multiple", new Object[]{scoreboardobjective.e(), collection.size()}), true);
                }

                return i;
            }
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            scoreboardserver.resetPlayerScores(s, (ScoreboardObjective) null);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.reset.all.single", new Object[]{collection.iterator().next()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.reset.all.multiple", new Object[]{collection.size()}), true);
        }

        return collection.size();
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            scoreboardserver.resetPlayerScores(s, scoreboardobjective);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.reset.specific.single", new Object[]{scoreboardobjective.e(), collection.iterator().next()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.reset.specific.multiple", new Object[]{scoreboardobjective.e(), collection.size()}), true);
        }

        return collection.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective, int i) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            ScoreboardScore scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);

            scoreboardscore.setScore(i);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.set.success.single", new Object[]{scoreboardobjective.e(), collection.iterator().next(), i}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.set.success.multiple", new Object[]{scoreboardobjective.e(), collection.size(), i}), true);
        }

        return i * collection.size();
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective, int i) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        int j = 0;

        ScoreboardScore scoreboardscore;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); j += scoreboardscore.getScore()) {
            String s = (String) iterator.next();

            scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);
            scoreboardscore.setScore(scoreboardscore.getScore() + i);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.add.success.single", new Object[]{i, scoreboardobjective.e(), collection.iterator().next(), j}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.add.success.multiple", new Object[]{i, scoreboardobjective.e(), collection.size()}), true);
        }

        return j;
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection, ScoreboardObjective scoreboardobjective, int i) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        int j = 0;

        ScoreboardScore scoreboardscore;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); j += scoreboardscore.getScore()) {
            String s = (String) iterator.next();

            scoreboardscore = scoreboardserver.getPlayerScoreForObjective(s, scoreboardobjective);
            scoreboardscore.setScore(scoreboardscore.getScore() - i);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.remove.success.single", new Object[]{i, scoreboardobjective.e(), collection.iterator().next(), j}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.remove.success.multiple", new Object[]{i, scoreboardobjective.e(), collection.size()}), true);
        }

        return j;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper) {
        Collection<String> collection = commandlistenerwrapper.getServer().getScoreboard().getPlayers();

        if (collection.isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.list.empty", new Object[0]), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.list.success", new Object[]{collection.size(), ChatComponentUtils.a(collection)}), false);
        }

        return collection.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s) {
        Map<ScoreboardObjective, ScoreboardScore> map = commandlistenerwrapper.getServer().getScoreboard().getPlayerObjectives(s);

        if (map.isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.empty", new Object[]{s}), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.success", new Object[]{s, map.size()}), false);
            Iterator iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<ScoreboardObjective, ScoreboardScore> entry = (Entry) iterator.next();

                commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.entry", new Object[]{((ScoreboardObjective) entry.getKey()).e(), ((ScoreboardScore) entry.getValue()).getScore()}), false);
            }
        }

        return map.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, int i) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        if (scoreboardserver.getObjectiveForSlot(i) == null) {
            throw CommandScoreboard.b.create();
        } else {
            scoreboardserver.setDisplaySlot(i, (ScoreboardObjective) null);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.display.cleared", new Object[]{Scoreboard.h()[i]}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, int i, ScoreboardObjective scoreboardobjective) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        if (scoreboardserver.getObjectiveForSlot(i) == scoreboardobjective) {
            throw CommandScoreboard.c.create();
        } else {
            scoreboardserver.setDisplaySlot(i, scoreboardobjective);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.display.set", new Object[]{Scoreboard.h()[i], scoreboardobjective.getDisplayName()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardObjective scoreboardobjective, IChatBaseComponent ichatbasecomponent) {
        if (!scoreboardobjective.getDisplayName().equals(ichatbasecomponent)) {
            scoreboardobjective.setDisplayName(ichatbasecomponent);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.modify.displayname", new Object[]{scoreboardobjective.getName(), scoreboardobjective.e()}), true);
        }

        return 0;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardObjective scoreboardobjective, IScoreboardCriteria.EnumScoreboardHealthDisplay iscoreboardcriteria_enumscoreboardhealthdisplay) {
        if (scoreboardobjective.getRenderType() != iscoreboardcriteria_enumscoreboardhealthdisplay) {
            scoreboardobjective.setRenderType(iscoreboardcriteria_enumscoreboardhealthdisplay);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.modify.rendertype", new Object[]{scoreboardobjective.e()}), true);
        }

        return 0;
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardObjective scoreboardobjective) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        scoreboardserver.unregisterObjective(scoreboardobjective);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.remove.success", new Object[]{scoreboardobjective.e()}), true);
        return scoreboardserver.getObjectives().size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s, IScoreboardCriteria iscoreboardcriteria, IChatBaseComponent ichatbasecomponent) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        if (scoreboardserver.getObjective(s) != null) {
            throw CommandScoreboard.a.create();
        } else if (s.length() > 16) {
            throw ArgumentScoreboardObjective.a.create(16);
        } else {
            scoreboardserver.registerObjective(s, iscoreboardcriteria, ichatbasecomponent, iscoreboardcriteria.e());
            ScoreboardObjective scoreboardobjective = scoreboardserver.getObjective(s);

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.add.success", new Object[]{scoreboardobjective.e()}), true);
            return scoreboardserver.getObjectives().size();
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper) {
        Collection<ScoreboardObjective> collection = commandlistenerwrapper.getServer().getScoreboard().getObjectives();

        if (collection.isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.list.empty", new Object[0]), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.scoreboard.objectives.list.success", new Object[]{collection.size(), ChatComponentUtils.b(collection, ScoreboardObjective::e)}), false);
        }

        return collection.size();
    }
}
