package taiwan402.TestMod00.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import taiwan402.TestMod00.items.ItemBase;
import taiwan402.TestMod00.items.armors.ArmorBase;
import taiwan402.TestMod00.items.tools.BowBase;
import taiwan402.TestMod00.items.tools.ToolAxe;
import taiwan402.TestMod00.items.tools.ToolHoe;
import taiwan402.TestMod00.items.tools.ToolPickaxe;
import taiwan402.TestMod00.items.tools.ToolSpade;
import taiwan402.TestMod00.items.tools.ToolSword;
import taiwan402.TestMod00.util.Ref;


public class ModItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//material
	public static final ToolMaterial MATERIAL_RUBY = EnumHelper.addToolMaterial("material_ruby", 3, 2000, 8, 8, 25);
	public static final ArmorMaterial ARMOR_MATERIAL_RUBY = EnumHelper.addArmorMaterial("armor_material_ruby", Ref.MODID+":ruby", 2000
			, new int[] {2, 5, 7, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
	
	//item
	public static final Item RUBY = new ItemBase("ruby");
	
	//tool
	public static final ItemSword RUBY_SWORD = new ToolSword("ruby_sword", MATERIAL_RUBY);
	public static final ItemSpade RUBY_SHOVEL = new ToolSpade("ruby_shovel", MATERIAL_RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", MATERIAL_RUBY);
	public static final ItemAxe RUBY_AXE = new ToolAxe("ruby_axe", MATERIAL_RUBY, 5f, -1f);
	public static final ItemHoe RUBY_HOE = new ToolHoe("ruby_hoe", MATERIAL_RUBY);
	public static final ItemBow RUBY_BOW = new BowBase("ruby_bow", 5f, 10f, 5f, 2000);
	
	//armor
	public static final Item RUBY_HELMET = new ArmorBase("ruby_helmet", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.HEAD);
	public static final Item RUBY_CHESTPLETE = new ArmorBase("ruby_chestplate", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.CHEST);
	public static final Item RUBY_LEGGINGS = new ArmorBase("ruby_leggings", ARMOR_MATERIAL_RUBY, 2, EntityEquipmentSlot.LEGS);
	public static final Item RUBY_BOOTS = new ArmorBase("ruby_boots", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.FEET);
}
