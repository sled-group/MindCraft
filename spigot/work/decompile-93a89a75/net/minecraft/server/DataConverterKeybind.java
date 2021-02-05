package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DataConverterKeybind extends DataFix {

    private static final Int2ObjectMap<String> a = (Int2ObjectMap) DataFixUtils.make(new Int2ObjectOpenHashMap(), (int2objectopenhashmap) -> {
        int2objectopenhashmap.put(0, "key.unknown");
        int2objectopenhashmap.put(11, "key.0");
        int2objectopenhashmap.put(2, "key.1");
        int2objectopenhashmap.put(3, "key.2");
        int2objectopenhashmap.put(4, "key.3");
        int2objectopenhashmap.put(5, "key.4");
        int2objectopenhashmap.put(6, "key.5");
        int2objectopenhashmap.put(7, "key.6");
        int2objectopenhashmap.put(8, "key.7");
        int2objectopenhashmap.put(9, "key.8");
        int2objectopenhashmap.put(10, "key.9");
        int2objectopenhashmap.put(30, "key.a");
        int2objectopenhashmap.put(40, "key.apostrophe");
        int2objectopenhashmap.put(48, "key.b");
        int2objectopenhashmap.put(43, "key.backslash");
        int2objectopenhashmap.put(14, "key.backspace");
        int2objectopenhashmap.put(46, "key.c");
        int2objectopenhashmap.put(58, "key.caps.lock");
        int2objectopenhashmap.put(51, "key.comma");
        int2objectopenhashmap.put(32, "key.d");
        int2objectopenhashmap.put(211, "key.delete");
        int2objectopenhashmap.put(208, "key.down");
        int2objectopenhashmap.put(18, "key.e");
        int2objectopenhashmap.put(207, "key.end");
        int2objectopenhashmap.put(28, "key.enter");
        int2objectopenhashmap.put(13, "key.equal");
        int2objectopenhashmap.put(1, "key.escape");
        int2objectopenhashmap.put(33, "key.f");
        int2objectopenhashmap.put(59, "key.f1");
        int2objectopenhashmap.put(68, "key.f10");
        int2objectopenhashmap.put(87, "key.f11");
        int2objectopenhashmap.put(88, "key.f12");
        int2objectopenhashmap.put(100, "key.f13");
        int2objectopenhashmap.put(101, "key.f14");
        int2objectopenhashmap.put(102, "key.f15");
        int2objectopenhashmap.put(103, "key.f16");
        int2objectopenhashmap.put(104, "key.f17");
        int2objectopenhashmap.put(105, "key.f18");
        int2objectopenhashmap.put(113, "key.f19");
        int2objectopenhashmap.put(60, "key.f2");
        int2objectopenhashmap.put(61, "key.f3");
        int2objectopenhashmap.put(62, "key.f4");
        int2objectopenhashmap.put(63, "key.f5");
        int2objectopenhashmap.put(64, "key.f6");
        int2objectopenhashmap.put(65, "key.f7");
        int2objectopenhashmap.put(66, "key.f8");
        int2objectopenhashmap.put(67, "key.f9");
        int2objectopenhashmap.put(34, "key.g");
        int2objectopenhashmap.put(41, "key.grave.accent");
        int2objectopenhashmap.put(35, "key.h");
        int2objectopenhashmap.put(199, "key.home");
        int2objectopenhashmap.put(23, "key.i");
        int2objectopenhashmap.put(210, "key.insert");
        int2objectopenhashmap.put(36, "key.j");
        int2objectopenhashmap.put(37, "key.k");
        int2objectopenhashmap.put(82, "key.keypad.0");
        int2objectopenhashmap.put(79, "key.keypad.1");
        int2objectopenhashmap.put(80, "key.keypad.2");
        int2objectopenhashmap.put(81, "key.keypad.3");
        int2objectopenhashmap.put(75, "key.keypad.4");
        int2objectopenhashmap.put(76, "key.keypad.5");
        int2objectopenhashmap.put(77, "key.keypad.6");
        int2objectopenhashmap.put(71, "key.keypad.7");
        int2objectopenhashmap.put(72, "key.keypad.8");
        int2objectopenhashmap.put(73, "key.keypad.9");
        int2objectopenhashmap.put(78, "key.keypad.add");
        int2objectopenhashmap.put(83, "key.keypad.decimal");
        int2objectopenhashmap.put(181, "key.keypad.divide");
        int2objectopenhashmap.put(156, "key.keypad.enter");
        int2objectopenhashmap.put(141, "key.keypad.equal");
        int2objectopenhashmap.put(55, "key.keypad.multiply");
        int2objectopenhashmap.put(74, "key.keypad.subtract");
        int2objectopenhashmap.put(38, "key.l");
        int2objectopenhashmap.put(203, "key.left");
        int2objectopenhashmap.put(56, "key.left.alt");
        int2objectopenhashmap.put(26, "key.left.bracket");
        int2objectopenhashmap.put(29, "key.left.control");
        int2objectopenhashmap.put(42, "key.left.shift");
        int2objectopenhashmap.put(219, "key.left.win");
        int2objectopenhashmap.put(50, "key.m");
        int2objectopenhashmap.put(12, "key.minus");
        int2objectopenhashmap.put(49, "key.n");
        int2objectopenhashmap.put(69, "key.num.lock");
        int2objectopenhashmap.put(24, "key.o");
        int2objectopenhashmap.put(25, "key.p");
        int2objectopenhashmap.put(209, "key.page.down");
        int2objectopenhashmap.put(201, "key.page.up");
        int2objectopenhashmap.put(197, "key.pause");
        int2objectopenhashmap.put(52, "key.period");
        int2objectopenhashmap.put(183, "key.print.screen");
        int2objectopenhashmap.put(16, "key.q");
        int2objectopenhashmap.put(19, "key.r");
        int2objectopenhashmap.put(205, "key.right");
        int2objectopenhashmap.put(184, "key.right.alt");
        int2objectopenhashmap.put(27, "key.right.bracket");
        int2objectopenhashmap.put(157, "key.right.control");
        int2objectopenhashmap.put(54, "key.right.shift");
        int2objectopenhashmap.put(220, "key.right.win");
        int2objectopenhashmap.put(31, "key.s");
        int2objectopenhashmap.put(70, "key.scroll.lock");
        int2objectopenhashmap.put(39, "key.semicolon");
        int2objectopenhashmap.put(53, "key.slash");
        int2objectopenhashmap.put(57, "key.space");
        int2objectopenhashmap.put(20, "key.t");
        int2objectopenhashmap.put(15, "key.tab");
        int2objectopenhashmap.put(22, "key.u");
        int2objectopenhashmap.put(200, "key.up");
        int2objectopenhashmap.put(47, "key.v");
        int2objectopenhashmap.put(17, "key.w");
        int2objectopenhashmap.put(45, "key.x");
        int2objectopenhashmap.put(21, "key.y");
        int2objectopenhashmap.put(44, "key.z");
    });

    public DataConverterKeybind(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsKeyLwjgl3Fix", this.getInputSchema().getType(DataConverterTypes.e), (typed) -> {
            return typed.update(DSL.remainderFinder(), (dynamic) -> {
                return (Dynamic) dynamic.getMapValues().map((map) -> {
                    return dynamic.createMap((Map) map.entrySet().stream().map((entry) -> {
                        if (((Dynamic) entry.getKey()).asString("").startsWith("key_")) {
                            int i = Integer.parseInt(((Dynamic) entry.getValue()).asString(""));

                            if (i < 0) {
                                int j = i + 100;
                                String s;

                                if (j == 0) {
                                    s = "key.mouse.left";
                                } else if (j == 1) {
                                    s = "key.mouse.right";
                                } else if (j == 2) {
                                    s = "key.mouse.middle";
                                } else {
                                    s = "key.mouse." + (j + 1);
                                }

                                return Pair.of(entry.getKey(), ((Dynamic) entry.getValue()).createString(s));
                            } else {
                                String s1 = (String) DataConverterKeybind.a.getOrDefault(i, "key.unknown");

                                return Pair.of(entry.getKey(), ((Dynamic) entry.getValue()).createString(s1));
                            }
                        } else {
                            return Pair.of(entry.getKey(), entry.getValue());
                        }
                    }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
                }).orElse(dynamic);
            });
        });
    }
}
