package tektor.minecraft.multiblock_watercraft.tile;

import tektor.minecraft.multiblock_watercraft.WatercraftBase;
import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachineTileEntity extends TileEntityElectrical {

	public TurbineMachineTileEntity() {

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

	public void invalidateMultiblock() {
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);

		revertDummies();
	}

	private void revertDummies() {
		// TODO Auto-generated method stub

	}

	public boolean checkIfProperlyFormed() {
		// check first where the turbine is
		// then same level
		//top
		//and the base at the end
		int forward;
		boolean xaxis;

		if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord + 1, yCoord, zCoord)) {
			forward = 1;
			xaxis = true;
			//same
			for(int i = -1; i<2; i=i+2)
			{
				for(int k = 0; k<3; k++)
				{
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord + 2, yCoord, zCoord))
				return false;
			//top
			if (!checkTurbinePart(xCoord + 1, yCoord + 1, zCoord))
				return false;
			//base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord - 1, yCoord, zCoord)) {
			forward = -1;
			xaxis = true;
			//same
			for(int i = -1; i<2; i=i+2)
			{
				for(int k = 0; k<3; k++)
				{
					if (!checkTurbinePart(xCoord - k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord - 2, yCoord, zCoord))
				return false;
			//top
			if (!checkTurbinePart(xCoord - 1, yCoord + 1, zCoord))
				return false;
			//base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord + 1)) {
			forward = 1;
			xaxis = false;
			//same
			for(int i = -1; i<2; i=i+2)
			{
				for(int k = 0; k<3; k++)
				{
					if (!checkTurbinePart(xCoord + i, yCoord, zCoord + k))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord+2))
				return false;
			//top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord + 1))
				return false;
			//base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord - 1)) {
			forward = -1;
			xaxis = false;
			//same
			for(int i = -1; i<2; i=i+2)
			{
				for(int k = 0; k<3; k++)
				{
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord - i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord-2))
				return false;
			//top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord - 1))
				return false;
			//base
			return checkBase(xaxis, forward);
		} else {
			return false;
		}
	}

	private boolean checkTurbinePart(int i, int j, int k) {
		return (WatercraftBase.turbineMachinePart.blockID == worldObj
				.getBlockId(i, j, k));
	}

	private boolean checkBase(boolean axis, int forward) {
		for (int x = 0; x < 3; x++) {
			for (int z = -1; z < 2; z++) {
				if (axis) {
					if (!checkTurbinePart(xCoord + (forward * x), yCoord - 1,
							zCoord + (z))) {

						return false;
					}
				
				} else {
					if (!checkTurbinePart(xCoord + (z), yCoord - 1, zCoord
							+ (x * forward))) {
						return false;
					}
				}

			}
		}

		return true;
	}

	public boolean getIsValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public void convertDummies() {
		// TODO Auto-generated method stub

	}

}
