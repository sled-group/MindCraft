package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSetCreativeSlot implements Packet<PacketListenerPlayIn> {

    private int slot;
    private ItemStack b;

    public PacketPlayInSetCreativeSlot() {
        this.b = ItemStack.a;
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.slot = packetdataserializer.readShort();
        this.b = packetdataserializer.m();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeShort(this.slot);
        packetdataserializer.a(this.b);
    }

    public int b() {
        return this.slot;
    }

    public ItemStack getItemStack() {
        return this.b;
    }
}
