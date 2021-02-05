package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class ChatBaseComponent implements IChatBaseComponent {

    protected final List<IChatBaseComponent> siblings = Lists.newArrayList();
    private ChatModifier b;

    public ChatBaseComponent() {}

    @Override
    public IChatBaseComponent addSibling(IChatBaseComponent ichatbasecomponent) {
        ichatbasecomponent.getChatModifier().setChatModifier(this.getChatModifier());
        this.siblings.add(ichatbasecomponent);
        return this;
    }

    @Override
    public List<IChatBaseComponent> getSiblings() {
        return this.siblings;
    }

    @Override
    public IChatBaseComponent setChatModifier(ChatModifier chatmodifier) {
        this.b = chatmodifier;
        Iterator iterator = this.siblings.iterator();

        while (iterator.hasNext()) {
            IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

            ichatbasecomponent.getChatModifier().setChatModifier(this.getChatModifier());
        }

        return this;
    }

    @Override
    public ChatModifier getChatModifier() {
        if (this.b == null) {
            this.b = new ChatModifier();
            Iterator iterator = this.siblings.iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

                ichatbasecomponent.getChatModifier().setChatModifier(this.b);
            }
        }

        return this.b;
    }

    @Override
    public Stream<IChatBaseComponent> c() {
        return Streams.concat(new Stream[]{Stream.of(this), this.siblings.stream().flatMap(IChatBaseComponent::c)});
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatBaseComponent)) {
            return false;
        } else {
            ChatBaseComponent chatbasecomponent = (ChatBaseComponent) object;

            return this.siblings.equals(chatbasecomponent.siblings) && this.getChatModifier().equals(chatbasecomponent.getChatModifier());
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getChatModifier(), this.siblings});
    }

    public String toString() {
        return "BaseComponent{style=" + this.b + ", siblings=" + this.siblings + '}';
    }
}
