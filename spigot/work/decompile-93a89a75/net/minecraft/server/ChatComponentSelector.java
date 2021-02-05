package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatComponentSelector extends ChatBaseComponent implements ChatComponentContextual {

    private static final Logger LOGGER = LogManager.getLogger();
    private final String c;
    @Nullable
    private final EntitySelector d;

    public ChatComponentSelector(String s) {
        this.c = s;
        EntitySelector entityselector = null;

        try {
            ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(new StringReader(s));

            entityselector = argumentparserselector.parse();
        } catch (CommandSyntaxException commandsyntaxexception) {
            ChatComponentSelector.LOGGER.warn("Invalid selector component: {}", s, commandsyntaxexception.getMessage());
        }

        this.d = entityselector;
    }

    public String i() {
        return this.c;
    }

    @Override
    public IChatBaseComponent a(@Nullable CommandListenerWrapper commandlistenerwrapper, @Nullable Entity entity, int i) throws CommandSyntaxException {
        return (IChatBaseComponent) (commandlistenerwrapper != null && this.d != null ? EntitySelector.a(this.d.getEntities(commandlistenerwrapper)) : new ChatComponentText(""));
    }

    @Override
    public String getText() {
        return this.c;
    }

    @Override
    public ChatComponentSelector g() {
        return new ChatComponentSelector(this.c);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatComponentSelector)) {
            return false;
        } else {
            ChatComponentSelector chatcomponentselector = (ChatComponentSelector) object;

            return this.c.equals(chatcomponentselector.c) && super.equals(object);
        }
    }

    @Override
    public String toString() {
        return "SelectorComponent{pattern='" + this.c + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
    }
}
