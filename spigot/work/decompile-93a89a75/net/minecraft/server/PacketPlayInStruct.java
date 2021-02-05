package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInStruct implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private TileEntityStructure.UpdateType b;
    private BlockPropertyStructureMode c;
    private String d;
    private BlockPosition e;
    private BlockPosition f;
    private EnumBlockMirror g;
    private EnumBlockRotation h;
    private String i;
    private boolean j;
    private boolean k;
    private boolean l;
    private float m;
    private long n;

    public PacketPlayInStruct() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
        this.b = (TileEntityStructure.UpdateType) packetdataserializer.a(TileEntityStructure.UpdateType.class);
        this.c = (BlockPropertyStructureMode) packetdataserializer.a(BlockPropertyStructureMode.class);
        this.d = packetdataserializer.e(32767);
        this.e = new BlockPosition(MathHelper.clamp(packetdataserializer.readByte(), -32, 32), MathHelper.clamp(packetdataserializer.readByte(), -32, 32), MathHelper.clamp(packetdataserializer.readByte(), -32, 32));
        this.f = new BlockPosition(MathHelper.clamp(packetdataserializer.readByte(), 0, 32), MathHelper.clamp(packetdataserializer.readByte(), 0, 32), MathHelper.clamp(packetdataserializer.readByte(), 0, 32));
        this.g = (EnumBlockMirror) packetdataserializer.a(EnumBlockMirror.class);
        this.h = (EnumBlockRotation) packetdataserializer.a(EnumBlockRotation.class);
        this.i = packetdataserializer.e(12);
        this.m = MathHelper.a(packetdataserializer.readFloat(), 0.0F, 1.0F);
        this.n = packetdataserializer.j();
        byte b0 = packetdataserializer.readByte();

        this.j = (b0 & 1) != 0;
        this.k = (b0 & 2) != 0;
        this.l = (b0 & 4) != 0;
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.b);
        packetdataserializer.a((Enum) this.c);
        packetdataserializer.a(this.d);
        packetdataserializer.writeByte(this.e.getX());
        packetdataserializer.writeByte(this.e.getY());
        packetdataserializer.writeByte(this.e.getZ());
        packetdataserializer.writeByte(this.f.getX());
        packetdataserializer.writeByte(this.f.getY());
        packetdataserializer.writeByte(this.f.getZ());
        packetdataserializer.a((Enum) this.g);
        packetdataserializer.a((Enum) this.h);
        packetdataserializer.a(this.i);
        packetdataserializer.writeFloat(this.m);
        packetdataserializer.b(this.n);
        int i = 0;

        if (this.j) {
            i |= 1;
        }

        if (this.k) {
            i |= 2;
        }

        if (this.l) {
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

    public TileEntityStructure.UpdateType c() {
        return this.b;
    }

    public BlockPropertyStructureMode d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public BlockPosition f() {
        return this.e;
    }

    public BlockPosition g() {
        return this.f;
    }

    public EnumBlockMirror h() {
        return this.g;
    }

    public EnumBlockRotation i() {
        return this.h;
    }

    public String j() {
        return this.i;
    }

    public boolean k() {
        return this.j;
    }

    public boolean l() {
        return this.k;
    }

    public boolean m() {
        return this.l;
    }

    public float n() {
        return this.m;
    }

    public long o() {
        return this.n;
    }
}
