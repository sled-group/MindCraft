package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nullable;

public class PacketPlayInSpectate implements Packet<PacketListenerPlayIn> {

    private UUID a;

    public PacketPlayInSpectate() {}

    public PacketPlayInSpectate(UUID uuid) {
        this.a = uuid;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.k();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Nullable
    public Entity a(WorldServer worldserver) {
        return worldserver.getEntity(this.a);
    }
}
