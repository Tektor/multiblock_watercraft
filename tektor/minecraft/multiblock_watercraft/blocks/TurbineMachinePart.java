package tektor.minecraft.multiblock_watercraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TurbineMachinePart extends BlockContainer{
	
	public static final int CORE = 1;
	public static final int NORMAL = 0;

	protected TurbineMachinePart(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
