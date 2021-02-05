package net.minecraft.server;

public class DataPaletteGlobal<T> implements DataPalette<T> {

    private final RegistryBlockID<T> a;
    private final T b;

    public DataPaletteGlobal(RegistryBlockID<T> registryblockid, T t0) {
        this.a = registryblockid;
        this.b = t0;
    }

    @Override
    public int a(T t0) {
        int i = this.a.getId(t0);

        return i == -1 ? 0 : i;
    }

    @Override
    public boolean b(T t0) {
        return true;
    }

    @Override
    public T a(int i) {
        T t0 = this.a.fromId(i);

        return t0 == null ? this.b : t0;
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) {}

    @Override
    public int a() {
        return PacketDataSerializer.a(0);
    }

    @Override
    public void a(NBTTagList nbttaglist) {}
}
