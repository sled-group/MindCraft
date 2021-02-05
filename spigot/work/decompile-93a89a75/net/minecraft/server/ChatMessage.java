package net.minecraft.server;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class ChatMessage extends ChatBaseComponent implements ChatComponentContextual {

    private static final LocaleLanguage d = new LocaleLanguage();
    private static final LocaleLanguage e = LocaleLanguage.a();
    private final String key;
    private final Object[] args;
    private final Object h = new Object();
    private long i = -1L;
    protected final List<IChatBaseComponent> b = Lists.newArrayList();
    public static final Pattern c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public ChatMessage(String s, Object... aobject) {
        this.key = s;
        this.args = aobject;

        for (int i = 0; i < aobject.length; ++i) {
            Object object = aobject[i];

            if (object instanceof IChatBaseComponent) {
                IChatBaseComponent ichatbasecomponent = ((IChatBaseComponent) object).h();

                this.args[i] = ichatbasecomponent;
                ichatbasecomponent.getChatModifier().setChatModifier(this.getChatModifier());
            } else if (object == null) {
                this.args[i] = "null";
            }
        }

    }

    @VisibleForTesting
    synchronized void i() {
        Object object = this.h;

        synchronized (this.h) {
            long i = ChatMessage.e.b();

            if (i == this.i) {
                return;
            }

            this.i = i;
            this.b.clear();
        }

        try {
            this.b(ChatMessage.e.a(this.key));
        } catch (ChatMessageException chatmessageexception) {
            this.b.clear();

            try {
                this.b(ChatMessage.d.a(this.key));
            } catch (ChatMessageException chatmessageexception1) {
                throw chatmessageexception;
            }
        }

    }

    protected void b(String s) {
        Matcher matcher = ChatMessage.c.matcher(s);

        try {
            int i = 0;

            int j;
            int k;

            for (k = 0; matcher.find(k); k = j) {
                int l = matcher.start();

                j = matcher.end();
                if (l > k) {
                    ChatComponentText chatcomponenttext = new ChatComponentText(String.format(s.substring(k, l)));

                    chatcomponenttext.getChatModifier().setChatModifier(this.getChatModifier());
                    this.b.add(chatcomponenttext);
                }

                String s1 = matcher.group(2);
                String s2 = s.substring(l, j);

                if ("%".equals(s1) && "%%".equals(s2)) {
                    ChatComponentText chatcomponenttext1 = new ChatComponentText("%");

                    chatcomponenttext1.getChatModifier().setChatModifier(this.getChatModifier());
                    this.b.add(chatcomponenttext1);
                } else {
                    if (!"s".equals(s1)) {
                        throw new ChatMessageException(this, "Unsupported format: '" + s2 + "'");
                    }

                    String s3 = matcher.group(1);
                    int i1 = s3 != null ? Integer.parseInt(s3) - 1 : i++;

                    if (i1 < this.args.length) {
                        this.b.add(this.b(i1));
                    }
                }
            }

            if (k < s.length()) {
                ChatComponentText chatcomponenttext2 = new ChatComponentText(String.format(s.substring(k)));

                chatcomponenttext2.getChatModifier().setChatModifier(this.getChatModifier());
                this.b.add(chatcomponenttext2);
            }

        } catch (IllegalFormatException illegalformatexception) {
            throw new ChatMessageException(this, illegalformatexception);
        }
    }

    private IChatBaseComponent b(int i) {
        if (i >= this.args.length) {
            throw new ChatMessageException(this, i);
        } else {
            Object object = this.args[i];
            Object object1;

            if (object instanceof IChatBaseComponent) {
                object1 = (IChatBaseComponent) object;
            } else {
                object1 = new ChatComponentText(object == null ? "null" : object.toString());
                ((IChatBaseComponent) object1).getChatModifier().setChatModifier(this.getChatModifier());
            }

            return (IChatBaseComponent) object1;
        }
    }

    @Override
    public IChatBaseComponent setChatModifier(ChatModifier chatmodifier) {
        super.setChatModifier(chatmodifier);
        Object[] aobject = this.args;
        int i = aobject.length;

        for (int j = 0; j < i; ++j) {
            Object object = aobject[j];

            if (object instanceof IChatBaseComponent) {
                ((IChatBaseComponent) object).getChatModifier().setChatModifier(this.getChatModifier());
            }
        }

        if (this.i > -1L) {
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

                ichatbasecomponent.getChatModifier().setChatModifier(chatmodifier);
            }
        }

        return this;
    }

    @Override
    public Stream<IChatBaseComponent> c() {
        this.i();
        return Streams.concat(new Stream[]{this.b.stream(), this.siblings.stream()}).flatMap(IChatBaseComponent::c);
    }

    @Override
    public String getText() {
        this.i();
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            stringbuilder.append(ichatbasecomponent.getText());
        }

        return stringbuilder.toString();
    }

    @Override
    public ChatMessage g() {
        Object[] aobject = new Object[this.args.length];

        for (int i = 0; i < this.args.length; ++i) {
            if (this.args[i] instanceof IChatBaseComponent) {
                aobject[i] = ((IChatBaseComponent) this.args[i]).h();
            } else {
                aobject[i] = this.args[i];
            }
        }

        return new ChatMessage(this.key, aobject);
    }

    @Override
    public IChatBaseComponent a(@Nullable CommandListenerWrapper commandlistenerwrapper, @Nullable Entity entity, int i) throws CommandSyntaxException {
        Object[] aobject = new Object[this.args.length];

        for (int j = 0; j < aobject.length; ++j) {
            Object object = this.args[j];

            if (object instanceof IChatBaseComponent) {
                aobject[j] = ChatComponentUtils.filterForDisplay(commandlistenerwrapper, (IChatBaseComponent) object, entity, i);
            } else {
                aobject[j] = object;
            }
        }

        return new ChatMessage(this.key, aobject);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatMessage)) {
            return false;
        } else {
            ChatMessage chatmessage = (ChatMessage) object;

            return Arrays.equals(this.args, chatmessage.args) && this.key.equals(chatmessage.key) && super.equals(object);
        }
    }

    @Override
    public int hashCode() {
        int i = super.hashCode();

        i = 31 * i + this.key.hashCode();
        i = 31 * i + Arrays.hashCode(this.args);
        return i;
    }

    @Override
    public String toString() {
        return "TranslatableComponent{key='" + this.key + '\'' + ", args=" + Arrays.toString(this.args) + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
    }

    public String getKey() {
        return this.key;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
