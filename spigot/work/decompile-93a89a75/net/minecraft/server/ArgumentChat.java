package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;

public class ArgumentChat implements ArgumentType<ArgumentChat.a> {

    private static final Collection<String> a = Arrays.asList("Hello world!", "foo", "@e", "Hello @p :)");

    public ArgumentChat() {}

    public static ArgumentChat a() {
        return new ArgumentChat();
    }

    public static IChatBaseComponent a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        return ((ArgumentChat.a) commandcontext.getArgument(s, ArgumentChat.a.class)).a((CommandListenerWrapper) commandcontext.getSource(), ((CommandListenerWrapper) commandcontext.getSource()).hasPermission(2));
    }

    public ArgumentChat.a parse(StringReader stringreader) throws CommandSyntaxException {
        return ArgumentChat.a.a(stringreader, true);
    }

    public Collection<String> getExamples() {
        return ArgumentChat.a;
    }

    public static class b {

        private final int a;
        private final int b;
        private final EntitySelector c;

        public b(int i, int j, EntitySelector entityselector) {
            this.a = i;
            this.b = j;
            this.c = entityselector;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        @Nullable
        public IChatBaseComponent a(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
            return EntitySelector.a(this.c.getEntities(commandlistenerwrapper));
        }
    }

    public static class a {

        private final String a;
        private final ArgumentChat.b[] b;

        public a(String s, ArgumentChat.b[] aargumentchat_b) {
            this.a = s;
            this.b = aargumentchat_b;
        }

        public IChatBaseComponent a(CommandListenerWrapper commandlistenerwrapper, boolean flag) throws CommandSyntaxException {
            if (this.b.length != 0 && flag) {
                ChatComponentText chatcomponenttext = new ChatComponentText(this.a.substring(0, this.b[0].a()));
                int i = this.b[0].a();
                ArgumentChat.b[] aargumentchat_b = this.b;
                int j = aargumentchat_b.length;

                for (int k = 0; k < j; ++k) {
                    ArgumentChat.b argumentchat_b = aargumentchat_b[k];
                    IChatBaseComponent ichatbasecomponent = argumentchat_b.a(commandlistenerwrapper);

                    if (i < argumentchat_b.a()) {
                        chatcomponenttext.a(this.a.substring(i, argumentchat_b.a()));
                    }

                    if (ichatbasecomponent != null) {
                        chatcomponenttext.addSibling(ichatbasecomponent);
                    }

                    i = argumentchat_b.b();
                }

                if (i < this.a.length()) {
                    chatcomponenttext.a(this.a.substring(i, this.a.length()));
                }

                return chatcomponenttext;
            } else {
                return new ChatComponentText(this.a);
            }
        }

        public static ArgumentChat.a a(StringReader stringreader, boolean flag) throws CommandSyntaxException {
            String s = stringreader.getString().substring(stringreader.getCursor(), stringreader.getTotalLength());

            if (!flag) {
                stringreader.setCursor(stringreader.getTotalLength());
                return new ArgumentChat.a(s, new ArgumentChat.b[0]);
            } else {
                List<ArgumentChat.b> list = Lists.newArrayList();
                int i = stringreader.getCursor();

                while (stringreader.canRead()) {
                    if (stringreader.peek() == '@') {
                        int j = stringreader.getCursor();

                        EntitySelector entityselector;

                        try {
                            ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader);

                            entityselector = argumentparserselector.parse();
                        } catch (CommandSyntaxException commandsyntaxexception) {
                            if (commandsyntaxexception.getType() != ArgumentParserSelector.d && commandsyntaxexception.getType() != ArgumentParserSelector.b) {
                                throw commandsyntaxexception;
                            }

                            stringreader.setCursor(j + 1);
                            continue;
                        }

                        list.add(new ArgumentChat.b(j - i, stringreader.getCursor() - i, entityselector));
                    } else {
                        stringreader.skip();
                    }
                }

                return new ArgumentChat.a(s, (ArgumentChat.b[]) list.toArray(new ArgumentChat.b[list.size()]));
            }
        }
    }
}
