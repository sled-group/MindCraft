package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DataConverterRiding extends DataFix {

    public DataConverterRiding(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        Schema schema1 = this.getOutputSchema();
        Type<?> type = schema.getTypeRaw(DataConverterTypes.o);
        Type<?> type1 = schema1.getTypeRaw(DataConverterTypes.o);
        Type<?> type2 = schema.getTypeRaw(DataConverterTypes.ENTITY);

        return this.a(schema, schema1, type, type1, type2);
    }

    private <OldEntityTree, NewEntityTree, Entity> TypeRewriteRule a(Schema schema, Schema schema1, Type<OldEntityTree> type, Type<NewEntityTree> type1, Type<Entity> type2) {
        Type<Pair<String, Pair<Either<OldEntityTree, com.mojang.datafixers.util.Unit>, Entity>>> type3 = DSL.named(DataConverterTypes.o.typeName(), DSL.and(DSL.optional(DSL.field("Riding", type)), type2));
        Type<Pair<String, Pair<Either<List<NewEntityTree>, com.mojang.datafixers.util.Unit>, Entity>>> type4 = DSL.named(DataConverterTypes.o.typeName(), DSL.and(DSL.optional(DSL.field("Passengers", DSL.list(type1))), type2));
        Type<?> type5 = schema.getType(DataConverterTypes.o);
        Type<?> type6 = schema1.getType(DataConverterTypes.o);

        if (!Objects.equals(type5, type3)) {
            throw new IllegalStateException("Old entity type is not what was expected.");
        } else if (!type6.equals(type4, true, true)) {
            throw new IllegalStateException("New entity type is not what was expected.");
        } else {
            OpticFinder<Pair<String, Pair<Either<OldEntityTree, com.mojang.datafixers.util.Unit>, Entity>>> opticfinder = DSL.typeFinder(type3);
            OpticFinder<Pair<String, Pair<Either<List<NewEntityTree>, com.mojang.datafixers.util.Unit>, Entity>>> opticfinder1 = DSL.typeFinder(type4);
            OpticFinder<NewEntityTree> opticfinder2 = DSL.typeFinder(type1);
            Type<?> type7 = schema.getType(DataConverterTypes.PLAYER);
            Type<?> type8 = schema1.getType(DataConverterTypes.PLAYER);

            return TypeRewriteRule.seq(this.fixTypeEverywhere("EntityRidingToPassengerFix", type3, type4, (dynamicops) -> {
                return (pair) -> {
                    Optional<Pair<String, Pair<Either<List<NewEntityTree>, com.mojang.datafixers.util.Unit>, Entity>>> optional = Optional.empty();
                    Pair pair1 = pair;

                    while (true) {
                        Either<List<NewEntityTree>, com.mojang.datafixers.util.Unit> either = (Either) DataFixUtils.orElse(optional.map((pair2) -> {
                            Typed<NewEntityTree> typed = (Typed) type1.pointTyped(dynamicops).orElseThrow(() -> {
                                return new IllegalStateException("Could not create new entity tree");
                            });
                            NewEntityTree newentitytree = typed.set(opticfinder1, pair2).getOptional(opticfinder2).orElseThrow(() -> {
                                return new IllegalStateException("Should always have an entity tree here");
                            });

                            return Either.left(ImmutableList.of(newentitytree));
                        }), Either.right(DSL.unit()));

                        optional = Optional.of(Pair.of(DataConverterTypes.o.typeName(), Pair.of(either, ((Pair) pair1.getSecond()).getSecond())));
                        Optional<OldEntityTree> optional1 = ((Either) ((Pair) pair1.getSecond()).getFirst()).left();

                        if (!optional1.isPresent()) {
                            return (Pair) optional.orElseThrow(() -> {
                                return new IllegalStateException("Should always have an entity tree here");
                            });
                        }

                        pair1 = (Pair) (new Typed(type, dynamicops, optional1.get())).getOptional(opticfinder).orElseThrow(() -> {
                            return new IllegalStateException("Should always have an entity here");
                        });
                    }
                };
            }), this.writeAndRead("player RootVehicle injecter", type7, type8));
        }
    }
}
