package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInTeleportAccept implements Packet<PacketListenerPlayIn> {

    private int a;

    public PacketPlayInTeleportAccept() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public int b() {
        return this.a;
    }
}
