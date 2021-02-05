package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUpdateSign implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private String[] b;

    public PacketPlayInUpdateSign() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.b[i] = packetdataserializer.e(384);
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.b[i]);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition b() {
        return this.a;
    }

    public String[] c() {
        return this.b;
    }
}
