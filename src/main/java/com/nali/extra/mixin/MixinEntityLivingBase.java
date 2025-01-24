package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

//*extra
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity
{
	public MixinEntityLivingBase(World worldIn)
	{
		super(worldIn);
	}

//	@Shadow public int hurtTime;
//	@Shadow public int maxHurtTime;
//	@Shadow public int maxHurtResistantTime;

//	@Inject(remap = false, method = "onUpdate", at = @At(value = "TAIL"))
//	private void nali_onUpdate(CallbackInfo ci)
//	{
//		this.hurtTime = 0;
//		this.maxHurtTime = 0;
//		this.hurtResistantTime = 0;
//		this.maxHurtResistantTime = 0;
//	}

//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;hurtTime:I"))
//	private void nali_attackEntityFrom_hurtTime(EntityLivingBase instance, int value)
//	{
////		this.hurtTime = 0;
//	}
//
//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;maxHurtTime:I"))
//	private void nali_attackEntityFrom_maxHurtTime(EntityLivingBase instance, int value)
//	{

	@Shadow protected abstract void collideWithEntity(Entity entityIn);

	/// /		this.maxHurtTime = 0;
//	}

	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;hurtResistantTime:I", ordinal = 1))
	private void nali_extra_attackEntityFrom_hurtResistantTime(EntityLivingBase instance, int value)
	{
//		this.hurtResistantTime = 0;
	}

//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;maxHurtResistantTime:I"))
//	private int nali_attackEntityFrom_maxHurtResistantTime(EntityLivingBase instance)
//	{
//		return 0;
//	}

	//remove hitbox
	@Overwrite
	protected void collideWithNearbyEntities()
	{
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty())
		{
			for (int l = 0; l < list.size(); ++l)
			{
				Entity entity = list.get(l);
				this.collideWithEntity(entity);
			}
		}
	}
//	@Overwrite
//	protected void collideWithEntity(Entity entityIn)
//	{
//	}
//
//	@Overwrite
//	protected void collideWithNearbyEntities()
//	{
//	}
//
////	@Overwrite
////	public boolean canBeCollidedWith()
////	{
////		return false;
////	}
}
