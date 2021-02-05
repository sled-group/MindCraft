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

public class ArgumentScoreboardTeam implements ArgumentType<String> {

    private static final Collection<String> a = Arrays.asList("foo", "123");
    private static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("team.notFound", new Object[]{object});
    });

    public ArgumentScoreboardTeam() {}

    public static ArgumentScoreboardTeam a() {
        return new ArgumentScoreboardTeam();
    }

    public static ScoreboardTeam a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        String s1 = (String) commandcontext.getArgument(s, String.class);
        ScoreboardServer scoreboardserver = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard();
        ScoreboardTeam scoreboardteam = scoreboardserver.getTeam(s1);

        if (scoreboardteam == null) {
            throw ArgumentScoreboardTeam.b.create(s1);
        } else {
            return scoreboardteam;
        }
    }

    public String parse(StringReader stringreader) throws CommandSyntaxException {
        return stringreader.readUnquotedString();
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        return commandcontext.getSource() instanceof ICompletionProvider ? ICompletionProvider.b((Iterable) ((ICompletionProvider) commandcontext.getSource()).m(), suggestionsbuilder) : Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return ArgumentScoreboardTeam.a;
    }
}
