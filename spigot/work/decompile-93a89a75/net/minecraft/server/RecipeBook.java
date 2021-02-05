package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;

public class RecipeBook {

    protected final Set<MinecraftKey> a = Sets.newHashSet();
    protected final Set<MinecraftKey> b = Sets.newHashSet();
    protected boolean c;
    protected boolean d;
    protected boolean e;
    protected boolean f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    protected boolean j;

    public RecipeBook() {}

    public void a(RecipeBook recipebook) {
        this.a.clear();
        this.b.clear();
        this.a.addAll(recipebook.a);
        this.b.addAll(recipebook.b);
    }

    public void a(IRecipe<?> irecipe) {
        if (!irecipe.isComplex()) {
            this.a(irecipe.getKey());
        }

    }

    protected void a(MinecraftKey minecraftkey) {
        this.a.add(minecraftkey);
    }

    public boolean b(@Nullable IRecipe<?> irecipe) {
        return irecipe == null ? false : this.a.contains(irecipe.getKey());
    }

    protected void b(MinecraftKey minecraftkey) {
        this.a.remove(minecraftkey);
        this.b.remove(minecraftkey);
    }

    public void e(IRecipe<?> irecipe) {
        this.b.remove(irecipe.getKey());
    }

    public void f(IRecipe<?> irecipe) {
        this.c(irecipe.getKey());
    }

    protected void c(MinecraftKey minecraftkey) {
        this.b.add(minecraftkey);
    }

    public void a(boolean flag) {
        this.c = flag;
    }

    public void b(boolean flag) {
        this.d = flag;
    }

    public void c(boolean flag) {
        this.e = flag;
    }

    public void d(boolean flag) {
        this.f = flag;
    }

    public void e(boolean flag) {
        this.g = flag;
    }

    public void f(boolean flag) {
        this.h = flag;
    }

    public void g(boolean flag) {
        this.i = flag;
    }

    public void h(boolean flag) {
        this.j = flag;
    }
}
