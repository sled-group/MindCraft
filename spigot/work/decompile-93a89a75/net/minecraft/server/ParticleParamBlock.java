package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class ParticleParamBlock implements ParticleParam {

    public static final ParticleParam.a<ParticleParamBlock> a = new ParticleParam.a<ParticleParamBlock>() {
        @Override
        public ParticleParamBlock b(Particle<ParticleParamBlock> particle, StringReader stringreader) throws CommandSyntaxException {
            stringreader.expect(' ');
            return new ParticleParamBlock(particle, (new ArgumentBlock(stringreader, false)).a(false).getBlockData());
        }

        @Override
        public ParticleParamBlock b(Particle<ParticleParamBlock> particle, PacketDataSerializer packetdataserializer) {
            return new ParticleParamBlock(particle, (IBlockData) Block.REGISTRY_ID.fromId(packetdataserializer.i()));
        }
    };
    private final Particle<ParticleParamBlock> b;
    private final IBlockData c;

    public ParticleParamBlock(Particle<ParticleParamBlock> particle, IBlockData iblockdata) {
        this.b = particle;
        this.c = iblockdata;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) {
        packetdataserializer.d(Block.REGISTRY_ID.getId(this.c));
    }

    @Override
    public String a() {
        return IRegistry.PARTICLE_TYPE.getKey(this.getParticle()) + " " + ArgumentBlock.a(this.c);
    }

    @Override
    public Particle<ParticleParamBlock> getParticle() {
        return this.b;
    }
}
