package BananaFructa.passingdays.fixes.galacticraft;

import micdoodle8.mods.galacticraft.core.blocks.BlockSolar;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySolar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSolarFixed extends BlockSolar {

    public BlockSolarFixed(String assetName) {
        super(assetName);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return this.func_176201_c(state) >= 4 ? new TileEntitySolarFixed(2) : new TileEntitySolarFixed(1);
    }
}
