package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

public class CommandPublish {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.publish.failed", new Object[0]));
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.publish.alreadyPublished", new Object[]{object});
    });

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("publish").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.getServer().isEmbeddedServer() && commandlistenerwrapper.hasPermission(4);
        })).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), HttpUtilities.a());
        })).then(CommandDispatcher.a("port", (ArgumentType) IntegerArgumentType.integer(0, 65535)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "port"));
        })));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, int i) throws CommandSyntaxException {
        if (commandlistenerwrapper.getServer().af()) {
            throw CommandPublish.b.create(commandlistenerwrapper.getServer().getPort());
        } else if (!commandlistenerwrapper.getServer().a(commandlistenerwrapper.getServer().getGamemode(), false, i)) {
            throw CommandPublish.a.create();
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.publish.success", new Object[]{i}), true);
            return i;
        }
    }
}
