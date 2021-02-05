package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutOpenBook implements Packet<PacketListenerPlayOut> {

    private EnumHand a;

    public PacketPlayOutOpenBook() {}

    public PacketPlayOutOpenBook(EnumHand enumhand) {
        this.a = enumhand;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (EnumHand) packetdataserializer.a(EnumHand.class);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
