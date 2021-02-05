package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayOutSpawnEntityPainting implements Packet<PacketListenerPlayOut> {

    private int a;
    private UUID b;
    private BlockPosition c;
    private EnumDirection d;
    private int e;

    public PacketPlayOutSpawnEntityPainting() {}

    public PacketPlayOutSpawnEntityPainting(EntityPainting entitypainting) {
        this.a = entitypainting.getId();
        this.b = entitypainting.getUniqueID();
        this.c = entitypainting.getBlockPosition();
        this.d = entitypainting.getDirection();
        this.e = IRegistry.MOTIVE.a((Object) entitypainting.art);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.k();
        this.e = packetdataserializer.i();
        this.c = packetdataserializer.e();
        this.d = EnumDirection.fromType2(packetdataserializer.readUnsignedByte());
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.d(this.e);
        packetdataserializer.a(this.c);
        packetdataserializer.writeByte(this.d.get2DRotationValue());
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
