package net.minecraft.server;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChatHoverable {

    private final ChatHoverable.EnumHoverAction a;
    private final IChatBaseComponent b;

    public ChatHoverable(ChatHoverable.EnumHoverAction chathoverable_enumhoveraction, IChatBaseComponent ichatbasecomponent) {
        this.a = chathoverable_enumhoveraction;
        this.b = ichatbasecomponent;
    }

    public ChatHoverable.EnumHoverAction a() {
        return this.a;
    }

    public IChatBaseComponent b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            ChatHoverable chathoverable = (ChatHoverable) object;

            if (this.a != chathoverable.a) {
                return false;
            } else {
                if (this.b != null) {
                    if (this.b.equals(chathoverable.b)) {
                        return true;
                    }
                } else if (chathoverable.b == null) {
                    return true;
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "HoverEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + (this.b != null ? this.b.hashCode() : 0);
        return i;
    }

    public static enum EnumHoverAction {

        SHOW_TEXT("show_text", true), SHOW_ITEM("show_item", true), SHOW_ENTITY("show_entity", true);

        private static final Map<String, ChatHoverable.EnumHoverAction> d = (Map) Arrays.stream(values()).collect(Collectors.toMap(ChatHoverable.EnumHoverAction::b, (chathoverable_enumhoveraction) -> {
            return chathoverable_enumhoveraction;
        }));
        private final boolean e;
        private final String f;

        private EnumHoverAction(String s, boolean flag) {
            this.f = s;
            this.e = flag;
        }

        public boolean a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }

        public static ChatHoverable.EnumHoverAction a(String s) {
            return (ChatHoverable.EnumHoverAction) ChatHoverable.EnumHoverAction.d.get(s);
        }
    }
}
