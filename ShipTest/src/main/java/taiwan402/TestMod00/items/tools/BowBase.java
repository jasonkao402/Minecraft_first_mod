package taiwan402.TestMod00.items.tools;

import javax.annotation.Nullable;
import javax.vecmath.Vector3d;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class BowBase extends ItemBow implements IHasModel
{
	float chargeTime, inaccuracy, speed;
	
	public void setChargeTime(float v) {
		chargeTime = v;
	}
	
	public void setInaccuracy(float v) {
		inaccuracy = v;
	}
	
	public void setSpeed(float v) {
		speed = v;
	}
	
	public BowBase(String n, float spd, float chargeT, float inacc, int durbty)
	{
		this.setCreativeTab(CreativeTabs.COMBAT);
		
		this.setMaxDamage(durbty);
		this.setChargeTime(chargeT);
		this.setInaccuracy(inacc);
		this.setSpeed(spd);
		
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter(){
			
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn){
				if (entityIn == null){
					return 0.0F;
				}
				else{
					return !(entityIn.getActiveItemStack().getItem() instanceof ItemBow) ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / chargeTime;
				}
			}
		});
		
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter(){
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
		
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ITEMS.add(this);
	}
	@Override
	public int getMaxItemUseDuration(ItemStack itemstack)
	{
		return 72000;
	}
	
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	
	private ItemStack findAmmo(EntityPlayer player)
	{
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))){
			return player.getHeldItem(EnumHand.OFF_HAND);
		}
		else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))){
			return player.getHeldItem(EnumHand.MAIN_HAND);
		}
		else{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i){
				ItemStack itemstack = player.inventory.getStackInSlot(i);
				if (this.isArrow(itemstack)){
					return itemstack;
				}
			}
			return ItemStack.EMPTY;
		}
	}
	
	public float getZoom(EntityLivingBase entity) {
		return 1-MathHelper.clamp(this.getMaxItemUseDuration(null) - entity.getItemInUseCount(), 0, 20)/60;
		//zooms from normal fov to 2/3 normal fov in one second
	}
	
	public static float getV(int charge, BowBase bowStat)//charge: ticks that right click has been held down
	{
		float f = (float)charge / bowStat.chargeTime;
		f = f * (f + 2.0F) / 3.0F;
	
		if (f > 1.0F){
			f = 1.0F;
		}
	
		return f;
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer){
			
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;//flag is true if no arrows are to be consumed
			ItemStack itemstack = this.findAmmo(entityplayer);//looks for ammo (see above)
	
			int i = this.getMaxItemUseDuration(stack) - timeLeft;//the time that it has been used for
			
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
			
			if (i < 0) return;
	
			if (!itemstack.isEmpty() || flag){
				if (itemstack.isEmpty()){
					itemstack = new ItemStack(Items.ARROW);
				}
				float f = getV(i, this);
		
				if ((double)f >= 0.1D){//if the velocity is lower than this, it does not fire
			
					boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));
		
					if (!worldIn.isRemote){
						for(int x = 0; x < 5; x++) {
							ItemArrow itemarrow = (ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
							EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);//creates the arrow entity
							
							if (f == 1.0F){
								entityarrow.setIsCritical(true);//arrow entity does more damage
							}
					
							int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						
							if (j > 0){
								entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);//adds damage to arrow entity
							}
						
							int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						
							if (k > 0){
								entityarrow.setKnockbackStrength(k);//adds knockback
							}
						
							if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0){
								entityarrow.setFire(100);//adds fire
							}
						
							
						
							if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)){
								entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;//prevents survival players from picking up the arrow
							}
							
							entityarrow.hurtResistantTime = 0;
							//Vector3d v = aimHelp(entityplayer, f * 5, 8);
							//entityarrow.setVelocity(v.x,v.y,v.z);
							entityarrow.shoot(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0, speed * f, inaccuracy);
							worldIn.spawnEntity(entityarrow);
						}
						
						stack.damageItem(1, entityplayer);//damages bow
					}
			
					worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);//plays the shooting sound
				
					if (!flag1 && !entityplayer.capabilities.isCreativeMode){
						itemstack.shrink(1);//removes the arrow
				
						if (itemstack.isEmpty()){
							entityplayer.inventory.deleteStack(itemstack);
						}
					}
				
					entityplayer.addStat(StatList.getObjectUseStats(this));//adds stat
				}
			}
		}
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	Vector3d aimHelp(Entity shooter, float velocity, float inaccuracy)
    {
		float pitch = (float) (shooter.rotationPitch + (Math.random() - 0.5) * inaccuracy);
		float yaw = (float) (shooter.rotationYaw + (Math.random() - 0.5) * inaccuracy);
        double f = -MathHelper.sin((yaw) * 0.017453292F) * MathHelper.cos((pitch) * 0.017453292F) * velocity;
        double f1 = -MathHelper.sin((pitch) * 0.017453292F) * velocity;
        double f2 = MathHelper.cos((yaw) * 0.017453292F) * MathHelper.cos((pitch) * 0.017453292F) * velocity;
        Vector3d v = new Vector3d (f + shooter.motionX, (shooter.onGround ? f1 : f1 + shooter.motionY), f2 + shooter.motionZ );
        
        return v;
    }
}
