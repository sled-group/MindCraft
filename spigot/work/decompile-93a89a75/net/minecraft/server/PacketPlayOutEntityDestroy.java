package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityDestroy implements Packet<PacketListenerPlayOut> {

    private int[] a;

    public PacketPlayOutEntityDestroy() {}

    public PacketPlayOutEntityDestroy(int... aint) {
        this.a = aint;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new int[packetdataserializer.i()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = packetdataserializer.i();
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a.length);
        int[] aint = this.a;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int k = aint[j];

            packetdataserializer.d(k);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
