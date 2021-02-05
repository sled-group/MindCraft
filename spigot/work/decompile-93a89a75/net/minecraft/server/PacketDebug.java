package net.minecraft.server;

import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketDebug {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void a(WorldServer worldserver, ChunkCoordIntPair chunkcoordintpair) {}

    public static void a(WorldServer worldserver, BlockPosition blockposition) {}

    public static void b(WorldServer worldserver, BlockPosition blockposition) {}

    public static void c(WorldServer worldserver, BlockPosition blockposition) {}

    public static void a(World world, EntityInsentient entityinsentient, @Nullable PathEntity pathentity, float f) {}

    public static void a(World world, BlockPosition blockposition) {}

    public static void a(GeneratorAccess generatoraccess, StructureStart structurestart) {}

    public static void a(World world, EntityInsentient entityinsentient, PathfinderGoalSelector pathfindergoalselector) {}

    public static void a(WorldServer worldserver, Collection<Raid> collection) {}

    public static void a(EntityLiving entityliving) {}
}
