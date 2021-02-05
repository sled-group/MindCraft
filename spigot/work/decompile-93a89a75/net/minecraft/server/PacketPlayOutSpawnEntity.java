package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayOutSpawnEntity implements Packet<PacketListenerPlayOut> {

    private int a;
    private UUID b;
    private double c;
    private double d;
    private double e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private EntityTypes<?> k;
    private int l;

    public PacketPlayOutSpawnEntity() {}

    public PacketPlayOutSpawnEntity(int i, UUID uuid, double d0, double d1, double d2, float f, float f1, EntityTypes<?> entitytypes, int j, Vec3D vec3d) {
        this.a = i;
        this.b = uuid;
        this.c = d0;
        this.d = d1;
        this.e = d2;
        this.i = MathHelper.d(f * 256.0F / 360.0F);
        this.j = MathHelper.d(f1 * 256.0F / 360.0F);
        this.k = entitytypes;
        this.l = j;
        this.f = (int) (MathHelper.a(vec3d.x, -3.9D, 3.9D) * 8000.0D);
        this.g = (int) (MathHelper.a(vec3d.y, -3.9D, 3.9D) * 8000.0D);
        this.h = (int) (MathHelper.a(vec3d.z, -3.9D, 3.9D) * 8000.0D);
    }

    public PacketPlayOutSpawnEntity(Entity entity) {
        this(entity, 0);
    }

    public PacketPlayOutSpawnEntity(Entity entity, int i) {
        this(entity.getId(), entity.getUniqueID(), entity.locX, entity.locY, entity.locZ, entity.pitch, entity.yaw, entity.getEntityType(), i, entity.getMot());
    }

    public PacketPlayOutSpawnEntity(Entity entity, EntityTypes<?> entitytypes, int i, BlockPosition blockposition) {
        this(entity.getId(), entity.getUniqueID(), (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), entity.pitch, entity.yaw, entitytypes, i, entity.getMot());
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.k();
        this.k = (EntityTypes) IRegistry.ENTITY_TYPE.fromId(packetdataserializer.i());
        this.c = packetdataserializer.readDouble();
        this.d = packetdataserializer.readDouble();
        this.e = packetdataserializer.readDouble();
        this.i = packetdataserializer.readByte();
        this.j = packetdataserializer.readByte();
        this.l = packetdataserializer.readInt();
        this.f = packetdataserializer.readShort();
        this.g = packetdataserializer.readShort();
        this.h = packetdataserializer.readShort();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.d(IRegistry.ENTITY_TYPE.a((Object) this.k));
        packetdataserializer.writeDouble(this.c);
        packetdataserializer.writeDouble(this.d);
        packetdataserializer.writeDouble(this.e);
        packetdataserializer.writeByte(this.i);
        packetdataserializer.writeByte(this.j);
        packetdataserializer.writeInt(this.l);
        packetdataserializer.writeShort(this.f);
        packetdataserializer.writeShort(this.g);
        packetdataserializer.writeShort(this.h);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
