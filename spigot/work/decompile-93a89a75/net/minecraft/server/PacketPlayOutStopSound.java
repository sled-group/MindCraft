package net.minecraft.server;

import java.io.IOException;
import javax.annotation.Nullable;

public class PacketPlayOutStopSound implements Packet<PacketListenerPlayOut> {

    private MinecraftKey a;
    private SoundCategory b;

    public PacketPlayOutStopSound() {}

    public PacketPlayOutStopSound(@Nullable MinecraftKey minecraftkey, @Nullable SoundCategory soundcategory) {
        this.a = minecraftkey;
        this.b = soundcategory;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        byte b0 = packetdataserializer.readByte();

        if ((b0 & 1) > 0) {
            this.b = (SoundCategory) packetdataserializer.a(SoundCategory.class);
        }

        if ((b0 & 2) > 0) {
            this.a = packetdataserializer.o();
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        if (this.b != null) {
            if (this.a != null) {
                packetdataserializer.writeByte(3);
                packetdataserializer.a((Enum) this.b);
                packetdataserializer.a(this.a);
            } else {
                packetdataserializer.writeByte(1);
                packetdataserializer.a((Enum) this.b);
            }
        } else if (this.a != null) {
            packetdataserializer.writeByte(2);
            packetdataserializer.a(this.a);
        } else {
            packetdataserializer.writeByte(0);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
