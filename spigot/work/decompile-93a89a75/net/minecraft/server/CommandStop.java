package net.minecraft.server;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CommandStop {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("stop").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(4);
        })).executes((commandcontext) -> {
            ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.stop.stopping", new Object[0]), true);
            ((CommandListenerWrapper) commandcontext.getSource()).getServer().safeShutdown(false);
            return 1;
        }));
    }
}
