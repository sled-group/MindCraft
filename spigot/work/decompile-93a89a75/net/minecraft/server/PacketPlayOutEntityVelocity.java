package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityVelocity implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private int c;
    private int d;

    public PacketPlayOutEntityVelocity() {}

    public PacketPlayOutEntityVelocity(Entity entity) {
        this(entity.getId(), entity.getMot());
    }

    public PacketPlayOutEntityVelocity(int i, Vec3D vec3d) {
        this.a = i;
        double d0 = 3.9D;
        double d1 = MathHelper.a(vec3d.x, -3.9D, 3.9D);
        double d2 = MathHelper.a(vec3d.y, -3.9D, 3.9D);
        double d3 = MathHelper.a(vec3d.z, -3.9D, 3.9D);

        this.b = (int) (d1 * 8000.0D);
        this.c = (int) (d2 * 8000.0D);
        this.d = (int) (d3 * 8000.0D);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readShort();
        this.d = packetdataserializer.readShort();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.writeShort(this.d);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
