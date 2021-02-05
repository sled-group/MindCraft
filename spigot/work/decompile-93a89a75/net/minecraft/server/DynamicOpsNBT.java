package net.minecraft.server;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DynamicOpsNBT implements DynamicOps<NBTBase> {

    public static final DynamicOpsNBT a = new DynamicOpsNBT();

    protected DynamicOpsNBT() {}

    public NBTBase empty() {
        return new NBTTagEnd();
    }

    public Type<?> getType(NBTBase nbtbase) {
        switch (nbtbase.getTypeId()) {
            case 0:
                return DSL.nilType();
            case 1:
                return DSL.byteType();
            case 2:
                return DSL.shortType();
            case 3:
                return DSL.intType();
            case 4:
                return DSL.longType();
            case 5:
                return DSL.floatType();
            case 6:
                return DSL.doubleType();
            case 7:
                return DSL.list(DSL.byteType());
            case 8:
                return DSL.string();
            case 9:
                return DSL.list(DSL.remainderType());
            case 10:
                return DSL.compoundList(DSL.remainderType(), DSL.remainderType());
            case 11:
                return DSL.list(DSL.intType());
            case 12:
                return DSL.list(DSL.longType());
            default:
                return DSL.remainderType();
        }
    }

    public Optional<Number> getNumberValue(NBTBase nbtbase) {
        return nbtbase instanceof NBTNumber ? Optional.of(((NBTNumber) nbtbase).j()) : Optional.empty();
    }

    public NBTBase createNumeric(Number number) {
        return new NBTTagDouble(number.doubleValue());
    }

    public NBTBase createByte(byte b0) {
        return new NBTTagByte(b0);
    }

    public NBTBase createShort(short short0) {
        return new NBTTagShort(short0);
    }

    public NBTBase createInt(int i) {
        return new NBTTagInt(i);
    }

    public NBTBase createLong(long i) {
        return new NBTTagLong(i);
    }

    public NBTBase createFloat(float f) {
        return new NBTTagFloat(f);
    }

    public NBTBase createDouble(double d0) {
        return new NBTTagDouble(d0);
    }

    public Optional<String> getStringValue(NBTBase nbtbase) {
        return nbtbase instanceof NBTTagString ? Optional.of(nbtbase.asString()) : Optional.empty();
    }

    public NBTBase createString(String s) {
        return new NBTTagString(s);
    }

    public NBTBase mergeInto(NBTBase nbtbase, NBTBase nbtbase1) {
        if (nbtbase1 instanceof NBTTagEnd) {
            return nbtbase;
        } else if (!(nbtbase instanceof NBTTagCompound)) {
            if (nbtbase instanceof NBTTagEnd) {
                throw new IllegalArgumentException("mergeInto called with a null input.");
            } else if (nbtbase instanceof NBTList) {
                NBTList<NBTBase> nbtlist = new NBTTagList();
                NBTList<?> nbtlist1 = (NBTList) nbtbase;

                nbtlist.addAll(nbtlist1);
                nbtlist.add(nbtbase1);
                return nbtlist;
            } else {
                return nbtbase;
            }
        } else if (!(nbtbase1 instanceof NBTTagCompound)) {
            return nbtbase;
        } else {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase;
            Iterator iterator = nbttagcompound1.getKeys().iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                nbttagcompound.set(s, nbttagcompound1.get(s));
            }

            NBTTagCompound nbttagcompound2 = (NBTTagCompound) nbtbase1;
            Iterator iterator1 = nbttagcompound2.getKeys().iterator();

            while (iterator1.hasNext()) {
                String s1 = (String) iterator1.next();

                nbttagcompound.set(s1, nbttagcompound2.get(s1));
            }

            return nbttagcompound;
        }
    }

    public NBTBase mergeInto(NBTBase nbtbase, NBTBase nbtbase1, NBTBase nbtbase2) {
        NBTTagCompound nbttagcompound;

        if (nbtbase instanceof NBTTagEnd) {
            nbttagcompound = new NBTTagCompound();
        } else {
            if (!(nbtbase instanceof NBTTagCompound)) {
                return nbtbase;
            }

            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase;

            nbttagcompound = new NBTTagCompound();
            nbttagcompound1.getKeys().forEach((s) -> {
                nbttagcompound.set(s, nbttagcompound1.get(s));
            });
        }

        nbttagcompound.set(nbtbase1.asString(), nbtbase2);
        return nbttagcompound;
    }

    public NBTBase merge(NBTBase nbtbase, NBTBase nbtbase1) {
        if (nbtbase instanceof NBTTagEnd) {
            return nbtbase1;
        } else if (nbtbase1 instanceof NBTTagEnd) {
            return nbtbase;
        } else {
            if (nbtbase instanceof NBTTagCompound && nbtbase1 instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase1;
                NBTTagCompound nbttagcompound2 = new NBTTagCompound();

                nbttagcompound.getKeys().forEach((s) -> {
                    nbttagcompound2.set(s, nbttagcompound.get(s));
                });
                nbttagcompound1.getKeys().forEach((s) -> {
                    nbttagcompound2.set(s, nbttagcompound1.get(s));
                });
            }

            if (nbtbase instanceof NBTList && nbtbase1 instanceof NBTList) {
                NBTTagList nbttaglist = new NBTTagList();

                nbttaglist.addAll((NBTList) nbtbase);
                nbttaglist.addAll((NBTList) nbtbase1);
                return nbttaglist;
            } else {
                throw new IllegalArgumentException("Could not merge " + nbtbase + " and " + nbtbase1);
            }
        }
    }

    public Optional<Map<NBTBase, NBTBase>> getMapValues(NBTBase nbtbase) {
        if (nbtbase instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;

            return Optional.of(nbttagcompound.getKeys().stream().map((s) -> {
                return Pair.of(this.createString(s), nbttagcompound.get(s));
            }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
        } else {
            return Optional.empty();
        }
    }

    public NBTBase createMap(Map<NBTBase, NBTBase> map) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<NBTBase, NBTBase> entry = (Entry) iterator.next();

            nbttagcompound.set(((NBTBase) entry.getKey()).asString(), (NBTBase) entry.getValue());
        }

        return nbttagcompound;
    }

    public Optional<Stream<NBTBase>> getStream(NBTBase nbtbase) {
        return nbtbase instanceof NBTList ? Optional.of(((NBTList) nbtbase).stream().map((nbtbase1) -> {
            return nbtbase1;
        })) : Optional.empty();
    }

    public Optional<ByteBuffer> getByteBuffer(NBTBase nbtbase) {
        return nbtbase instanceof NBTTagByteArray ? Optional.of(ByteBuffer.wrap(((NBTTagByteArray) nbtbase).getBytes())) : super.getByteBuffer(nbtbase);
    }

    public NBTBase createByteList(ByteBuffer bytebuffer) {
        return new NBTTagByteArray(DataFixUtils.toArray(bytebuffer));
    }

    public Optional<IntStream> getIntStream(NBTBase nbtbase) {
        return nbtbase instanceof NBTTagIntArray ? Optional.of(Arrays.stream(((NBTTagIntArray) nbtbase).getInts())) : super.getIntStream(nbtbase);
    }

    public NBTBase createIntList(IntStream intstream) {
        return new NBTTagIntArray(intstream.toArray());
    }

    public Optional<LongStream> getLongStream(NBTBase nbtbase) {
        return nbtbase instanceof NBTTagLongArray ? Optional.of(Arrays.stream(((NBTTagLongArray) nbtbase).getLongs())) : super.getLongStream(nbtbase);
    }

    public NBTBase createLongList(LongStream longstream) {
        return new NBTTagLongArray(longstream.toArray());
    }

    public NBTBase createList(Stream<NBTBase> stream) {
        PeekingIterator<NBTBase> peekingiterator = Iterators.peekingIterator(stream.iterator());

        if (!peekingiterator.hasNext()) {
            return new NBTTagList();
        } else {
            NBTBase nbtbase = (NBTBase) peekingiterator.peek();
            ArrayList arraylist;

            if (nbtbase instanceof NBTTagByte) {
                arraylist = Lists.newArrayList(Iterators.transform(peekingiterator, (nbtbase1) -> {
                    return ((NBTTagByte) nbtbase1).asByte();
                }));
                return new NBTTagByteArray(arraylist);
            } else if (nbtbase instanceof NBTTagInt) {
                arraylist = Lists.newArrayList(Iterators.transform(peekingiterator, (nbtbase1) -> {
                    return ((NBTTagInt) nbtbase1).asInt();
                }));
                return new NBTTagIntArray(arraylist);
            } else if (nbtbase instanceof NBTTagLong) {
                arraylist = Lists.newArrayList(Iterators.transform(peekingiterator, (nbtbase1) -> {
                    return ((NBTTagLong) nbtbase1).asLong();
                }));
                return new NBTTagLongArray(arraylist);
            } else {
                NBTTagList nbttaglist = new NBTTagList();

                while (peekingiterator.hasNext()) {
                    NBTBase nbtbase1 = (NBTBase) peekingiterator.next();

                    if (!(nbtbase1 instanceof NBTTagEnd)) {
                        nbttaglist.add(nbtbase1);
                    }
                }

                return nbttaglist;
            }
        }
    }

    public NBTBase remove(NBTBase nbtbase, String s) {
        if (nbtbase instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound.getKeys().stream().filter((s1) -> {
                return !Objects.equals(s1, s);
            }).forEach((s1) -> {
                nbttagcompound1.set(s1, nbttagcompound.get(s1));
            });
            return nbttagcompound1;
        } else {
            return nbtbase;
        }
    }

    public String toString() {
        return "NBT";
    }
}
