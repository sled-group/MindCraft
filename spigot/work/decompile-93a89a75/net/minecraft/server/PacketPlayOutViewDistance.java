package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutViewDistance implements Packet<PacketListenerPlayOut> {

    private int a;

    public PacketPlayOutViewDistance() {}

    public PacketPlayOutViewDistance(int i) {
        this.a = i;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
