package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CommandRecipe {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.recipe.give.failed", new Object[0]));
    private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.recipe.take.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("recipe").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("give").then(((RequiredArgumentBuilder) CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).then(CommandDispatcher.a("recipe", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.b).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), Collections.singleton(ArgumentMinecraftKeyRegistered.b(commandcontext, "recipe")));
        }))).then(CommandDispatcher.a("*").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ((CommandListenerWrapper) commandcontext.getSource()).getServer().getCraftingManager().b());
        }))))).then(CommandDispatcher.a("take").then(((RequiredArgumentBuilder) CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).then(CommandDispatcher.a("recipe", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.b).executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), Collections.singleton(ArgumentMinecraftKeyRegistered.b(commandcontext, "recipe")));
        }))).then(CommandDispatcher.a("*").executes((commandcontext) -> {
            return b((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ((CommandListenerWrapper) commandcontext.getSource()).getServer().getCraftingManager().b());
        })))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<EntityPlayer> collection, Collection<IRecipe<?>> collection1) throws CommandSyntaxException {
        int i = 0;

        EntityPlayer entityplayer;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += entityplayer.discoverRecipes(collection1)) {
            entityplayer = (EntityPlayer) iterator.next();
        }

        if (i == 0) {
            throw CommandRecipe.a.create();
        } else {
            if (collection.size() == 1) {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.recipe.give.success.single", new Object[]{collection1.size(), ((EntityPlayer) collection.iterator().next()).getScoreboardDisplayName()}), true);
            } else {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.recipe.give.success.multiple", new Object[]{collection1.size(), collection.size()}), true);
            }

            return i;
        }
    }

    private static int b(CommandListenerWrapper commandlistenerwrapper, Collection<EntityPlayer> collection, Collection<IRecipe<?>> collection1) throws CommandSyntaxException {
        int i = 0;

        EntityPlayer entityplayer;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += entityplayer.undiscoverRecipes(collection1)) {
            entityplayer = (EntityPlayer) iterator.next();
        }

        if (i == 0) {
            throw CommandRecipe.b.create();
        } else {
            if (collection.size() == 1) {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.recipe.take.success.single", new Object[]{collection1.size(), ((EntityPlayer) collection.iterator().next()).getScoreboardDisplayName()}), true);
            } else {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.recipe.take.success.multiple", new Object[]{collection1.size(), collection.size()}), true);
            }

            return i;
        }
    }
}
