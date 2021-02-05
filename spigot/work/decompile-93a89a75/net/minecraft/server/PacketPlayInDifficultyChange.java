package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInDifficultyChange implements Packet<PacketListenerPlayIn> {

    private EnumDifficulty a;

    public PacketPlayInDifficultyChange() {}

    public PacketPlayInDifficultyChange(EnumDifficulty enumdifficulty) {
        this.a = enumdifficulty;
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = EnumDifficulty.getById(packetdataserializer.readUnsignedByte());
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.a.a());
    }

    public EnumDifficulty b() {
        return this.a;
    }
}
