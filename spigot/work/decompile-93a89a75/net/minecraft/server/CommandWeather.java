package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CommandWeather {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("weather").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(((LiteralArgumentBuilder) CommandDispatcher.a("clear").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), 6000);
        })).then(CommandDispatcher.a("duration", (ArgumentType) IntegerArgumentType.integer(0, 1000000)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "duration") * 20);
        })))).then(((LiteralArgumentBuilder) CommandDispatcher.a("rain").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), 6000);
        })).then(CommandDispatcher.a("duration", (ArgumentType) IntegerArgumentType.integer(0, 1000000)).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "duration") * 20);
        })))).then(((LiteralArgumentBuilder) CommandDispatcher.a("thunder").executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource(), 6000);
        })).then(CommandDispatcher.a("duration", (ArgumentType) IntegerArgumentType.integer(0, 1000000)).executes((commandcontext) -> {
            return c((CommandListenerWrapper) commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "duration") * 20);
        }))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, int i) {
        commandlistenerwrapper.getWorld().getWorldData().g(i);
        commandlistenerwrapper.getWorld().getWorldData().setWeatherDuration(0);
        commandlistenerwrapper.getWorld().getWorldData().setThunderDuration(0);
        commandlistenerwrapper.getWorld().getWorldData().setStorm(false);
        commandlistenerwrapper.getWorld().getWorldData().setThundering(false);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.weather.set.clear", new Object[0]), true);
        return i;
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, int i) {
        commandlistenerwrapper.getWorld().getWorldData().g(0);
        commandlistenerwrapper.getWorld().getWorldData().setWeatherDuration(i);
        commandlistenerwrapper.getWorld().getWorldData().setThunderDuration(i);
        commandlistenerwrapper.getWorld().getWorldData().setStorm(true);
        commandlistenerwrapper.getWorld().getWorldData().setThundering(false);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.weather.set.rain", new Object[0]), true);
        return i;
    }

    private static int c(CommandListenerWrapper commandlistenerwrapper, int i) {
        commandlistenerwrapper.getWorld().getWorldData().g(0);
        commandlistenerwrapper.getWorld().getWorldData().setWeatherDuration(i);
        commandlistenerwrapper.getWorld().getWorldData().setThunderDuration(i);
        commandlistenerwrapper.getWorld().getWorldData().setStorm(true);
        commandlistenerwrapper.getWorld().getWorldData().setThundering(true);
        commandlistenerwrapper.sendMessage(new ChatMessage("commands.weather.set.thunder", new Object[0]), true);
        return i;
    }
}
