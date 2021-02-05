package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutViewCentre implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;

    public PacketPlayOutViewCentre() {}

    public PacketPlayOutViewCentre(int i, int j) {
        this.a = i;
        this.b = j;
    }

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

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
