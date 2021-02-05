package net.minecraft.server;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MojangsonParser {

    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.trailing", new Object[0]));
    public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.expected.key", new Object[0]));
    public static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.nbt.expected.value", new Object[0]));
    public static final Dynamic2CommandExceptionType d = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.nbt.list.mixed", new Object[]{object, object1});
    });
    public static final Dynamic2CommandExceptionType e = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("argument.nbt.array.mixed", new Object[]{object, object1});
    });
    public static final DynamicCommandExceptionType f = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.nbt.array.invalid", new Object[]{object});
    });
    private static final Pattern g = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", 2);
    private static final Pattern h = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
    private static final Pattern i = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", 2);
    private static final Pattern j = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", 2);
    private static final Pattern k = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", 2);
    private static final Pattern l = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", 2);
    private static final Pattern m = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");
    private final StringReader n;

    public static NBTTagCompound parse(String s) throws CommandSyntaxException {
        return (new MojangsonParser(new StringReader(s))).a();
    }

    @VisibleForTesting
    NBTTagCompound a() throws CommandSyntaxException {
        NBTTagCompound nbttagcompound = this.f();

        this.n.skipWhitespace();
        if (this.n.canRead()) {
            throw MojangsonParser.a.createWithContext(this.n);
        } else {
            return nbttagcompound;
        }
    }

    public MojangsonParser(StringReader stringreader) {
        this.n = stringreader;
    }

    protected String b() throws CommandSyntaxException {
        this.n.skipWhitespace();
        if (!this.n.canRead()) {
            throw MojangsonParser.b.createWithContext(this.n);
        } else {
            return this.n.readString();
        }
    }

    protected NBTBase c() throws CommandSyntaxException {
        this.n.skipWhitespace();
        int i = this.n.getCursor();

        if (StringReader.isQuotedStringStart(this.n.peek())) {
            return new NBTTagString(this.n.readQuotedString());
        } else {
            String s = this.n.readUnquotedString();

            if (s.isEmpty()) {
                this.n.setCursor(i);
                throw MojangsonParser.c.createWithContext(this.n);
            } else {
                return this.parseLiteral(s);
            }
        }
    }

    public NBTBase parseLiteral(String s) {
        try {
            if (MojangsonParser.i.matcher(s).matches()) {
                return new NBTTagFloat(Float.parseFloat(s.substring(0, s.length() - 1)));
            }

            if (MojangsonParser.j.matcher(s).matches()) {
                return new NBTTagByte(Byte.parseByte(s.substring(0, s.length() - 1)));
            }

            if (MojangsonParser.k.matcher(s).matches()) {
                return new NBTTagLong(Long.parseLong(s.substring(0, s.length() - 1)));
            }

            if (MojangsonParser.l.matcher(s).matches()) {
                return new NBTTagShort(Short.parseShort(s.substring(0, s.length() - 1)));
            }

            if (MojangsonParser.m.matcher(s).matches()) {
                return new NBTTagInt(Integer.parseInt(s));
            }

            if (MojangsonParser.h.matcher(s).matches()) {
                return new NBTTagDouble(Double.parseDouble(s.substring(0, s.length() - 1)));
            }

            if (MojangsonParser.g.matcher(s).matches()) {
                return new NBTTagDouble(Double.parseDouble(s));
            }

            if ("true".equalsIgnoreCase(s)) {
                return new NBTTagByte((byte) 1);
            }

            if ("false".equalsIgnoreCase(s)) {
                return new NBTTagByte((byte) 0);
            }
        } catch (NumberFormatException numberformatexception) {
            ;
        }

        return new NBTTagString(s);
    }

    public NBTBase d() throws CommandSyntaxException {
        this.n.skipWhitespace();
        if (!this.n.canRead()) {
            throw MojangsonParser.c.createWithContext(this.n);
        } else {
            char c0 = this.n.peek();

            return (NBTBase) (c0 == '{' ? this.f() : (c0 == '[' ? this.e() : this.c()));
        }
    }

    protected NBTBase e() throws CommandSyntaxException {
        return this.n.canRead(3) && !StringReader.isQuotedStringStart(this.n.peek(1)) && this.n.peek(2) == ';' ? this.parseArray() : this.g();
    }

    public NBTTagCompound f() throws CommandSyntaxException {
        this.a('{');
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.n.skipWhitespace();

        while (this.n.canRead() && this.n.peek() != '}') {
            int i = this.n.getCursor();
            String s = this.b();

            if (s.isEmpty()) {
                this.n.setCursor(i);
                throw MojangsonParser.b.createWithContext(this.n);
            }

            this.a(':');
            nbttagcompound.set(s, this.d());
            if (!this.i()) {
                break;
            }

            if (!this.n.canRead()) {
                throw MojangsonParser.b.createWithContext(this.n);
            }
        }

        this.a('}');
        return nbttagcompound;
    }

    private NBTBase g() throws CommandSyntaxException {
        this.a('[');
        this.n.skipWhitespace();
        if (!this.n.canRead()) {
            throw MojangsonParser.c.createWithContext(this.n);
        } else {
            NBTTagList nbttaglist = new NBTTagList();
            byte b0 = -1;

            while (this.n.peek() != ']') {
                int i = this.n.getCursor();
                NBTBase nbtbase = this.d();
                byte b1 = nbtbase.getTypeId();

                if (b0 < 0) {
                    b0 = b1;
                } else if (b1 != b0) {
                    this.n.setCursor(i);
                    throw MojangsonParser.d.createWithContext(this.n, NBTBase.l(b1), NBTBase.l(b0));
                }

                nbttaglist.add(nbtbase);
                if (!this.i()) {
                    break;
                }

                if (!this.n.canRead()) {
                    throw MojangsonParser.c.createWithContext(this.n);
                }
            }

            this.a(']');
            return nbttaglist;
        }
    }

    public NBTBase parseArray() throws CommandSyntaxException {
        this.a('[');
        int i = this.n.getCursor();
        char c0 = this.n.read();

        this.n.read();
        this.n.skipWhitespace();
        if (!this.n.canRead()) {
            throw MojangsonParser.c.createWithContext(this.n);
        } else if (c0 == 'B') {
            return new NBTTagByteArray(this.a((byte) 7, (byte) 1));
        } else if (c0 == 'L') {
            return new NBTTagLongArray(this.a((byte) 12, (byte) 4));
        } else if (c0 == 'I') {
            return new NBTTagIntArray(this.a((byte) 11, (byte) 3));
        } else {
            this.n.setCursor(i);
            throw MojangsonParser.f.createWithContext(this.n, String.valueOf(c0));
        }
    }

    private <T extends Number> List<T> a(byte b0, byte b1) throws CommandSyntaxException {
        ArrayList arraylist = Lists.newArrayList();

        while (true) {
            if (this.n.peek() != ']') {
                int i = this.n.getCursor();
                NBTBase nbtbase = this.d();
                byte b2 = nbtbase.getTypeId();

                if (b2 != b1) {
                    this.n.setCursor(i);
                    throw MojangsonParser.e.createWithContext(this.n, NBTBase.l(b2), NBTBase.l(b0));
                }

                if (b1 == 1) {
                    arraylist.add(((NBTNumber) nbtbase).asByte());
                } else if (b1 == 4) {
                    arraylist.add(((NBTNumber) nbtbase).asLong());
                } else {
                    arraylist.add(((NBTNumber) nbtbase).asInt());
                }

                if (this.i()) {
                    if (!this.n.canRead()) {
                        throw MojangsonParser.c.createWithContext(this.n);
                    }
                    continue;
                }
            }

            this.a(']');
            return arraylist;
        }
    }

    private boolean i() {
        this.n.skipWhitespace();
        if (this.n.canRead() && this.n.peek() == ',') {
            this.n.skip();
            this.n.skipWhitespace();
            return true;
        } else {
            return false;
        }
    }

    private void a(char c0) throws CommandSyntaxException {
        this.n.skipWhitespace();
        this.n.expect(c0);
    }
}
