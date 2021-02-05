package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ArgumentMobEffect implements ArgumentType<MobEffectList> {

    private static final Collection<String> b = Arrays.asList("spooky", "effect");
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("effect.effectNotFound", new Object[]{object});
    });

    public ArgumentMobEffect() {}

    public static ArgumentMobEffect a() {
        return new ArgumentMobEffect();
    }

    public static MobEffectList a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return (MobEffectList) commandcontext.getArgument(s, MobEffectList.class);
    }

    public MobEffectList parse(StringReader stringreader) throws CommandSyntaxException {
        MinecraftKey minecraftkey = MinecraftKey.a(stringreader);

        return (MobEffectList) IRegistry.MOB_EFFECT.getOptional(minecraftkey).orElseThrow(() -> {
            return ArgumentMobEffect.a.create(minecraftkey);
        });
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.a((Iterable) IRegistry.MOB_EFFECT.keySet(), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentMobEffect.b;
    }
}
