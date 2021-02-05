package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutOpenSignEditor implements Packet<PacketListenerPlayOut> {

    private BlockPosition a;

    public PacketPlayOutOpenSignEditor() {}

    public PacketPlayOutOpenSignEditor(BlockPosition blockposition) {
        this.a = blockposition;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }
}
