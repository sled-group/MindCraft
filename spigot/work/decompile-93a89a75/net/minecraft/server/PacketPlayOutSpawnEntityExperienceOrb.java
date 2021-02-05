package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutSpawnEntityExperienceOrb implements Packet<PacketListenerPlayOut> {

    private int a;
    private double b;
    private double c;
    private double d;
    private int e;

    public PacketPlayOutSpawnEntityExperienceOrb() {}

    public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb entityexperienceorb) {
        this.a = entityexperienceorb.getId();
        this.b = entityexperienceorb.locX;
        this.c = entityexperienceorb.locY;
        this.d = entityexperienceorb.locZ;
        this.e = entityexperienceorb.f();
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readDouble();
        this.e = packetdataserializer.readShort();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeDouble(this.d);
        packetdataserializer.writeShort(this.e);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
