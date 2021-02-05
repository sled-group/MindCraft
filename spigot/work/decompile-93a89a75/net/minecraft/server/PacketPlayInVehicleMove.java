package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInVehicleMove implements Packet<PacketListenerPlayIn> {

    private double a;
    private double b;
    private double c;
    private float d;
    private float e;

    public PacketPlayInVehicleMove() {}

    public PacketPlayInVehicleMove(Entity entity) {
        this.a = entity.locX;
        this.b = entity.locY;
        this.c = entity.locZ;
        this.d = entity.yaw;
        this.e = entity.pitch;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readDouble();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeDouble(this.a);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public double getX() {
        return this.a;
    }

    public double getY() {
        return this.b;
    }

    public double getZ() {
        return this.c;
    }

    public float getYaw() {
        return this.d;
    }

    public float getPitch() {
        return this.e;
    }
}
