package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutWorldParticles implements Packet<PacketListenerPlayOut> {

    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private int h;
    private boolean i;
    private ParticleParam j;

    public PacketPlayOutWorldParticles() {}

    public <T extends ParticleParam> PacketPlayOutWorldParticles(T t0, boolean flag, float f, float f1, float f2, float f3, float f4, float f5, float f6, int i) {
        this.j = t0;
        this.i = flag;
        this.a = f;
        this.b = f1;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
        this.g = f6;
        this.h = i;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        Particle<?> particle = (Particle) IRegistry.PARTICLE_TYPE.fromId(packetdataserializer.readInt());

        if (particle == null) {
            particle = Particles.BARRIER;
        }

        this.i = packetdataserializer.readBoolean();
        this.a = packetdataserializer.readFloat();
        this.b = packetdataserializer.readFloat();
        this.c = packetdataserializer.readFloat();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
        this.f = packetdataserializer.readFloat();
        this.g = packetdataserializer.readFloat();
        this.h = packetdataserializer.readInt();
        this.j = this.a(packetdataserializer, (Particle) particle);
    }

    private <T extends ParticleParam> T a(PacketDataSerializer packetdataserializer, Particle<T> particle) {
        return particle.d().b(particle, packetdataserializer);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(IRegistry.PARTICLE_TYPE.a((Object) this.j.getParticle()));
        packetdataserializer.writeBoolean(this.i);
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.writeFloat(this.b);
        packetdataserializer.writeFloat(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeFloat(this.f);
        packetdataserializer.writeFloat(this.g);
        packetdataserializer.writeInt(this.h);
        this.j.a(packetdataserializer);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
