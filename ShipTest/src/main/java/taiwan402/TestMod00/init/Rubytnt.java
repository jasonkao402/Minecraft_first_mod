package taiwan402.TestMod00.init;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import taiwan402.TestMod00.blocks.BlockBase;

public class Rubytnt extends BlockBase{
	
	public Rubytnt(String n, Material m) {
		super(n, m);
		setSoundType(SoundType.CLOTH);
	}
	
	public boolean onBlockActivatedWorld (World worldIn,
    BlockPos pos,
    IBlockState state,
    EntityPlayer playerIn,
    EnumHand hand,
    EnumFacing side,
    float hitX,
    float hitY,
    float hitZ)
	{
		if (playerIn.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL) {
        	worldIn.setBlockToAir(pos);
            EntityTNTPrimed var6 = new EntityTNTPrimed(worldIn, pos.getX(), pos.getY(), pos.getZ(), null);
            var6.setFuse(80);
            worldIn.spawnEntity(var6);
            return true;
        } 
        else {
        	return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
        }
    }
}
