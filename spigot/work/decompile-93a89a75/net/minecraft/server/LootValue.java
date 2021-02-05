package net.minecraft.server;

import java.util.Random;

public interface LootValue {

    MinecraftKey a = new MinecraftKey("constant");
    MinecraftKey b = new MinecraftKey("uniform");
    MinecraftKey c = new MinecraftKey("binomial");

    int a(Random random);

    MinecraftKey a();
}
