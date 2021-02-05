package net.minecraft.server;

import com.google.common.base.Joiner;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ChatComponentNBT extends ChatBaseComponent implements ChatComponentContextual {

    private static final Logger LOGGER = LogManager.getLogger();
    protected final boolean b;
    protected final String c;
    @Nullable
    protected final ArgumentNBTKey.h d;

    @Nullable
    private static ArgumentNBTKey.h b(String s) {
        try {
            return (new ArgumentNBTKey()).parse(new StringReader(s));
        } catch (CommandSyntaxException commandsyntaxexception) {
            return null;
        }
    }

    public ChatComponentNBT(String s, boolean flag) {
        this(s, b(s), flag);
    }

    protected ChatComponentNBT(String s, @Nullable ArgumentNBTKey.h argumentnbtkey_h, boolean flag) {
        this.c = s;
        this.d = argumentnbtkey_h;
        this.b = flag;
    }

    protected abstract Stream<NBTTagCompound> a(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException;

    @Override
    public String getText() {
        return "";
    }

    public String i() {
        return this.c;
    }

    public boolean j() {
        return this.b;
    }

    @Override
    public IChatBaseComponent a(@Nullable CommandListenerWrapper commandlistenerwrapper, @Nullable Entity entity, int i) throws CommandSyntaxException {
        if (commandlistenerwrapper != null && this.d != null) {
            Stream<String> stream = this.a(commandlistenerwrapper).flatMap((nbttagcompound) -> {
                try {
                    return this.d.a((NBTBase) nbttagcompound).stream();
                } catch (CommandSyntaxException commandsyntaxexception) {
                    return Stream.empty();
                }
            }).map(NBTBase::asString);

            return (IChatBaseComponent) (this.b ? (IChatBaseComponent) stream.flatMap((s) -> {
                try {
                    IChatBaseComponent ichatbasecomponent = IChatBaseComponent.ChatSerializer.a(s);

                    return Stream.of(ChatComponentUtils.filterForDisplay(commandlistenerwrapper, ichatbasecomponent, entity, i));
                } catch (Exception exception) {
                    ChatComponentNBT.LOGGER.warn("Failed to parse component: " + s, exception);
                    return Stream.of();
                }
            }).reduce((ichatbasecomponent, ichatbasecomponent1) -> {
                return ichatbasecomponent.a(", ").addSibling(ichatbasecomponent1);
            }).orElse(new ChatComponentText("")) : new ChatComponentText(Joiner.on(", ").join(stream.iterator())));
        } else {
            return new ChatComponentText("");
        }
    }

    public static class a extends ChatComponentNBT {

        private final String e;
        @Nullable
        private final IVectorPosition f;

        public a(String s, boolean flag, String s1) {
            super(s, flag);
            this.e = s1;
            this.f = this.b(this.e);
        }

        @Nullable
        private IVectorPosition b(String s) {
            try {
                return ArgumentPosition.a().parse(new StringReader(s));
            } catch (CommandSyntaxException commandsyntaxexception) {
                return null;
            }
        }

        private a(String s, @Nullable ArgumentNBTKey.h argumentnbtkey_h, boolean flag, String s1, @Nullable IVectorPosition ivectorposition) {
            super(s, argumentnbtkey_h, flag);
            this.e = s1;
            this.f = ivectorposition;
        }

        @Nullable
        public String k() {
            return this.e;
        }

        @Override
        public IChatBaseComponent g() {
            return new ChatComponentNBT.a(this.c, this.d, this.b, this.e, this.f);
        }

        @Override
        protected Stream<NBTTagCompound> a(CommandListenerWrapper commandlistenerwrapper) {
            if (this.f != null) {
                WorldServer worldserver = commandlistenerwrapper.getWorld();
                BlockPosition blockposition = this.f.c(commandlistenerwrapper);

                if (worldserver.n(blockposition)) {
                    TileEntity tileentity = worldserver.getTileEntity(blockposition);

                    if (tileentity != null) {
                        return Stream.of(tileentity.save(new NBTTagCompound()));
                    }
                }
            }

            return Stream.empty();
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (!(object instanceof ChatComponentNBT.a)) {
                return false;
            } else {
                ChatComponentNBT.a chatcomponentnbt_a = (ChatComponentNBT.a) object;

                return Objects.equals(this.e, chatcomponentnbt_a.e) && Objects.equals(this.c, chatcomponentnbt_a.c) && super.equals(object);
            }
        }

        @Override
        public String toString() {
            return "BlockPosArgument{pos='" + this.e + '\'' + "path='" + this.c + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
        }
    }

    public static class b extends ChatComponentNBT {

        private final String e;
        @Nullable
        private final EntitySelector f;

        public b(String s, boolean flag, String s1) {
            super(s, flag);
            this.e = s1;
            this.f = b(s1);
        }

        @Nullable
        private static EntitySelector b(String s) {
            try {
                ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(new StringReader(s));

                return argumentparserselector.parse();
            } catch (CommandSyntaxException commandsyntaxexception) {
                return null;
            }
        }

        private b(String s, @Nullable ArgumentNBTKey.h argumentnbtkey_h, boolean flag, String s1, @Nullable EntitySelector entityselector) {
            super(s, argumentnbtkey_h, flag);
            this.e = s1;
            this.f = entityselector;
        }

        public String k() {
            return this.e;
        }

        @Override
        public IChatBaseComponent g() {
            return new ChatComponentNBT.b(this.c, this.d, this.b, this.e, this.f);
        }

        @Override
        protected Stream<NBTTagCompound> a(CommandListenerWrapper commandlistenerwrapper) throws CommandSyntaxException {
            if (this.f != null) {
                List<? extends Entity> list = this.f.getEntities(commandlistenerwrapper);

                return list.stream().map(CriterionConditionNBT::b);
            } else {
                return Stream.empty();
            }
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (!(object instanceof ChatComponentNBT.b)) {
                return false;
            } else {
                ChatComponentNBT.b chatcomponentnbt_b = (ChatComponentNBT.b) object;

                return Objects.equals(this.e, chatcomponentnbt_b.e) && Objects.equals(this.c, chatcomponentnbt_b.c) && super.equals(object);
            }
        }

        @Override
        public String toString() {
            return "EntityNbtComponent{selector='" + this.e + '\'' + "path='" + this.c + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
        }
    }
}
