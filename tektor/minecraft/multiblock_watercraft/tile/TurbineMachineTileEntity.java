package tektor.minecraft.multiblock_watercraft.tile;

import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachineTileEntity extends TileEntityElectrical{
	
	public TurbineMachineTileEntity()
	{
		int meta = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(meta == 1)
		{
			
		}
		else	
		{
			
		}
	}

	@Override
	public float getRequest(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 900;
	}

	@Override
	public float getMaxEnergyStored() {
		return 3600000;
	}

}
