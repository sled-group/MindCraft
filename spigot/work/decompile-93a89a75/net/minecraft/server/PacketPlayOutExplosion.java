package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PacketPlayOutExplosion implements Packet<PacketListenerPlayOut> {

    private double a;
    private double b;
    private double c;
    private float d;
    private List<BlockPosition> e;
    private float f;
    private float g;
    private float h;

    public PacketPlayOutExplosion() {}

    public PacketPlayOutExplosion(double d0, double d1, double d2, float f, List<BlockPosition> list, Vec3D vec3d) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
        this.d = f;
        this.e = Lists.newArrayList(list);
        if (vec3d != null) {
            this.f = (float) vec3d.x;
            this.g = (float) vec3d.y;
            this.h = (float) vec3d.z;
        }

    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (double) packetdataserializer.readFloat();
        this.b = (double) packetdataserializer.readFloat();
        this.c = (double) packetdataserializer.readFloat();
        this.d = packetdataserializer.readFloat();
        int i = packetdataserializer.readInt();

        this.e = Lists.newArrayListWithCapacity(i);
        int j = MathHelper.floor(this.a);
        int k = MathHelper.floor(this.b);
        int l = MathHelper.floor(this.c);

        for (int i1 = 0; i1 < i; ++i1) {
            int j1 = packetdataserializer.readByte() + j;
            int k1 = packetdataserializer.readByte() + k;
            int l1 = packetdataserializer.readByte() + l;

            this.e.add(new BlockPosition(j1, k1, l1));
        }

        this.f = packetdataserializer.readFloat();
        this.g = packetdataserializer.readFloat();
        this.h = packetdataserializer.readFloat();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeFloat((float) this.a);
        packetdataserializer.writeFloat((float) this.b);
        packetdataserializer.writeFloat((float) this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeInt(this.e.size());
        int i = MathHelper.floor(this.a);
        int j = MathHelper.floor(this.b);
        int k = MathHelper.floor(this.c);
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            BlockPosition blockposition = (BlockPosition) iterator.next();
            int l = blockposition.getX() - i;
            int i1 = blockposition.getY() - j;
            int j1 = blockposition.getZ() - k;

            packetdataserializer.writeByte(l);
            packetdataserializer.writeByte(i1);
            packetdataserializer.writeByte(j1);
        }

        packetdataserializer.writeFloat(this.f);
        packetdataserializer.writeFloat(this.g);
        packetdataserializer.writeFloat(this.h);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
