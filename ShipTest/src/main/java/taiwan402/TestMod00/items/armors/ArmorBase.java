package taiwan402.TestMod00.items.armors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class ArmorBase extends ItemArmor implements IHasModel{

	public ArmorBase(String n, ArmorMaterial p_i46750_1_, int p_i46750_2_, EntityEquipmentSlot p_i46750_3_)
	{
		super(p_i46750_1_, p_i46750_2_, p_i46750_3_);
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
