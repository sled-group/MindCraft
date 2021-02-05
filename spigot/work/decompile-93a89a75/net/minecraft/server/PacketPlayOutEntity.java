package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntity implements Packet<PacketListenerPlayOut> {

    protected int a;
    protected short b;
    protected short c;
    protected short d;
    protected byte e;
    protected byte f;
    protected boolean g;
    protected boolean h;

    public static long a(double d0) {
        return MathHelper.d(d0 * 4096.0D);
    }

    public static Vec3D a(long i, long j, long k) {
        return (new Vec3D((double) i, (double) j, (double) k)).a(2.44140625E-4D);
    }

    public PacketPlayOutEntity() {}

    public PacketPlayOutEntity(int i) {
        this.a = i;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    public static class PacketPlayOutEntityLook extends PacketPlayOutEntity {

        public PacketPlayOutEntityLook() {
            this.h = true;
        }

        public PacketPlayOutEntityLook(int i, byte b0, byte b1, boolean flag) {
            super(i);
            this.e = b0;
            this.f = b1;
            this.h = true;
            this.g = flag;
        }

        @Override
        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.e = packetdataserializer.readByte();
            this.f = packetdataserializer.readByte();
            this.g = packetdataserializer.readBoolean();
        }

        @Override
        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeByte(this.e);
            packetdataserializer.writeByte(this.f);
            packetdataserializer.writeBoolean(this.g);
        }
    }

    public static class PacketPlayOutRelEntityMove extends PacketPlayOutEntity {

        public PacketPlayOutRelEntityMove() {}

        public PacketPlayOutRelEntityMove(int i, short short0, short short1, short short2, boolean flag) {
            super(i);
            this.b = short0;
            this.c = short1;
            this.d = short2;
            this.g = flag;
        }

        @Override
        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.b = packetdataserializer.readShort();
            this.c = packetdataserializer.readShort();
            this.d = packetdataserializer.readShort();
            this.g = packetdataserializer.readBoolean();
        }

        @Override
        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeShort(this.b);
            packetdataserializer.writeShort(this.c);
            packetdataserializer.writeShort(this.d);
            packetdataserializer.writeBoolean(this.g);
        }
    }

    public static class PacketPlayOutRelEntityMoveLook extends PacketPlayOutEntity {

        public PacketPlayOutRelEntityMoveLook() {
            this.h = true;
        }

        public PacketPlayOutRelEntityMoveLook(int i, short short0, short short1, short short2, byte b0, byte b1, boolean flag) {
            super(i);
            this.b = short0;
            this.c = short1;
            this.d = short2;
            this.e = b0;
            this.f = b1;
            this.g = flag;
            this.h = true;
        }

        @Override
        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            super.a(packetdataserializer);
            this.b = packetdataserializer.readShort();
            this.c = packetdataserializer.readShort();
            this.d = packetdataserializer.readShort();
            this.e = packetdataserializer.readByte();
            this.f = packetdataserializer.readByte();
            this.g = packetdataserializer.readBoolean();
        }

        @Override
        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            super.b(packetdataserializer);
            packetdataserializer.writeShort(this.b);
            packetdataserializer.writeShort(this.c);
            packetdataserializer.writeShort(this.d);
            packetdataserializer.writeByte(this.e);
            packetdataserializer.writeByte(this.f);
            packetdataserializer.writeBoolean(this.g);
        }
    }
}
