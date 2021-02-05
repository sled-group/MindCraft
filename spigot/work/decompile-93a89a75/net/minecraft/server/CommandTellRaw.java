package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Iterator;

public class CommandTellRaw {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("tellraw").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).then(CommandDispatcher.a("message", (ArgumentType) ArgumentChatComponent.a()).executes((commandcontext) -> {
            int i = 0;

            for (Iterator iterator = ArgumentEntity.f(commandcontext, "targets").iterator(); iterator.hasNext(); ++i) {
                EntityPlayer entityplayer = (EntityPlayer) iterator.next();

                entityplayer.sendMessage(ChatComponentUtils.filterForDisplay((CommandListenerWrapper) commandcontext.getSource(), ArgumentChatComponent.a(commandcontext, "message"), entityplayer, 0));
            }

            return i;
        }))));
    }
}
