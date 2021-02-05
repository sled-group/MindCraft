package net.minecraft.server;

import it.unimi.dsi.fastutil.shorts.ShortList;
import it.unimi.dsi.fastutil.shorts.ShortListIterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ProtoChunkTickList<T> implements TickList<T> {

    protected final Predicate<T> a;
    private final ChunkCoordIntPair b;
    private final ShortList[] c;

    public ProtoChunkTickList(Predicate<T> predicate, ChunkCoordIntPair chunkcoordintpair) {
        this(predicate, chunkcoordintpair, new NBTTagList());
    }

    public ProtoChunkTickList(Predicate<T> predicate, ChunkCoordIntPair chunkcoordintpair, NBTTagList nbttaglist) {
        this.c = new ShortList[16];
        this.a = predicate;
        this.b = chunkcoordintpair;

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagList nbttaglist1 = nbttaglist.b(i);

            for (int j = 0; j < nbttaglist1.size(); ++j) {
                IChunkAccess.a(this.c, i).add(nbttaglist1.d(j));
            }
        }

    }

    public NBTTagList b() {
        return ChunkRegionLoader.a(this.c);
    }

    public void a(TickList<T> ticklist, Function<BlockPosition, T> function) {
        for (int i = 0; i < this.c.length; ++i) {
            if (this.c[i] != null) {
                ShortListIterator shortlistiterator = this.c[i].iterator();

                while (shortlistiterator.hasNext()) {
                    Short oshort = (Short) shortlistiterator.next();
                    BlockPosition blockposition = ProtoChunk.a(oshort, i, this.b);

                    ticklist.a(blockposition, function.apply(blockposition), 0);
                }

                this.c[i].clear();
            }
        }

    }

    @Override
    public boolean a(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {
        IChunkAccess.a(this.c, blockposition.getY() >> 4).add(ProtoChunk.l(blockposition));
    }

    @Override
    public boolean b(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(Stream<NextTickListEntry<T>> stream) {
        stream.forEach((nextticklistentry) -> {
            this.a(nextticklistentry.a, nextticklistentry.b(), 0, nextticklistentry.c);
        });
    }
}
