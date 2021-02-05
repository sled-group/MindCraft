package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class CommandTeamMsg {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.teammsg.failed.noteam", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        LiteralCommandNode<CommandListenerWrapper> literalcommandnode = com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) CommandDispatcher.a("teammsg").then(CommandDispatcher.a("message", (ArgumentType) ArgumentChat.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentChat.a(commandcontext, "message"));
        })));

        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) CommandDispatcher.a("tm").redirect(literalcommandnode));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, IChatBaseComponent ichatbasecomponent) throws CommandSyntaxException {
        Entity entity = commandlistenerwrapper.g();
        ScoreboardTeam scoreboardteam = (ScoreboardTeam) entity.getScoreboardTeam();

        if (scoreboardteam == null) {
            throw CommandTeamMsg.a.create();
        } else {
            Consumer<ChatModifier> consumer = (chatmodifier) -> {
                chatmodifier.setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_TEXT, new ChatMessage("chat.type.team.hover", new Object[0]))).setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/teammsg "));
            };
            IChatBaseComponent ichatbasecomponent1 = scoreboardteam.d().a(consumer);
            Iterator iterator = ichatbasecomponent1.getSiblings().iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent2 = (IChatBaseComponent) iterator.next();

                ichatbasecomponent2.a(consumer);
            }

            List<EntityPlayer> list = commandlistenerwrapper.getServer().getPlayerList().getPlayers();
            Iterator iterator1 = list.iterator();

            while (iterator1.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer) iterator1.next();

                if (entityplayer == entity) {
                    entityplayer.sendMessage(new ChatMessage("chat.type.team.sent", new Object[]{ichatbasecomponent1, commandlistenerwrapper.getScoreboardDisplayName(), ichatbasecomponent.h()}));
                } else if (entityplayer.getScoreboardTeam() == scoreboardteam) {
                    entityplayer.sendMessage(new ChatMessage("chat.type.team.text", new Object[]{ichatbasecomponent1, commandlistenerwrapper.getScoreboardDisplayName(), ichatbasecomponent.h()}));
                }
            }

            return list.size();
        }
    }
}
