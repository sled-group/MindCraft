package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutResourcePackSend implements Packet<PacketListenerPlayOut> {

    private String a;
    private String b;

    public PacketPlayOutResourcePackSend() {}

    public PacketPlayOutResourcePackSend(String s, String s1) {
        this.a = s;
        this.b = s1;
        if (s1.length() > 40) {
            throw new IllegalArgumentException("Hash is too long (max 40, was " + s1.length() + ")");
        }
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e(32767);
        this.b = packetdataserializer.e(40);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
