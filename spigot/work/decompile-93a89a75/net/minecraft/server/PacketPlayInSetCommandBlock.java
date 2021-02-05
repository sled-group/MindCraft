package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSetCommandBlock implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private TileEntityCommand.Type f;

    public PacketPlayInSetCommandBlock() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.e(32767);
        this.f = (TileEntityCommand.Type) packetdataserializer.a(TileEntityCommand.Type.class);
        byte b0 = packetdataserializer.readByte();

        this.c = (b0 & 1) != 0;
        this.d = (b0 & 2) != 0;
        this.e = (b0 & 4) != 0;
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.a((Enum) this.f);
        int i = 0;

        if (this.c) {
            i |= 1;
        }

        if (this.d) {
            i |= 2;
        }

        if (this.e) {
            i |= 4;
        }

        packetdataserializer.writeByte(i);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }

    public TileEntityCommand.Type g() {
        return this.f;
    }
}
