package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSteerVehicle implements Packet<PacketListenerPlayIn> {

    private float a;
    private float b;
    private boolean c;
    private boolean d;

    public PacketPlayInSteerVehicle() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readFloat();
        this.b = packetdataserializer.readFloat();
        byte b0 = packetdataserializer.readByte();

        this.c = (b0 & 1) > 0;
        this.d = (b0 & 2) > 0;
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.writeFloat(this.b);
        byte b0 = 0;

        if (this.c) {
            b0 = (byte) (b0 | 1);
        }

        if (this.d) {
            b0 = (byte) (b0 | 2);
        }

        packetdataserializer.writeByte(b0);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public float b() {
        return this.a;
    }

    public float c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }
}
