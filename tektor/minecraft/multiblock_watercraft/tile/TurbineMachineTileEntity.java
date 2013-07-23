package tektor.minecraft.multiblock_watercraft.tile;

import tektor.minecraft.multiblock_watercraft.WatercraftBase;
import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachineTileEntity extends TileEntityElectrical {

	private boolean isValidMultiblock = false;
	public boolean xaxis = false;
	public int forward = 0;

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

	public boolean getIsValid() {
		return isValidMultiblock;
	}

	private void revertDummies() {
		if (xaxis) {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord+(k*forward), yCoord, zCoord+i, 0, 3);
					worldObj.setBlockMetadataWithNotify(xCoord+(k*forward), yCoord-1, zCoord+i, 0, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord + (forward*2), yCoord, zCoord,0,3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward*2), yCoord-1, zCoord,0,3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord+1, zCoord,0,3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord-1, zCoord,0,3);
			
		} else {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord+i, yCoord, zCoord+(k*forward), 0, 3);
					worldObj.setBlockMetadataWithNotify(xCoord+i, yCoord-1, zCoord+(k*forward), 0, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord, zCoord+ (forward*2),0,3);
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord-1, zCoord+ (forward*2),0,3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord+1, zCoord+ (forward),0,3);
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord-1, zCoord+ (forward),0,3);

		}

	}

	public void convertDummies() {
		if (xaxis) {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord+(k*forward), yCoord, zCoord+i, 5, 3);
					worldObj.setBlockMetadataWithNotify(xCoord+(k*forward), yCoord-1, zCoord+i, 5, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord + (forward*2), yCoord, zCoord,5,3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward*2), yCoord-1, zCoord,5,3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord+1, zCoord,5,3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord-1, zCoord,5,3);
			
		} else {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord+i, yCoord, zCoord+(k*forward), 5, 3);
					worldObj.setBlockMetadataWithNotify(xCoord+i, yCoord-1, zCoord+(k*forward), 5, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord, zCoord+ (forward*2),5,3);
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord-1, zCoord+ (forward*2),5,3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord+1, zCoord+ (forward),5,3);
			worldObj.setBlockMetadataWithNotify(xCoord , yCoord-1, zCoord+ (forward),5,3);

		}

	}

	public boolean checkIfProperlyFormed() {
		// check first where the turbine is
		// then same level
		// top
		// and the base at the end

		if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord + 1, yCoord, zCoord)) {
			forward = 1;
			xaxis = true;
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord + 2, yCoord, zCoord))
				return false;
			// top
			if (!checkTurbinePart(xCoord + 1, yCoord + 1, zCoord))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord - 1, yCoord, zCoord)) {
			forward = -1;
			xaxis = true;
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord - k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord - 2, yCoord, zCoord))
				return false;
			// top
			if (!checkTurbinePart(xCoord - 1, yCoord + 1, zCoord))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord + 1)) {
			forward = 1;
			xaxis = false;
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + i, yCoord, zCoord + k))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord + 2))
				return false;
			// top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord + 1))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord - 1)) {
			forward = -1;
			xaxis = false;
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord - i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord - 2))
				return false;
			// top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord - 1))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else {
			return false;
		}
	}

	private boolean checkTurbinePart(int i, int j, int k) {
		return ((WatercraftBase.turbineMachinePart.blockID == worldObj
				.getBlockId(i, j, k)) && 0 == worldObj
				.getBlockMetadata(i, j, k));
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

	public void invalidateMultiblock() {
		isValidMultiblock = false;

		revertDummies();
	}

}
