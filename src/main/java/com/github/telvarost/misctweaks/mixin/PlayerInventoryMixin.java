package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.entity.player.StationFlatteningPlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements InventoryBase, StationFlatteningPlayerInventory {

    @Redirect(
            method = "getArmourValue",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemInstance;getDamage2()I"
            )
    )
    public int getArmourValue(ItemInstance instance) {
        if (Config.ConfigFields.modernArmorDefensePoints) {
            return 0;
        } else {
            return instance.getDamage2();
        }
    }
}
