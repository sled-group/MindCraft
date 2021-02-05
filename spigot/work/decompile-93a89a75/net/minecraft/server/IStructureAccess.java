package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Map;
import javax.annotation.Nullable;

public interface IStructureAccess extends IBlockAccess {

    @Nullable
    StructureStart a(String s);

    void a(String s, StructureStart structurestart);

    LongSet b(String s);

    void a(String s, long i);

    Map<String, LongSet> v();

    void b(Map<String, LongSet> map);
}
