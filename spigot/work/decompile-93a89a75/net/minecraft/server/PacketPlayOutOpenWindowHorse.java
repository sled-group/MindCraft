package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutOpenWindowHorse implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;

    public PacketPlayOutOpenWindowHorse() {}

    public PacketPlayOutOpenWindowHorse(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.i();
        this.c = packetdataserializer.readInt();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.d(this.b);
        packetdataserializer.writeInt(this.c);
    }
}
