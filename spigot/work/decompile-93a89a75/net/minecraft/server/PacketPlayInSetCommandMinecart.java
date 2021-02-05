package net.minecraft.server;

import java.io.IOException;
import javax.annotation.Nullable;

public class PacketPlayInSetCommandMinecart implements Packet<PacketListenerPlayIn> {

    private int a;
    private String b;
    private boolean c;

    public PacketPlayInSetCommandMinecart() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.e(32767);
        this.c = packetdataserializer.readBoolean();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeBoolean(this.c);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Nullable
    public CommandBlockListenerAbstract a(World world) {
        Entity entity = world.getEntity(this.a);

        return entity instanceof EntityMinecartCommandBlock ? ((EntityMinecartCommandBlock) entity).getCommandBlock() : null;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
