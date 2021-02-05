package net.minecraft.server;

public class ChatComponentText extends ChatBaseComponent {

    private final String b;

    public ChatComponentText(String s) {
        this.b = s;
    }

    public String i() {
        return this.b;
    }

    @Override
    public String getText() {
        return this.b;
    }

    @Override
    public ChatComponentText g() {
        return new ChatComponentText(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatComponentText)) {
            return false;
        } else {
            ChatComponentText chatcomponenttext = (ChatComponentText) object;

            return this.b.equals(chatcomponenttext.i()) && super.equals(object);
        }
    }

    @Override
    public String toString() {
        return "TextComponent{text='" + this.b + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatModifier() + '}';
    }
}
