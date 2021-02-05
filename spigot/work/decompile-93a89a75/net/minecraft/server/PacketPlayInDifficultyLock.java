package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInDifficultyLock implements Packet<PacketListenerPlayIn> {

    private boolean a;

    public PacketPlayInDifficultyLock() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readBoolean();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeBoolean(this.a);
    }

    public boolean b() {
        return this.a;
    }
}
