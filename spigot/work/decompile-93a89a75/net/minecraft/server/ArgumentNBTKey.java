package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class ArgumentNBTKey implements ArgumentType<ArgumentNBTKey.h> {

    private static final Collection<String> c = Arrays.asList("foo", "foo.bar", "foo[0]", "[0]", "[]", "{foo=bar}");
    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("arguments.nbtpath.node.invalid", new Object[0]));
    public static final DynamicCommandExceptionType b = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("arguments.nbtpath.nothing_found", new Object[]{object});
    });

    public ArgumentNBTKey() {}

    public static ArgumentNBTKey a() {
        return new ArgumentNBTKey();
    }

    public static ArgumentNBTKey.h a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        return (ArgumentNBTKey.h) commandcontext.getArgument(s, ArgumentNBTKey.h.class);
    }

    public ArgumentNBTKey.h parse(StringReader stringreader) throws CommandSyntaxException {
        List<ArgumentNBTKey.i> list = Lists.newArrayList();
        int i = stringreader.getCursor();
        Object2IntMap<ArgumentNBTKey.i> object2intmap = new Object2IntOpenHashMap();
        boolean flag = true;

        while (stringreader.canRead() && stringreader.peek() != ' ') {
            ArgumentNBTKey.i argumentnbtkey_i = a(stringreader, flag);

            list.add(argumentnbtkey_i);
            object2intmap.put(argumentnbtkey_i, stringreader.getCursor() - i);
            flag = false;
            if (stringreader.canRead()) {
                char c0 = stringreader.peek();

                if (c0 != ' ' && c0 != '[' && c0 != '{') {
                    stringreader.expect('.');
                }
            }
        }

        return new ArgumentNBTKey.h(stringreader.getString().substring(i, stringreader.getCursor()), (ArgumentNBTKey.i[]) list.toArray(new ArgumentNBTKey.i[0]), object2intmap);
    }

    private static ArgumentNBTKey.i a(StringReader stringreader, boolean flag) throws CommandSyntaxException {
        String s;

        switch (stringreader.peek()) {
            case '"':
                s = stringreader.readString();
                return a(stringreader, s);
            case '[':
                stringreader.skip();
                char c0 = stringreader.peek();

                if (c0 == '{') {
                    NBTTagCompound nbttagcompound = (new MojangsonParser(stringreader)).f();

                    stringreader.expect(']');
                    return new ArgumentNBTKey.e(nbttagcompound);
                } else {
                    if (c0 == ']') {
                        stringreader.skip();
                        return ArgumentNBTKey.a.a;
                    }

                    int i = stringreader.readInt();

                    stringreader.expect(']');
                    return new ArgumentNBTKey.c(i);
                }
            case '{':
                if (!flag) {
                    throw ArgumentNBTKey.a.createWithContext(stringreader);
                }

                NBTTagCompound nbttagcompound1 = (new MojangsonParser(stringreader)).f();

                return new ArgumentNBTKey.g(nbttagcompound1);
            default:
                s = b(stringreader);
                return a(stringreader, s);
        }
    }

    private static ArgumentNBTKey.i a(StringReader stringreader, String s) throws CommandSyntaxException {
        if (stringreader.canRead() && stringreader.peek() == '{') {
            NBTTagCompound nbttagcompound = (new MojangsonParser(stringreader)).f();

            return new ArgumentNBTKey.f(s, nbttagcompound);
        } else {
            return new ArgumentNBTKey.b(s);
        }
    }

    private static String b(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        while (stringreader.canRead() && a(stringreader.peek())) {
            stringreader.skip();
        }

        if (stringreader.getCursor() == i) {
            throw ArgumentNBTKey.a.createWithContext(stringreader);
        } else {
            return stringreader.getString().substring(i, stringreader.getCursor());
        }
    }

    public Collection<String> getExamples() {
        return ArgumentNBTKey.c;
    }

    private static boolean a(char c0) {
        return c0 != ' ' && c0 != '"' && c0 != '[' && c0 != ']' && c0 != '.' && c0 != '{' && c0 != '}';
    }

    private static Predicate<NBTBase> b(NBTTagCompound nbttagcompound) {
        return (nbtbase) -> {
            return GameProfileSerializer.a(nbttagcompound, nbtbase, true);
        };
    }

    static class g implements ArgumentNBTKey.i {

        private final Predicate<NBTBase> a;

        public g(NBTTagCompound nbttagcompound) {
            this.a = ArgumentNBTKey.b(nbttagcompound);
        }

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagCompound && this.a.test(nbtbase)) {
                list.add(nbtbase);
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            this.a(nbtbase, list);
        }

        @Override
        public NBTBase a() {
            return new NBTTagCompound();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            return 0;
        }

        @Override
        public int a(NBTBase nbtbase) {
            return 0;
        }
    }

    static class f implements ArgumentNBTKey.i {

        private final String a;
        private final NBTTagCompound b;
        private final Predicate<NBTBase> c;

        public f(String s, NBTTagCompound nbttagcompound) {
            this.a = s;
            this.b = nbttagcompound;
            this.c = ArgumentNBTKey.b(nbttagcompound);
        }

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTBase nbtbase1 = ((NBTTagCompound) nbtbase).get(this.a);

                if (this.c.test(nbtbase1)) {
                    list.add(nbtbase1);
                }
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTBase nbtbase1 = nbttagcompound.get(this.a);

                if (nbtbase1 == null) {
                    NBTTagCompound nbttagcompound1 = this.b.clone();

                    nbttagcompound.set(this.a, nbttagcompound1);
                    list.add(nbttagcompound1);
                } else if (this.c.test(nbtbase1)) {
                    list.add(nbtbase1);
                }
            }

        }

        @Override
        public NBTBase a() {
            return new NBTTagCompound();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTBase nbtbase1 = nbttagcompound.get(this.a);

                if (this.c.test(nbtbase1)) {
                    NBTBase nbtbase2 = (NBTBase) supplier.get();

                    if (!nbtbase2.equals(nbtbase1)) {
                        nbttagcompound.set(this.a, nbtbase2);
                        return 1;
                    }
                }
            }

            return 0;
        }

        @Override
        public int a(NBTBase nbtbase) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTBase nbtbase1 = nbttagcompound.get(this.a);

                if (this.c.test(nbtbase1)) {
                    nbttagcompound.remove(this.a);
                    return 1;
                }
            }

            return 0;
        }
    }

    static class a implements ArgumentNBTKey.i {

        public static final ArgumentNBTKey.a a = new ArgumentNBTKey.a();

        private a() {}

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTList) {
                list.addAll((NBTList) nbtbase);
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            if (nbtbase instanceof NBTList) {
                NBTList<?> nbtlist = (NBTList) nbtbase;

                if (nbtlist.isEmpty()) {
                    NBTBase nbtbase1 = (NBTBase) supplier.get();

                    if (nbtlist.b(0, nbtbase1)) {
                        list.add(nbtbase1);
                    }
                } else {
                    list.addAll(nbtlist);
                }
            }

        }

        @Override
        public NBTBase a() {
            return new NBTTagList();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            if (!(nbtbase instanceof NBTList)) {
                return 0;
            } else {
                NBTList<?> nbtlist = (NBTList) nbtbase;
                int i = nbtlist.size();

                if (i == 0) {
                    nbtlist.b(0, (NBTBase) supplier.get());
                    return 1;
                } else {
                    NBTBase nbtbase1 = (NBTBase) supplier.get();
                    Stream stream = nbtlist.stream();

                    nbtbase1.getClass();
                    int j = i - (int) stream.filter(nbtbase1::equals).count();

                    if (j == 0) {
                        return 0;
                    } else {
                        nbtlist.clear();
                        if (!nbtlist.b(0, nbtbase1)) {
                            return 0;
                        } else {
                            for (int k = 1; k < i; ++k) {
                                nbtlist.b(k, (NBTBase) supplier.get());
                            }

                            return j;
                        }
                    }
                }
            }
        }

        @Override
        public int a(NBTBase nbtbase) {
            if (nbtbase instanceof NBTList) {
                NBTList<?> nbtlist = (NBTList) nbtbase;
                int i = nbtlist.size();

                if (i > 0) {
                    nbtlist.clear();
                    return i;
                }
            }

            return 0;
        }
    }

    static class e implements ArgumentNBTKey.i {

        private final NBTTagCompound a;
        private final Predicate<NBTBase> b;

        public e(NBTTagCompound nbttagcompound) {
            this.a = nbttagcompound;
            this.b = ArgumentNBTKey.b(nbttagcompound);
        }

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagList) {
                NBTTagList nbttaglist = (NBTTagList) nbtbase;

                nbttaglist.stream().filter(this.b).forEach(list::add);
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            MutableBoolean mutableboolean = new MutableBoolean();

            if (nbtbase instanceof NBTTagList) {
                NBTTagList nbttaglist = (NBTTagList) nbtbase;

                nbttaglist.stream().filter(this.b).forEach((nbtbase1) -> {
                    list.add(nbtbase1);
                    mutableboolean.setTrue();
                });
                if (mutableboolean.isFalse()) {
                    NBTTagCompound nbttagcompound = this.a.clone();

                    nbttaglist.add(nbttagcompound);
                    list.add(nbttagcompound);
                }
            }

        }

        @Override
        public NBTBase a() {
            return new NBTTagList();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            int i = 0;

            if (nbtbase instanceof NBTTagList) {
                NBTTagList nbttaglist = (NBTTagList) nbtbase;
                int j = nbttaglist.size();

                if (j == 0) {
                    nbttaglist.add(supplier.get());
                    ++i;
                } else {
                    for (int k = 0; k < j; ++k) {
                        NBTBase nbtbase1 = nbttaglist.get(k);

                        if (this.b.test(nbtbase1)) {
                            NBTBase nbtbase2 = (NBTBase) supplier.get();

                            if (!nbtbase2.equals(nbtbase1) && nbttaglist.a(k, nbtbase2)) {
                                ++i;
                            }
                        }
                    }
                }
            }

            return i;
        }

        @Override
        public int a(NBTBase nbtbase) {
            int i = 0;

            if (nbtbase instanceof NBTTagList) {
                NBTTagList nbttaglist = (NBTTagList) nbtbase;

                for (int j = nbttaglist.size() - 1; j >= 0; --j) {
                    if (this.b.test(nbttaglist.get(j))) {
                        nbttaglist.remove(j);
                        ++i;
                    }
                }
            }

            return i;
        }
    }

    static class c implements ArgumentNBTKey.i {

        private final int a;

        public c(int i) {
            this.a = i;
        }

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTList) {
                NBTList<?> nbtlist = (NBTList) nbtbase;
                int i = nbtlist.size();
                int j = this.a < 0 ? i + this.a : this.a;

                if (0 <= j && j < i) {
                    list.add(nbtlist.get(j));
                }
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            this.a(nbtbase, list);
        }

        @Override
        public NBTBase a() {
            return new NBTTagList();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            if (nbtbase instanceof NBTList) {
                NBTList<?> nbtlist = (NBTList) nbtbase;
                int i = nbtlist.size();
                int j = this.a < 0 ? i + this.a : this.a;

                if (0 <= j && j < i) {
                    NBTBase nbtbase1 = (NBTBase) nbtlist.get(j);
                    NBTBase nbtbase2 = (NBTBase) supplier.get();

                    if (!nbtbase2.equals(nbtbase1) && nbtlist.a(j, nbtbase2)) {
                        return 1;
                    }
                }
            }

            return 0;
        }

        @Override
        public int a(NBTBase nbtbase) {
            if (nbtbase instanceof NBTList) {
                NBTList<?> nbtlist = (NBTList) nbtbase;
                int i = nbtlist.size();
                int j = this.a < 0 ? i + this.a : this.a;

                if (0 <= j && j < i) {
                    nbtlist.remove(j);
                    return 1;
                }
            }

            return 0;
        }
    }

    static class b implements ArgumentNBTKey.i {

        private final String a;

        public b(String s) {
            this.a = s;
        }

        @Override
        public void a(NBTBase nbtbase, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTBase nbtbase1 = ((NBTTagCompound) nbtbase).get(this.a);

                if (nbtbase1 != null) {
                    list.add(nbtbase1);
                }
            }

        }

        @Override
        public void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTBase nbtbase1;

                if (nbttagcompound.hasKey(this.a)) {
                    nbtbase1 = nbttagcompound.get(this.a);
                } else {
                    nbtbase1 = (NBTBase) supplier.get();
                    nbttagcompound.set(this.a, nbtbase1);
                }

                list.add(nbtbase1);
            }

        }

        @Override
        public NBTBase a() {
            return new NBTTagCompound();
        }

        @Override
        public int a(NBTBase nbtbase, Supplier<NBTBase> supplier) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
                NBTBase nbtbase1 = (NBTBase) supplier.get();
                NBTBase nbtbase2 = nbttagcompound.set(this.a, nbtbase1);

                if (!nbtbase1.equals(nbtbase2)) {
                    return 1;
                }
            }

            return 0;
        }

        @Override
        public int a(NBTBase nbtbase) {
            if (nbtbase instanceof NBTTagCompound) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;

                if (nbttagcompound.hasKey(this.a)) {
                    nbttagcompound.remove(this.a);
                    return 1;
                }
            }

            return 0;
        }
    }

    interface i {

        void a(NBTBase nbtbase, List<NBTBase> list);

        void a(NBTBase nbtbase, Supplier<NBTBase> supplier, List<NBTBase> list);

        NBTBase a();

        int a(NBTBase nbtbase, Supplier<NBTBase> supplier);

        int a(NBTBase nbtbase);

        default List<NBTBase> a(List<NBTBase> list) {
            return this.a(list, this::a);
        }

        default List<NBTBase> a(List<NBTBase> list, Supplier<NBTBase> supplier) {
            return this.a(list, (nbtbase, list1) -> {
                this.a(nbtbase, supplier, list1);
            });
        }

        default List<NBTBase> a(List<NBTBase> list, BiConsumer<NBTBase, List<NBTBase>> biconsumer) {
            List<NBTBase> list1 = Lists.newArrayList();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                NBTBase nbtbase = (NBTBase) iterator.next();

                biconsumer.accept(nbtbase, list1);
            }

            return list1;
        }
    }

    public static class h {

        private final String a;
        private final Object2IntMap<ArgumentNBTKey.i> b;
        private final ArgumentNBTKey.i[] c;

        public h(String s, ArgumentNBTKey.i[] aargumentnbtkey_i, Object2IntMap<ArgumentNBTKey.i> object2intmap) {
            this.a = s;
            this.c = aargumentnbtkey_i;
            this.b = object2intmap;
        }

        public List<NBTBase> a(NBTBase nbtbase) throws CommandSyntaxException {
            List<NBTBase> list = Collections.singletonList(nbtbase);
            ArgumentNBTKey.i[] aargumentnbtkey_i = this.c;
            int i = aargumentnbtkey_i.length;

            for (int j = 0; j < i; ++j) {
                ArgumentNBTKey.i argumentnbtkey_i = aargumentnbtkey_i[j];

                list = argumentnbtkey_i.a(list);
                if (list.isEmpty()) {
                    throw this.a(argumentnbtkey_i);
                }
            }

            return list;
        }

        public int b(NBTBase nbtbase) {
            List<NBTBase> list = Collections.singletonList(nbtbase);
            ArgumentNBTKey.i[] aargumentnbtkey_i = this.c;
            int i = aargumentnbtkey_i.length;

            for (int j = 0; j < i; ++j) {
                ArgumentNBTKey.i argumentnbtkey_i = aargumentnbtkey_i[j];

                list = argumentnbtkey_i.a(list);
                if (list.isEmpty()) {
                    return 0;
                }
            }

            return list.size();
        }

        private List<NBTBase> d(NBTBase nbtbase) throws CommandSyntaxException {
            List<NBTBase> list = Collections.singletonList(nbtbase);

            for (int i = 0; i < this.c.length - 1; ++i) {
                ArgumentNBTKey.i argumentnbtkey_i = this.c[i];
                int j = i + 1;
                ArgumentNBTKey.i argumentnbtkey_i1 = this.c[j];

                this.c[j].getClass();
                list = argumentnbtkey_i.a(list, argumentnbtkey_i1::a);
                if (list.isEmpty()) {
                    throw this.a(argumentnbtkey_i);
                }
            }

            return list;
        }

        public List<NBTBase> a(NBTBase nbtbase, Supplier<NBTBase> supplier) throws CommandSyntaxException {
            List<NBTBase> list = this.d(nbtbase);
            ArgumentNBTKey.i argumentnbtkey_i = this.c[this.c.length - 1];

            return argumentnbtkey_i.a(list, supplier);
        }

        private static int a(List<NBTBase> list, Function<NBTBase, Integer> function) {
            return (Integer) list.stream().map(function).reduce(0, (integer, integer1) -> {
                return integer + integer1;
            });
        }

        public int b(NBTBase nbtbase, Supplier<NBTBase> supplier) throws CommandSyntaxException {
            List<NBTBase> list = this.d(nbtbase);
            ArgumentNBTKey.i argumentnbtkey_i = this.c[this.c.length - 1];

            return a(list, (nbtbase1) -> {
                return argumentnbtkey_i.a(nbtbase1, supplier);
            });
        }

        public int c(NBTBase nbtbase) {
            List<NBTBase> list = Collections.singletonList(nbtbase);

            for (int i = 0; i < this.c.length - 1; ++i) {
                list = this.c[i].a(list);
            }

            ArgumentNBTKey.i argumentnbtkey_i = this.c[this.c.length - 1];

            argumentnbtkey_i.getClass();
            return a(list, argumentnbtkey_i::a);
        }

        private CommandSyntaxException a(ArgumentNBTKey.i argumentnbtkey_i) {
            int i = this.b.getInt(argumentnbtkey_i);

            return ArgumentNBTKey.b.create(this.a.substring(0, i));
        }

        public String toString() {
            return this.a;
        }
    }
}
