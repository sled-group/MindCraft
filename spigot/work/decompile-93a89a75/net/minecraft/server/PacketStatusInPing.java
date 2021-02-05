package net.minecraft.server;

import java.io.IOException;

public class PacketStatusInPing implements Packet<PacketStatusInListener> {

    private long a;

    public PacketStatusInPing() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readLong();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeLong(this.a);
    }

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public long b() {
        return this.a;
    }
}
