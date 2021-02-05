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

public class ArgumentScoreboardObjective implements ArgumentType<String> {

    private static final Collection<String> b = Arrays.asList("foo", "*", "012");
    private static final DynamicCommandExceptionType c = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.objective.notFound", new Object[]{object});
    });
    private static final DynamicCommandExceptionType d = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.objective.readonly", new Object[]{object});
    });
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("commands.scoreboard.objectives.add.longName", new Object[]{object});
    });

    public ArgumentScoreboardObjective() {}

    public static ArgumentScoreboardObjective a() {
        return new ArgumentScoreboardObjective();
    }

    public static ScoreboardObjective a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        String s1 = (String) commandcontext.getArgument(s, String.class);
        ScoreboardServer scoreboardserver = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard();
        ScoreboardObjective scoreboardobjective = scoreboardserver.getObjective(s1);

        if (scoreboardobjective == null) {
            throw ArgumentScoreboardObjective.c.create(s1);
        } else {
            return scoreboardobjective;
        }
    }

    public static ScoreboardObjective b(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        ScoreboardObjective scoreboardobjective = a(commandcontext, s);

        if (scoreboardobjective.getCriteria().isReadOnly()) {
            throw ArgumentScoreboardObjective.d.create(scoreboardobjective.getName());
        } else {
            return scoreboardobjective;
        }
    }

    public String parse(StringReader stringreader) throws CommandSyntaxException {
        String s = stringreader.readUnquotedString();

        if (s.length() > 16) {
            throw ArgumentScoreboardObjective.a.create(16);
        } else {
            return s;
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        if (commandcontext.getSource() instanceof CommandListenerWrapper) {
            return ICompletionProvider.b((Iterable) ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard().d(), suggestionsbuilder);
        } else if (commandcontext.getSource() instanceof ICompletionProvider) {
            ICompletionProvider icompletionprovider = (ICompletionProvider) commandcontext.getSource();

            return icompletionprovider.a(commandcontext, suggestionsbuilder);
        } else {
            return Suggestions.empty();
        }
    }

    public Collection<String> getExamples() {
        return ArgumentScoreboardObjective.b;
    }
}
