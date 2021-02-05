package net.minecraft.server;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameProfileSerializer {

    private static final Logger LOGGER = LogManager.getLogger();

    @Nullable
    public static GameProfile deserialize(NBTTagCompound nbttagcompound) {
        String s = null;
        String s1 = null;

        if (nbttagcompound.hasKeyOfType("Name", 8)) {
            s = nbttagcompound.getString("Name");
        }

        if (nbttagcompound.hasKeyOfType("Id", 8)) {
            s1 = nbttagcompound.getString("Id");
        }

        try {
            UUID uuid;

            try {
                uuid = UUID.fromString(s1);
            } catch (Throwable throwable) {
                uuid = null;
            }

            GameProfile gameprofile = new GameProfile(uuid, s);

            if (nbttagcompound.hasKeyOfType("Properties", 10)) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
                Iterator iterator = nbttagcompound1.getKeys().iterator();

                while (iterator.hasNext()) {
                    String s2 = (String) iterator.next();
                    NBTTagList nbttaglist = nbttagcompound1.getList(s2, 10);

                    for (int i = 0; i < nbttaglist.size(); ++i) {
                        NBTTagCompound nbttagcompound2 = nbttaglist.getCompound(i);
                        String s3 = nbttagcompound2.getString("Value");

                        if (nbttagcompound2.hasKeyOfType("Signature", 8)) {
                            gameprofile.getProperties().put(s2, new Property(s2, s3, nbttagcompound2.getString("Signature")));
                        } else {
                            gameprofile.getProperties().put(s2, new Property(s2, s3));
                        }
                    }
                }
            }

            return gameprofile;
        } catch (Throwable throwable1) {
            return null;
        }
    }

    public static NBTTagCompound serialize(NBTTagCompound nbttagcompound, GameProfile gameprofile) {
        if (!UtilColor.b(gameprofile.getName())) {
            nbttagcompound.setString("Name", gameprofile.getName());
        }

        if (gameprofile.getId() != null) {
            nbttagcompound.setString("Id", gameprofile.getId().toString());
        }

        if (!gameprofile.getProperties().isEmpty()) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            Iterator iterator = gameprofile.getProperties().keySet().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                NBTTagList nbttaglist = new NBTTagList();

                NBTTagCompound nbttagcompound2;

                for (Iterator iterator1 = gameprofile.getProperties().get(s).iterator(); iterator1.hasNext(); nbttaglist.add(nbttagcompound2)) {
                    Property property = (Property) iterator1.next();

                    nbttagcompound2 = new NBTTagCompound();
                    nbttagcompound2.setString("Value", property.getValue());
                    if (property.hasSignature()) {
                        nbttagcompound2.setString("Signature", property.getSignature());
                    }
                }

                nbttagcompound1.set(s, nbttaglist);
            }

            nbttagcompound.set("Properties", nbttagcompound1);
        }

        return nbttagcompound;
    }

    @VisibleForTesting
    public static boolean a(@Nullable NBTBase nbtbase, @Nullable NBTBase nbtbase1, boolean flag) {
        if (nbtbase == nbtbase1) {
            return true;
        } else if (nbtbase == null) {
            return true;
        } else if (nbtbase1 == null) {
            return false;
        } else if (!nbtbase.getClass().equals(nbtbase1.getClass())) {
            return false;
        } else if (nbtbase instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase1;
            Iterator iterator = nbttagcompound.getKeys().iterator();

            String s;
            NBTBase nbtbase2;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                s = (String) iterator.next();
                nbtbase2 = nbttagcompound.get(s);
            } while (a(nbtbase2, nbttagcompound1.get(s), flag));

            return false;
        } else if (nbtbase instanceof NBTTagList && flag) {
            NBTTagList nbttaglist = (NBTTagList) nbtbase;
            NBTTagList nbttaglist1 = (NBTTagList) nbtbase1;

            if (nbttaglist.isEmpty()) {
                return nbttaglist1.isEmpty();
            } else {
                int i = 0;

                while (i < nbttaglist.size()) {
                    NBTBase nbtbase3 = nbttaglist.get(i);
                    boolean flag1 = false;
                    int j = 0;

                    while (true) {
                        if (j < nbttaglist1.size()) {
                            if (!a(nbtbase3, nbttaglist1.get(j), flag)) {
                                ++j;
                                continue;
                            }

                            flag1 = true;
                        }

                        if (!flag1) {
                            return false;
                        }

                        ++i;
                        break;
                    }
                }

                return true;
            }
        } else {
            return nbtbase.equals(nbtbase1);
        }
    }

    public static NBTTagCompound a(UUID uuid) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setLong("M", uuid.getMostSignificantBits());
        nbttagcompound.setLong("L", uuid.getLeastSignificantBits());
        return nbttagcompound;
    }

    public static UUID b(NBTTagCompound nbttagcompound) {
        return new UUID(nbttagcompound.getLong("M"), nbttagcompound.getLong("L"));
    }

    public static BlockPosition c(NBTTagCompound nbttagcompound) {
        return new BlockPosition(nbttagcompound.getInt("X"), nbttagcompound.getInt("Y"), nbttagcompound.getInt("Z"));
    }

    public static NBTTagCompound a(BlockPosition blockposition) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setInt("X", blockposition.getX());
        nbttagcompound.setInt("Y", blockposition.getY());
        nbttagcompound.setInt("Z", blockposition.getZ());
        return nbttagcompound;
    }

    public static IBlockData d(NBTTagCompound nbttagcompound) {
        if (!nbttagcompound.hasKeyOfType("Name", 8)) {
            return Blocks.AIR.getBlockData();
        } else {
            Block block = (Block) IRegistry.BLOCK.get(new MinecraftKey(nbttagcompound.getString("Name")));
            IBlockData iblockdata = block.getBlockData();

            if (nbttagcompound.hasKeyOfType("Properties", 10)) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Properties");
                BlockStateList<Block, IBlockData> blockstatelist = block.getStates();
                Iterator iterator = nbttagcompound1.getKeys().iterator();

                while (iterator.hasNext()) {
                    String s = (String) iterator.next();
                    IBlockState<?> iblockstate = blockstatelist.a(s);

                    if (iblockstate != null) {
                        iblockdata = (IBlockData) a(iblockdata, iblockstate, s, nbttagcompound1, nbttagcompound);
                    }
                }
            }

            return iblockdata;
        }
    }

    private static <S extends IBlockDataHolder<S>, T extends Comparable<T>> S a(S s0, IBlockState<T> iblockstate, String s, NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1) {
        Optional<T> optional = iblockstate.b(nbttagcompound.getString(s));

        if (optional.isPresent()) {
            return (IBlockDataHolder) s0.set(iblockstate, (Comparable) optional.get());
        } else {
            GameProfileSerializer.LOGGER.warn("Unable to read property: {} with value: {} for blockstate: {}", s, nbttagcompound.getString(s), nbttagcompound1.toString());
            return s0;
        }
    }

    public static NBTTagCompound a(IBlockData iblockdata) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setString("Name", IRegistry.BLOCK.getKey(iblockdata.getBlock()).toString());
        ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap = iblockdata.getStateMap();

        if (!immutablemap.isEmpty()) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            UnmodifiableIterator unmodifiableiterator = immutablemap.entrySet().iterator();

            while (unmodifiableiterator.hasNext()) {
                Entry<IBlockState<?>, Comparable<?>> entry = (Entry) unmodifiableiterator.next();
                IBlockState<?> iblockstate = (IBlockState) entry.getKey();

                nbttagcompound1.setString(iblockstate.a(), a(iblockstate, (Comparable) entry.getValue()));
            }

            nbttagcompound.set("Properties", nbttagcompound1);
        }

        return nbttagcompound;
    }

    private static <T extends Comparable<T>> String a(IBlockState<T> iblockstate, Comparable<?> comparable) {
        return iblockstate.a(comparable);
    }

    public static NBTTagCompound a(DataFixer datafixer, DataFixTypes datafixtypes, NBTTagCompound nbttagcompound, int i) {
        return a(datafixer, datafixtypes, nbttagcompound, i, SharedConstants.a().getWorldVersion());
    }

    public static NBTTagCompound a(DataFixer datafixer, DataFixTypes datafixtypes, NBTTagCompound nbttagcompound, int i, int j) {
        return (NBTTagCompound) datafixer.update(datafixtypes.a(), new Dynamic(DynamicOpsNBT.a, nbttagcompound), i, j).getValue();
    }
}
