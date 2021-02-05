package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutRespawn implements Packet<PacketListenerPlayOut> {

    private DimensionManager a;
    private EnumGamemode b;
    private WorldType c;

    public PacketPlayOutRespawn() {}

    public PacketPlayOutRespawn(DimensionManager dimensionmanager, WorldType worldtype, EnumGamemode enumgamemode) {
        this.a = dimensionmanager;
        this.b = enumgamemode;
        this.c = worldtype;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = DimensionManager.a(packetdataserializer.readInt());
        this.b = EnumGamemode.getById(packetdataserializer.readUnsignedByte());
        this.c = WorldType.getType(packetdataserializer.e(16));
        if (this.c == null) {
            this.c = WorldType.NORMAL;
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a.getDimensionID());
        packetdataserializer.writeByte(this.b.getId());
        packetdataserializer.a(this.c.name());
    }
}
