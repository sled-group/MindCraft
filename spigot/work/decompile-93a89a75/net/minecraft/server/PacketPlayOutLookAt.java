package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutLookAt implements Packet<PacketListenerPlayOut> {

    private double a;
    private double b;
    private double c;
    private int d;
    private ArgumentAnchor.Anchor e;
    private ArgumentAnchor.Anchor f;
    private boolean g;

    public PacketPlayOutLookAt() {}

    public PacketPlayOutLookAt(ArgumentAnchor.Anchor argumentanchor_anchor, double d0, double d1, double d2) {
        this.e = argumentanchor_anchor;
        this.a = d0;
        this.b = d1;
        this.c = d2;
    }

    public PacketPlayOutLookAt(ArgumentAnchor.Anchor argumentanchor_anchor, Entity entity, ArgumentAnchor.Anchor argumentanchor_anchor1) {
        this.e = argumentanchor_anchor;
        this.d = entity.getId();
        this.f = argumentanchor_anchor1;
        Vec3D vec3d = argumentanchor_anchor1.a(entity);

        this.a = vec3d.x;
        this.b = vec3d.y;
        this.c = vec3d.z;
        this.g = true;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.e = (ArgumentAnchor.Anchor) packetdataserializer.a(ArgumentAnchor.Anchor.class);
        this.a = packetdataserializer.readDouble();
        this.b = packetdataserializer.readDouble();
        this.c = packetdataserializer.readDouble();
        if (packetdataserializer.readBoolean()) {
            this.g = true;
            this.d = packetdataserializer.i();
            this.f = (ArgumentAnchor.Anchor) packetdataserializer.a(ArgumentAnchor.Anchor.class);
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.e);
        packetdataserializer.writeDouble(this.a);
        packetdataserializer.writeDouble(this.b);
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeBoolean(this.g);
        if (this.g) {
            packetdataserializer.d(this.d);
            packetdataserializer.a((Enum) this.f);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
