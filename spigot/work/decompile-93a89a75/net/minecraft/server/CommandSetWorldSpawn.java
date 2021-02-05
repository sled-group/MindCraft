package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CommandSetWorldSpawn {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("setworldspawn").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), new BlockPosition(((CommandListenerWrapper) commandcontext.getSource()).getPosition()));
        })).then(CommandDispatcher.a("pos", (ArgumentType) ArgumentPosition.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentPosition.b(commandcontext, "pos"));
        })));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, BlockPosition blockposition) {
        commandlistenerwrapper.getWorld().a_(blockposition);
        commandlistenerwrapper.getServer().getPlayerList().sendAll(new PacketPlayOutSpawnPosition(blockposition));
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.setworldspawn.success", new Object[]{blockposition.getX(), blockposition.getY(), blockposition.getZ()}), true);
        return 1;
    }
}
