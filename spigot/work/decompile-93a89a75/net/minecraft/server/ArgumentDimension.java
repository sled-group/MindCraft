package net.minecraft.server;

import com.google.common.collect.Streams;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgumentDimension implements ArgumentType<DimensionManager> {

    private static final Collection<String> b = (Collection) Stream.of(DimensionManager.OVERWORLD, DimensionManager.NETHER).map((dimensionmanager) -> {
        return DimensionManager.a(dimensionmanager).toString();
    }).collect(Collectors.toList());
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.dimension.invalid", new Object[]{object});
    });

    public ArgumentDimension() {}

    public DimensionManager parse(StringReader stringreader) throws CommandSyntaxException {
        MinecraftKey minecraftkey = MinecraftKey.a(stringreader);

        return (DimensionManager) IRegistry.DIMENSION_TYPE.getOptional(minecraftkey).orElseThrow(() -> {
            return ArgumentDimension.a.create(minecraftkey);
        });
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.a(Streams.stream(DimensionManager.a()).map(DimensionManager::a), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentDimension.b;
    }

    public static ArgumentDimension a() {
        return new ArgumentDimension();
    }

    public static DimensionManager a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (DimensionManager) commandcontext.getArgument(s, DimensionManager.class);
    }
}
