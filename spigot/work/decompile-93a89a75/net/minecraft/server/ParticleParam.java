package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface ParticleParam {

    Particle<?> getParticle();

    void a(PacketDataSerializer packetdataserializer);

    String a();

    public interface a<T extends ParticleParam> {

        T b(Particle<T> particle, StringReader stringreader) throws CommandSyntaxException;

        T b(Particle<T> particle, PacketDataSerializer packetdataserializer);
    }
}
