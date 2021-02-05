package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CommandTeam {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.team.add.duplicate", new Object[0]));
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.team.add.longName", new Object[]{object});
    });
    private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.team.empty.unchanged", new Object[0]));
    private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.name.unchanged", new Object[0]));
    private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.color.unchanged", new Object[0]));
    private static final SimpleCommandExceptionType f = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.friendlyfire.alreadyEnabled", new Object[0]));
    private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.friendlyfire.alreadyDisabled", new Object[0]));
    private static final SimpleCommandExceptionType h = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.seeFriendlyInvisibles.alreadyEnabled", new Object[0]));
    private static final SimpleCommandExceptionType i = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.seeFriendlyInvisibles.alreadyDisabled", new Object[0]));
    private static final SimpleCommandExceptionType j = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.nametagVisibility.unchanged", new Object[0]));
    private static final SimpleCommandExceptionType k = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.deathMessageVisibility.unchanged", new Object[0]));
    private static final SimpleCommandExceptionType l = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.collisionRule.unchanged", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("team").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(((LiteralArgumentBuilder) CommandDispatcher.a("list").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource());
        })).then(CommandDispatcher.a("team", (ArgumentType) ArgumentScoreboardTeam.a()).executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"));
        })))).then(CommandDispatcher.a("add").then(((RequiredArgumentBuilder) CommandDispatcher.a("team", (ArgumentType) StringArgumentType.word()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), StringArgumentType.getString(commandcontext, "team"));
        })).then(CommandDispatcher.a("displayName", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), StringArgumentType.getString(commandcontext, "team"), ArgumentChatComponent.a(commandcontext, "displayName"));
        }))))).then(CommandDispatcher.a("remove").then(CommandDispatcher.a("team", (ArgumentType) ArgumentScoreboardTeam.a()).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"));
        })))).then(CommandDispatcher.a("empty").then(CommandDispatcher.a("team", (ArgumentType) ArgumentScoreboardTeam.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"));
        })))).then(CommandDispatcher.a("join").then(((RequiredArgumentBuilder) CommandDispatcher.a("team", (ArgumentType) ArgumentScoreboardTeam.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), (Collection) Collections.singleton(((CommandListenerWrapper) commandcontext.getSource()).g().getName()));
        })).then(CommandDispatcher.a("members", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ArgumentScoreholder.c(commandcontext, "members"));
        }))))).then(CommandDispatcher.a("leave").then(CommandDispatcher.a("members", (ArgumentType) ArgumentScoreholder.b()).suggests(ArgumentScoreholder.a).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreholder.c(commandcontext, "members"));
        })))).then(CommandDispatcher.a("modify").then(((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("team", (ArgumentType) ArgumentScoreboardTeam.a()).then(CommandDispatcher.a("displayName").then(CommandDispatcher.a("displayName", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ArgumentChatComponent.a(commandcontext, "displayName"));
        })))).then(CommandDispatcher.a("color").then(CommandDispatcher.a("value", (ArgumentType) ArgumentChatFormat.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ArgumentChatFormat.a(commandcontext, "value"));
        })))).then(CommandDispatcher.a("friendlyFire").then(CommandDispatcher.a("allowed", (ArgumentType) BoolArgumentType.bool()).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), BoolArgumentType.getBool(commandcontext, "allowed"));
        })))).then(CommandDispatcher.a("seeFriendlyInvisibles").then(CommandDispatcher.a("allowed", (ArgumentType) BoolArgumentType.bool()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), BoolArgumentType.getBool(commandcontext, "allowed"));
        })))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("nametagVisibility").then(CommandDispatcher.a("never").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.NEVER);
        }))).then(CommandDispatcher.a("hideForOtherTeams").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }))).then(CommandDispatcher.a("hideForOwnTeam").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM);
        }))).then(CommandDispatcher.a("always").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS);
        })))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("deathMessageVisibility").then(CommandDispatcher.a("never").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.NEVER);
        }))).then(CommandDispatcher.a("hideForOtherTeams").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }))).then(CommandDispatcher.a("hideForOwnTeam").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM);
        }))).then(CommandDispatcher.a("always").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS);
        })))).then(((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("collisionRule").then(CommandDispatcher.a("never").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumTeamPush.NEVER);
        }))).then(CommandDispatcher.a("pushOwnTeam").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumTeamPush.PUSH_OWN_TEAM);
        }))).then(CommandDispatcher.a("pushOtherTeams").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumTeamPush.PUSH_OTHER_TEAMS);
        }))).then(CommandDispatcher.a("always").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ScoreboardTeamBase.EnumTeamPush.ALWAYS);
        })))).then(CommandDispatcher.a("prefix").then(CommandDispatcher.a("prefix", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ArgumentChatComponent.a(commandcontext, "prefix"));
        })))).then(CommandDispatcher.a("suffix").then(CommandDispatcher.a("suffix", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource(), ArgumentScoreboardTeam.a(commandcontext, "team"), ArgumentChatComponent.a(commandcontext, "suffix"));
        }))))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<String> collection) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            scoreboardserver.removePlayerFromTeam(s);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.leave.success.single", new Object[]{collection.iterator().next()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.leave.success.multiple", new Object[]{collection.size()}), true);
        }

        return collection.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, Collection<String> collection) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            scoreboardserver.addPlayerToTeam(s, scoreboardteam);
        }

        if (collection.size() == 1) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.join.success.single", new Object[]{collection.iterator().next(), scoreboardteam.d()}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.join.success.multiple", new Object[]{collection.size(), scoreboardteam.d()}), true);
        }

        return collection.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility) throws CommandSyntaxException {
        if (scoreboardteam.getNameTagVisibility() == scoreboardteambase_enumnametagvisibility) {
            throw CommandTeam.j.create();
        } else {
            scoreboardteam.setNameTagVisibility(scoreboardteambase_enumnametagvisibility);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.nametagVisibility.success", new Object[]{scoreboardteam.d(), scoreboardteambase_enumnametagvisibility.b()}), true);
            return 0;
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility) throws CommandSyntaxException {
        if (scoreboardteam.getDeathMessageVisibility() == scoreboardteambase_enumnametagvisibility) {
            throw CommandTeam.k.create();
        } else {
            scoreboardteam.setDeathMessageVisibility(scoreboardteambase_enumnametagvisibility);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.deathMessageVisibility.success", new Object[]{scoreboardteam.d(), scoreboardteambase_enumnametagvisibility.b()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, ScoreboardTeamBase.EnumTeamPush scoreboardteambase_enumteampush) throws CommandSyntaxException {
        if (scoreboardteam.getCollisionRule() == scoreboardteambase_enumteampush) {
            throw CommandTeam.l.create();
        } else {
            scoreboardteam.setCollisionRule(scoreboardteambase_enumteampush);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.collisionRule.success", new Object[]{scoreboardteam.d(), scoreboardteambase_enumteampush.b()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, boolean flag) throws CommandSyntaxException {
        if (scoreboardteam.canSeeFriendlyInvisibles() == flag) {
            if (flag) {
                throw CommandTeam.h.create();
            } else {
                throw CommandTeam.i.create();
            }
        } else {
            scoreboardteam.setCanSeeFriendlyInvisibles(flag);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.seeFriendlyInvisibles." + (flag ? "enabled" : "disabled"), new Object[]{scoreboardteam.d()}), true);
            return 0;
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, boolean flag) throws CommandSyntaxException {
        if (scoreboardteam.allowFriendlyFire() == flag) {
            if (flag) {
                throw CommandTeam.f.create();
            } else {
                throw CommandTeam.g.create();
            }
        } else {
            scoreboardteam.setAllowFriendlyFire(flag);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.friendlyfire." + (flag ? "enabled" : "disabled"), new Object[]{scoreboardteam.d()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, IChatBaseComponent ichatbasecomponent) throws CommandSyntaxException {
        if (scoreboardteam.getDisplayName().equals(ichatbasecomponent)) {
            throw CommandTeam.d.create();
        } else {
            scoreboardteam.setDisplayName(ichatbasecomponent);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.name.success", new Object[]{scoreboardteam.d()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, EnumChatFormat enumchatformat) throws CommandSyntaxException {
        if (scoreboardteam.getColor() == enumchatformat) {
            throw CommandTeam.e.create();
        } else {
            scoreboardteam.setColor(enumchatformat);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.color.success", new Object[]{scoreboardteam.d(), enumchatformat.g()}), true);
            return 0;
        }
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();
        Collection<String> collection = Lists.newArrayList(scoreboardteam.getPlayerNameSet());

        if (collection.isEmpty()) {
            throw CommandTeam.c.create();
        } else {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                scoreboardserver.removePlayerFromTeam(s, scoreboardteam);
            }

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.empty.success", new Object[]{collection.size(), scoreboardteam.d()}), true);
            return collection.size();
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam) {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        scoreboardserver.removeTeam(scoreboardteam);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.remove.success", new Object[]{scoreboardteam.d()}), true);
        return scoreboardserver.getTeams().size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s) throws CommandSyntaxException {
        return a(commandlistenerwrapper, s, (IChatBaseComponent) (new ChatComponentText(s)));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s, IChatBaseComponent ichatbasecomponent) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = commandlistenerwrapper.getServer().getScoreboard();

        if (scoreboardserver.getTeam(s) != null) {
            throw CommandTeam.a.create();
        } else if (s.length() > 16) {
            throw CommandTeam.b.create(16);
        } else {
            ScoreboardTeam scoreboardteam = scoreboardserver.createTeam(s);

            scoreboardteam.setDisplayName(ichatbasecomponent);
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.add.success", new Object[]{scoreboardteam.d()}), true);
            return scoreboardserver.getTeams().size();
        }
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam) {
        Collection<String> collection = scoreboardteam.getPlayerNameSet();

        if (collection.isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.list.members.empty", new Object[]{scoreboardteam.d()}), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.list.members.success", new Object[]{scoreboardteam.d(), collection.size(), ChatComponentUtils.a(collection)}), false);
        }

        return collection.size();
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper) {
        Collection<ScoreboardTeam> collection = commandlistenerwrapper.getServer().getScoreboard().getTeams();

        if (collection.isEmpty()) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.list.teams.empty", new Object[0]), false);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.list.teams.success", new Object[]{collection.size(), ChatComponentUtils.b(collection, ScoreboardTeam::d)}), false);
        }

        return collection.size();
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, IChatBaseComponent ichatbasecomponent) {
        scoreboardteam.setPrefix(ichatbasecomponent);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.prefix.success", new Object[]{ichatbasecomponent}), false);
        return 1;
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper, ScoreboardTeam scoreboardteam, IChatBaseComponent ichatbasecomponent) {
        scoreboardteam.setSuffix(ichatbasecomponent);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.team.option.suffix.success", new Object[]{ichatbasecomponent}), false);
        return 1;
    }
}
