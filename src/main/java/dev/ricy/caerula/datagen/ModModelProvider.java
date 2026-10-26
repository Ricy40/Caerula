package dev.ricy.caerula.datagen;

import com.mojang.math.Quadrant;
import dev.ricy.caerula.Caerula;
import dev.ricy.caerula.block.ModBlocks;
import dev.ricy.caerula.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Caerula.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        /* ITEMS */
        itemModels.generateFlatItem(ModItems.CAERULA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_NIXIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.NIXIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.NIXIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.RED_SEAGRASS.get().asItem(), ModelTemplates.FLAT_ITEM);


        /* BLOCKS */
        blockModels.family(ModBlocks.NIXIUM_BLOCK.get())
                .stairs(ModBlocks.NIXIUM_STAIRS.get())
                .slab(ModBlocks.NIXIUM_SLAB.get());
        blockModels.createTrivialCube(ModBlocks.RAW_NIXIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.NIXIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_NIXIUM_ORE.get());

        Identifier redSeagrassModel = ModelTemplates.SEAGRASS.create(ModBlocks.RED_SEAGRASS.get(), TextureMapping.defaultTexture(ModBlocks.RED_SEAGRASS.get()), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.RED_SEAGRASS.get(), BlockModelGenerators.plainVariant(redSeagrassModel)));

        Identifier tallSeagrassBottom = ModelTemplates.SEAGRASS.createWithSuffix(ModBlocks.TALL_RED_SEAGRASS.get(), "_bottom", singleFaceTexture("tall_red_seagrass_bottom"), blockModels.modelOutput);
        Identifier tallSeagrassTop = ModelTemplates.SEAGRASS.createWithSuffix(ModBlocks.TALL_RED_SEAGRASS.get(), "_top", singleFaceTexture("tall_red_seagrass_top"), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.TALL_RED_SEAGRASS.get(), BlockModelGenerators.plainVariant(tallSeagrassBottom))
                .with(PropertyDispatch.modify(net.minecraft.world.level.block.TallSeagrassBlock.HALF)
                        .select(DoubleBlockHalf.LOWER, VariantMutator.MODEL.withValue(tallSeagrassBottom))
                        .select(DoubleBlockHalf.UPPER, VariantMutator.MODEL.withValue(tallSeagrassTop))));

        blockModels.createCrossBlockWithDefaultItem(ModBlocks.BUSH_CORAL.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.DEAD_BUSH_CORAL.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        TextureMapping myceliumSandTextures = new TextureMapping()
                .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Caerula.MOD_ID, "block/mycelium_side_sand")))
                .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Caerula.MOD_ID, "block/mycelium_top")))
                .put(TextureSlot.BOTTOM, new Material(Identifier.withDefaultNamespace("block/sand")));
        Identifier myceliumSandModel = ModelTemplates.CUBE_BOTTOM_TOP.create(ModBlocks.MYCELIUM_SAND.get(), myceliumSandTextures, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.MYCELIUM_SAND.get(), BlockModelGenerators.plainVariant(myceliumSandModel)));
        blockModels.registerSimpleItemModel(ModBlocks.MYCELIUM_SAND.get(), myceliumSandModel);

        blockModels.createCrossBlockWithDefaultItem(ModBlocks.PURPLE_SEASHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.YELLOW_SEASHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        Identifier seashroomInside = ModelTemplates.SINGLE_FACE.create(
                Identifier.fromNamespaceAndPath(Caerula.MOD_ID, "block/seashroom_block_inside"),
                singleFaceTexture("seashroom_block_inside"),
                blockModels.modelOutput);
        createHugeSeashroom(blockModels, ModBlocks.PURPLE_SEASHROOM_BLOCK.get(), seashroomInside);
        createHugeSeashroom(blockModels, ModBlocks.YELLOW_SEASHROOM_BLOCK.get(), seashroomInside);
        createHugeSeashroom(blockModels, ModBlocks.ORANGE_SEASHROOM_BLOCK.get(), seashroomInside);
        createHugeSeashroom(blockModels, ModBlocks.BROWN_SEASHROOM_BLOCK.get(), seashroomInside);
        createHugeSeashroom(blockModels, ModBlocks.SEASHROOM_STEM.get(), seashroomInside);
    }

    private static TextureMapping singleFaceTexture(String name) {
        return new TextureMapping().put(TextureSlot.TEXTURE, new Material(Identifier.fromNamespaceAndPath(Caerula.MOD_ID, "block/" + name)));
    }

    private static void createHugeSeashroom(BlockModelGenerators blockModels, Block block, Identifier insideModel) {
        Identifier skinModel = ModelTemplates.SINGLE_FACE.create(block, TextureMapping.defaultTexture(block), blockModels.modelOutput);
        Identifier inventoryModel = ModelTemplates.CUBE_ALL.createWithSuffix(block, "_inventory", TextureMapping.cube(block), blockModels.modelOutput);
        blockModels.registerSimpleItemModel(block, inventoryModel);

        MultiVariant skin = BlockModelGenerators.plainVariant(skinModel);
        MultiVariant inside = BlockModelGenerators.plainVariant(insideModel);

        blockModels.blockStateOutput.accept(MultiPartGenerator.multiPart(block)
                .with(facing(HugeMushroomBlock.NORTH, true), skin)
                .with(facing(HugeMushroomBlock.EAST, true), rotatedY(skin, Quadrant.R90))
                .with(facing(HugeMushroomBlock.SOUTH, true), rotatedY(skin, Quadrant.R180))
                .with(facing(HugeMushroomBlock.WEST, true), rotatedY(skin, Quadrant.R270))
                .with(facing(HugeMushroomBlock.UP, true), rotatedX(skin, Quadrant.R270))
                .with(facing(HugeMushroomBlock.DOWN, true), rotatedX(skin, Quadrant.R90))
                .with(facing(HugeMushroomBlock.NORTH, false), inside)
                .with(facing(HugeMushroomBlock.EAST, false), rotatedYPlain(inside, Quadrant.R90))
                .with(facing(HugeMushroomBlock.SOUTH, false), rotatedYPlain(inside, Quadrant.R180))
                .with(facing(HugeMushroomBlock.WEST, false), rotatedYPlain(inside, Quadrant.R270))
                .with(facing(HugeMushroomBlock.UP, false), rotatedXPlain(inside, Quadrant.R270))
                .with(facing(HugeMushroomBlock.DOWN, false), rotatedXPlain(inside, Quadrant.R90)));
    }

    private static ConditionBuilder facing(net.minecraft.world.level.block.state.properties.BooleanProperty property, boolean value) {
        return new ConditionBuilder().term(property, value);
    }

    private static MultiVariant rotatedY(MultiVariant model, Quadrant rotation) {
        return model.with(VariantMutator.Y_ROT.withValue(rotation)).with(VariantMutator.UV_LOCK.withValue(true));
    }

    private static MultiVariant rotatedX(MultiVariant model, Quadrant rotation) {
        return model.with(VariantMutator.X_ROT.withValue(rotation)).with(VariantMutator.UV_LOCK.withValue(true));
    }

    private static MultiVariant rotatedYPlain(MultiVariant model, Quadrant rotation) {
        return model.with(VariantMutator.Y_ROT.withValue(rotation));
    }

    private static MultiVariant rotatedXPlain(MultiVariant model, Quadrant rotation) {
        return model.with(VariantMutator.X_ROT.withValue(rotation));
    }
}
