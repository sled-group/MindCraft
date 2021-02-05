package net.minecraft.server;

public class HandshakeListener implements PacketHandshakingInListener {

    private final MinecraftServer a;
    private final NetworkManager b;

    public HandshakeListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
        this.a = minecraftserver;
        this.b = networkmanager;
    }

    @Override
    public void a(PacketHandshakingInSetProtocol packethandshakinginsetprotocol) {
        switch (packethandshakinginsetprotocol.b()) {
            case LOGIN:
                this.b.setProtocol(EnumProtocol.LOGIN);
                ChatMessage chatmessage;

                if (packethandshakinginsetprotocol.c() > SharedConstants.a().getProtocolVersion()) {
                    chatmessage = new ChatMessage("multiplayer.disconnect.outdated_server", new Object[]{SharedConstants.a().getName()});
                    this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
                    this.b.close(chatmessage);
                } else if (packethandshakinginsetprotocol.c() < SharedConstants.a().getProtocolVersion()) {
                    chatmessage = new ChatMessage("multiplayer.disconnect.outdated_client", new Object[]{SharedConstants.a().getName()});
                    this.b.sendPacket(new PacketLoginOutDisconnect(chatmessage));
                    this.b.close(chatmessage);
                } else {
                    this.b.setPacketListener(new LoginListener(this.a, this.b));
                }
                break;
            case STATUS:
                this.b.setProtocol(EnumProtocol.STATUS);
                this.b.setPacketListener(new PacketStatusListener(this.a, this.b));
                break;
            default:
                throw new UnsupportedOperationException("Invalid intention " + packethandshakinginsetprotocol.b());
        }

    }

    @Override
    public void a(IChatBaseComponent ichatbasecomponent) {}

    @Override
    public NetworkManager a() {
        return this.b;
    }
}
