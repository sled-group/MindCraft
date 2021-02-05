package net.minecraft.server;

public class MobSpawnerData extends WeightedRandom.WeightedRandomChoice {

    private final NBTTagCompound entity;

    public MobSpawnerData() {
        super(1);
        this.entity = new NBTTagCompound();
        this.entity.setString("id", "minecraft:pig");
    }

    public MobSpawnerData(NBTTagCompound nbttagcompound) {
        this(nbttagcompound.hasKeyOfType("Weight", 99) ? nbttagcompound.getInt("Weight") : 1, nbttagcompound.getCompound("Entity"));
    }

    public MobSpawnerData(int i, NBTTagCompound nbttagcompound) {
        super(i);
        this.entity = nbttagcompound;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (!this.entity.hasKeyOfType("id", 8)) {
            this.entity.setString("id", "minecraft:pig");
        } else if (!this.entity.getString("id").contains(":")) {
            this.entity.setString("id", (new MinecraftKey(this.entity.getString("id"))).toString());
        }

        nbttagcompound.set("Entity", this.entity);
        nbttagcompound.setInt("Weight", this.a);
        return nbttagcompound;
    }

    public NBTTagCompound getEntity() {
        return this.entity;
    }
}
