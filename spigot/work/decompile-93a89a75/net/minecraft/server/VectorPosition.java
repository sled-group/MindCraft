package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class VectorPosition implements IVectorPosition {

    private final ArgumentParserPosition a;
    private final ArgumentParserPosition b;
    private final ArgumentParserPosition c;

    public VectorPosition(ArgumentParserPosition argumentparserposition, ArgumentParserPosition argumentparserposition1, ArgumentParserPosition argumentparserposition2) {
        this.a = argumentparserposition;
        this.b = argumentparserposition1;
        this.c = argumentparserposition2;
    }

    @Override
    public Vec3D a(CommandListenerWrapper commandlistenerwrapper) {
        Vec3D vec3d = commandlistenerwrapper.getPosition();

        return new Vec3D(this.a.a(vec3d.x), this.b.a(vec3d.y), this.c.a(vec3d.z));
    }

    @Override
    public Vec2F b(CommandListenerWrapper commandlistenerwrapper) {
        Vec2F vec2f = commandlistenerwrapper.i();

        return new Vec2F((float) this.a.a((double) vec2f.i), (float) this.b.a((double) vec2f.j));
    }

    @Override
    public boolean a() {
        return this.a.a();
    }

    @Override
    public boolean b() {
        return this.b.a();
    }

    @Override
    public boolean c() {
        return this.c.a();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof VectorPosition)) {
            return false;
        } else {
            VectorPosition vectorposition = (VectorPosition) object;

            return !this.a.equals(vectorposition.a) ? false : (!this.b.equals(vectorposition.b) ? false : this.c.equals(vectorposition.c));
        }
    }

    public static VectorPosition a(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();
        ArgumentParserPosition argumentparserposition = ArgumentParserPosition.a(stringreader);

        if (stringreader.canRead() && stringreader.peek() == ' ') {
            stringreader.skip();
            ArgumentParserPosition argumentparserposition1 = ArgumentParserPosition.a(stringreader);

            if (stringreader.canRead() && stringreader.peek() == ' ') {
                stringreader.skip();
                ArgumentParserPosition argumentparserposition2 = ArgumentParserPosition.a(stringreader);

                return new VectorPosition(argumentparserposition, argumentparserposition1, argumentparserposition2);
            } else {
                stringreader.setCursor(i);
                throw ArgumentVec3.a.createWithContext(stringreader);
            }
        } else {
            stringreader.setCursor(i);
            throw ArgumentVec3.a.createWithContext(stringreader);
        }
    }

    public static VectorPosition a(StringReader stringreader, boolean flag) throws CommandSyntaxException {
        int i = stringreader.getCursor();
        ArgumentParserPosition argumentparserposition = ArgumentParserPosition.a(stringreader, flag);

        if (stringreader.canRead() && stringreader.peek() == ' ') {
            stringreader.skip();
            ArgumentParserPosition argumentparserposition1 = ArgumentParserPosition.a(stringreader, false);

            if (stringreader.canRead() && stringreader.peek() == ' ') {
                stringreader.skip();
                ArgumentParserPosition argumentparserposition2 = ArgumentParserPosition.a(stringreader, flag);

                return new VectorPosition(argumentparserposition, argumentparserposition1, argumentparserposition2);
            } else {
                stringreader.setCursor(i);
                throw ArgumentVec3.a.createWithContext(stringreader);
            }
        } else {
            stringreader.setCursor(i);
            throw ArgumentVec3.a.createWithContext(stringreader);
        }
    }

    public static VectorPosition d() {
        return new VectorPosition(new ArgumentParserPosition(true, 0.0D), new ArgumentParserPosition(true, 0.0D), new ArgumentParserPosition(true, 0.0D));
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + this.b.hashCode();
        i = 31 * i + this.c.hashCode();
        return i;
    }
}
