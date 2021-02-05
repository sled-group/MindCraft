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

public class ArgumentScoreboardSlot implements ArgumentType<Integer> {

    private static final Collection<String> b = Arrays.asList("sidebar", "foo.bar");
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.scoreboardDisplaySlot.invalid", new Object[]{object});
    });

    private ArgumentScoreboardSlot() {}

    public static ArgumentScoreboardSlot a() {
        return new ArgumentScoreboardSlot();
    }

    public static int a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (Integer) commandcontext.getArgument(s, Integer.class);
    }

    public Integer parse(StringReader stringreader) throws CommandSyntaxException {
        String s = stringreader.readUnquotedString();
        int i = Scoreboard.getSlotForName(s);

        if (i == -1) {
            throw ArgumentScoreboardSlot.a.create(s);
        } else {
            return i;
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return ICompletionProvider.a(Scoreboard.h(), suggestionsbuilder);
    }

    public Collection<String> getExamples() {
        return ArgumentScoreboardSlot.b;
    }
}
