package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutLogin implements Packet<PacketListenerPlayOut> {

    private int a;
    private boolean b;
    private EnumGamemode c;
    private DimensionManager d;
    private int e;
    private WorldType f;
    private int g;
    private boolean h;

    public PacketPlayOutLogin() {}

    public PacketPlayOutLogin(int i, EnumGamemode enumgamemode, boolean flag, DimensionManager dimensionmanager, int j, WorldType worldtype, int k, boolean flag1) {
        this.a = i;
        this.d = dimensionmanager;
        this.c = enumgamemode;
        this.e = j;
        this.b = flag;
        this.f = worldtype;
        this.g = k;
        this.h = flag1;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readInt();
        short short0 = packetdataserializer.readUnsignedByte();

        this.b = (short0 & 8) == 8;
        int i = short0 & -9;

        this.c = EnumGamemode.getById(i);
        this.d = DimensionManager.a(packetdataserializer.readInt());
        this.e = packetdataserializer.readUnsignedByte();
        this.f = WorldType.getType(packetdataserializer.e(16));
        if (this.f == null) {
            this.f = WorldType.NORMAL;
        }

        this.g = packetdataserializer.i();
        this.h = packetdataserializer.readBoolean();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a);
        int i = this.c.getId();

        if (this.b) {
            i |= 8;
        }

        packetdataserializer.writeByte(i);
        packetdataserializer.writeInt(this.d.getDimensionID());
        packetdataserializer.writeByte(this.e);
        packetdataserializer.a(this.f.name());
        packetdataserializer.d(this.g);
        packetdataserializer.writeBoolean(this.h);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
