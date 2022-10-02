package winterfox.betterfences.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import winterfox.betterfences.registry.BlockRegistry;

public class BetterFencesBlockstateProvider extends BlockStateProvider {
    public BetterFencesBlockstateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_ACACIA.get(), Blocks.ACACIA_PLANKS, Blocks.ACACIA_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_BIRCH.get(), Blocks.BIRCH_PLANKS, Blocks.BIRCH_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_CRIMSON.get(), Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_JUNGLE.get(), Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_OAK.get(), Blocks.OAK_PLANKS, Blocks.OAK_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_DARK_OAK.get(), Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_MANGROVE.get(), Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_SPRUCE.get(), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_WARPED.get(), Blocks.WARPED_PLANKS, Blocks.WARPED_FENCE);

        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_cobblestone_wall.get(), Blocks.COBBLESTONE, Blocks.COBBLESTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_mossy_cobblestone_wall.get(), Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_brick_wall.get(), Blocks.BRICKS, Blocks.BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_prismarine_wall.get(), Blocks.PRISMARINE, Blocks.PRISMARINE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_red_sandstone_wall.get(), Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_mossy_stone_brick_wall.get(), Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_granite_wall.get(), Blocks.GRANITE, Blocks.GRANITE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_stone_brick_wall.get(), Blocks.STONE_BRICKS, Blocks.STONE_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_nether_brick_wall.get(), Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_andesite_wall.get(), Blocks.ANDESITE, Blocks.ANDESITE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_red_nether_brick_wall.get(), Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_sandstone_wall.get(), Blocks.SANDSTONE, Blocks.SANDSTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_end_stone_brick_wall.get(), Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_diorite_wall.get(), Blocks.DIORITE, Blocks.DIORITE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_blackstone_wall.get(), Blocks.BLACKSTONE, Blocks.BLACKSTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_polished_blackstone_brick_wall.get(), Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_polished_blackstone_wall.get(), Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_cobbled_deepslate_wall.get(), Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_polished_deepslate_wall.get(), Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_deepslate_tile_wall.get(), Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_deepslate_brick_wall.get(), Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_WALL);
        wallStateBlockItemModel((WallBlock) BlockRegistry.onlyfence_mud_brick_wall.get(), Blocks.MUD_BRICKS, Blocks.MUD_BRICK_WALL);
    }

    private void simpleStateBlockItemModel(Block block) {
        simpleBlock(block);
        simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
    }

    private void fenceStateBlockItemModel(FenceBlock block, Block texture, Block fence) {
        fenceBlock((FenceBlock) block, blockTexture(texture));
        //simpleBlockItem(block, parent);
        simpleBlockItem(block, blockTexture(fence).getPath()+"_inventory");
    }

    private void wallStateBlockItemModel(WallBlock block, Block texture, Block fence) {
        wallBlock((WallBlock) block, blockTexture(texture));
        //simpleBlockItem(block, parent);
        simpleBlockItem(block, blockTexture(fence).getPath()+"_inventory");
    }

    private void simpleBlockItem(Block block, String location) {
        ModelFile model = new ModelFile(new ResourceLocation(location)) {
            @Override
            protected boolean exists() {
                return true;
            }
        };
        simpleBlockItem(block, model);
    }

}
