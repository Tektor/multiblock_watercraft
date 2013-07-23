package tektor.minecraft.multiblock_watercraft.tile;

import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachineTileEntity extends TileEntityElectrical{
	
	public TurbineMachineTileEntity()
	{
		
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
	
	public void invalidateMultiblock()
	{
	    int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
	     	     
	    revertDummies();
	}
	
	private void revertDummies() {
		// TODO Auto-generated method stub
		
	}

	public boolean checkIfProperlyFormed()
    {
        
        /*
         *          FORWARD     BACKWARD
         * North:   -z              +z
         * South:   +z              -z
         * East:    +x              -x
         * West:    -x              +x
         *
         * Should move BACKWARD for depth (facing = direction of block face, not direction of player looking at face)
         */
         
        for(int x = -1; x <= 1; x++) 
        {
            
        }
         
        return false;
    }

	public boolean getIsValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public void convertDummies() {
		// TODO Auto-generated method stub
		
	}

}
