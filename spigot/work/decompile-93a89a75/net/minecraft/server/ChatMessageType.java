package net.minecraft.server;

public enum ChatMessageType {

    CHAT((byte) 0, false), SYSTEM((byte) 1, true), GAME_INFO((byte) 2, true);

    private final byte d;
    private final boolean e;

    private ChatMessageType(byte b0, boolean flag) {
        this.d = b0;
        this.e = flag;
    }

    public byte a() {
        return this.d;
    }

    public static ChatMessageType a(byte b0) {
        ChatMessageType[] achatmessagetype = values();
        int i = achatmessagetype.length;

        for (int j = 0; j < i; ++j) {
            ChatMessageType chatmessagetype = achatmessagetype[j];

            if (b0 == chatmessagetype.d) {
                return chatmessagetype;
            }
        }

        return ChatMessageType.CHAT;
    }
}
