package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class ParticleType extends Particle<ParticleType> implements ParticleParam {

    private static final ParticleParam.a<ParticleType> a = new ParticleParam.a<ParticleType>() {
        @Override
        public ParticleType b(Particle<ParticleType> particle, StringReader stringreader) throws CommandSyntaxException {
            return (ParticleType) particle;
        }

        @Override
        public ParticleType b(Particle<ParticleType> particle, PacketDataSerializer packetdataserializer) {
            return (ParticleType) particle;
        }
    };

    protected ParticleType(boolean flag) {
        super(flag, ParticleType.a);
    }

    @Override
    public Particle<ParticleType> getParticle() {
        return this;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) {}

    @Override
    public String a() {
        return IRegistry.PARTICLE_TYPE.getKey(this).toString();
    }
}
