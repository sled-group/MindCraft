package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInBoatMove implements Packet<PacketListenerPlayIn> {

    private boolean a;
    private boolean b;

    public PacketPlayInBoatMove() {}

    public PacketPlayInBoatMove(boolean flag, boolean flag1) {
        this.a = flag;
        this.b = flag1;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readBoolean();
        this.b = packetdataserializer.readBoolean();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeBoolean(this.a);
        packetdataserializer.writeBoolean(this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public boolean b() {
        return this.a;
    }

    public boolean c() {
        return this.b;
    }
}
