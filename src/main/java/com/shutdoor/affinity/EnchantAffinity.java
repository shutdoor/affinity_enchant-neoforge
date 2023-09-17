package com.shutdoor.affinity;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.shutdoor.affinity.Affinity.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class EnchantAffinity extends Enchantment {
    public EnchantAffinity() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{
                EquipmentSlot.HEAD
        });
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public int getMaxLevel() {return 1;}

    @Override
    protected boolean checkCompatibility(Enchantment p_44690_) {
        return super.checkCompatibility(p_44690_) && !(p_44690_.getFullname(0).toString().toLowerCase().contains("affinity"));
    }

    @SubscribeEvent
    public static void breakSpeed(PlayerEvent.BreakSpeed e) {
        Player p = e.getEntity();

        if(EnchantmentHelper.getEnchantmentLevel(EnchantmentReg.AFFINITY.get(), p) > 0){


            if(!(p.onGround()) && !(p.isUnderWater())){
                float oldSpeed = e.getOriginalSpeed();

                e.setNewSpeed(oldSpeed * 5);
            }

            if(p.isUnderWater()){
                float oldSpeed = e.getOriginalSpeed();

                e.setNewSpeed(oldSpeed * 5);
            }
        }
    }
}