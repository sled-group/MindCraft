package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

public class CommandStopSound {

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        RequiredArgumentBuilder<CommandListenerWrapper, EntitySelector> requiredargumentbuilder = (RequiredArgumentBuilder) ((RequiredArgumentBuilder) CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), (SoundCategory) null, (MinecraftKey) null);
        })).then(CommandDispatcher.a("*").then(CommandDispatcher.a("sound", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.c).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), (SoundCategory) null, ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"));
        })));
        SoundCategory[] asoundcategory = SoundCategory.values();
        int i = asoundcategory.length;

        for (int j = 0; j < i; ++j) {
            SoundCategory soundcategory = asoundcategory[j];

            requiredargumentbuilder.then(((LiteralArgumentBuilder) CommandDispatcher.a(soundcategory.a()).executes((commandcontext) -> {
                return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), soundcategory, (MinecraftKey) null);
            })).then(CommandDispatcher.a("sound", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.c).executes((commandcontext) -> {
                return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), soundcategory, ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"));
            })));
        }

        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("stopsound").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(requiredargumentbuilder));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<EntityPlayer> collection, @Nullable SoundCategory soundcategory, @Nullable MinecraftKey minecraftkey) {
        PacketPlayOutStopSound packetplayoutstopsound = new PacketPlayOutStopSound(minecraftkey, soundcategory);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            entityplayer.playerConnection.sendPacket(packetplayoutstopsound);
        }

        if (soundcategory != null) {
            if (minecraftkey != null) {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.stopsound.success.source.sound", new Object[]{minecraftkey, soundcategory.a()}), true);
            } else {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.stopsound.success.source.any", new Object[]{soundcategory.a()}), true);
            }
        } else if (minecraftkey != null) {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.stopsound.success.sourceless.sound", new Object[]{minecraftkey}), true);
        } else {
            commandlistenerwrapper.sendMessage(new ChatMessage("commands.stopsound.success.sourceless.any", new Object[0]), true);
        }

        return collection.size();
    }
}
