package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInItemName implements Packet<PacketListenerPlayIn> {

    private String a;

    public PacketPlayInItemName() {}

    public PacketPlayInItemName(String s) {
        this.a = s;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e(32767);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public String b() {
        return this.a;
    }
}
