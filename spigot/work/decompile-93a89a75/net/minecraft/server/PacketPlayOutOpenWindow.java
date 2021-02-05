package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutOpenWindow implements Packet<PacketListenerPlayOut> {

    private int a;
    private int b;
    private IChatBaseComponent c;

    public PacketPlayOutOpenWindow() {}

    public PacketPlayOutOpenWindow(int i, Containers<?> containers, IChatBaseComponent ichatbasecomponent) {
        this.a = i;
        this.b = IRegistry.MENU.a((Object) containers);
        this.c = ichatbasecomponent;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.i();
        this.c = packetdataserializer.h();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.d(this.b);
        packetdataserializer.a(this.c);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
