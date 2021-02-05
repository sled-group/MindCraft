package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ArgumentScoreboardCriteria implements ArgumentType<IScoreboardCriteria> {

    private static final Collection<String> b = Arrays.asList("foo", "foo.bar.baz", "minecraft:foo");
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.criteria.invalid", new Object[]{object});
    });

    private ArgumentScoreboardCriteria() {}

    public static ArgumentScoreboardCriteria a() {
        return new ArgumentScoreboardCriteria();
    }

    public static IScoreboardCriteria a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (IScoreboardCriteria) commandcontext.getArgument(s, IScoreboardCriteria.class);
    }

    public IScoreboardCriteria parse(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        while (stringreader.canRead() && stringreader.peek() != ' ') {
            stringreader.skip();
        }

        String s = stringreader.getString().substring(i, stringreader.getCursor());

        return (IScoreboardCriteria) IScoreboardCriteria.a(s).orElseThrow(() -> {
            stringreader.setCursor(i);
            return ArgumentScoreboardCriteria.a.create(s);
        });
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        List<String> list = Lists.newArrayList(IScoreboardCriteria.criteria.keySet());
        Iterator iterator = IRegistry.STATS.iterator();

        while (iterator.hasNext()) {
            StatisticWrapper<?> statisticwrapper = (StatisticWrapper) iterator.next();
            Iterator iterator1 = statisticwrapper.getRegistry().iterator();

            while (iterator1.hasNext()) {
                Object object = iterator1.next();
                String s = this.a(statisticwrapper, object);

                list.add(s);
            }
        }

        return ICompletionProvider.b((Iterable) list, suggestionsbuilder);
    }

    public <T> String a(StatisticWrapper<T> statisticwrapper, Object object) {
        return Statistic.a(statisticwrapper, object);
    }

    public Collection<String> getExamples() {
        return ArgumentScoreboardCriteria.b;
    }
}
