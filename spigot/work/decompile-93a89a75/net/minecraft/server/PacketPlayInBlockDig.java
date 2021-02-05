package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInBlockDig implements Packet<PacketListenerPlayIn> {

    private BlockPosition a;
    private EnumDirection b;
    private PacketPlayInBlockDig.EnumPlayerDigType c;

    public PacketPlayInBlockDig() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.c = (PacketPlayInBlockDig.EnumPlayerDigType) packetdataserializer.a(PacketPlayInBlockDig.EnumPlayerDigType.class);
        this.a = packetdataserializer.e();
        this.b = EnumDirection.fromType1(packetdataserializer.readUnsignedByte());
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.c);
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.b.a());
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition b() {
        return this.a;
    }

    public EnumDirection c() {
        return this.b;
    }

    public PacketPlayInBlockDig.EnumPlayerDigType d() {
        return this.c;
    }

    public static enum EnumPlayerDigType {

        START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM, SWAP_HELD_ITEMS;

        private EnumPlayerDigType() {}
    }
}
