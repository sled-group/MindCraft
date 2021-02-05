package net.minecraft.server;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ICompletionProvider {

    Collection<String> l();

    default Collection<String> p() {
        return Collections.emptyList();
    }

    Collection<String> m();

    Collection<MinecraftKey> n();

    Stream<MinecraftKey> o();

    CompletableFuture<Suggestions> a(CommandContext<ICompletionProvider> commandcontext, SuggestionsBuilder suggestionsbuilder);

    default Collection<ICompletionProvider.a> q() {
        return Collections.singleton(ICompletionProvider.a.b);
    }

    default Collection<ICompletionProvider.a> r() {
        return Collections.singleton(ICompletionProvider.a.b);
    }

    boolean hasPermission(int i);

    static <T> void a(Iterable<T> iterable, String s, Function<T, MinecraftKey> function, Consumer<T> consumer) {
        boolean flag = s.indexOf(58) > -1;
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T t0 = iterator.next();
            MinecraftKey minecraftkey = (MinecraftKey) function.apply(t0);

            if (flag) {
                String s1 = minecraftkey.toString();

                if (s1.startsWith(s)) {
                    consumer.accept(t0);
                }
            } else if (minecraftkey.getNamespace().startsWith(s) || minecraftkey.getNamespace().equals("minecraft") && minecraftkey.getKey().startsWith(s)) {
                consumer.accept(t0);
            }
        }

    }

    static <T> void a(Iterable<T> iterable, String s, String s1, Function<T, MinecraftKey> function, Consumer<T> consumer) {
        if (s.isEmpty()) {
            iterable.forEach(consumer);
        } else {
            String s2 = Strings.commonPrefix(s, s1);

            if (!s2.isEmpty()) {
                String s3 = s.substring(s2.length());

                a(iterable, s3, function, consumer);
            }
        }

    }

    static CompletableFuture<Suggestions> a(Iterable<MinecraftKey> iterable, SuggestionsBuilder suggestionsbuilder, String s) {
        String s1 = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);

        a(iterable, s1, s, (minecraftkey) -> {
            return minecraftkey;
        }, (minecraftkey) -> {
            suggestionsbuilder.suggest(s + minecraftkey);
        });
        return suggestionsbuilder.buildFuture();
    }

    static CompletableFuture<Suggestions> a(Iterable<MinecraftKey> iterable, SuggestionsBuilder suggestionsbuilder) {
        String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);

        a(iterable, s, (minecraftkey) -> {
            return minecraftkey;
        }, (minecraftkey) -> {
            suggestionsbuilder.suggest(minecraftkey.toString());
        });
        return suggestionsbuilder.buildFuture();
    }

    static <T> CompletableFuture<Suggestions> a(Iterable<T> iterable, SuggestionsBuilder suggestionsbuilder, Function<T, MinecraftKey> function, Function<T, Message> function1) {
        String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);

        a(iterable, s, function, (object) -> {
            suggestionsbuilder.suggest(((MinecraftKey) function.apply(object)).toString(), (Message) function1.apply(object));
        });
        return suggestionsbuilder.buildFuture();
    }

    static CompletableFuture<Suggestions> a(Stream<MinecraftKey> stream, SuggestionsBuilder suggestionsbuilder) {
        return a(stream::iterator, suggestionsbuilder);
    }

    static <T> CompletableFuture<Suggestions> a(Stream<T> stream, SuggestionsBuilder suggestionsbuilder, Function<T, MinecraftKey> function, Function<T, Message> function1) {
        return a(stream::iterator, suggestionsbuilder, function, function1);
    }

    static CompletableFuture<Suggestions> a(String s, Collection<ICompletionProvider.a> collection, SuggestionsBuilder suggestionsbuilder, Predicate<String> predicate) {
        List<String> list = Lists.newArrayList();

        if (Strings.isNullOrEmpty(s)) {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                ICompletionProvider.a icompletionprovider_a = (ICompletionProvider.a) iterator.next();
                String s1 = icompletionprovider_a.c + " " + icompletionprovider_a.d + " " + icompletionprovider_a.e;

                if (predicate.test(s1)) {
                    list.add(icompletionprovider_a.c);
                    list.add(icompletionprovider_a.c + " " + icompletionprovider_a.d);
                    list.add(s1);
                }
            }
        } else {
            String[] astring = s.split(" ");
            Iterator iterator1;
            ICompletionProvider.a icompletionprovider_a1;
            String s2;

            if (astring.length == 1) {
                iterator1 = collection.iterator();

                while (iterator1.hasNext()) {
                    icompletionprovider_a1 = (ICompletionProvider.a) iterator1.next();
                    s2 = astring[0] + " " + icompletionprovider_a1.d + " " + icompletionprovider_a1.e;
                    if (predicate.test(s2)) {
                        list.add(astring[0] + " " + icompletionprovider_a1.d);
                        list.add(s2);
                    }
                }
            } else if (astring.length == 2) {
                iterator1 = collection.iterator();

                while (iterator1.hasNext()) {
                    icompletionprovider_a1 = (ICompletionProvider.a) iterator1.next();
                    s2 = astring[0] + " " + astring[1] + " " + icompletionprovider_a1.e;
                    if (predicate.test(s2)) {
                        list.add(s2);
                    }
                }
            }
        }

        return b((Iterable) list, suggestionsbuilder);
    }

    static CompletableFuture<Suggestions> b(String s, Collection<ICompletionProvider.a> collection, SuggestionsBuilder suggestionsbuilder, Predicate<String> predicate) {
        List<String> list = Lists.newArrayList();

        if (Strings.isNullOrEmpty(s)) {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                ICompletionProvider.a icompletionprovider_a = (ICompletionProvider.a) iterator.next();
                String s1 = icompletionprovider_a.c + " " + icompletionprovider_a.e;

                if (predicate.test(s1)) {
                    list.add(icompletionprovider_a.c);
                    list.add(s1);
                }
            }
        } else {
            String[] astring = s.split(" ");

            if (astring.length == 1) {
                Iterator iterator1 = collection.iterator();

                while (iterator1.hasNext()) {
                    ICompletionProvider.a icompletionprovider_a1 = (ICompletionProvider.a) iterator1.next();
                    String s2 = astring[0] + " " + icompletionprovider_a1.e;

                    if (predicate.test(s2)) {
                        list.add(s2);
                    }
                }
            }
        }

        return b((Iterable) list, suggestionsbuilder);
    }

    static CompletableFuture<Suggestions> b(Iterable<String> iterable, SuggestionsBuilder suggestionsbuilder) {
        String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (s1.toLowerCase(Locale.ROOT).startsWith(s)) {
                suggestionsbuilder.suggest(s1);
            }
        }

        return suggestionsbuilder.buildFuture();
    }

    static CompletableFuture<Suggestions> b(Stream<String> stream, SuggestionsBuilder suggestionsbuilder) {
        String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);

        stream.filter((s1) -> {
            return s1.toLowerCase(Locale.ROOT).startsWith(s);
        }).forEach(suggestionsbuilder::suggest);
        return suggestionsbuilder.buildFuture();
    }

    static CompletableFuture<Suggestions> a(String[] astring, SuggestionsBuilder suggestionsbuilder) {
        String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);
        String[] astring1 = astring;
        int i = astring.length;

        for (int j = 0; j < i; ++j) {
            String s1 = astring1[j];

            if (s1.toLowerCase(Locale.ROOT).startsWith(s)) {
                suggestionsbuilder.suggest(s1);
            }
        }

        return suggestionsbuilder.buildFuture();
    }

    public static class a {

        public static final ICompletionProvider.a a = new ICompletionProvider.a("^", "^", "^");
        public static final ICompletionProvider.a b = new ICompletionProvider.a("~", "~", "~");
        public final String c;
        public final String d;
        public final String e;

        public a(String s, String s1, String s2) {
            this.c = s;
            this.d = s1;
            this.e = s2;
        }
    }
}
