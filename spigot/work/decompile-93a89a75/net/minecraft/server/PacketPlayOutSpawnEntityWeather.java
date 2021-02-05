package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutSpawnEntityWeather implements Packet<PacketListenerPlayOut> {

    private int a;
    private double b;
    private double c;
    private double d;
    private int e;

    public PacketPlayOutSpawnEntityWeather() {}

    public PacketPlayOutSpawnEntityWeather(Entity entity) {
        this.a = entity.getId();
        this.b = entity.locX;
        this.c = entity.locY;
        this.d = entity.locZ;
        if (entity instanceof EntityLightning) {
            this.e = 1;
        }

    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.e = packetdataserializer.readByte();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readDouble();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeDouble(this.d);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
