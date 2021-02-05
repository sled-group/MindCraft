package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInBEdit implements Packet<PacketListenerPlayIn> {

    private ItemStack a;
    private boolean b;
    private EnumHand c;

    public PacketPlayInBEdit() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.m();
        this.b = packetdataserializer.readBoolean();
        this.c = (EnumHand) packetdataserializer.a(EnumHand.class);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.writeBoolean(this.b);
        packetdataserializer.a((Enum) this.c);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public ItemStack b() {
        return this.a;
    }

    public boolean c() {
        return this.b;
    }

    public EnumHand d() {
        return this.c;
    }
}
