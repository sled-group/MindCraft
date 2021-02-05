package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Objects;
import javax.annotation.Nullable;

public class ChatModifier {

    private ChatModifier a;
    private EnumChatFormat color;
    private Boolean bold;
    private Boolean italic;
    private Boolean underlined;
    private Boolean strikethrough;
    private Boolean obfuscated;
    private ChatClickable clickEvent;
    private ChatHoverable hoverEvent;
    private String insertion;
    private static final ChatModifier k = new ChatModifier() {
        @Nullable
        @Override
        public EnumChatFormat getColor() {
            return null;
        }

        @Override
        public boolean isBold() {
            return false;
        }

        @Override
        public boolean isItalic() {
            return false;
        }

        @Override
        public boolean isStrikethrough() {
            return false;
        }

        @Override
        public boolean isUnderlined() {
            return false;
        }

        @Override
        public boolean isRandom() {
            return false;
        }

        @Nullable
        @Override
        public ChatClickable getClickEvent() {
            return null;
        }

        @Nullable
        @Override
        public ChatHoverable getHoverEvent() {
            return null;
        }

        @Nullable
        @Override
        public String getInsertion() {
            return null;
        }

        @Override
        public ChatModifier setColor(EnumChatFormat enumchatformat) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setBold(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setItalic(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setStrikethrough(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setUnderline(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setRandom(Boolean obool) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setChatClickable(ChatClickable chatclickable) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ChatModifier setChatModifier(ChatModifier chatmodifier) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            return "Style.ROOT";
        }

        @Override
        public ChatModifier clone() {
            return this;
        }

        @Override
        public ChatModifier n() {
            return this;
        }

        @Override
        public String k() {
            return "";
        }
    };

    public ChatModifier() {}

    @Nullable
    public EnumChatFormat getColor() {
        return this.color == null ? this.o().getColor() : this.color;
    }

    public boolean isBold() {
        return this.bold == null ? this.o().isBold() : this.bold;
    }

    public boolean isItalic() {
        return this.italic == null ? this.o().isItalic() : this.italic;
    }

    public boolean isStrikethrough() {
        return this.strikethrough == null ? this.o().isStrikethrough() : this.strikethrough;
    }

    public boolean isUnderlined() {
        return this.underlined == null ? this.o().isUnderlined() : this.underlined;
    }

    public boolean isRandom() {
        return this.obfuscated == null ? this.o().isRandom() : this.obfuscated;
    }

    public boolean g() {
        return this.bold == null && this.italic == null && this.strikethrough == null && this.underlined == null && this.obfuscated == null && this.color == null && this.clickEvent == null && this.hoverEvent == null && this.insertion == null;
    }

    @Nullable
    public ChatClickable getClickEvent() {
        return this.clickEvent == null ? this.o().getClickEvent() : this.clickEvent;
    }

    @Nullable
    public ChatHoverable getHoverEvent() {
        return this.hoverEvent == null ? this.o().getHoverEvent() : this.hoverEvent;
    }

    @Nullable
    public String getInsertion() {
        return this.insertion == null ? this.o().getInsertion() : this.insertion;
    }

    public ChatModifier setColor(EnumChatFormat enumchatformat) {
        this.color = enumchatformat;
        return this;
    }

    public ChatModifier setBold(Boolean obool) {
        this.bold = obool;
        return this;
    }

    public ChatModifier setItalic(Boolean obool) {
        this.italic = obool;
        return this;
    }

    public ChatModifier setStrikethrough(Boolean obool) {
        this.strikethrough = obool;
        return this;
    }

    public ChatModifier setUnderline(Boolean obool) {
        this.underlined = obool;
        return this;
    }

    public ChatModifier setRandom(Boolean obool) {
        this.obfuscated = obool;
        return this;
    }

    public ChatModifier setChatClickable(ChatClickable chatclickable) {
        this.clickEvent = chatclickable;
        return this;
    }

    public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
        this.hoverEvent = chathoverable;
        return this;
    }

    public ChatModifier setInsertion(String s) {
        this.insertion = s;
        return this;
    }

    public ChatModifier setChatModifier(ChatModifier chatmodifier) {
        this.a = chatmodifier;
        return this;
    }

    public String k() {
        if (this.g()) {
            return this.a != null ? this.a.k() : "";
        } else {
            StringBuilder stringbuilder = new StringBuilder();

            if (this.getColor() != null) {
                stringbuilder.append(this.getColor());
            }

            if (this.isBold()) {
                stringbuilder.append(EnumChatFormat.BOLD);
            }

            if (this.isItalic()) {
                stringbuilder.append(EnumChatFormat.ITALIC);
            }

            if (this.isUnderlined()) {
                stringbuilder.append(EnumChatFormat.UNDERLINE);
            }

            if (this.isRandom()) {
                stringbuilder.append(EnumChatFormat.OBFUSCATED);
            }

            if (this.isStrikethrough()) {
                stringbuilder.append(EnumChatFormat.STRIKETHROUGH);
            }

            return stringbuilder.toString();
        }
    }

    private ChatModifier o() {
        return this.a == null ? ChatModifier.k : this.a;
    }

    public String toString() {
        return "Style{hasParent=" + (this.a != null) + ", color=" + this.color + ", bold=" + this.bold + ", italic=" + this.italic + ", underlined=" + this.underlined + ", obfuscated=" + this.obfuscated + ", clickEvent=" + this.getClickEvent() + ", hoverEvent=" + this.getHoverEvent() + ", insertion=" + this.getInsertion() + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatModifier)) {
            return false;
        } else {
            ChatModifier chatmodifier = (ChatModifier) object;
            boolean flag;

            if (this.isBold() == chatmodifier.isBold() && this.getColor() == chatmodifier.getColor() && this.isItalic() == chatmodifier.isItalic() && this.isRandom() == chatmodifier.isRandom() && this.isStrikethrough() == chatmodifier.isStrikethrough() && this.isUnderlined() == chatmodifier.isUnderlined()) {
                label65:
                {
                    if (this.getClickEvent() != null) {
                        if (!this.getClickEvent().equals(chatmodifier.getClickEvent())) {
                            break label65;
                        }
                    } else if (chatmodifier.getClickEvent() != null) {
                        break label65;
                    }

                    if (this.getHoverEvent() != null) {
                        if (!this.getHoverEvent().equals(chatmodifier.getHoverEvent())) {
                            break label65;
                        }
                    } else if (chatmodifier.getHoverEvent() != null) {
                        break label65;
                    }

                    if (this.getInsertion() != null) {
                        if (!this.getInsertion().equals(chatmodifier.getInsertion())) {
                            break label65;
                        }
                    } else if (chatmodifier.getInsertion() != null) {
                        break label65;
                    }

                    flag = true;
                    return flag;
                }
            }

            flag = false;
            return flag;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion});
    }

    public ChatModifier clone() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.bold = this.bold;
        chatmodifier.italic = this.italic;
        chatmodifier.strikethrough = this.strikethrough;
        chatmodifier.underlined = this.underlined;
        chatmodifier.obfuscated = this.obfuscated;
        chatmodifier.color = this.color;
        chatmodifier.clickEvent = this.clickEvent;
        chatmodifier.hoverEvent = this.hoverEvent;
        chatmodifier.a = this.a;
        chatmodifier.insertion = this.insertion;
        return chatmodifier;
    }

    public ChatModifier n() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.setBold(this.isBold());
        chatmodifier.setItalic(this.isItalic());
        chatmodifier.setStrikethrough(this.isStrikethrough());
        chatmodifier.setUnderline(this.isUnderlined());
        chatmodifier.setRandom(this.isRandom());
        chatmodifier.setColor(this.getColor());
        chatmodifier.setChatClickable(this.getClickEvent());
        chatmodifier.setChatHoverable(this.getHoverEvent());
        chatmodifier.setInsertion(this.getInsertion());
        return chatmodifier;
    }

    public static class ChatModifierSerializer implements JsonDeserializer<ChatModifier>, JsonSerializer<ChatModifier> {

        public ChatModifierSerializer() {}

        @Nullable
        public ChatModifier deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            if (jsonelement.isJsonObject()) {
                ChatModifier chatmodifier = new ChatModifier();
                JsonObject jsonobject = jsonelement.getAsJsonObject();

                if (jsonobject == null) {
                    return null;
                } else {
                    if (jsonobject.has("bold")) {
                        chatmodifier.bold = jsonobject.get("bold").getAsBoolean();
                    }

                    if (jsonobject.has("italic")) {
                        chatmodifier.italic = jsonobject.get("italic").getAsBoolean();
                    }

                    if (jsonobject.has("underlined")) {
                        chatmodifier.underlined = jsonobject.get("underlined").getAsBoolean();
                    }

                    if (jsonobject.has("strikethrough")) {
                        chatmodifier.strikethrough = jsonobject.get("strikethrough").getAsBoolean();
                    }

                    if (jsonobject.has("obfuscated")) {
                        chatmodifier.obfuscated = jsonobject.get("obfuscated").getAsBoolean();
                    }

                    if (jsonobject.has("color")) {
                        chatmodifier.color = (EnumChatFormat) jsondeserializationcontext.deserialize(jsonobject.get("color"), EnumChatFormat.class);
                    }

                    if (jsonobject.has("insertion")) {
                        chatmodifier.insertion = jsonobject.get("insertion").getAsString();
                    }

                    JsonObject jsonobject1;
                    String s;

                    if (jsonobject.has("clickEvent")) {
                        jsonobject1 = ChatDeserializer.t(jsonobject, "clickEvent");
                        s = ChatDeserializer.a(jsonobject1, "action", (String) null);
                        ChatClickable.EnumClickAction chatclickable_enumclickaction = s == null ? null : ChatClickable.EnumClickAction.a(s);
                        String s1 = ChatDeserializer.a(jsonobject1, "value", (String) null);

                        if (chatclickable_enumclickaction != null && s1 != null && chatclickable_enumclickaction.a()) {
                            chatmodifier.clickEvent = new ChatClickable(chatclickable_enumclickaction, s1);
                        }
                    }

                    if (jsonobject.has("hoverEvent")) {
                        jsonobject1 = ChatDeserializer.t(jsonobject, "hoverEvent");
                        s = ChatDeserializer.a(jsonobject1, "action", (String) null);
                        ChatHoverable.EnumHoverAction chathoverable_enumhoveraction = s == null ? null : ChatHoverable.EnumHoverAction.a(s);
                        IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) jsondeserializationcontext.deserialize(jsonobject1.get("value"), IChatBaseComponent.class);

                        if (chathoverable_enumhoveraction != null && ichatbasecomponent != null && chathoverable_enumhoveraction.a()) {
                            chatmodifier.hoverEvent = new ChatHoverable(chathoverable_enumhoveraction, ichatbasecomponent);
                        }
                    }

                    return chatmodifier;
                }
            } else {
                return null;
            }
        }

        @Nullable
        public JsonElement serialize(ChatModifier chatmodifier, Type type, JsonSerializationContext jsonserializationcontext) {
            if (chatmodifier.g()) {
                return null;
            } else {
                JsonObject jsonobject = new JsonObject();

                if (chatmodifier.bold != null) {
                    jsonobject.addProperty("bold", chatmodifier.bold);
                }

                if (chatmodifier.italic != null) {
                    jsonobject.addProperty("italic", chatmodifier.italic);
                }

                if (chatmodifier.underlined != null) {
                    jsonobject.addProperty("underlined", chatmodifier.underlined);
                }

                if (chatmodifier.strikethrough != null) {
                    jsonobject.addProperty("strikethrough", chatmodifier.strikethrough);
                }

                if (chatmodifier.obfuscated != null) {
                    jsonobject.addProperty("obfuscated", chatmodifier.obfuscated);
                }

                if (chatmodifier.color != null) {
                    jsonobject.add("color", jsonserializationcontext.serialize(chatmodifier.color));
                }

                if (chatmodifier.insertion != null) {
                    jsonobject.add("insertion", jsonserializationcontext.serialize(chatmodifier.insertion));
                }

                JsonObject jsonobject1;

                if (chatmodifier.clickEvent != null) {
                    jsonobject1 = new JsonObject();
                    jsonobject1.addProperty("action", chatmodifier.clickEvent.a().b());
                    jsonobject1.addProperty("value", chatmodifier.clickEvent.b());
                    jsonobject.add("clickEvent", jsonobject1);
                }

                if (chatmodifier.hoverEvent != null) {
                    jsonobject1 = new JsonObject();
                    jsonobject1.addProperty("action", chatmodifier.hoverEvent.a().b());
                    jsonobject1.add("value", jsonserializationcontext.serialize(chatmodifier.hoverEvent.b()));
                    jsonobject.add("hoverEvent", jsonobject1);
                }

                return jsonobject;
            }
        }
    }
}
