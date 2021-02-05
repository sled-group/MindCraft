package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.datafixers.util.Either;

public class CommandSchedule {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.schedule.same_tick", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("schedule").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("function").then(CommandDispatcher.a("function", (ArgumentType) ArgumentTag.a()).suggests(CommandFunction.a).then(CommandDispatcher.a("time", (ArgumentType) ArgumentTime.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentTag.b(commandcontext, "function"), IntegerArgumentType.getInteger(commandcontext, "time"));
        })))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Either<CustomFunction, Tag<CustomFunction>> either, int i) throws CommandSyntaxException {
        if (i == 0) {
            throw CommandSchedule.a.create();
        } else {
            long j = commandlistenerwrapper.getWorld().getTime() + (long) i;

            either.ifLeft((customfunction) -> {
                MinecraftKey minecraftkey = customfunction.a();

                commandlistenerwrapper.getWorld().getWorldData().y().b(minecraftkey.toString(), j, new CustomFunctionCallback(minecraftkey));
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.schedule.created.function", new Object[]{minecraftkey, i, j}), true);
            }).ifRight((tag) -> {
                MinecraftKey minecraftkey = tag.c();

                commandlistenerwrapper.getWorld().getWorldData().y().b("#" + minecraftkey.toString(), j, new CustomFunctionCallbackTag(minecraftkey));
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.schedule.created.tag", new Object[]{minecraftkey, i, j}), true);
            });
            return (int) Math.floorMod(j, 2147483647L);
        }
    }
}
