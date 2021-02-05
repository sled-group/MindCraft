package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInTileNBTQuery implements Packet<PacketListenerPlayIn> {

    private int a;
    private BlockPosition b;

    public PacketPlayInTileNBTQuery() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.e();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public int b() {
        return this.a;
    }

    public BlockPosition c() {
        return this.b;
    }
}
