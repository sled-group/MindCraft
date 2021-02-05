package net.minecraft.server;

public interface AreaFactory<A extends Area> {

    A make();
}
