package net.minecraft.server;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import javax.annotation.Nullable;

public class DataWatcherRegistry {

    private static final RegistryID<DataWatcherSerializer<?>> t = new RegistryID<>(16);
    public static final DataWatcherSerializer<Byte> a = new DataWatcherSerializer<Byte>() {
        public void a(PacketDataSerializer packetdataserializer, Byte obyte) {
            packetdataserializer.writeByte(obyte);
        }

        @Override
        public Byte a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.readByte();
        }

        public Byte a(Byte obyte) {
            return obyte;
        }
    };
    public static final DataWatcherSerializer<Integer> b = new DataWatcherSerializer<Integer>() {
        public void a(PacketDataSerializer packetdataserializer, Integer integer) {
            packetdataserializer.d(integer);
        }

        @Override
        public Integer a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.i();
        }

        public Integer a(Integer integer) {
            return integer;
        }
    };
    public static final DataWatcherSerializer<Float> c = new DataWatcherSerializer<Float>() {
        public void a(PacketDataSerializer packetdataserializer, Float ofloat) {
            packetdataserializer.writeFloat(ofloat);
        }

        @Override
        public Float a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.readFloat();
        }

        public Float a(Float ofloat) {
            return ofloat;
        }
    };
    public static final DataWatcherSerializer<String> d = new DataWatcherSerializer<String>() {
        public void a(PacketDataSerializer packetdataserializer, String s) {
            packetdataserializer.a(s);
        }

        @Override
        public String a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.e(32767);
        }

        public String a(String s) {
            return s;
        }
    };
    public static final DataWatcherSerializer<IChatBaseComponent> e = new DataWatcherSerializer<IChatBaseComponent>() {
        public void a(PacketDataSerializer packetdataserializer, IChatBaseComponent ichatbasecomponent) {
            packetdataserializer.a(ichatbasecomponent);
        }

        @Override
        public IChatBaseComponent a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.h();
        }

        public IChatBaseComponent a(IChatBaseComponent ichatbasecomponent) {
            return ichatbasecomponent.h();
        }
    };
    public static final DataWatcherSerializer<Optional<IChatBaseComponent>> f = new DataWatcherSerializer<Optional<IChatBaseComponent>>() {
        public void a(PacketDataSerializer packetdataserializer, Optional<IChatBaseComponent> optional) {
            if (optional.isPresent()) {
                packetdataserializer.writeBoolean(true);
                packetdataserializer.a((IChatBaseComponent) optional.get());
            } else {
                packetdataserializer.writeBoolean(false);
            }

        }

        @Override
        public Optional<IChatBaseComponent> a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.readBoolean() ? Optional.of(packetdataserializer.h()) : Optional.empty();
        }

        public Optional<IChatBaseComponent> a(Optional<IChatBaseComponent> optional) {
            return optional.isPresent() ? Optional.of(((IChatBaseComponent) optional.get()).h()) : Optional.empty();
        }
    };
    public static final DataWatcherSerializer<ItemStack> g = new DataWatcherSerializer<ItemStack>() {
        public void a(PacketDataSerializer packetdataserializer, ItemStack itemstack) {
            packetdataserializer.a(itemstack);
        }

        @Override
        public ItemStack a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.m();
        }

        public ItemStack a(ItemStack itemstack) {
            return itemstack.cloneItemStack();
        }
    };
    public static final DataWatcherSerializer<Optional<IBlockData>> h = new DataWatcherSerializer<Optional<IBlockData>>() {
        public void a(PacketDataSerializer packetdataserializer, Optional<IBlockData> optional) {
            if (optional.isPresent()) {
                packetdataserializer.d(Block.getCombinedId((IBlockData) optional.get()));
            } else {
                packetdataserializer.d(0);
            }

        }

        @Override
        public Optional<IBlockData> a(PacketDataSerializer packetdataserializer) {
            int i = packetdataserializer.i();

            return i == 0 ? Optional.empty() : Optional.of(Block.getByCombinedId(i));
        }

        public Optional<IBlockData> a(Optional<IBlockData> optional) {
            return optional;
        }
    };
    public static final DataWatcherSerializer<Boolean> i = new DataWatcherSerializer<Boolean>() {
        public void a(PacketDataSerializer packetdataserializer, Boolean obool) {
            packetdataserializer.writeBoolean(obool);
        }

        @Override
        public Boolean a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.readBoolean();
        }

        public Boolean a(Boolean obool) {
            return obool;
        }
    };
    public static final DataWatcherSerializer<ParticleParam> j = new DataWatcherSerializer<ParticleParam>() {
        public void a(PacketDataSerializer packetdataserializer, ParticleParam particleparam) {
            packetdataserializer.d(IRegistry.PARTICLE_TYPE.a((Object) particleparam.getParticle()));
            particleparam.a(packetdataserializer);
        }

        @Override
        public ParticleParam a(PacketDataSerializer packetdataserializer) {
            return this.a(packetdataserializer, (Particle) IRegistry.PARTICLE_TYPE.fromId(packetdataserializer.i()));
        }

        private <T extends ParticleParam> T a(PacketDataSerializer packetdataserializer, Particle<T> particle) {
            return particle.d().b(particle, packetdataserializer);
        }

        public ParticleParam a(ParticleParam particleparam) {
            return particleparam;
        }
    };
    public static final DataWatcherSerializer<Vector3f> k = new DataWatcherSerializer<Vector3f>() {
        public void a(PacketDataSerializer packetdataserializer, Vector3f vector3f) {
            packetdataserializer.writeFloat(vector3f.getX());
            packetdataserializer.writeFloat(vector3f.getY());
            packetdataserializer.writeFloat(vector3f.getZ());
        }

        @Override
        public Vector3f a(PacketDataSerializer packetdataserializer) {
            return new Vector3f(packetdataserializer.readFloat(), packetdataserializer.readFloat(), packetdataserializer.readFloat());
        }

        public Vector3f a(Vector3f vector3f) {
            return vector3f;
        }
    };
    public static final DataWatcherSerializer<BlockPosition> l = new DataWatcherSerializer<BlockPosition>() {
        public void a(PacketDataSerializer packetdataserializer, BlockPosition blockposition) {
            packetdataserializer.a(blockposition);
        }

        @Override
        public BlockPosition a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.e();
        }

        public BlockPosition a(BlockPosition blockposition) {
            return blockposition;
        }
    };
    public static final DataWatcherSerializer<Optional<BlockPosition>> m = new DataWatcherSerializer<Optional<BlockPosition>>() {
        public void a(PacketDataSerializer packetdataserializer, Optional<BlockPosition> optional) {
            packetdataserializer.writeBoolean(optional.isPresent());
            if (optional.isPresent()) {
                packetdataserializer.a((BlockPosition) optional.get());
            }

        }

        @Override
        public Optional<BlockPosition> a(PacketDataSerializer packetdataserializer) {
            return !packetdataserializer.readBoolean() ? Optional.empty() : Optional.of(packetdataserializer.e());
        }

        public Optional<BlockPosition> a(Optional<BlockPosition> optional) {
            return optional;
        }
    };
    public static final DataWatcherSerializer<EnumDirection> n = new DataWatcherSerializer<EnumDirection>() {
        public void a(PacketDataSerializer packetdataserializer, EnumDirection enumdirection) {
            packetdataserializer.a((Enum) enumdirection);
        }

        @Override
        public EnumDirection a(PacketDataSerializer packetdataserializer) {
            return (EnumDirection) packetdataserializer.a(EnumDirection.class);
        }

        public EnumDirection a(EnumDirection enumdirection) {
            return enumdirection;
        }
    };
    public static final DataWatcherSerializer<Optional<UUID>> o = new DataWatcherSerializer<Optional<UUID>>() {
        public void a(PacketDataSerializer packetdataserializer, Optional<UUID> optional) {
            packetdataserializer.writeBoolean(optional.isPresent());
            if (optional.isPresent()) {
                packetdataserializer.a((UUID) optional.get());
            }

        }

        @Override
        public Optional<UUID> a(PacketDataSerializer packetdataserializer) {
            return !packetdataserializer.readBoolean() ? Optional.empty() : Optional.of(packetdataserializer.k());
        }

        public Optional<UUID> a(Optional<UUID> optional) {
            return optional;
        }
    };
    public static final DataWatcherSerializer<NBTTagCompound> p = new DataWatcherSerializer<NBTTagCompound>() {
        public void a(PacketDataSerializer packetdataserializer, NBTTagCompound nbttagcompound) {
            packetdataserializer.a(nbttagcompound);
        }

        @Override
        public NBTTagCompound a(PacketDataSerializer packetdataserializer) {
            return packetdataserializer.l();
        }

        public NBTTagCompound a(NBTTagCompound nbttagcompound) {
            return nbttagcompound.clone();
        }
    };
    public static final DataWatcherSerializer<VillagerData> q = new DataWatcherSerializer<VillagerData>() {
        public void a(PacketDataSerializer packetdataserializer, VillagerData villagerdata) {
            packetdataserializer.d(IRegistry.VILLAGER_TYPE.a((Object) villagerdata.getType()));
            packetdataserializer.d(IRegistry.VILLAGER_PROFESSION.a((Object) villagerdata.getProfession()));
            packetdataserializer.d(villagerdata.getLevel());
        }

        @Override
        public VillagerData a(PacketDataSerializer packetdataserializer) {
            return new VillagerData((VillagerType) IRegistry.VILLAGER_TYPE.fromId(packetdataserializer.i()), (VillagerProfession) IRegistry.VILLAGER_PROFESSION.fromId(packetdataserializer.i()), packetdataserializer.i());
        }

        public VillagerData a(VillagerData villagerdata) {
            return villagerdata;
        }
    };
    public static final DataWatcherSerializer<OptionalInt> r = new DataWatcherSerializer<OptionalInt>() {
        public void a(PacketDataSerializer packetdataserializer, OptionalInt optionalint) {
            packetdataserializer.d(optionalint.orElse(-1) + 1);
        }

        @Override
        public OptionalInt a(PacketDataSerializer packetdataserializer) {
            int i = packetdataserializer.i();

            return i == 0 ? OptionalInt.empty() : OptionalInt.of(i - 1);
        }

        public OptionalInt a(OptionalInt optionalint) {
            return optionalint;
        }
    };
    public static final DataWatcherSerializer<EntityPose> s = new DataWatcherSerializer<EntityPose>() {
        public void a(PacketDataSerializer packetdataserializer, EntityPose entitypose) {
            packetdataserializer.a((Enum) entitypose);
        }

        @Override
        public EntityPose a(PacketDataSerializer packetdataserializer) {
            return (EntityPose) packetdataserializer.a(EntityPose.class);
        }

        public EntityPose a(EntityPose entitypose) {
            return entitypose;
        }
    };

    public static void a(DataWatcherSerializer<?> datawatcherserializer) {
        DataWatcherRegistry.t.c(datawatcherserializer);
    }

    @Nullable
    public static DataWatcherSerializer<?> a(int i) {
        return (DataWatcherSerializer) DataWatcherRegistry.t.fromId(i);
    }

    public static int b(DataWatcherSerializer<?> datawatcherserializer) {
        return DataWatcherRegistry.t.getId(datawatcherserializer);
    }

    static {
        a(DataWatcherRegistry.a);
        a(DataWatcherRegistry.b);
        a(DataWatcherRegistry.c);
        a(DataWatcherRegistry.d);
        a(DataWatcherRegistry.e);
        a(DataWatcherRegistry.f);
        a(DataWatcherRegistry.g);
        a(DataWatcherRegistry.i);
        a(DataWatcherRegistry.k);
        a(DataWatcherRegistry.l);
        a(DataWatcherRegistry.m);
        a(DataWatcherRegistry.n);
        a(DataWatcherRegistry.o);
        a(DataWatcherRegistry.h);
        a(DataWatcherRegistry.p);
        a(DataWatcherRegistry.j);
        a(DataWatcherRegistry.q);
        a(DataWatcherRegistry.r);
        a(DataWatcherRegistry.s);
    }
}
