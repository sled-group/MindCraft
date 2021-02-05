package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CommandSay {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("say").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("message", (ArgumentType) ArgumentChat.a()).executes((commandcontext) -> {
            IChatBaseComponent ichatbasecomponent = ArgumentChat.a(commandcontext, "message");

            ((CommandListenerWrapper) commandcontext.getSource()).getServer().getPlayerList().sendMessage(new ChatMessage("chat.type.announcement", new Object[]{((CommandListenerWrapper) commandcontext.getSource()).getScoreboardDisplayName(), ichatbasecomponent}));
            return 1;
        })));
    }
}
