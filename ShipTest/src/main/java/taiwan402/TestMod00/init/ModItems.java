package taiwan402.TestMod00.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import taiwan402.TestMod00.items.ItemBase;
import taiwan402.TestMod00.items.tools.ToolAxe;
import taiwan402.TestMod00.items.tools.ToolHoe;
import taiwan402.TestMod00.items.tools.ToolPickaxe;
import taiwan402.TestMod00.items.tools.ToolSpade;
import taiwan402.TestMod00.items.tools.ToolSword;


public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	//material
	public static final ToolMaterial MATERIAL_RUBY = EnumHelper.addToolMaterial("material_ruby", 3, 2000, 8, 8, 25);
	//item
	public static final Item RUBY = new ItemBase("ruby");
	
	//tool
	public static final ItemSword RUBY_SWORD = new ToolSword("ruby_sword", MATERIAL_RUBY);
	public static final ItemSpade RUBY_SHOVEL = new ToolSpade("ruby_shovel", MATERIAL_RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", MATERIAL_RUBY);
	public static final ItemAxe RUBY_AXE = new ToolAxe("ruby_axe", MATERIAL_RUBY);
	public static final ItemHoe RUBY_HOE = new ToolHoe("ruby_hoe", MATERIAL_RUBY);
}
