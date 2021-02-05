package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Objects;

public class ArgumentVectorPosition implements IVectorPosition {

    private final double a;
    private final double b;
    private final double c;

    public ArgumentVectorPosition(double d0, double d1, double d2) {
        this.a = d0;
        this.b = d1;
        this.c = d2;
    }

    @Override
    public Vec3D a(CommandListenerWrapper commandlistenerwrapper) {
        Vec2F vec2f = commandlistenerwrapper.i();
        Vec3D vec3d = commandlistenerwrapper.k().a(commandlistenerwrapper);
        float f = MathHelper.cos((vec2f.j + 90.0F) * 0.017453292F);
        float f1 = MathHelper.sin((vec2f.j + 90.0F) * 0.017453292F);
        float f2 = MathHelper.cos(-vec2f.i * 0.017453292F);
        float f3 = MathHelper.sin(-vec2f.i * 0.017453292F);
        float f4 = MathHelper.cos((-vec2f.i + 90.0F) * 0.017453292F);
        float f5 = MathHelper.sin((-vec2f.i + 90.0F) * 0.017453292F);
        Vec3D vec3d1 = new Vec3D((double) (f * f2), (double) f3, (double) (f1 * f2));
        Vec3D vec3d2 = new Vec3D((double) (f * f4), (double) f5, (double) (f1 * f4));
        Vec3D vec3d3 = vec3d1.c(vec3d2).a(-1.0D);
        double d0 = vec3d1.x * this.c + vec3d2.x * this.b + vec3d3.x * this.a;
        double d1 = vec3d1.y * this.c + vec3d2.y * this.b + vec3d3.y * this.a;
        double d2 = vec3d1.z * this.c + vec3d2.z * this.b + vec3d3.z * this.a;

        return new Vec3D(vec3d.x + d0, vec3d.y + d1, vec3d.z + d2);
    }

    @Override
    public Vec2F b(CommandListenerWrapper commandlistenerwrapper) {
        return Vec2F.a;
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public boolean c() {
        return true;
    }

    public static ArgumentVectorPosition a(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();
        double d0 = a(stringreader, i);

        if (stringreader.canRead() && stringreader.peek() == ' ') {
            stringreader.skip();
            double d1 = a(stringreader, i);

            if (stringreader.canRead() && stringreader.peek() == ' ') {
                stringreader.skip();
                double d2 = a(stringreader, i);

                return new ArgumentVectorPosition(d0, d1, d2);
            } else {
                stringreader.setCursor(i);
                throw ArgumentVec3.a.createWithContext(stringreader);
            }
        } else {
            stringreader.setCursor(i);
            throw ArgumentVec3.a.createWithContext(stringreader);
        }
    }

    private static double a(StringReader stringreader, int i) throws CommandSyntaxException {
        if (!stringreader.canRead()) {
            throw ArgumentParserPosition.a.createWithContext(stringreader);
        } else if (stringreader.peek() != '^') {
            stringreader.setCursor(i);
            throw ArgumentVec3.b.createWithContext(stringreader);
        } else {
            stringreader.skip();
            return stringreader.canRead() && stringreader.peek() != ' ' ? stringreader.readDouble() : 0.0D;
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ArgumentVectorPosition)) {
            return false;
        } else {
            ArgumentVectorPosition argumentvectorposition = (ArgumentVectorPosition) object;

            return this.a == argumentvectorposition.a && this.b == argumentvectorposition.b && this.c == argumentvectorposition.c;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.a, this.b, this.c});
    }
}
