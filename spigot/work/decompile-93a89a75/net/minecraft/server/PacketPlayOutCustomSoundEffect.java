package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCustomSoundEffect implements Packet<PacketListenerPlayOut> {

    private MinecraftKey a;
    private SoundCategory b;
    private int c;
    private int d = Integer.MAX_VALUE;
    private int e;
    private float f;
    private float g;

    public PacketPlayOutCustomSoundEffect() {}

    public PacketPlayOutCustomSoundEffect(MinecraftKey minecraftkey, SoundCategory soundcategory, Vec3D vec3d, float f, float f1) {
        this.a = minecraftkey;
        this.b = soundcategory;
        this.c = (int) (vec3d.x * 8.0D);
        this.d = (int) (vec3d.y * 8.0D);
        this.e = (int) (vec3d.z * 8.0D);
        this.f = f;
        this.g = f1;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.o();
        this.b = (SoundCategory) packetdataserializer.a(SoundCategory.class);
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readInt();
        this.f = packetdataserializer.readFloat();
        this.g = packetdataserializer.readFloat();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        packetdataserializer.writeInt(this.e);
        packetdataserializer.writeFloat(this.f);
        packetdataserializer.writeFloat(this.g);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
