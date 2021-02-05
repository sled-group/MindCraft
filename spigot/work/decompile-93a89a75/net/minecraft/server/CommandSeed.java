package net.minecraft.server;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CommandSeed {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("seed").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.getServer().isEmbeddedServer() || commandlistenerwrapper.hasPermission(2);
        })).executes((commandcontext) -> {
            long i = ((CommandListenerWrapper) commandcontext.getSource()).getWorld().getSeed();
            IChatBaseComponent ichatbasecomponent = ChatComponentUtils.a((new ChatComponentText(String.valueOf(i))).a((chatmodifier) -> {
                chatmodifier.setColor(EnumChatFormat.GREEN).setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, String.valueOf(i))).setInsertion(String.valueOf(i));
            }));

            ((CommandListenerWrapper) commandcontext.getSource()).sendMessage(new ChatMessage("commands.seed.success", new Object[]{ichatbasecomponent}), false);
            return (int) i;
        }));
    }
}
