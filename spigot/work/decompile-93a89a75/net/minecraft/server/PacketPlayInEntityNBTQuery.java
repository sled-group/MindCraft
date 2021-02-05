package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInEntityNBTQuery implements Packet<PacketListenerPlayIn> {

    private int a;
    private int b;

    public PacketPlayInEntityNBTQuery() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.i();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.d(this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }
}
