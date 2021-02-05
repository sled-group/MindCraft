package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityEffect implements Packet<PacketListenerPlayOut> {

    private int a;
    private byte b;
    private byte c;
    private int d;
    private byte e;

    public PacketPlayOutEntityEffect() {}

    public PacketPlayOutEntityEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = (byte) (MobEffectList.getId(mobeffect.getMobEffect()) & 255);
        this.c = (byte) (mobeffect.getAmplifier() & 255);
        if (mobeffect.getDuration() > 32767) {
            this.d = 32767;
        } else {
            this.d = mobeffect.getDuration();
        }

        this.e = 0;
        if (mobeffect.isAmbient()) {
            this.e = (byte) (this.e | 1);
        }

        if (mobeffect.isShowParticles()) {
            this.e = (byte) (this.e | 2);
        }

        if (mobeffect.f()) {
            this.e = (byte) (this.e | 4);
        }

    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.i();
        this.e = packetdataserializer.readByte();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.d(this.d);
        packetdataserializer.writeByte(this.e);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
