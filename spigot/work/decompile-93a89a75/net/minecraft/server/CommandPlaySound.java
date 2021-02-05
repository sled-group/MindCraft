package net.minecraft.server;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Iterator;

public class CommandPlaySound {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.playsound.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        RequiredArgumentBuilder<CommandListenerWrapper, MinecraftKey> requiredargumentbuilder = CommandDispatcher.a("sound", (ArgumentType) ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.c);
        SoundCategory[] asoundcategory = SoundCategory.values();
        int i = asoundcategory.length;

        for (int j = 0; j < i; ++j) {
            SoundCategory soundcategory = asoundcategory[j];

            requiredargumentbuilder.then(a(soundcategory));
        }

        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("playsound").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(requiredargumentbuilder));
    }

    private static LiteralArgumentBuilder<CommandListenerWrapper> a(SoundCategory soundcategory) {
        return (LiteralArgumentBuilder) CommandDispatcher.a(soundcategory.a()).then(((RequiredArgumentBuilder) CommandDispatcher.a("targets", (ArgumentType) ArgumentEntity.d()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"), soundcategory, ((CommandListenerWrapper) commandcontext.getSource()).getPosition(), 1.0F, 1.0F, 0.0F);
        })).then(((RequiredArgumentBuilder) CommandDispatcher.a("pos", (ArgumentType) ArgumentVec3.a()).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"), soundcategory, ArgumentVec3.a(commandcontext, "pos"), 1.0F, 1.0F, 0.0F);
        })).then(((RequiredArgumentBuilder) CommandDispatcher.a("volume", (ArgumentType) FloatArgumentType.floatArg(0.0F)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"), soundcategory, ArgumentVec3.a(commandcontext, "pos"), (Float) commandcontext.getArgument("volume", Float.class), 1.0F, 0.0F);
        })).then(((RequiredArgumentBuilder) CommandDispatcher.a("pitch", (ArgumentType) FloatArgumentType.floatArg(0.0F, 2.0F)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"), soundcategory, ArgumentVec3.a(commandcontext, "pos"), (Float) commandcontext.getArgument("volume", Float.class), (Float) commandcontext.getArgument("pitch", Float.class), 0.0F);
        })).then(CommandDispatcher.a("minVolume", (ArgumentType) FloatArgumentType.floatArg(0.0F, 1.0F)).executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), ArgumentEntity.f(commandcontext, "targets"), ArgumentMinecraftKeyRegistered.c(commandcontext, "sound"), soundcategory, ArgumentVec3.a(commandcontext, "pos"), (Float) commandcontext.getArgument("volume", Float.class), (Float) commandcontext.getArgument("pitch", Float.class), (Float) commandcontext.getArgument("minVolume", Float.class));
        }))))));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<EntityPlayer> collection, MinecraftKey minecraftkey, SoundCategory soundcategory, Vec3D vec3d, float f, float f1, float f2) throws CommandSyntaxException {
        double d0 = Math.pow(f > 1.0F ? (double) (f * 16.0F) : 16.0D, 2.0D);
        int i = 0;
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();
            double d1 = vec3d.x - entityplayer.locX;
            double d2 = vec3d.y - entityplayer.locY;
            double d3 = vec3d.z - entityplayer.locZ;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;
            Vec3D vec3d1 = vec3d;
            float f3 = f;

            if (d4 > d0) {
                if (f2 <= 0.0F) {
                    continue;
                }

                double d5 = (double) MathHelper.sqrt(d4);

                vec3d1 = new Vec3D(entityplayer.locX + d1 / d5 * 2.0D, entityplayer.locY + d2 / d5 * 2.0D, entityplayer.locZ + d3 / d5 * 2.0D);
                f3 = f2;
            }

            entityplayer.playerConnection.sendPacket(new PacketPlayOutCustomSoundEffect(minecraftkey, soundcategory, vec3d1, f3, f1));
            ++i;
        }

        if (i == 0) {
            throw CommandPlaySound.a.create();
        } else {
            if (collection.size() == 1) {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.playsound.success.single", new Object[]{minecraftkey, ((EntityPlayer) collection.iterator().next()).getScoreboardDisplayName()}), true);
            } else {
                commandlistenerwrapper.sendMessage(new ChatMessage("commands.playsound.success.single", new Object[]{minecraftkey, ((EntityPlayer) collection.iterator().next()).getScoreboardDisplayName()}), true);
            }

            return i;
        }
    }
}
