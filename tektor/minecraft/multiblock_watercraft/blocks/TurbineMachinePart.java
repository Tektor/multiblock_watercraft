package tektor.minecraft.multiblock_watercraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TurbineMachinePart extends BlockContainer{
	
	public static final int CORE = 1;
	public static final int NORMAL = 0;
	public static final int WATER_IN = 2;
	public static final int WATER_OUT = 3;
	public static final int ENERGY_OUT = 4;
	public static final int DUMMY = 5;

	public TurbineMachinePart(int par1, Material par2Material) {
		super(par1, par2Material);
		this.func_111022_d("multiblock_watercraft:machinePart");
		this.setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("turbineMachinePart");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
