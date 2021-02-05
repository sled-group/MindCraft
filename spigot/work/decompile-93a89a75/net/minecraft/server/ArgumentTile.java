package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ArgumentTile implements ArgumentType<ArgumentTileLocation> {

    private static final Collection<String> a = Arrays.asList("stone", "minecraft:stone", "stone[foo=bar]", "foo{bar=baz}");

    public ArgumentTile() {}

    public static ArgumentTile a() {
        return new ArgumentTile();
    }

    public ArgumentTileLocation parse(StringReader stringreader) throws CommandSyntaxException {
        ArgumentBlock argumentblock = (new ArgumentBlock(stringreader, false)).a(true);

        return new ArgumentTileLocation(argumentblock.getBlockData(), argumentblock.getStateMap().keySet(), argumentblock.c());
    }

    public static ArgumentTileLocation a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (ArgumentTileLocation) commandcontext.getArgument(s, ArgumentTileLocation.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        StringReader stringreader = new StringReader(suggestionsbuilder.getInput());

        stringreader.setCursor(suggestionsbuilder.getStart());
        ArgumentBlock argumentblock = new ArgumentBlock(stringreader, false);

        try {
            argumentblock.a(true);
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        return argumentblock.a(suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentTile.a;
    }
}
