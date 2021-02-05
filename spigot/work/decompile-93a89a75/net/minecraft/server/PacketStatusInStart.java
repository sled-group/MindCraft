package net.minecraft.server;

import java.io.IOException;

public class PacketStatusInStart implements Packet<PacketStatusInListener> {

    public PacketStatusInStart() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {}

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {}

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }
}
