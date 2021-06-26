package com.blagodarsky.chaos.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.blagodarsky.chaos.Chaos;

public class WitherSword implements Listener
{
	private List <Player> cooldown = new ArrayList<>(); 
	@EventHandler
	public void sword (PlayerInteractEvent e)
	{
		int in = 600;
		Player p = e.getPlayer();
		if (cooldown.contains(p))
		{
			return;
		}
		if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getHand() == EquipmentSlot.HAND)
		{
			ItemStack i = p.getInventory().getItemInMainHand();
			if (i.getType().toString().contains("_SWORD"))
			{
				if (p.hasPermission("Chaos.VIP"))
				{
					in = 300;
				}
				if (p.hasPermission("Chaos.Admin"))
				{
					in = 0;
				}
				cooldown.add(p);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Chaos.plugin, new Runnable() {
					
					@Override
					public void run() 
					{
						cooldown.remove(p);
					}
				}, in);
				p.launchProjectile(WitherSkull.class);
				if (p.getGameMode() == GameMode.CREATIVE)
				{
					return;
				}
				i.setDurability((short) (i.getDurability()+5));
				if (i.getDurability() >= i.getType().getMaxDurability())
				{
					i.setAmount(0);
					p.playSound( p.getLocation(), Sound.ENTITY_TURTLE_EGG_CRACK, 1, 1);
				}
			}
		}
	}
}
