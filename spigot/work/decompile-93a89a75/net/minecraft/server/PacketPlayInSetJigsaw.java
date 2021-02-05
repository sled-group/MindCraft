package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSetJigsaw implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private MinecraftKey b;
    private MinecraftKey c;
    private String d;

    public PacketPlayInSetJigsaw() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.o();
        this.c = packetdataserializer.o();
        this.d = packetdataserializer.e(32767);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.a(this.c);
        packetdataserializer.a(this.d);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition b() {
        return this.a;
    }

    public MinecraftKey c() {
        return this.c;
    }

    public MinecraftKey d() {
        return this.b;
    }

    public String e() {
        return this.d;
    }
}
