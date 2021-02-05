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

public class ArgumentItemStack implements ArgumentType<ArgumentPredicateItemStack> {

    private static final Collection<String> a = Arrays.asList("stick", "minecraft:stick", "stick{foo=bar}");

    public ArgumentItemStack() {}

    public static ArgumentItemStack a() {
        return new ArgumentItemStack();
    }

    public ArgumentPredicateItemStack parse(StringReader stringreader) throws CommandSyntaxException {
        ArgumentParserItemStack argumentparseritemstack = (new ArgumentParserItemStack(stringreader, false)).h();

        return new ArgumentPredicateItemStack(argumentparseritemstack.b(), argumentparseritemstack.c());
    }

    public static <S> ArgumentPredicateItemStack a(CommandContext<S> commandcontext, String s) {
        return (ArgumentPredicateItemStack) commandcontext.getArgument(s, ArgumentPredicateItemStack.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        StringReader stringreader = new StringReader(suggestionsbuilder.getInput());

        stringreader.setCursor(suggestionsbuilder.getStart());
        ArgumentParserItemStack argumentparseritemstack = new ArgumentParserItemStack(stringreader, false);

        try {
            argumentparseritemstack.h();
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        return argumentparseritemstack.a(suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentItemStack.a;
    }
}
