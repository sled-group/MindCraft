package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutKickDisconnect implements Packet<PacketListenerPlayOut> {

    private IChatBaseComponent a;

    public PacketPlayOutKickDisconnect() {}

    public PacketPlayOutKickDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.h();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
