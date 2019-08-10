package taiwan402.TestMod00.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class ToolSword extends ItemSword implements IHasModel{

	public ToolSword(String n, ToolMaterial m)
	{
		super(m);
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
