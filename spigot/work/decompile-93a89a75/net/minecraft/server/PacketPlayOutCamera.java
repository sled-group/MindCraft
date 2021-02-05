package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCamera implements Packet<PacketListenerPlayOut> {

    public int a;

    public PacketPlayOutCamera() {}

    public PacketPlayOutCamera(Entity entity) {
        this.a = entity.getId();
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
