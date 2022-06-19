package com.ricy40.caerula.world.gen.placedfeatures.configuredfeatures;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacementUtils {

    public static Holder<PlacedFeature> inlinePlaced(Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifier) {
        return Holder.direct(new PlacedFeature(Holder.hackyErase(feature), List.of(modifier)));
    }
}
