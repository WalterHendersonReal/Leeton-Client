package net.leeton.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.ParseResults;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.network.message.ArgumentSignatureDataMap;
import net.minecraft.network.message.DecoratedContents;
import net.minecraft.network.message.LastSeenMessageList;
import net.minecraft.network.message.MessageMetadata;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import net.leeton.event.EventManager;
import net.leeton.LeetonClient;
import net.leeton.mixinterface.IClientPlayerEntity;
import net.leeton.events.UpdateListener.UpdateEvent;;;


@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntity{
    @Shadow
	private float lastYaw;
	@Shadow
	private float lastPitch;
	@Shadow
	private ClientPlayNetworkHandler networkHandler;
	@Shadow
	@Final
	protected MinecraftClient client;

    private Screen tempCurrentScreen;
	
	public ClientPlayerEntityMixin(LeetonClient wurst, ClientWorld world,
		GameProfile profile, PlayerPublicKey playerPublicKey)
	{
		super(world, profile, playerPublicKey);
	}

    @Inject(at = @At(value = "INVOKE",
		target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V",
		ordinal = 0), method = "tick()V")
	private void onTick(CallbackInfo ci)
	{
		EventManager.fire(UpdateEvent.INSTANCE);
	}

    @Override
	public void setNoClip(boolean noClip)
	{
		this.noClip = noClip;
	}
	
	@Override
	public float getLastYaw()
	{
		return lastYaw;
	}
	
	@Override
	public float getLastPitch()
	{
		return lastPitch;
	}
	
	@Override
	public void setMovementMultiplier(Vec3d movementMultiplier)
	{
		this.movementMultiplier = movementMultiplier;
	}

    @Override
	public boolean isTouchingWaterBypass()
	{
		return super.isTouchingWater();
	}
}
