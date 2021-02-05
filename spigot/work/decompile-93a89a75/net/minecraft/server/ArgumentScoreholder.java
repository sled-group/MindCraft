package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ArgumentScoreholder implements ArgumentType<ArgumentScoreholder.a> {

    public static final SuggestionProvider<CommandListenerWrapper> a = (commandcontext, suggestionsbuilder) -> {
        StringReader stringreader = new StringReader(suggestionsbuilder.getInput());

        stringreader.setCursor(suggestionsbuilder.getStart());
        ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader);

        try {
            argumentparserselector.parse();
        } catch (CommandSyntaxException commandsyntaxexception) {
            ;
        }

        return argumentparserselector.a(suggestionsbuilder, (suggestionsbuilder1) -> {
            ICompletionProvider.b((Iterable) ((CommandListenerWrapper) commandcontext.getSource()).l(), suggestionsbuilder1);
        });
    };
    private static final Collection<String> b = Arrays.asList("Player", "0123", "*", "@e");
    private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.scoreHolder.empty", new Object[0]));
    private final boolean d;

    public ArgumentScoreholder(boolean flag) {
        this.d = flag;
    }

    public static String a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return (String) b(commandcontext, s).iterator().next();
    }

    public static Collection<String> b(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return a(commandcontext, s, Collections::emptyList);
    }

    public static Collection<String> c(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        ScoreboardServer scoreboardserver = ((CommandListenerWrapper) commandcontext.getSource()).getServer().getScoreboard();

        scoreboardserver.getClass();
        return a(commandcontext, s, scoreboardserver::getPlayers);
    }

    public static Collection<String> a(CommandContext<CommandListenerWrapper> commandcontext, String s, Supplier<Collection<String>> supplier) throws CommandSyntaxException {
        Collection<String> collection = ((ArgumentScoreholder.a) commandcontext.getArgument(s, ArgumentScoreholder.a.class)).getNames((CommandListenerWrapper) commandcontext.getSource(), supplier);

        if (collection.isEmpty()) {
            throw ArgumentEntity.d.create();
        } else {
            return collection;
        }
    }

    public static ArgumentScoreholder a() {
        return new ArgumentScoreholder(false);
    }

    public static ArgumentScoreholder b() {
        return new ArgumentScoreholder(true);
    }

    public ArgumentScoreholder.a parse(StringReader stringreader) throws CommandSyntaxException {
        if (stringreader.canRead() && stringreader.peek() == '@') {
            ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader);
            EntitySelector entityselector = argumentparserselector.parse();

            if (!this.d && entityselector.a() > 1) {
                throw ArgumentEntity.a.create();
            } else {
                return new ArgumentScoreholder.b(entityselector);
            }
        } else {
            int i = stringreader.getCursor();

            while (stringreader.canRead() && stringreader.peek() != ' ') {
                stringreader.skip();
            }

            String s = stringreader.getString().substring(i, stringreader.getCursor());

            if (s.equals("*")) {
                return (commandlistenerwrapper, supplier) -> {
                    Collection<String> collection = (Collection) supplier.get();

                    if (collection.isEmpty()) {
                        throw ArgumentScoreholder.c.create();
                    } else {
                        return collection;
                    }
                };
            } else {
                Collection<String> collection = Collections.singleton(s);

                return (commandlistenerwrapper, supplier) -> {
                    return collection;
                };
            }
        }
    }

    public Collection<String> getExamples() {
        return ArgumentScoreholder.b;
    }

    public static class c implements ArgumentSerializer<ArgumentScoreholder> {

        public c() {}

        public void a(ArgumentScoreholder argumentscoreholder, PacketDataSerializer packetdataserializer) {
            byte b0 = 0;

            if (argumentscoreholder.d) {
                b0 = (byte) (b0 | 1);
            }

            packetdataserializer.writeByte(b0);
        }

        @Override
        public ArgumentScoreholder b(PacketDataSerializer packetdataserializer) {
            byte b0 = packetdataserializer.readByte();
            boolean flag = (b0 & 1) != 0;

            return new ArgumentScoreholder(flag);
        }

        public void a(ArgumentScoreholder argumentscoreholder, JsonObject jsonobject) {
            jsonobject.addProperty("amount", argumentscoreholder.d ? "multiple" : "single");
        }
    }

    public static class b implements ArgumentScoreholder.a {

        private final EntitySelector a;

        public b(EntitySelector entityselector) {
            this.a = entityselector;
        }

        @Override
        public Collection<String> getNames(CommandListenerWrapper commandlistenerwrapper, Supplier<Collection<String>> supplier) throws CommandSyntaxException {
            List<? extends Entity> list = this.a.getEntities(commandlistenerwrapper);

            if (list.isEmpty()) {
                throw ArgumentEntity.d.create();
            } else {
                List<String> list1 = Lists.newArrayList();
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    Entity entity = (Entity) iterator.next();

                    list1.add(entity.getName());
                }

                return list1;
            }
        }
    }

    @FunctionalInterface
    public interface a {

        Collection<String> getNames(CommandListenerWrapper commandlistenerwrapper, Supplier<Collection<String>> supplier) throws CommandSyntaxException;
    }
}
