package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInTransaction implements Packet<PacketListenerPlayIn> {

    private int a;
    private short b;
    private boolean c;

    public PacketPlayInTransaction() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readByte() != 0;
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeByte(this.c ? 1 : 0);
    }

    public int b() {
        return this.a;
    }

    public short c() {
        return this.b;
    }
}
