package net.minecraft.server;

import java.io.IOException;
import org.apache.commons.lang3.Validate;

public class PacketPlayOutEntitySound implements Packet<PacketListenerPlayOut> {

    private SoundEffect a;
    private SoundCategory b;
    private int c;
    private float d;
    private float e;

    public PacketPlayOutEntitySound() {}

    public PacketPlayOutEntitySound(SoundEffect soundeffect, SoundCategory soundcategory, Entity entity, float f, float f1) {
        Validate.notNull(soundeffect, "sound", new Object[0]);
        this.a = soundeffect;
        this.b = soundcategory;
        this.c = entity.getId();
        this.d = f;
        this.e = f1;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (SoundEffect) IRegistry.SOUND_EVENT.fromId(packetdataserializer.i());
        this.b = (SoundCategory) packetdataserializer.a(SoundCategory.class);
        this.c = packetdataserializer.i();
        this.d = packetdataserializer.readFloat();
        this.e = packetdataserializer.readFloat();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(IRegistry.SOUND_EVENT.a((Object) this.a));
        packetdataserializer.a((Enum) this.b);
        packetdataserializer.d(this.c);
        packetdataserializer.writeFloat(this.d);
        packetdataserializer.writeFloat(this.e);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
