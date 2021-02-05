package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutTags implements Packet<PacketListenerPlayOut> {

    private TagRegistry a;

    public PacketPlayOutTags() {}

    public PacketPlayOutTags(TagRegistry tagregistry) {
        this.a = tagregistry;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = TagRegistry.b(packetdataserializer);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        this.a.a(packetdataserializer);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
